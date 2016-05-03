
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;
import net.brewspberry.util.DateManipulator;


public class DateManipulatorTest {
	
	
	
	@Test
	public void shouldFormatDateFromVariousPatterns(){
		
		String date1 = "2016-04-28 17:00:00";
		String date2 = "2015-03-20 16:25:33.2000";
		String date3 = "10/02/2014 15:40:50";
		String date4 = "12/01/2014 13:32:59.1234";
		
		Date resDate1 = DateManipulator.formatDateFromVariousPatterns(date1);
		Calendar cal  = Calendar.getInstance();
		
		
		Date resDate2 = DateManipulator.formatDateFromVariousPatterns(date2);
		Date resDate3 = DateManipulator.formatDateFromVariousPatterns(date3);
		Date resDate4 = DateManipulator.formatDateFromVariousPatterns(date4);
		
		cal.setTime(resDate1);

		
		Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), 28);

		cal.setTime(resDate2);
		Assert.assertEquals(cal.get(Calendar.MILLISECOND), 2000);
		

		cal.setTime(resDate3);
		Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), 10);
		

		cal.setTime(resDate4);
		Assert.assertEquals(cal.get(Calendar.SECOND), 59);

	}
	

}
