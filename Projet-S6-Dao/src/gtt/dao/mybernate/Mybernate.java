package gtt.dao.mybernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import gtt.dao.DAOException;
import gtt.dao.InterfaceDAO;
import gtt.model.BaseModel;

public class Mybernate implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private SessionFactory factory;
	
	// CONSTRUCTS :
	
	/*public Mybernate() {
		
		this.factory = MbUtils.getFactory();
		
	}*/
	
	// GETTERS AND SETTERS :
	
	public SessionFactory getFactory() { return factory; }
	
	public void setFactory(SessionFactory factory) { this.factory = factory; }
	
	// METHODS :
	
	protected int doSave(Session session, BaseModel model) throws Exception{
		
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		
		return 1;
		
	}

	@Override
	public int save(BaseModel model) throws Exception {
		
		int result = 0;
		
		Session session = factory.openSession();
		
		try {
			
			result = this.doSave(session, model);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		} return result;
		
	}
	
	protected int doUpdate(Session session, BaseModel model) throws Exception {
		
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		
		return 1;
		
	}

	@Override
	public int update(BaseModel model) throws Exception {
		
		int result = 0;
		
		Session session = factory.openSession();
		
		try {
			
			result = this.doUpdate(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		} return result;
		
	}
	
	protected int doDelete(Session session, BaseModel model) throws Exception{
		
		session.beginTransaction();
		session.delete(model);
		session.getTransaction().commit();
		
		return 1;
		
	}

	@Override
	public int delete(BaseModel model) throws Exception {
		
		int result = 0;
		
		Session session = factory.openSession();
		
		try {
			
			result = this.doDelete(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		} return result;
		
	}
	
	protected void doFindById(Session session, BaseModel model) throws Exception {
		
		session.beginTransaction();
		BaseModel toCopy = session.get(model.getClass(), model.getId());
		model.copy(toCopy);
		session.getTransaction().commit();
		
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		
		Session session = factory.openSession();
		
		try {
			
			this.doFindById(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	protected List findAllWithModel(Session session, BaseModel model) throws Exception {
		
		List result = new ArrayList<>();
		
		Example example = Example.create(model).enableLike().ignoreCase();
		session.beginTransaction();
		result = session.createCriteria(model.getClass()).add(example).list();
		session.getTransaction().commit();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	protected List findAllWithModel(int page, int row, Session session, BaseModel model) throws Exception {
		
		List result = new ArrayList<>();
		
		Example example = Example.create(model).enableLike().ignoreCase();
		int offset = (page - 1) * row;
		session.beginTransaction();
		result = session.createCriteria(model.getClass()).add(example).setFirstResult(offset).setMaxResults(row).list();
		session.getTransaction().commit();
		
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List findAllWithHQL(Session session, String query) throws Exception {
		
		List result = new ArrayList<>();
		
		session.beginTransaction();
		result = session.createQuery(query).getResultList();
		session.getTransaction().commit();
		
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List findAllWithHQL(int page, int row, Session session, String query) throws Exception {
		
		List result = new ArrayList<>();
		
		int offset = (page - 1) * row;
		session.beginTransaction();
		result = session.createQuery(query + " LIMIT " + row + " OFFSET " + offset).getResultList();
		session.getTransaction().commit();
		
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List manageFindAll(Session session, BaseModel model, String query) throws Exception {
		
		if(query == null)
			return this.findAllWithModel(session, model);
		else
			return this.findAllWithHQL(session, query);
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List manageFindAll(int page, int row, Session session, BaseModel model, String query) throws Exception {
		
		if(query == null)
			return this.findAllWithModel(page, row, session, model);
		else
			return this.findAllWithHQL(page, row, session, query);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(BaseModel model, String query) throws Exception {
		
		List result = null;
		
		Session session = factory.openSession();
		
		try {
			
			result = this.manageFindAll(session, model, query);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		} return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(int page, int row, BaseModel model, String query) throws Exception {
		
		List result = null;
		
		Session session = factory.openSession();
		
		try {
			
			result = this.manageFindAll(page, row, session, model, query);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		} return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		
		throw new DAOException("This method doesn't work with Mybernate !");
		
	}
	
	// STATIC METHODS :
	
	

}
