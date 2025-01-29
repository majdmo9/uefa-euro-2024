package utils;

public enum Country {
	
	POLAND	("PO"),
	UKRAINE	("UA"),
	GERMANY ("DE"),
	RUSSIA 	("RU"),
	ITALY 	("IT"),
	FRANCE 	("FR"),
	NETHERLANDS ("NL"),
	GREECE 	("GR"),
	ENGLAND ("GB"),
	DENMARK ("DK"),
	SPAIN 	("LT"),
	SWEDEN 	("SE"),
	CROATIA ("HR"),
	CZECHREPUBLIC ("CZ"),
	PORTUGAL("PT"),
	IRELAND	("IE"),
	UNKNOWN ("UN");
	
	private final String acronym;
	
	Country(String acronym){
		this.acronym=acronym;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public static Country getCounrtyByName(String country){	
		for(Country c :values()){
			if(c.toString().equals((country)))
				return c;
		}
		return UNKNOWN;
	}
	
	public String printCountry(){
		return String.format("Country:%s,%s",this,getAcronym());
	}
}
