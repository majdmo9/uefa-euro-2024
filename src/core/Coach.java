package core;

import java.util.HashSet;
import java.util.Set;

import utils.E_Levels;

public class Coach extends Person {
	private static final long serialVersionUID = 1L;
	private Team currentTeam;
	private E_Levels level;
	private Set<Team> teams;
	private Trophy<Coach> trophy;

	public Coach(String pId) {
		super(pId);
	}

	public Coach(String pId, String fullName, int age, String nation, Team currentTeam, E_Levels level) {
		super(pId, fullName, age, nation);
		this.currentTeam = currentTeam;
		this.level = level;
		this.teams = new HashSet<Team>();
	}
	
	public boolean addTeam(Team t) {
		if (t == null || this.teams == null) {
			return false;
		}
		return this.teams.add(t);
	}

	public boolean removeTeam(Team t) {
		if (t == null || this.teams == null) {
			return false;
		}
		return this.teams.remove(t);
	}

	public void setTrophy(Trophy<Coach> t) {
		this.trophy = t;
	}

	public Trophy<Coach> getTrophy() {
		return this.trophy;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	// -------------------------------More Methods------------------------------ **/
	/*
	 * This method adds the coach to the given team and removes the coach from its
	 * current team*only if the given team doesn't equal to the Coach's current
	 * team.
	 * 
	 * @param team
	 * 
	 * @return true if the coach was added successfully to team,false otherwise /*
	 */

	public boolean transferTo(Team team) {
		if (team == null || this.currentTeam.equals(team)) {
			return false;
		}
		team.setCoach(this);
		return true;
	}

	@Override
	public String toString() {
		return "Coach [currentTeam=" + currentTeam + ", level=" + level + "]";
	}

}
