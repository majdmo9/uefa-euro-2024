package init;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import core.Coach;
import core.Customer;
import core.Match;
import core.MatchResult;
import core.Player;
import core.Sponsor;
import core.Team;
import core.Trophy;
import utils.Country;
import utils.E_Levels;
import utils.E_Position;
import utils.E_Trophy;

public class JEuroTournament {
	// ---------------------the class parameters -------------------------
	// -------------------------------Class Members------------------------------
	private static JEuroTournament instance;
	private HashMap<String, Player> players; // All the Players
	private HashMap<String, Coach> coaches; // All the Coaches
	private HashMap<String, Customer> customers; // All Customers
	private HashMap<String, Sponsor> sponsors; // All Sopnsores
	private HashMap<String, Team> teams; // All teams
	private HashMap<String, Match> matches; // all matches
	private HashMap<String, MatchResult> matchResults; // all matches results
	private HashSet<Trophy<?>> trophies;

	// --------------------- Constructors -------------------------
	/**
	 * Constructor Perform initialization for related data structures
	 */
	public JEuroTournament() {
		super();
		teams = new HashMap<>(); // done
		matches = new HashMap<>(); // done
		players = new HashMap<>(); // done
		coaches = new HashMap<>(); // done
		customers = new HashMap<>(); // done
		sponsors = new HashMap<>(); // done
		matchResults = new HashMap<>();
		trophies = new HashSet<>();

	}

	// ---------------------- DB Methods -----------------------------

	public static JEuroTournament getInstance() {
		if (instance == null)
			instance = new JEuroTournament();
		return instance;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public HashMap<String, Coach> getCoaches() {
		return coaches;
	}

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public HashMap<String, Sponsor> getsponsors() {
		return sponsors;
	}

	public HashMap<String, Team> getTeams() {
		return teams;
	}

	public HashMap<String, Match> getMatches() {
		return matches;
	}

	public HashMap<String, MatchResult> getMatchResults() {
		return matchResults;
	}

	public HashSet<Trophy<?>> getTrophies() {
		return trophies;
	}

	/**
	 * the method creates and add new team to the system IFF the team does not
	 * exist.
	 * 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 */
	public boolean addPlayer(String pId, String fullName, int age, String nation, int pNum, E_Position position,
			int fansCount, Team currentTeam) {
		if (pId == null || fullName == null || age <= 0 || nation == null || pNum <= 0 || position == null
				|| fansCount <= 0 || currentTeam == null)
			return false;
		Player player = new Player(pId);
		if (players.containsKey(player.getpId())) {
			return false;
		}
		Player toPlayer = new Player(pId, fullName, age, nation, pNum, position, fansCount, currentTeam);
		players.put(pId, toPlayer);
		return true;
	}

	/**
	 * the method creates and add new team to the system IFF the team does not
	 * exist.
	 * 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 */
	public boolean addCoach(String pId, String fullName, int age, String nation, Team currentTeam, E_Levels level) {
		if (pId == null || fullName == null || age <= 0 || nation == null || level == null || currentTeam == null)
			return false;
		Coach coach = new Coach(pId);
		if (coaches.containsKey(coach.getpId())) {
			return false;
		}
		Coach toCoach = new Coach(pId, fullName, age, nation, currentTeam, level);
		coaches.put(pId, toCoach);
		return true;

	}

	/**
	 * the method creates and add new team to the system IFF the team does not
	 * exist.
	 * 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 */
	public boolean addCustomer(String pId, String fullName, int age, String nation, URL email, E_Levels level,
			Team favoriteTeam) {
		if (pId == null || fullName == null || age <= 0 || nation == null || email == null || level == null
				|| favoriteTeam == null)
			return false;
		Customer customer = new Customer(pId);
		if (customers.containsKey(customer.getpId())) {
			return false;
		}
		Customer toCostumer = new Customer(pId, fullName, age, nation, email, level, favoriteTeam);
		customers.put(pId, toCostumer);
		return true;

	}

	/**
	 * the method creates and add new team to the system IFF the team does not
	 * exist.
	 * 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 */
	public boolean addSponsor(String pId, String fullName, int age, String nation, String nickName) {
		if (pId == null || fullName == null || age <= 0 || nation == null || nickName == null)
			return false;
		Sponsor sponsor = new Sponsor(pId);
		if (sponsors.containsKey(sponsor.getNickName())) {
			return false;
		}
		Sponsor tosponsor = new Sponsor(pId, fullName, age, nation, nickName);
		sponsors.put(pId, tosponsor);
		return true;

	}

	/**
	 * the method creates and add new team to the system IFF the team does not
	 * exist.
	 * 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 */
	public boolean addTeam(String tNumber, String tName, int fansCount, String represents) {
		if (tNumber == null || tName == null || represents == null || fansCount <= 0)
			return false;
		Team team = new Team(tNumber);
		if (teams.containsKey(team.gettNumber())) {
			return false;
		}
		Team toTeam = new Team(tNumber, tName, fansCount, represents);
		teams.put(tNumber, toTeam);
		return true;

	} // ~ END OF addTeam

	/**
	 * adds a match to the system IFF it does not exist
	 * 
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @return true if managed to add the match
	 */
	public boolean addMatch(String matchID, Date date, String stadium, long totalTickets, MatchResult MatchR) {
		if (matchID == null || date == null || stadium == null || totalTickets <= 0)
			return false;
		Match match = new Match(matchID);
		if (matches.containsKey(match.getmId())) {
			return false;
		}
		Match toMatch = new Match(matchID, date, stadium, totalTickets, MatchR);
		matches.put(matchID, toMatch);
		return true;

	} // ~ END OF addMatch

	/**
	 * adds a match to the system IFF it does not exist
	 * 
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @return true if managed to add the match
	 */
	public boolean addMatchResult(Team homeTeam, Team awayTeam, String matchId, int homeTeamScore, int awayTeamScore,
			int totalTime, boolean penaltyEnd) {
		if (homeTeam == null || awayTeam == null || matchId == null || homeTeamScore <= 0 || awayTeamScore <= 0
				|| totalTime < 0)
			return false;
		MatchResult matchResult = new MatchResult(homeTeam, awayTeam, matchId, totalTime, penaltyEnd, homeTeamScore,
				awayTeamScore);

		if (matchResults.containsKey(matchId)) {
			return false;
		}

		matchResults.put(matchId, matchResult);
		return true;

	} // ~ END OF addMatch

	/**
	 * adds a match to the system IFF it does not exist
	 * 
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @return true if managed to add the match
	 */
	public <T> boolean addTrophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
		// TODO
		if (trophy == null || owner == null || trophyWinningDate == null)
			return false;
		Trophy<T> trophys = new Trophy<T>(trophy, owner, trophyWinningDate);
		if (trophies.contains(trophys)) {
			return false;
		}
		trophies.add(trophys);
		return true;
	}// ~ END OF addTrophy

