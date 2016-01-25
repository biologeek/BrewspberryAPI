package net.brewspberry.business.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("l")
public class Levure extends Ingredient implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8449094340159438833L;
	private String lev_espece;
    private String lev_floculation;
    private String lev_aromes;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lev_bra_id")
    private Brassin lev_brassin;

    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lev_etp_id")
    private Etape lev_etape;
    
	public Levure() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Brassin getLev_brassin() {
		return lev_brassin;
	}

	public void setLev_brassin(Brassin lev_brassin) {
		this.lev_brassin = lev_brassin;
	}

	public String getLev_espece() {
		return lev_espece;
	}
	public void setLev_espece(String lev_espece) {
		this.lev_espece = lev_espece;
	}
	public String getLev_aromes() {
		return lev_aromes;
	}
	public void setLev_aromes(String lev_aromes) {
		this.lev_aromes = lev_aromes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Levure [lev_espece=" + lev_espece + ", elv_floculation="
				+ lev_floculation + ", lev_aromes=" + lev_aromes
				+", toString()=" + super.toString()
				+ "]";
	}

	public String getLev_floculation() {
		return lev_floculation;
	}

	public void setLev_floculation(String lev_floculation) {
		this.lev_floculation = lev_floculation;
	}

	public Etape getLev_etape() {
		return lev_etape;
	}

	public void setLev_etape(Etape lev_etape) {
		this.lev_etape = lev_etape;
	}
	

}