package core;

/**
 * Player Class
 * @author Fadi Yassin
 * @author Amik  - 2024 
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import utils.E_Position;

public class Player extends Person implements Comparable<Player>, Serializable {

	private static final long serialVersionUID = 1L;

	private int pNum;
	private E_Position position;
	private int fansCount;
	private Team currentTeam;
	private Set<Match> matches;
	private Trophy<Player> trophy;

	// -------------------------------Constructor---------------------------------------------------------
	// constructor with Id
	public Player(String pId) {
		super(pId);
		// TODO Auto-generated constructor stub
	}

	public Player(String pId, String fullName, int age, String nation, int pNum, E_Position position, int fansCount,
			Team currentTeam) {
		super(pId, fullName, age, nation);
		this.pNum = pNum;
		this.position = position;
		this.fansCount = fansCount;
		this.currentTeam = currentTeam;
		this.matches = new HashSet<Match>();
	}

	public boolean addMatch(Match m) {
		if (this.matches != null && m != null) {
			return this.matches.add(m);
		}
		return false;
	}

	public boolean removeMatch(Match m) {
		if (this.matches == null || m == null)
			return false;
		return this.matches.remove(m);
	}

	public Set<Match> getMatchs() {
		return this.matches;
	}

	public void setTrophy(Trophy<Player> t) {
		this.trophy = t;
	}

	public Trophy<Player> getTrophy() {
		return this.trophy;
	}

	// -------------------------------Getters And
	// Setters---------------------------------------------------------
	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public E_Position getPosition() {
		return position;
	}

	public void setPosition(E_Position position) {
		this.position = position;
	}

	// -------------------------------HashCode And
	// Equlas---------------------------------------------------------

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Player player = (Player) o;
		return pId.equals(player.pId); // Ensure you are comparing unique identifiers
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), pId); // Use unique identifier
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds the player to the given team and removes the player from its
	 * current team only if the given team doesn't equal to the Player's current
	 * team.
	 *
	 * @param team
	 * @return true if the player was added successfully to team, false otherwise
	 */
	public boolean transferTo(Team team) {
		if (team == null || this.currentTeam.equals(team)) {
			return false;
		}
		this.currentTeam = team;
		return true;
	}

	@Override
	public int compareTo(Player o) {
		// TODO Auto-generated method stub
		return this.getpId().compareTo(o.getpId());
	}

}
