package net.brewspberry.business.service;

import java.util.ArrayList;
import java.util.List;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.IGenericService;
import net.brewspberry.business.ISpecificIngredientService;
import net.brewspberry.business.ISpecificMaltDAO;
import net.brewspberry.business.beans.Malt;
import net.brewspberry.dao.MaltDAOImpl;
import net.brewspberry.exceptions.DAOException;

public class MaltServiceImpl implements IGenericService<Malt>, ISpecificMaltDAO, ISpecificIngredientService{

	
	IGenericDao<Malt> maltDAO = new MaltDAOImpl ();
	@Override
	public Malt save(Malt arg0) throws DAOException {
		// TODO Auto-generated method stub
		return maltDAO.save(arg0);
	}

	@Override
	public Malt update(Malt arg0) {
		// TODO Auto-generated method stub
		return maltDAO.update(arg0);
	}

	@Override
	public Malt getElementById(long id) {
		// TODO Auto-generated method stub
		return maltDAO.getElementById(id);
	}

	@Override
	public List<Malt> getAllElements() {
		// TODO Auto-generated method stub
		return maltDAO.getAllElements();
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(Malt arg0) {
		// TODO Auto-generated method stub
		maltDAO.deleteElement(arg0);
	}

	@Override
	public List<Malt> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return maltDAO.getAllDistinctElements();
	}

	@Override
	public List<Malt> getIngredientFromArrayId(String[] array) {
		List<Malt> result = new ArrayList<Malt>();

		
		if (array != null) {
			
			if (array.length != 0){
				
				for (int i = 0 ; i < array.length ; i++){
					
					long currentID = 0;
					
					try {
						currentID = Long.parseLong(array[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
						
					
					Malt currentIngredient = maltDAO.getElementById(currentID);
					
					currentIngredient.setIng_id(0);
								
					
					currentIngredient.setIng_quantite((float) 0.0);
					currentIngredient.setMalt_brassin(null);

					if (!currentIngredient.equals(new Malt())){
						result.add(currentIngredient);	
					}					
				}
				
			}
			
		}
		
		
		return result;
	}

}