	// ===================== Queries ===================================

	/**
	 * adds a Team to Coach
	 * 
	 * @param TeamID
	 * @param CoachID
	 * @return true if managed to add the Team to coach
	 * 
	 */
	public boolean addTeamToCoach(String teamId, String coachId) {
		Coach coach = this.coaches.get(coachId);
		Team team = this.teams.get(teamId);

		if (coach == null || team == null)
			return false;
		if (coach.addTeam(team)) {
			if (team.getCoach() == null) {
				coach.transferTo(team);
				return true;
			}
		}
		coach.removeTeam(team);
		return false;
	}

	/**
	 * adds a Match to Player
	 * 
	 * @param MatchId
	 * @param PlayerId
	 * @return true if managed to add the Match to Player
	 * 
	 */
	public boolean addMatchToPlayer(String matchId, String playerId) {
		Match match = this.matches.get(matchId);
		Player player = this.players.get(playerId);
		if (player == null) {
			return false;
		}
		return player.addMatch(match);
	}

	/**
	 * adds a Team to Sponsor
	 * 
	 * @param teamId
	 * @param nickName
	 * @return true if managed to add the Team to Sponsor
	 * 
	 */
	public boolean addTeamToSponsor(String teamId, String pId) {
		Team team = this.teams.get(teamId);
		Sponsor sponsor = this.sponsors.get(pId);
		if (sponsor == null) {
			return false;
		}
		if (sponsor.addTeam(team)) {
			if (team.getSponsor() == null) {
				team.setSponsor(sponsor);
				return true;
			}
		}
		sponsor.removeTeam(team);
		return false;
	}

	/**
	 * adds a Player to Team
	 * 
	 * @param teamId
	 * @param PlayerId
	 * @return true if managed to add the Player to Team
	 * 
	 */
	public boolean addPlayerToTeam(String teamId, String playerId) {
		Team team = this.teams.get(teamId);
		Player player = this.players.get(playerId);
		
		if (team == null) {
			return false;
		}
		if (team.addPlayer(player)) {
			if (player.transferTo(team)) {
				return true;
			}
		}
		team.removePlayer(player);
		return false;
	}

