package net.brewspberry.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.ISpecificBrassinDAO;
import net.brewspberry.business.beans.Biere;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.HibernateUtil;

public class BrassinDaoImpl implements IGenericDao<Brassin>, ISpecificBrassinDAO {

	private Session session = HibernateUtil.getSession();

	@Override
	public Brassin save(Brassin arg0) throws DAOException {
		Transaction tx = session.beginTransaction();

		try {
			long id = (long) session.save(arg0);
			tx.commit();

			arg0.setBra_id(id);
			return arg0;
		} catch (HibernateException e) {
			tx.rollback();
			return new Brassin();
		}
		finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Brassin update(Brassin arg0) {
		Transaction tx = session.beginTransaction();

		if (arg0.getBra_id() != null) {
			try {
				session.update(arg0);
				tx.commit();
				return arg0;
			} catch (HibernateException e) {
				tx.rollback();
				return new Brassin();
			}
		}
		try {
			long id = (long) session.save(arg0);
			tx.commit();
			arg0.setBra_id(id);
			return arg0;
		} catch (HibernateException e) {
			tx.rollback();
			return new Brassin();
		}
		finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Brassin getElementById(long id) {
		Transaction tx = session.beginTransaction();
		Brassin result = new Brassin();

		try {
			result = (Brassin) session.get(Brassin.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brassin> getAllElements() {

		List<Brassin> result = null;

		Transaction tx = session.beginTransaction();

		try {

			result = (List<Brassin>) session.createQuery("from Brassin").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// Not rollbacking as it generates TransactionException 
			//tx.rollback();
			result = new ArrayList<Brassin>();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@Override
	public void deleteElement(long id) {

	}

	@Override
	public void deleteElement(Brassin arg0) {
		Transaction tx = session.beginTransaction();

		try {
			session.delete(arg0);
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Brassin> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brassin getBrassinByBeer(Biere beer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brassin getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
