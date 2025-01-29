package core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import utils.Country;

public class Team implements Comparable<Team> {
	/**
	 * the team identification number
	 */
	private String tNumber;
	/**
	 * team full name
	 */
	private String tName;
	/**
	 * this calculated field is a result of total fans of all player of this team
	 */
	private int fansCount;
	/**
	 * sponsor that the team sponsored from of this team
	 */
	private Sponsor sponsor;
	/**
	 * country that this team represent, each team represent one country
	 */
	private Country represents;
	/**
	 * The coach of the team
	 * 
	 */
	private Coach coach;
	/**
	 * Team players
	 * 
	 */
	private Set<Player> players;
	/**
	 * Team matches
	 * 
	 */
	private Set<Match> matches;
	/**
	 * Team Funds
	 * 
	 */
	private Set<Found> funds;
	/**
	 * Team Customers
	 */
	private Set<Customer> customers;
	/**
	 * Team trophy
	 */
	private Trophy<Team> trophy;

	// -------------------------------Constructors------------------------------
	public Team(String tNumber) {
		super();
		this.tNumber = tNumber;
	}

	public Team(String tNumber, String tName, int fansCount, String represents) {
		super();
		this.tNumber = tNumber;
		this.tName = tName;
		this.fansCount = fansCount;
		setRepresents(represents);
		this.players = new TreeSet<Player>();
		this.funds = new HashSet<Found>();
		this.matches = new TreeSet<Match>();
		this.customers = new HashSet<Customer>();
	}

	public boolean addPlayer(Player p) {
		if (p != null && this.players != null && this.players.size() < 20) {
			return this.players.add(p);
		}
		return false;
	}

	public boolean removePlayer(Player p) {
		return this.players.remove(p);
	}

	public Set<Player> getPlayers() {
		return this.players;
	}

	public boolean addCustomer(Customer c) {
		if (c != null && this.customers != null) {
			return this.customers.add(c);
		}
		return false;
	}

	public boolean removeCustomer(Customer c) {
		return this.customers.remove(c);
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public boolean addMatch(Match m) {
		if (this.matches == null || m == null)
			return false;
		return this.matches.add(m);
	}

	public boolean removeMatch(Match m) {
		if (this.matches == null || m == null)
			return false;
		return this.matches.remove(m);
	}

	public Set<Match> getMatches() {
		return this.matches;
	}

	public void setTrophy(Trophy<Team> t) {
		this.trophy = t;
	}

	public Trophy<Team> getTrophy() {
		return this.trophy;
	}

	public boolean addFund(Found f) {
		if (f == null || this.funds == null)
			return false;
		return this.funds.add(f);
	}

	public Set<Found> getFunds() {
		return this.funds;
	}

	// -------------------------------Getters And
	// Setters------------------------------

	public String gettNumber() {
		return tNumber;
	}

	public void settNumber(String tNumber) {
		this.tNumber = tNumber;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public Country getRepresents() {
		return represents;
	}

	public void setRepresents(String represents) {
		this.represents = Country.getCounrtyByName(represents);
	}

	public void setRepresents(Country represents) {
		this.represents = represents;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public void setCoach(Coach c) {
		this.coach = c;
	}

	public Coach getCoach() {
		return this.coach;
	}

	// -------------------------------HashCode And
	// Equals------------------------------
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Team team = (Team) o;
		return tNumber.equals(team.tNumber); // Ensure you are comparing unique identifiers
	}

	@Override
	public int hashCode() {
		return Objects.hash(tNumber); // Use unique identifier
	}

	// -------------------------------More Methods------------------------------

	@Override
	public int compareTo(Team other) {
		return Integer.compare(this.fansCount, other.fansCount);
	}
	
	

}
