package net.brewspberry.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.beans.Etape;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.HibernateUtil;

public class EtapeDaoImpl implements IGenericDao<Etape> {

	
	private Session session = HibernateUtil.getSession();
	
	
	@Override
	public Etape save(Etape arg0) throws DAOException {
		Transaction tx = session.beginTransaction();
		
		try {
			
			long etape = (long) session.save(arg0);
			
			tx.commit();
			
			arg0.setEtp_id(etape);
			
		}
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			arg0 = new Etape();
		}
		finally {
			HibernateUtil.closeSession();
		}
		
		return arg0;
	}

	@Override
	public Etape update(Etape arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Etape getElementById(long id) {
		Etape result;
		try {
			result = (Etape) session.get(Etape.class, id);
		}
		finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etape> getAllElements() {
		List<Etape> result;
		try {
			result = (List<Etape>) session.createQuery("from Etape").list();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Etape arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etape> getAllDistinctElements() {
		Transaction tx = session.beginTransaction();

		List<Etape> result = new ArrayList<Etape>();
		try {
			result = (List<Etape>) session.createQuery("from Etape")
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}

		return result;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Etape getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