	/**
	 * adds a Match to Team
	 * 
	 * @param teamId
	 * @param MatchId
	 * @return true if managed to add the Match to Team
	 * 
	 */
	public boolean addMatchToTeam(String matchId, String teamId) {
		Match match = this.matches.get(matchId);
		Team team = this.teams.get(teamId);
		if (team == null)
			return false;
		if (team.addMatch(match)) {
			if (match.addTeam(team)) {
				return true;
			}
		}
		team.removeMatch(match);
		return false;
	}

	/**
	 * adds a Costumer to match
	 * 
	 * @param costumerId
	 * @param matchId
	 * @return true if managed to add the Costumer to match
	 * 
	 */
	public boolean addCostumerToMatch(String costumerId, String matchId) {
		Match match = this.matches.get(matchId);
		Customer customer = this.customers.get(costumerId);
		if (match == null)
			return false;
		if (match.addCustomer(customer)) {
			if (customer.addMatch(match)) {
				return true;
			} else {
				customer.removeMatch(match);
				return false;
			}
		}
		match.removeCustomer(customer);
		return false;
	}

	/**
	 * This query returns the most favored team. Most favored team is the team that
	 * has the highest number of customers that the team is their favorite team.
	 * 
	 * @return team object if found, null otherwise
	 */
	public Team getTheMostFavoredTeam() {
		Map<String, Integer> favoriteTeamsCount = new TreeMap<String, Integer>();
		
		for (Map.Entry<String, Customer> c : this.customers.entrySet()) {
			Integer favTeamCount = favoriteTeamsCount.get(c.getValue().getFavoriteTeam().gettNumber());
			if (favTeamCount == null) {
				favTeamCount = 0;
			}
			favoriteTeamsCount.put(c.getValue().getFavoriteTeam().gettNumber(), favTeamCount + 1);
		}

		List<Map.Entry<String, Integer>> sortedFavTeams = new ArrayList<>(favoriteTeamsCount.entrySet());
		sortedFavTeams.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		
		System.out.println(sortedFavTeams);
		return this.teams.get(sortedFavTeams.get(0).getKey());
		
	}

	/**
	 * This query returns the all best customers. "beset customer" that had level of
	 * PROFESSIONAL and from UKRAINE.
	 * 
	 * @return array of customers if players were found, empty list otherwise
	 */
	public Collection<Customer> getTheBesetCustomer() {
		// TODO
		ArrayList<Customer> superCustomers = new ArrayList<Customer>();

		for (Map.Entry<String, Customer> c : this.customers.entrySet()) {
			if (c.getValue().getLevel().equals(E_Levels.PROFESSIONAL)
					&& c.getValue().getNation().equals(Country.UKRAINE)) {
				superCustomers.add(c.getValue());
			}
		}
		return superCustomers;
	}

	/**
	 * This query returns the Match with the high number of crowd. from all the
	 * matches return the match with high number of crowd
	 * 
	 * @return match object if found, null otherwise
	 */
	public Match getTheBestHomeMatch() {
		int max = 0;
		String bestMatchId = new String();
		List<Map.Entry<String, Match>> matchesList = new ArrayList<Map.Entry<String, Match>>(this.matches.entrySet());
		for (Entry<String, Match> m : matchesList) {
			if (m.getValue().getCustomers().size() > max) {
				max = m.getValue().getCustomers().size();
				bestMatchId = m.getValue().getmId();
			}
		}
		return this.matches.get(bestMatchId);
	}

	/**
	 * This query returns the Player that has Played the most games' if there more
	 * than one return the fist one .
	 * 
	 * @return Player if found. null otherwise
	 */
	public Player getMostActivePlayer() {
		int max = 0;
		String mostActivePlayerId = new String();
		List<Map.Entry<String, Player>> playersList = new ArrayList<Map.Entry<String, Player>>(this.players.entrySet());
		for (Entry<String, Player> p : playersList) {
			if (p.getValue().getMatchs().size() > max) {
				max = p.getValue().getMatchs().size();
				mostActivePlayerId = p.getValue().getpId();
			}
		}
		return this.players.get(mostActivePlayerId);
	}

	/**
	 * This query returns the Match with maximum number of sold out Tickets. and
	 * ends with penalty kicks if there more than one return the fist one .
	 * 
	 * @return Matc if found. null otherwise
	 */
	public Match getMatchWithMaxSoldOutTickets() {
		long max = 0;
		String maxSoldMatchId = new String();
		List<Map.Entry<String, Match>> matchesList = new ArrayList<Map.Entry<String, Match>>(this.matches.entrySet());
		for (Entry<String, Match> m : matchesList) {
			if (m.getValue().getTotalTickets() > max) {
				max = m.getValue().getTotalTickets();
				maxSoldMatchId = m.getValue().getmId();
			}
		}
		return this.matches.get(maxSoldMatchId);
	}

