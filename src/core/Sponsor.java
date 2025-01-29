package core;

import java.util.ArrayList;
import java.util.Objects;


public class Sponsor extends Person implements Comparable<Sponsor> {

	private static final long serialVersionUID = 1L;
	private String nickName;
	private ArrayList<Team> teams;

	// -------------------------------Constructor---------------------------------------------------------
	public Sponsor(String pId) {
		super(pId);
		// TODO Auto-generated constructor stub
	}

	public Sponsor(String pId, String fullName, int age, String nation, String nickName) {
		super(pId, fullName, age, nation);
		this.nickName = nickName;
		this.teams = new ArrayList<Team>();
	}

	public boolean addTeam(Team t) {
		if (t == null || this.teams == null || this.teams.size() > 3)
			return false;
		this.teams.add(t);
		return true;
	}

	public boolean removeTeam(Team t) {
		if (t == null || this.teams == null || this.teams.size() > 3)
			return false;
		this.teams.remove(t);
		return true;
	}

	public ArrayList<Team> getTeams() {
		return this.teams;
	}

	// -------------------------------Getters And
	// Setters---------------------------------------------------

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	// -------------------------------HashCode And
	// Equlas---------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nickName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sponsor other = (Sponsor) obj;
		return Objects.equals(nickName, other.nickName);
	}

	// -------------------------------More
	// Methods---------------------------------------------------------
	@Override
	public int compareTo(Sponsor o) {
		// TODO Auto-generated method stub
		return this.getpId().compareTo(o.getpId());
	}

}
