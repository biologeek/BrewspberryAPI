package net.brewspberry.business;

import java.util.List;

import net.brewspberry.business.beans.Ingredient;

public interface ISpecificIngredientService {
	<T extends Ingredient> List<T>  getIngredientFromArrayId(String[] array);

}