	/**
	 * This query returns Sponsors that supports Germany
	 * 
	 * @return Collection of Sponsors if found. empty otherwise
	 */
	public Collection<Sponsor> getSponsorsOfGermany() {
		ArrayList<Sponsor> germanySponsors = new ArrayList<Sponsor>();

		for (Map.Entry<String, Sponsor> s : this.sponsors.entrySet()) {
			boolean flag = false;
			for (int i = 0; i < s.getValue().getTeams().size() && !flag; i++) {
				Team t = s.getValue().getTeams().get(i);
				if (t.getRepresents().equals(Country.GERMANY)) {
					germanySponsors.add(s.getValue());
					flag = true;
				}
			}
		}
		return germanySponsors;
	}

	/**
	 * This query returns the entity that has won the most trophies.
	 * 
	 * @return object if found. null otherwise
	 */
	public Object getEntityWithMostTrophies() {
		int playersCount = 0;
		int coachesCount = 0;
		int teamsCount = 0;
		int maxCount = 0;

		for (Map.Entry<String, Player> p : this.players.entrySet()) {
			if (p.getValue().getTrophy() != null)
				playersCount++;
		}

		for (Map.Entry<String, Coach> c : this.coaches.entrySet()) {
			if (c.getValue().getTrophy() != null)
				coachesCount++;
		}

		for (Map.Entry<String, Team> t : this.teams.entrySet()) {
			if (t.getValue().getTrophy() != null)
				teamsCount++;
		}

		maxCount = Math.max(coachesCount, Math.max(playersCount, teamsCount));
		if (maxCount == playersCount) {
			return this.players.get(this.players.keySet().toArray()[0]);
		} else if (maxCount == coachesCount) {
			return this.coaches.get(this.coaches.keySet().toArray()[0]);
		}
		return this.teams.get(this.teams.keySet().toArray()[0]);
	}

	/**
	 * This query returns all the Teams of the "Best Home Team" "Best Home Team" is
	 * the team with the highest homeTeamScore winnings
	 * 
	 * @return array of players if players were found, empty list otherwise
	 */
	public Collection<Team> getTeamsBestHomeScore() {
		// TODO
		Map<String, Integer> winningHomeTeams = new TreeMap<String, Integer>();
		Map<String, Integer> winningAwayTeams = new TreeMap<String, Integer>();
		List<Team> superTeams = new ArrayList<Team>();

		for (Map.Entry<String, MatchResult> mr : this.matchResults.entrySet()) {
			if (mr.getValue().getHomeTeamScore() > mr.getValue().getAwayTeamScore()) {
				Integer wins = winningHomeTeams.get(mr.getValue().getHomeTeam().gettNumber());
				if (wins == null) {
					wins = 0;
				}
				winningHomeTeams.put(mr.getValue().getHomeTeam().gettNumber(), wins + 1);
			} else if (mr.getValue().getHomeTeamScore() < mr.getValue().getAwayTeamScore()) {
				Integer wins = winningAwayTeams.get(mr.getValue().getAwayTeam().gettNumber());
				if (wins == null) {
					wins = 0;
				}
				winningAwayTeams.put(mr.getValue().getAwayTeam().gettNumber(), wins + 1);
			}
		}

		for (Map.Entry<String, Integer> wh : winningHomeTeams.entrySet()) {
			Integer awayWins = winningAwayTeams.get(wh.getKey());
			if (awayWins == null || wh.getValue() > awayWins) {
				superTeams.add(this.teams.get(wh.getKey()));
			}
		}

		return superTeams;
	}

	/**
	 * This query returns the most popular position. Most popular position is the
	 * type that belongs to the highest number of players.
	 * 
	 * @return position object if found, null otherwise
	 */
	public E_Position getTheMostPopularPosition() {
		// TODO
		TreeMap<E_Position, Integer> positions = new TreeMap<E_Position, Integer>();
		
		for (Map.Entry<String, Player> p : this.players.entrySet()) {
			Integer epCount = positions.get(p.getValue().getPosition());
			if (epCount == null) {
				epCount = 0;
			}
			positions.put(p.getValue().getPosition(), epCount + 1);
		}
		List<Map.Entry<E_Position, Integer>> sortedEntries = new ArrayList<>(positions.entrySet());
		sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		return sortedEntries.get(0).getKey();
	}

}
