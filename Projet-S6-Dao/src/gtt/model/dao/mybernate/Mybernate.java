package gtt.model.dao.mybernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.BaseModel;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;

@Component
public class Mybernate implements InterfaceDAO {
	
	// ATTRIBUTES :
	private boolean autoTransaction;
	private Transaction transaction;
	
	@Autowired
	private SessionFactory factory;
	
	// CONSTRUCTS :
	
	/*public Mybernate() {
		
		this.factory = MbUtils.getFactory();
		
	}*/
	
	// GETTERS AND SETTERS :
	
	public boolean isAutoTransaction() { return autoTransaction; }
	
	public void setAutoTransaction(boolean value) { this.autoTransaction = value; }
	
	public SessionFactory getFactory() { return factory; }
	
	public void setFactory(SessionFactory factory) { this.factory = factory; }
	
	public Session createSession() { return factory.openSession(); }
	
	// METHODS :
	
	protected int doSave(Session session, BaseModel model) throws Exception{
		
		if(autoTransaction) transaction = session.beginTransaction();
		session.save(model);
		if(autoTransaction) transaction.commit();
		
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
	
	public int save(BaseModel model, Session session) throws Exception {
		
		int result = 0;
		
		try {
			
			result = this.doSave(session, model);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} return result;
		
	}
	
	protected int doUpdate(Session session, BaseModel model) throws Exception {
		
		if(autoTransaction) transaction = session.beginTransaction();
		session.update(model);
		if(autoTransaction) transaction.commit();
		
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
	
	public int update(BaseModel model, Session session) throws Exception {
		
		int result = 0;
		
		try {
			
			result = this.doUpdate(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} return result;
		
	}
	
	protected int doDelete(Session session, BaseModel model) throws Exception{
		
		if(autoTransaction) transaction = session.beginTransaction();
		session.delete(model);
		if(autoTransaction) transaction.commit();
		
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
	
	public int delete(BaseModel model, Session session) throws Exception {
		
		int result = 0;
		
		try {
			
			result = this.doDelete(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} return result;
		
	}
	
	protected void doFindById(Session session, BaseModel model) throws Exception {
		
		if(autoTransaction) transaction = session.beginTransaction();
		session.load(model, model.getId());
		if(autoTransaction) transaction.commit();
		
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
	
	public void findById(BaseModel model, Session session) throws Exception {
		
		try {
			
			this.doFindById(session, model);
			
		} catch (Exception ex) {
			
			throw ex;
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	protected List findAllWithModel(Session session, BaseModel model) throws Exception {
		
		List result = new ArrayList<>();
		
		Example example = Example.create(model).enableLike().ignoreCase();
		if(autoTransaction) transaction = session.beginTransaction();
		result = session.createCriteria(model.getClass()).add(example).list();
		if(autoTransaction) transaction.commit();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	protected List findAllWithModel(int page, int row, Session session, BaseModel model) throws Exception {
		
		List result = new ArrayList<>();
		
		Example example = Example.create(model).enableLike().ignoreCase();
		int offset = (page - 1) * row;
		if(autoTransaction) transaction = session.beginTransaction();
		result = session.createCriteria(model.getClass()).add(example).setFirstResult(offset).setMaxResults(row).list();
		if(autoTransaction) transaction.commit();
		
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List findAllWithHQL(Session session, String query) throws Exception {
		
		List result = new ArrayList<>();
		
		if(autoTransaction) transaction = session.beginTransaction();
		result = session.createQuery(query).getResultList();
		if(autoTransaction) transaction.commit();
		
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	protected List findAllWithHQL(int page, int row, Session session, String query) throws Exception {
		
		List result = new ArrayList<>();
		
		int offset = (page - 1) * row;
		if(autoTransaction) transaction = session.beginTransaction();
		result = session.createQuery(query + " LIMIT " + row + " OFFSET " + offset).getResultList();
		if(autoTransaction) transaction.commit();
		
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
	public List findAll(BaseModel model, String query, Session session) throws Exception {
		
		List result = null;
		
		try {
			
			result = this.manageFindAll(session, model, query);
			
		} catch (Exception ex) {
			
			throw ex;
			
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
	public List findAll(int page, int row, BaseModel model, String query, Session session) throws Exception {
		
		List result = null;
		
		try {
			
			result = this.manageFindAll(page, row, session, model, query);
			
		} catch (Exception ex) {
			
			throw ex;
			
		} return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		
		throw new DAOException("This method doesn't work with Mybernate !");
		
	}
	
	// STATIC METHODS :
	
	

}
