package net.brewspberry.business.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("m")
@Table(name="malt")
public class Malt extends Ingredient implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4518248665359104487L;
	private String malt_cereale;
    private String malt_type;
    private Integer malt_couleur;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="malt_bra_id")
    private Brassin malt_brassin;
    
	public Malt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMalt_cereale() {
		return malt_cereale;
	}
	public void setMalt_cereale(String malt_cereale) {
		this.malt_cereale = malt_cereale;
	}
	public String getMalt_type() {
		return malt_type;
	}
	public void setMalt_type(String malt_type) {
		this.malt_type = malt_type;
	}
	public Integer getMalt_couleur() {
		return malt_couleur;
	}
	public void setMalt_couleur(Integer malt_couleur) {
		this.malt_couleur = malt_couleur;
	}

	public Brassin getMalt_brassin() {
		return malt_brassin;
	}

	public void setMalt_brassin(Brassin malt_brassin) {
		this.malt_brassin = malt_brassin;
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	public long getIng_Id (){
		return super.getIng_id();
	}

	@Override
	public String toString() {
		
		return super.toString()+" Malt [malt_cereale=" + malt_cereale + ", malt_type="
				+ malt_type + ", malt_couleur=" + malt_couleur + "]";
	}
	
}