package net.brewspberry.business.beans;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class IngredientEtape {
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="ie_ing_id")
	private Ingredient ie_ingredient;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ie_etp_id")
	private Etape ie_etape;
	
    private Double ing_quantite;
    private Double ing_prix;
    
    
	public IngredientEtape() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ingredient getIe_ingredient() {
		return ie_ingredient;
	}
	public void setIe_ingredient(Ingredient ie_ingredient) {
		this.ie_ingredient = ie_ingredient;
	}
	public Etape getIe_etape() {
		return ie_etape;
	}
	public void setIe_etape(Etape ie_etape) {
		this.ie_etape = ie_etape;
	}
	public Double getIng_quantite() {
		return ing_quantite;
	}
	public void setIng_quantite(Double ing_quantite) {
		this.ing_quantite = ing_quantite;
	}
	public Double getIng_prix() {
		return ing_prix;
	}
	public void setIng_prix(Double ing_prix) {
		this.ing_prix = ing_prix;
	}

}
