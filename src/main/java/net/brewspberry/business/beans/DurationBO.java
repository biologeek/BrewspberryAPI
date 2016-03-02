package net.brewspberry.business.beans;

import java.io.Serializable;

public class DurationBO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3610500240459984835L;
	private long week;
	private long day;
	private long hour;
	private long minute;
	private long second;
	private long milisecond;
	
	
	public DurationBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public long getWeek() {
		return week;
	}
	public void setWeek(long week) {
		this.week = week;
	}
	public long getDay() {
		return day;
	}
	public void setDay(long l) {
		this.day = l;
	}
	public long getHour() {
		return hour;
	}
	public void setHour(long hour) {
		this.hour = hour;
	}
	public long getMinute() {
		return minute;
	}
	public void setMinute(long minute) {
		this.minute = minute;
	}
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	public long getMilisecond() {
		return milisecond;
	}
	public void setMilisecond(long milisecond) {
		this.milisecond = milisecond;
	}


	@Override
	public String toString() {
		return  week + "w " + day + "d " + hour
				+ "h " + minute + "m " + second + "s "+ milisecond + "ms";
	}
	

}
