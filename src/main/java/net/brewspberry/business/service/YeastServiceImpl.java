package net.brewspberry.business.service;

import java.util.ArrayList;
import java.util.List;

import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.IGenericService;
import net.brewspberry.business.ISpecificIngredientService;
import net.brewspberry.business.beans.Ingredient;
import net.brewspberry.business.beans.Levure;
import net.brewspberry.business.beans.Malt;
import net.brewspberry.dao.YeastDAOImpl;
import net.brewspberry.exceptions.DAOException;

public class YeastServiceImpl implements IGenericService<Levure>,
		ISpecificIngredientService {

	IGenericDao<Levure> levureDao = new YeastDAOImpl();

	@Override
	public void deleteElement(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Levure arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Levure> getAllDistinctElements() {
		return levureDao.getAllDistinctElements();
	}

	@Override
	public List<Levure> getAllElements() {
		return levureDao.getAllElements();
	}

	@Override
	public Levure getElementById(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Levure save(Levure arg0) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Levure update(Levure arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Levure> getIngredientFromArrayId(String[] array) {
		List<Levure> result = new ArrayList<Levure>();

		if (array != null) {

			if (array.length != 0) {

				for (int i = 0; i < array.length; i++) {

					long currentID = 0;

					try {
						currentID = Long.parseLong(array[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}

					Levure currentIngredient = levureDao.getElementById(currentID);
					currentIngredient.setIng_id(0);
					currentIngredient.setLev_brassin(null);

					if (!currentIngredient.equals(new Levure())) {
						result.add(currentIngredient);
					}
				}

			}

		}

		return result;
	}

}
