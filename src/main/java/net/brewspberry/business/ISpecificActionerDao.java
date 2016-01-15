package net.brewspberry.business;

import net.brewspberry.business.beans.Actioner;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.business.beans.Etape;

public interface ISpecificActionerDao {
	
	public Actioner getActionerByFullCharacteristics (Actioner actioner);
	
	
}
