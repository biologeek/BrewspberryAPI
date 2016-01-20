package net.brewspberry.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.ISpecificActionerDao;
import net.brewspberry.business.beans.Actioner;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.HibernateUtil;
import net.brewspberry.util.LogManager;

public class ActionerDaoImpl implements IGenericDao<Actioner>, ISpecificActionerDao {

	static final Logger logger = LogManager.getInstance(ActionerDaoImpl.class.toString());
	
	Session session = HibernateUtil.getSession();
	
	@Override
	public Actioner save(Actioner arg0) throws DAOException {
		Transaction tx = session.beginTransaction();
		Long actID;
		
		try {
			logger.info("Saving Actioner whith uuid "+arg0.getAct_uuid());
			actID = (Long) session.save(arg0);
			
			tx.commit();
			if (arg0.getAct_id() == 0 && actID != 0){
				arg0.setAct_id(actID);
			}
			else {
				
				logger.severe("Oh, I think we got a problem here : act_id = "+actID);
			}
		}
		catch (HibernateException e){
			tx.rollback();
			throw new DAOException();
		}
		finally {
			HibernateUtil.closeSession();
		}
		logger.info("save returned id : "+actID+" "+arg0.getAct_id());
		return arg0;
	}

	@Override
	public Actioner update(Actioner arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actioner getElementById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actioner getElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actioner> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Actioner arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Actioner> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actioner getActionerByFullCharacteristics(Actioner actioner) {
		
		String hqlReq = "from "+Actioner.class+" WHERE act_bra_id = "+actioner.getAct_brassin().getBra_id()
				+" AND act_etp_id = "+actioner.getAct_etape().getEtp_id()
				+" AND act_type = "+actioner.getAct_type()
				+" AND act_uuid = "+actioner.getAct_uuid()
				;
		
		Actioner result = (Actioner) session.createQuery(hqlReq).uniqueResult();

		HibernateUtil.closeSession();
		
		return result;
	}

}