package gtt.mybernate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import gtt.model.BaseModel;
import gtt.model.dao.DaoException;
import gtt.model.dao.InterfaceDAO;
import gtt.model.dao.UtilsDAO;

public class Mybernate implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private Session session;
	
	// CONSTRUCTS :
	
	public Mybernate() {}
	
	// GETTERS AND SETTERS :
	
	public Session getSession() { return session; }
	
	// METHODS :
	
	protected SessionFactory createFactory(Class<?> c) {
		
		return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(c).buildSessionFactory();
		
	}
	
	public void initSession(Class<?> c) {
		
		session = this.createFactory(c).getCurrentSession();
		
	}
	
	protected void saveWithSession(BaseModel model) throws Exception{
		
		this.initSession(model.getClass());
		
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		
	}
	
	protected void doUpdate(BaseModel toUpdate, BaseModel from) throws Exception{
		
		List<Field> attrs = UtilsDAO.getAttrNotNull(from, UtilsDAO.getAttributesWithoutId(from));
		for(Field f : attrs) {
			
			Method setter = UtilsDAO.getSetter(toUpdate, f.getName());
			setter.invoke(toUpdate, UtilsDAO.getValueOf(from, f));
			
		}
		
	}
	
	protected void updateWithSession(BaseModel model) throws Exception{
		
		this.initSession(model.getClass());
		
		session.beginTransaction();
		BaseModel toUpdate = session.get(model.getClass(), model.getId());
		this.doUpdate(toUpdate, model);
		session.getTransaction().commit();
		
	}
	
	protected void deleteWithSession(BaseModel model) throws Exception{
		
		this.initSession(model.getClass());
		
		session.beginTransaction();
		BaseModel toDelete = session.get(model.getClass(), model.getId());
		session.delete(toDelete);
		session.getTransaction().commit();
		
	}
	
	protected void findByIdWithSession(BaseModel model) throws Exception{
		
		this.initSession(model.getClass());
		
		session.beginTransaction();
		BaseModel tmp = session.get(model.getClass(), model.getId());
		this.doUpdate(model, tmp);
		session.getTransaction().commit();
		
	}
	
	protected List<?> findAllWithCriteria(int page, int row, BaseModel condition) throws Exception{
		
		int offset = (page - 1) * row;
		this.initSession(condition.getClass());
		session.beginTransaction();
		Criteria criteria = this.createCriteria(condition);
		criteria.setFirstResult(offset);
		criteria.setMaxResults(row);
		
		return this.findAllByCriteria(condition.getClass(), criteria);
		
	}
	
	public List<?> findAllByCriteria(Class<?> c, Criteria criteria) throws Exception{
		
		List<?> result = new ArrayList<>();
		
		try {
			
			result = criteria.list();
			session.getTransaction().commit();
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		}; return result;
		
	}
	
	@SuppressWarnings("deprecation")
	protected Criteria createCriteria(BaseModel condition) throws Exception{
		
		Criteria result = session.createCriteria(condition.getClass());
		
		List<Field> attrs = UtilsDAO.getAttrNotNull(condition, UtilsDAO.getAttributes(condition));
		for(Field f : attrs) {
			
			if(f.getType().getSimpleName().equals("String"))
				result.add(Restrictions.like(f.getName(), UtilsDAO.getValueOf(condition, f)));
			else
				result.add(Restrictions.eq(f.getName(), UtilsDAO.getValueOf(condition, f)));
			
		} return result;
		
	}
	
	@SuppressWarnings("unchecked")
	protected List<?> findAllWithSession(Class<?> c, String query){
		
		List<BaseModel> result = new ArrayList<>();
		
		this.initSession(c);
		session.beginTransaction();
		result = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		
		return result;
		
	}
	
	protected List<?> findAllWithHQL(int page, int row, Class<?> c, String query) throws Exception{
		
		int offset = (page - 1) * row;
		query += " LIMIT " + row + " OFFSET " + offset;

		return this.findAllByHQL(c, query);
		
	}
	
	public List<?> findAllByHQL(Class<?> c, String query) throws Exception{
		
		List<?> result = new ArrayList<>();
		
		try {
			
			result = this.findAllWithSession(c, query);
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		}; return result;
		
	}
	
	@SuppressWarnings("deprecation")
	public Criteria loadCriteria(Class<?> c) throws Exception {
		
		this.initSession(c);
		session.beginTransaction();
		
		return session.createCriteria(c);
		
	}
	
	// INHERITED METHODS :

	@Override
	public int save(BaseModel model) throws Exception {
		
		int result = 0;
		
		try {
		
			this.saveWithSession(model);
			result = 1;
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		} return result;
		
	}

	@Override
	public int update(BaseModel model) throws Exception {
		
		int result = 0;
		
		try {
		
			this.updateWithSession(model);
			result = 1;
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		} return result;
		
	}

	@Override
	public int delete(BaseModel model) throws Exception {
		
		int result = 0;
		
		try {
		
			this.deleteWithSession(model);
			result = 1;
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		} return result;
		
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		
		try {
		
			this.findByIdWithSession(model);
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			session.close();
			
		};

	}

	@Override
	public List<?> findAll(BaseModel condition, String query) throws Exception {
		
		List<?> result = new ArrayList<>();
		
		if(query != null)
			result = this.findAllByHQL(condition.getClass(), query);
		else if(condition != null) {
			
			this.initSession(condition.getClass());
			session.beginTransaction();
			result = this.findAllByCriteria(condition.getClass(), this.createCriteria(condition));
			
		} else
			throw new DaoException("You must precise a condition with BaseModel or with a Hibernate Query Language !");
		
		return result;
		
	}

	@Override
	public List<?> findAll(int page, int row, BaseModel condition, String query) throws Exception {
		
		List<?> result = new ArrayList<>();
		
		if(query != null)
			result = this.findAllWithHQL(page, row, condition.getClass(), query);
		else if(condition != null)
			result = this.findAllWithCriteria(page, row, condition);
		else
			throw new DaoException("You must precise a condition with BaseModel or with a Hibernate Query Language !");
		
		return result;
		
	}
	
	// STATIC METHODS :
	
	

}
