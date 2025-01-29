package utils;


public enum Stadium {

	NationalStadium(56070, Country.POLAND, "Warsaw"), PGE_Arena(39150, Country.POLAND, "Gdansk"),
	MunicipalStadium(40000, Country.POLAND, "Wroclaw"), MunicipalRStadium(39550, Country.POLAND, "Poznan"),
	OlympicStadium(64640, Country.UKRAINE, "Kiev"), DonbassArena(49400, Country.UKRAINE, "Donetsk"),
	MetalistStadium(37750, Country.UKRAINE, "Kharkiv"), ArenaLviv(32990, Country.UKRAINE, "Lviv");

	private final int capacity;
	private final Country country;
	private final String city;

	private Stadium(int capacity, Country country, String city) {
		this.capacity = capacity;
		this.country = country;
		this.city = city;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	public static Stadium getStadiumByName(String stadium) {
		for (Stadium s : Stadium.values()) {
			if (s.toString().equals(stadium)) {
				return s;
			}
		}
		return null;
	}

	public String printStudium() {
		return String.format("Stadium:%s\n\tCountry:%s\n\tcapacity:%d\n\tCity:%s\n", this, getCountry(), getCapacity(),
				getCity());
	}
}
