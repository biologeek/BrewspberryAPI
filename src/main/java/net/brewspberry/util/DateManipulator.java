package net.brewspberry.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import net.brewspberry.business.beans.DurationBO;

public class DateManipulator {

	private static DateManipulator dateManipulator;

	public static DateManipulator getInstance() {

		if (dateManipulator == null) {
			dateManipulator = new DateManipulator();
		}

		return dateManipulator;
	}

	public DurationBO getDurationBetween(Date beginning, Date end) {

		DurationBO result = new DurationBO();

		long beginningL = beginning.getTime();
		long endL = end.getTime();

		long diff = endL - beginningL;

		result.setWeek(getDurationInWeeks(diff));
		result.setDay(getDurationInDays(diff) - getDurationInWeeks(diff) * 7);
		result.setHour(getDurationInHours(diff) - getDurationInDays(diff)*24);
		result.setMinute(getDurationInMinutes(diff)
				- getDurationInHours(diff)*60);
		result.setSecond(diff
				- getDurationInMinutes(diff)*60);
		result.setMilisecond(0);

		return result;

	}

	public String getDurationAsString(DurationBO duration) {

		return duration.getWeek() + ";" + duration.getDay() + ";"
				+ duration.getHour() + ";" + duration.getMinute() + ";"
				+ duration.getSecond() + ";" + duration.getMilisecond();
	}

	public long getDurationInWeeks(long diff) {

		return (long) (Double.valueOf(diff) / (60.0 * 60.0 * 24.0 * 7));

	}

	public long getDurationInDays(long diff) {

		return (long) (Double.valueOf(diff) / (60.0 * 60.0 * 24.0));

	}

	public long getDurationInHours(long diff) {

		return (long) (Double.valueOf(diff) / (60.0 * 60.0));

	}

	public long getDurationInMinutes(long diff) {

		return (long) (Double.valueOf(diff) / 60.0);

	}
	
}
