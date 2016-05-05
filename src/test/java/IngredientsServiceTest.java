import java.util.List;
import java.util.logging.Level;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import net.brewspberry.business.ISpecificIngredientService;
import net.brewspberry.business.beans.Houblon;
import net.brewspberry.business.beans.Levure;
import net.brewspberry.business.beans.Malt;
import net.brewspberry.business.service.HopServiceImpl;
import net.brewspberry.business.service.MaltServiceImpl;
import net.brewspberry.business.service.YeastServiceImpl;
import net.brewspberry.dao.YeastDAOImpl;

//@PrepareForTest(ISpecificIngredientService.class)
public class IngredientsServiceTest {
	
	ISpecificIngredientService ingService;
	static String[] arrayMal;
	static String[] arrayMalQte;
	static String[] arrayMalPrix;
	
	static String[] arrayHop;
	static String[] arrayHopQte;
	static String[] arrayHopPrix;
	
	static String[] arrayYeast;
	static String[] arrayYeastQte;
	static String[] arrayYeastPrix;
	
	@BeforeClass
	public static void setUpTestData(){
		
		arrayMal = new String[]{"0", "1"};
		arrayMalQte = new String[]{"12", "21"};
		arrayMalPrix = new String[]{"1.2", "2.1"};
		
		arrayHop = new String[]{"2", "3"};
		arrayHopQte = new String[]{"34", "43"};
		arrayHopPrix = new String[]{"3.4", "4.3"};
		
		arrayYeast = new String[]{"4", "5"};
		arrayYeastQte = new String[]{"45", "54"};
		arrayYeastPrix = new String[]{"4.5", "5.4"};
		
		
	}
	
	@Test
	public void shouldReturnIngredientFromArrayId(){

		
		ingService = new HopServiceImpl();
		
		List<Houblon> hopList = ingService.getIngredientFromArrayId(arrayHop, arrayHopQte, arrayHopPrix);
	
		Assert.assertEquals(2, hopList.size());
		Assert.assertEquals(12, hopList.get(0).getIng_quantite());
		Assert.assertEquals(2.1, hopList.get(1).getIng_prix());
		
		ingService = new MaltServiceImpl();
		
		List<Malt> maltList = ingService.getIngredientFromArrayId(arrayMal, arrayMalQte, arrayMalPrix);
	
		Assert.assertEquals(2, maltList.size());
		Assert.assertEquals(34, maltList.get(0).getIng_quantite());
		Assert.assertEquals(4.3, maltList.get(1).getIng_prix());
		
		ingService = new YeastServiceImpl();
		
		List<Levure> yeastList = ingService.getIngredientFromArrayId(arrayYeast, arrayYeastQte, arrayYeastPrix);
	

		Assert.assertEquals(2, yeastList.size());
		Assert.assertEquals(45, yeastList.get(0).getIng_quantite());
		Assert.assertEquals(5.4, yeastList.get(1).getIng_prix());
	}

}
