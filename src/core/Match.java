package core;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Java Course Fadi Yassin
 * @author YVC college - 2024
 * 
 */

import java.util.Date;
import java.util.Objects;

import utils.Stadium;

public class Match implements Comparable<Match> {
	/**
	 * game identification number , used as key
	 */
	private String mId;
	/**
	 * the date when the game took place
	 */
	private Date mDate;
	/**
	 * the place where the game took place;
	 */
	private Stadium tookPlace;

	/**
	 * total Tickets sold
	 */
	private long totalTickets;
	/**
	 * as the two teams participant in ONE match
	 */
	private MatchResult result;

	private ArrayList<Customer> customers;

	private Team team1;
	private Team team2;

	// constructors

	public Match(String mId, Date mDate, String tookPlace, long totalTickets, MatchResult result) {
		super();
		this.mId = mId;
		setmDate(mDate);
		setTookPlace(tookPlace);
		this.totalTickets = totalTickets;
		this.result = result;
		this.customers = new ArrayList<Customer>();
	}

	public Match(String mId) {
		super();
		this.mId = mId;
	}

	public boolean addTeam(Team t) {
		if (t == null || t.equals(this.team1) || t.equals(this.team2)) {
			return false;
		}
		if (this.team1 == null) {
			this.team1 = t;
			return true;
		}
		if (this.team2 == null) {
			this.team2 = t;
			return true;
		}
		return false;
	}

	public boolean addCustomer(Customer c) {
		if (c == null || this.customers == null || this.customers.size() >= this.totalTickets) {
			return false;
		}
		return this.customers.add(c);

	}

	public boolean removeCustomer(Customer c) {
		if (c == null || this.customers == null) {
			return false;
		}
		return this.customers.remove(c);

	}

	public ArrayList<Customer> getCustomers() {
		return this.customers;
	}

	public boolean removeTeam1(Team t) {
		if (t != null && this.team1 != null) {
			this.team1 = null;
			return true;
		}
		return false;
	}

	public boolean removeTeam2(Team t) {
		if (t != null && this.team2 != null) {
			this.team2 = null;
			return true;
		}
		return false;
	}

	// -------------------------------Getters And
	// Setters------------------------------
	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public Stadium getTookPlace() {
		return tookPlace;
	}

	public void setTookPlace(String tookPlace) {
		this.tookPlace = Stadium.getStadiumByName(tookPlace);
	}

	public long getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(long totalTickets) {
		this.totalTickets = totalTickets;
	}

	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}

	public void setTookPlace(Stadium tookPlace) {
		this.tookPlace = tookPlace;
	}

	// -------------------------------hashCode equals &
	// toString------------------------------
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Match match = (Match) o;
		return mId.equals(match.mId); // Ensure you are comparing unique identifiers
	}

	@Override
	public int hashCode() {
		return Objects.hash(mId); // Use unique identifier
	}

	// -------------------------------More Methods------------------------------
	@Override
	public int compareTo(Match o) {
		// TODO Auto-generated method stub
		return this.getmDate().compareTo(o.getmDate());
	}

}
