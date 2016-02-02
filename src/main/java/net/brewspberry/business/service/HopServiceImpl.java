package net.brewspberry.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.IGenericService;
import net.brewspberry.business.ISpecificIngredientService;
import net.brewspberry.business.beans.Houblon;
import net.brewspberry.dao.HopDaoImpl;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.util.LogManager;

public class HopServiceImpl implements IGenericService<Houblon>, ISpecificIngredientService {

	
	IGenericDao<Houblon> hopDao = new HopDaoImpl ();
	
	static Logger logger = LogManager.getInstance(HopServiceImpl.class.getName());
	@Override
	public Houblon save(Houblon arg0) throws DAOException {
		// TODO Auto-generated method stub
		return hopDao.save(arg0);
	}

	@Override
	public Houblon update(Houblon arg0) {
		// TODO Auto-generated method stub
		return hopDao.update(arg0);
	}

	@Override
	public Houblon getElementById(long id) {
		// TODO Auto-generated method stub
		return hopDao.getElementById(id);
	}

	@Override
	public List<Houblon> getAllElements() {
		// TODO Auto-generated method stub
		return hopDao.getAllElements();
	}

	@Override
	public void deleteElement(long id) {
		hopDao.deleteElement(id);
		
	}

	@Override
	public void deleteElement(Houblon arg0) {
		hopDao.deleteElement(arg0);
		
	}

	@Override
	public List<Houblon> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return hopDao.getAllDistinctElements();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Houblon> getIngredientFromArrayId(String[] array) {
		List<Houblon> result = new ArrayList<Houblon>();
		
		
		if (array != null) {
			
			if (array.length != 0){
				
				for (int i = 0 ; i < array.length ; i++){
					
					String currentName = "";
					long currentNameNumeric = 0;
					System.out.println(array);
					try {
						currentName = array[i];
						currentNameNumeric = Long.parseLong(currentName);
					} catch (Exception e) {
						e.printStackTrace();
					}
						logger.info("Getting hopName "+currentName);
					
					Houblon currentIngredient = hopDao.getElementById(currentNameNumeric);
					
					//Resetting ID so that it is saved when creating new brew
					currentIngredient.setIng_id((long) 0);
					//currentIngredient.getIng_ingredientEtape().(0.0);
					currentIngredient.setHbl_brassin(null);
					
					if (currentIngredient != null){
						if (!currentIngredient.equals(new Houblon())){
							result.add(currentIngredient);	
						}			
					}
				}
				
			}
			
		}
		
		
		return result;
	}


}
