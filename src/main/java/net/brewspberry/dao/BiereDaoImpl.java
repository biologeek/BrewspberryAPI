package net.brewspberry.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.ISpecificBiereDAO;
import net.brewspberry.business.beans.Biere;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.HibernateUtil;

public class BiereDaoImpl implements IGenericDao<Biere>, ISpecificBiereDAO {

	private Session session = HibernateUtil.getSession();

	@Override
	public Biere save(Biere arg0) throws DAOException {
		Transaction tx = session .beginTransaction();

		try {
			long id = (long) session.save(arg0);
			tx.commit();

			arg0.setBeer_id(id);
			return arg0;
		} catch (HibernateException e) {
			tx.rollback();
			return new Biere();
		}
		finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Biere update(Biere arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Biere getElementById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Biere> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(Biere arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Biere> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Biere getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
