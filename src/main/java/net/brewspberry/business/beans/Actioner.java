package net.brewspberry.business.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Actioner {

	/**
	 * 
	 * An Actioner is an object related to the devices plugged to Raspberry Pi.
	 * 
	 * It handles action related data : - Device
	 * 
	 **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long act_id;
	public String act_type;
	public String act_nom;
	public String act_uuid;
	public int act_status;
	public Date act_date_debut;
	public Date act_date_fin;
	public String act_raspi_pin;
	public Boolean act_activated;
	public Boolean act_used;

	@ManyToOne
	@JoinColumn(name = "act_bra_id")
	public Brassin act_brassin;

	@ManyToOne
	@JoinColumn(name = "act_etp_id")
	public Etape act_etape;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tmes_actioner")
	List<TemperatureMeasurement> act_temperature_measurements;

	public Actioner() {
		super();
	}

	public long getAct_id() {
		return act_id;
	}

	public void setAct_id(long act_id) {
		this.act_id = act_id;
	}

	public Boolean getAct_activated() {
		return act_activated;
	}

	public void setAct_activated(Boolean act_activated) {
		this.act_activated = act_activated;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public Brassin getAct_brassin() {
		return act_brassin;
	}

	public void setAct_brassin(Brassin act_brassin) {
		this.act_brassin = act_brassin;
	}

	public Etape getAct_etape() {
		return act_etape;
	}

	public String getAct_raspi_pin() {
		return act_raspi_pin;
	}

	public void setAct_raspi_pin(String act_raspi_pin) {
		this.act_raspi_pin = act_raspi_pin;
	}

	public void setAct_etape(Etape act_etape) {
		this.act_etape = act_etape;
	}

	public Boolean getAct_used() {
		return act_used;
	}

	public void setAct_used(Boolean act_used) {
		this.act_used = act_used;
	}

	public String getAct_nom() {
		return act_nom;
	}

	public void setAct_nom(String act_nom) {
		this.act_nom = act_nom;
	}

	public String getAct_uuid() {
		return act_uuid;
	}

	public void setAct_uuid(String act_uuid) {
		this.act_uuid = act_uuid;
	}

	public int getAct_status() {
		return act_status;
	}

	public void setAct_status(int act_status) {
		this.act_status = act_status;
	}

	public Date getAct_date_debut() {
		return act_date_debut;
	}

	public void setAct_date_debut(Date act_date_debut) {
		this.act_date_debut = act_date_debut;
	}

	public Date getAct_date_fin() {
		return act_date_fin;
	}

	public void setAct_date_fin(Date act_date_fin) {
		this.act_date_fin = act_date_fin;
	}

	public List<TemperatureMeasurement> getAct_temperature_measurements() {
		return act_temperature_measurements;
	}

	public void setAct_temperature_measurements(
			List<TemperatureMeasurement> act_temperature_measurements) {
		this.act_temperature_measurements = act_temperature_measurements;
	}

}
