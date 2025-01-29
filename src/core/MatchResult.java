package core;

import java.util.Objects;

public class MatchResult {
	/**
	 * team A, participant in the game
	 */
	private Team homeTeam;
	/**
	 * team B, participant in the game
	 */
	private Team awayTeam;
	/**
	 * team A, participant in the game
	 */
	private String matchID;
	/**
	 * duration of the game in minutes
	 */
	private int totalTime;
	/**
	 * indicates if the was determined by penalty kicks 
	 */
	private boolean penaltyEnd;
	/**
	 * total goals by team A
	 */
	private int homeTeamScore;
	/**
	 * total goals by team B
	 */
	private int awayTeamScore;
	/**
	 * full constructor, <b>notice:<b>
	 * <p>cannot change the values of a result, no use of setters
	 * @param teamA
	 * @param teamB
	 * @param match
	 * @param totalTime
	 * @param tAgoals
	 * @param tBgoals
	 */
	public MatchResult(Team homeTeam, Team awayTeam, String matchID, int totalTime, boolean penaltyEnd, int homeTeamScore,
			int awayTeamScore) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchID = matchID;
		this.totalTime = totalTime;
		this.penaltyEnd = penaltyEnd;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}

	// -------------------------------Getters And Setters---------------------------------------------------------
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}

	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public boolean isPenaltyEnd() {
		return penaltyEnd;
	}
	public void setPenaltyEnd(boolean penaltyEnd) {
		this.penaltyEnd = penaltyEnd;
	}
	public int getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public int getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	// -------------------------------HashCode And Equlas---------------------------------------------------------

	@Override
	public int hashCode() {
		return Objects.hash(awayTeam, awayTeamScore, homeTeam, homeTeamScore, matchID, penaltyEnd, totalTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchResult other = (MatchResult) obj;
		return Objects.equals(awayTeam, other.awayTeam) && awayTeamScore == other.awayTeamScore
				&& Objects.equals(homeTeam, other.homeTeam) && homeTeamScore == other.homeTeamScore
				&& Objects.equals(matchID, other.matchID) && penaltyEnd == other.penaltyEnd
				&& totalTime == other.totalTime;
	}


	// -------------------------------KeyMethod---------------------------------------------------------

	  

	
}
