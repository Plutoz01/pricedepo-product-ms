package org.plutoz.pricedepo.product.ms.domain;

public enum Measurement {
	WEIGHT_MILLIGRAMM ("mg"),
	WEIGHT_GRAMM ("g"),
	WEIGHT_KILOGRAMM ("kg"),
	
	VOLUME_MILLILITER ("ml"),
	VOLUME_LITER ("l"),
	VOLUME_CUBIC_CENTIMETER ("cm3"),
	VOLUME_CUBIC_METER ("m3"),
	
	LENGTH_MILLIMETER ("mm"),
	LENGTH_CENTIMETER ("cm"),
	LENGTH_METER ("m"),
	
	AREA_SQUARE_CM ("cm2"),
	AREA_SQUARE_M ("m2"),
	
	PIECE ("piece");
	
	private final String abbr;
	
	private Measurement (String abbr){
		this.abbr = abbr;
	}
	
	public String getAbbrevation(){
		return this.abbr;
	}
}
