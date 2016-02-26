package net.brewspberry.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.ISpecificTemperatureMeasurementService;
import net.brewspberry.business.beans.Etape;
import net.brewspberry.business.beans.TemperatureMeasurement;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.HibernateUtil;
import net.brewspberry.util.LogManager;

public class TemperatureMeasurementDaoImpl implements
		ISpecificTemperatureMeasurementService, IGenericDao<TemperatureMeasurement> {

	
	Logger logger = LogManager.getInstance(TemperatureMeasurementDaoImpl.class.getName());
	Session session = HibernateUtil.getSession();

	@SuppressWarnings("unchecked")
	@Override
	public List<TemperatureMeasurement> getTemperatureMeasurementByBrassin(
			Long brassin) {
		String sqlQuery = "SELECT * FROM TemperatureMeasurement WHERE tmes_bra_id = "
				+ brassin + " ORDER BY tmes_date;";

		List<TemperatureMeasurement> result = session.createSQLQuery(sqlQuery)
				.list();

		HibernateUtil.closeSession();
		return result;
	}

	@Override
	public List<TemperatureMeasurement> getTemperatureMeasurementByEtape(
			Etape etape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemperatureMeasurement getLastTemperatureMeasurementByUUID(
			String uuid) throws Exception {

		TemperatureMeasurement result = new TemperatureMeasurement();

		if (uuid != null) {

			if (uuid != "") {
				Criteria cr = session
						.createCriteria(TemperatureMeasurement.class);

				cr.add(Restrictions.eq("tmes_probeUI", uuid));
				cr.addOrder(Order.desc("tmes_date"));

				result = ((List<TemperatureMeasurement>) cr.list()).get(0);

			}
		} else {
			throw new Exception("Empty string is not permitted !!");
		}
		if (result == null || result.equals(new TemperatureMeasurement())) {
			throw new EntityNotFoundException(
					"Temperature measurement not found for uuid "+uuid);
		}


		HibernateUtil.closeSession();
		return result;
	}

	@Override
	public TemperatureMeasurement getLastTemperatureMeasurementByName(
			String name) throws Exception {

		String sqlQuery = "FROM TemperatureMeasurement WHERE tmes_probe_name = '"+name+"' ORDER BY tmes_date DESC";

		TemperatureMeasurement result = new TemperatureMeasurement();

		if (name != null) {

			if (name != "") {
				
				

				/*
				 *  Beware, if multiple brews at the same time, there may be conflict :
				 *  
				 *  should add as parameter and criterias Brassin and Etape objects to better filter
				 *  
				 *  but it's ok if you do only 1 brew or step at a time
				 *  
				 */
				
				Query request = session.createQuery(sqlQuery).setMaxResults(1);
				
				result = (TemperatureMeasurement) request.uniqueResult();
				
				
			} else {
				throw new Exception("Empty string is not permitted !!");
			}

		}

		if (result == null || result.equals(new TemperatureMeasurement())) {
			throw new EntityNotFoundException(
					"Temperature measurement not found for name "+name);
		}

		HibernateUtil.closeSession();
		return result;
	}


	@Override
	public List<TemperatureMeasurement> getAllLastTemperatureMeasurements(
			List<String> uuidOrName, Boolean uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TemperatureMeasurement> getAllLastTemperatureMeasurementsFromCSV(
			List<String> uuidOrName, Boolean uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemperatureMeasurement getLastTemperatureMeasurementsByNameFromCSV(
			String uuidOrName, Boolean uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemperatureMeasurement save(TemperatureMeasurement arg0)
			throws DAOException {
		Transaction tx = (Transaction) session.beginTransaction();
		TemperatureMeasurement result = null;
		try {
			
			long id = (long) session.save(arg0);
			tx.commit();
			
			result = this.getElementById(id);
			
			logger.info("Saved TemperatureMeasurement with id "+id);
			
		}catch(HibernateException e){
			
			tx.rollback();
			e.printStackTrace();
		}

		
		return result;
	}

	@Override
	public TemperatureMeasurement update(TemperatureMeasurement arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemperatureMeasurement getElementById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemperatureMeasurement getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TemperatureMeasurement> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(TemperatureMeasurement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TemperatureMeasurement> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return null;
	}

}
