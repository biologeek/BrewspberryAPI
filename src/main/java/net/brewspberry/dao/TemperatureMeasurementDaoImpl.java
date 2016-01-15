package net.brewspberry.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mysql.jdbc.PreparedStatement;

import net.brewspberry.business.IGenericService;
import net.brewspberry.business.ISpecificTemperatureMeasurementService;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.business.beans.Etape;
import net.brewspberry.business.beans.TemperatureMeasurement;
import net.brewspberry.business.service.BrassinServiceImpl;
import net.brewspberry.util.HibernateUtil;

public class TemperatureMeasurementDaoImpl implements
		ISpecificTemperatureMeasurementService {

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

}
