package net.brewspberry.business;

import java.util.List;

import net.brewspberry.business.beans.Biere;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.business.beans.Ingredient;

public interface ISpecificBrassinService {
	
	public Brassin getBrassinByBeer(Biere beer);
	

}
