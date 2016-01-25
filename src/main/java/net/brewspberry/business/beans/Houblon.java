package net.brewspberry.business.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("h")
public class Houblon extends Ingredient implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -9085140125050205061L;
	private String hbl_variete;
    private Double hbl_acide_alpha;
    private String hbl_aromes;
    private Integer hbl_type;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hbl_bra_id")
    private Brassin hbl_brassin;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hbl_etp_id")
    private Etape hbl_etape;
    
    
	public Houblon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getHbl_variete() {
		return hbl_variete;
	}
	public void setHbl_variete(String hbl_variete) {
		this.hbl_variete = hbl_variete;
	}
	public Double getHbl_acide_alpha() {
		return hbl_acide_alpha;
	}
	public void setHbl_acide_alpha(Double hbl_acide_alpha) {
		this.hbl_acide_alpha = hbl_acide_alpha;
	}
	
	public String getHbl_aromes() {
		return hbl_aromes;
	}
	public void setHbl_aromes(String hbl_aromes) {
		this.hbl_aromes = hbl_aromes;
	}
	public Integer getHbl_type() {
		return hbl_type;
	}
	public void setHbl_type(Integer hbl_type) {
		this.hbl_type = hbl_type;
	}
	public Brassin getHbl_brassin() {
		return hbl_brassin;
	}
	public void setHbl_brassin(Brassin hbl_brassin) {
		this.hbl_brassin = hbl_brassin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Houblon [hbl_variete=" + hbl_variete + ", hbl_acide_alpha="
				+ hbl_acide_alpha + ", hbl_aromes=" + hbl_aromes
				+ ", hbl_type=" + hbl_type + "]";
	}
	public Etape getHbl_etape() {
		return hbl_etape;
	}
	public void setHbl_etape(Etape hbl_etape) {
		this.hbl_etape = hbl_etape;
	}

    
	
    
}