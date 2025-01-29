package core;

import java.net.URL;
import java.util.ArrayList;

import utils.E_Levels;

public class Customer extends Person implements Comparable<Customer> {
	private static final long serialVersionUID = 1L;
	private URL url;
	private E_Levels level;
	private Team favoriteTeam;
	private ArrayList<Match> matches;

	public Customer(String pId) {
		super(pId);
	}

	public Customer(String pId, String fullName, int age, String nation, URL url, E_Levels level, Team favoriteTeam) {
		super(pId, fullName, age, nation);
		this.url = url;
		this.level = level;
		this.favoriteTeam = favoriteTeam;
		this.matches = new ArrayList<Match>();
	}

	public boolean addMatch(Match m) {
		if (m == null || this.matches == null) {
			return false;
		}
		return this.matches.add(m);
	}

	public boolean removeMatch(Match m) {
		if (m == null || this.matches == null) {
			return false;
		}
		return this.matches.remove(m);

	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public Team getFavoriteTeam() {
		return favoriteTeam;
	}

	public void setFavoriteTeam(Team favoriteTeam) {
		this.favoriteTeam = favoriteTeam;
	}

	@Override
	public String toString() {
		return super.toString() + ", url=" + url + ", level=" + level + ", favoriteTeam=" + favoriteTeam + "]";
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return this.level.compareTo(o.level);
	}

}
