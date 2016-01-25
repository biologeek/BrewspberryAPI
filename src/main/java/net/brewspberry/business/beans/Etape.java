package net.brewspberry.business.beans;

import java.io.Serializable;
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
import javax.persistence.OneToOne;


@Entity
public class Etape implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1849499413089750093L;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long etp_id;
	private Integer etp_numero; // step number in the brew
    private String etp_nom;
    private Date etp_debut;
    private Date etp_fin;
    private DurationBO etp_duree;
    private Double etp_temperature_theorique;
    private String etp_remarque;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ing_id")
    private Ingredient etp_ing_ajoute;
    private Date etp_date_ajout_ing;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="etp_bra_id")
    private Brassin etp_brassin;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="tmes_etape")
    private List<TemperatureMeasurement> etp_temperature_measurement;
    
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="act_etape")
    private List<Actioner> etp_actioner;
    

    @OneToMany(fetch=FetchType.LAZY, mappedBy="malt_etape")
    private List<Malt> etp_malts;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="hbl_etape")
    private List<Houblon> etp_houblons;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="lev_etape")
    private List<Levure> etp_levures;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="etp_brassin")
    List<Etape> bra_etapes;
    
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="ie_etape")
    private List<IngredientEtape> etp_ingredientEtapes;
    
	public Etape() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getEtp_id() {
		return etp_id;
	}

	public void setEtp_id(Long etp_id) {
		this.etp_id = etp_id;
	}

	public Integer getEtp_numero() {
		return etp_numero;
	}

	public void setEtp_numero(Integer etp_numero) {
		this.etp_numero = etp_numero;
	}

	public Brassin getEtp_brassin() {
		return etp_brassin;
	}

	public void setEtp_brassin(Brassin etp_brassin) {
		this.etp_brassin = etp_brassin;
	}

	public List<Actioner> getEtp_actioner() {
		return etp_actioner;
	}

	public void setEtp_actioner(List<Actioner> etp_actioner) {
		this.etp_actioner = etp_actioner;
	}

	public String getEtp_nom() {
		return etp_nom;
	}
	public void setEtp_nom(String etp_nom) {
		this.etp_nom = etp_nom;
	}
	public Date getEtp_debut() {
		return etp_debut;
	}
	public void setEtp_debut(Date etp_debut) {
		this.etp_debut = etp_debut;
	}
	public Date getEtp_fin() {
		return etp_fin;
	}
	public void setEtp_fin(Date etp_fin) {
		this.etp_fin = etp_fin;
	}
	public DurationBO getEtp_duree() {
		return etp_duree;
	}
	public void setEtp_duree(DurationBO etp_duree) {
		this.etp_duree = etp_duree;
	}
	public Double getEtp_temperature_theorique() {
		return etp_temperature_theorique;
	}
	public void setEtp_temperature_theorique(Double etp_temperature_theorique) {
		this.etp_temperature_theorique = etp_temperature_theorique;
	}
	public String getEtp_remarque() {
		return etp_remarque;
	}
	public void setEtp_remarque(String etp_remarque) {
		this.etp_remarque = etp_remarque;
	}

	public Ingredient getEtp_ing_ajoute() {
		return etp_ing_ajoute;
	}

	public void setEtp_ing_ajoute(Ingredient etp_ing_ajoute) {
		this.etp_ing_ajoute = etp_ing_ajoute;
	}

	public Date getEtp_date_ajout_ing() {
		return etp_date_ajout_ing;
	}

	public void setEtp_date_ajout_ing(Date etp_date_ajout_ing) {
		this.etp_date_ajout_ing = etp_date_ajout_ing;
	}

	public List<TemperatureMeasurement> getEtp_temperature_measurement() {
		return etp_temperature_measurement;
	}

	public void setEtp_temperature_measurement(
			List<TemperatureMeasurement> etp_temperature_measurement) {
		this.etp_temperature_measurement = etp_temperature_measurement;
	}

	public List<Malt> getEtp_malts() {
		return etp_malts;
	}

	public void setEtp_malts(List<Malt> etp_malts) {
		this.etp_malts = etp_malts;
	}

	public List<Houblon> getEtp_houblons() {
		return etp_houblons;
	}

	public void setEtp_houblons(List<Houblon> etp_houblons) {
		this.etp_houblons = etp_houblons;
	}

	public List<Levure> getEtp_levures() {
		return etp_levures;
	}

	public void setEtp_levures(List<Levure> etp_levures) {
		this.etp_levures = etp_levures;
	}

	public List<Etape> getBra_etapes() {
		return bra_etapes;
	}

	public void setBra_etapes(List<Etape> bra_etapes) {
		this.bra_etapes = bra_etapes;
	}

	public List<IngredientEtape> getEtp_ingredientEtapes() {
		return etp_ingredientEtapes;
	}

	public void setEtp_ingredientEtapes(List<IngredientEtape> etp_ingredientEtapes) {
		this.etp_ingredientEtapes = etp_ingredientEtapes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Etape [etp_id=" + etp_id + ", etp_numero=" + etp_numero
				+ ", etp_nom=" + etp_nom + ", etp_debut=" + etp_debut
				+ ", etp_fin=" + etp_fin + ", etp_duree=" + etp_duree
				+ ", etp_temperature_theorique=" + etp_temperature_theorique
				+ ", etp_remarque=" + etp_remarque 
				+ ", etp_date_ajout_ing=" + etp_date_ajout_ing
				+ "]";
	}
    
    
    

}