package init;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import core.Customer;
import core.Match;
import core.MatchResult;
import core.Player;
import core.Sponsor;
import core.Team;
import utils.E_Levels;
import utils.E_Position;
import utils.E_Trophy;
import utils.MyFileLogWriter;


public class MainClass {

    /**
     * The main method of this system gets input from text file Writes output
     * to output.txt file
     * @param args
     * @throws IOException
     * @throws ParseException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        // the command read from the input file 
        String command;

        // to check if the command updated the objects 
        boolean isUpdated;

        // writer buffer creation used after update 
        MyFileLogWriter.initializeMyFileWriter();

        DateFormat df = new SimpleDateFormat("d/M/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));

        // the JEuroTournament Object    
        JEuroTournament jEuroTournament = JEuroTournament.getInstance();

        // create Scanner for the text file named "input.txt" 
        Scanner input = new Scanner(new File("input.txt"));

        /*
         *  read the commands. loop while input file [input.hasnext()]
         * and the jEuroTournament is not null 
         */
        while (input.hasNextLine() && jEuroTournament != null) {
            // read the entire line
            String line = input.nextLine().trim();
            // split the line into tokens
            String[] tokens = line.split("\\s+");

            // ensure there are tokens to process
            if (tokens.length == 0) continue;

            // the first token is the command
            command = tokens[0];
            isUpdated = false;

            // ================                Command            ================
            if (command.equals("addTeam")) {
                if (tokens.length != 5) {
                    System.out.println("Invalid input for addTeam: " + line);
                    continue;
                }

                String tId = tokens[1];
                String tName = tokens[2];
                String represents = tokens[4];
                int fansCount = 0;      
          

                try {
                    fansCount = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading fansCount for addTeam: " + e.getMessage());
                }
               
                
                System.out.println("addTeam: tId=" + tId + ", tName=" + tName + ", represents=" + represents + ", fansCount=" + fansCount);

                isUpdated = jEuroTournament.addTeam(tId, tName, fansCount,represents);

                MyFileLogWriter.writeToFileInSeparateLine("addTeam returns:");

                if (isUpdated) { // if adding successfully, then true returned
                    // the following message is written to the output file
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added team " + tId + ", " + represents + " to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add new team " + tId + " to jEuroTournament");
                }
            }
            // ================                Command            ================
            else if (command.equals("addMatch")) {
                if (tokens.length != 5) {
                    System.out.println("Invalid input for addMatch: " + line);
                    continue;
                }

                String matchID = tokens[1];
                Date date = null;
                String stadium = tokens[3];
                long totalTickets = 0;
                MatchResult MatchR = jEuroTournament.getMatchResults().get(tokens[1]);
                

                try {
                    date = df.parse(tokens[2]);
                } catch (ParseException e) {
                    System.out.println("Error parsing date for addMatch: " + e.getMessage());
                }

                try {
                    totalTickets = Long.parseLong(tokens[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading totalTickets for addMatch: " + e.getMessage());
                }

                System.out.println("addMatch: matchID=" + matchID + ", date=" + date + ", stadium=" + stadium + ", totalTickets=" + totalTickets + ", MatchResult=" + MatchR);

                isUpdated = jEuroTournament.addMatch(matchID, date, stadium, totalTickets,MatchR);
                MyFileLogWriter.writeToFileInSeparateLine("addMatch returns:");

                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added match with identifier: " + matchID + " " + date + " to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add new match " + matchID + " to jEuroTournament");
                }
            }
            // ================                Command            ================
            else if (command.equals("addPlayer")) {
                if (tokens.length != 10) {
                    System.out.println("Invalid input for addPlayer: " + line);
                    continue;
                }

                String pId = tokens[1];
                String fullName = tokens[2] + " " + tokens[3];
                int age = 0;
                String nation = tokens[5];
                int pNum = 0;
               
                int fansCount = Integer.parseInt(tokens[8]);
                Team currentTeam = jEuroTournament.getTeams().get(tokens[9]);

                try {
                    age = Integer.parseInt(tokens[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading age for addPlayer: " + e.getMessage());
                }

                try {
                    pNum = Integer.parseInt(tokens[6]);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading pNum for addPlayer: " + e.getMessage());
                }
                E_Position position;
                try {
                    position = Enum.valueOf(E_Position.class, tokens[7].toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid position specified: " + tokens[7]);
                    // Handle or log the error appropriately
                    continue; // or return false; depending on your flow
                }

                System.out.println("addPlayer: pId=" + pId + ", fullName=" + fullName + ", age=" + age + ", nation=" + nation + ", pNum=" + pNum  + ", fansCount=" + fansCount + ", currentTeam=" + currentTeam);

                if (pId != null && fullName != null && nation != null &&  currentTeam != null) {

                    isUpdated = jEuroTournament.addPlayer(pId, fullName, age, nation, pNum, position, fansCount, currentTeam);

                    MyFileLogWriter.writeToFileInSeparateLine("addPlayer returns:");
                    if (isUpdated) {
                        MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added Player " + pId + ", " + pNum + " to jEuroTournament");
                    } else {
                        MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding Player " + pId + ", " + pNum + " to jEuroTournament");
                    }
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("addPlayer returns: invalid input pId, " + pId);
                }
            }
            // ================                Command            ================
            else if (command.equals("addCoach")) {
                if (tokens.length != 8) {
                    System.out.println("Invalid input for addCoach: " + line);
                    continue;
                }

                String pId = tokens[1];
                String fullName = tokens[2] + " " + tokens[3];
                int age = 0;
                String nation = tokens[5];
                Team currentTeam = jEuroTournament.getTeams().get(tokens[6]);
                E_Levels level = null;

                try {
                    age = Integer.parseInt(tokens[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading age for addCoach: " + e.getMessage());
                }

                try {
                    level = E_Levels.valueOf(tokens[7]);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error reading level for addCoach: " + e.getMessage());
                }

                System.out.println("addCoach: pId=" + pId + ", fullName=" + fullName + ", age=" + age + ", nation=" + nation + ", currentTeam=" + currentTeam + ", level=" + level);

                if (pId != null && fullName != null && nation != null && level != null && currentTeam != null) {

                    isUpdated = jEuroTournament.addCoach(pId, fullName, age, nation, currentTeam, level);

                    MyFileLogWriter.writeToFileInSeparateLine("addCoach returns:");
                    if (isUpdated) {
                        MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added Coach " + pId + " to jEuroTournament");
                    } else {
                        MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding Coach " + pId + " to jEuroTournament");
                    }
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("addCoach returns: invalid input pId, " + pId);
                }
            }
            // ~ End of addCoach

  
        // ================                Command            ================
        else if (command.equals("addCustomer")) {
            if (tokens.length != 9) {
                System.out.println("Invalid input for addCustomer: " + line);
                continue;
            }

            String pId = tokens[1];
            String fullName = tokens[2] + " " + tokens[3];
            int age = 0;
            String nation = tokens[5];
            URL email = null;
            try {
                email = new URL(tokens[6]);
            } catch (MalformedURLException e) {
                System.out.println("Invalid URL for addCustomer: " + e.getMessage());
            }
            E_Levels level = E_Levels.valueOf(tokens[7]);
            Team favoriteTeam = jEuroTournament.getTeams().get(tokens[8]);

            try {
                age = Integer.parseInt(tokens[4]);
            } catch (NumberFormatException e) {
                System.out.println("Error reading age for addCustomer: " + e.getMessage());
            }

            System.out.println("addCustomer: pId=" + pId + ", fullName=" + fullName + ", age=" + age + ", nation=" + nation + ", email=" + email + ", level=" + level + ", favoriteTeam=" + favoriteTeam);

            if (pId != null && fullName != null && nation != null && email != null && level != null && favoriteTeam != null) {

                isUpdated = jEuroTournament.addCustomer(pId, fullName, age, nation, email, level, favoriteTeam);

                MyFileLogWriter.writeToFileInSeparateLine("addCustomer returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added Customer " + pId + " to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding Customer " + pId + " to jEuroTournament");
                }
            } else {
                MyFileLogWriter.writeToFileInSeparateLine("addCustomer returns: invalid input pId , " + pId);
            }
        }
        // ================                Command            ================
        else if (command.equals("addSponsor")) {
            if (tokens.length != 7) {
                System.out.println("Invalid input for addSponsor: " + line);
                continue;
            }

            String pId = tokens[1];
            String fullName = tokens[2] + " " + tokens[3];
            int age = 0;
            String nation = tokens[5];
            String nickName = tokens[6];

            try {
                age = Integer.parseInt(tokens[4]);
            } catch (NumberFormatException e) {
                System.out.println("Error reading age for addSponsor: " + e.getMessage());
            }

            System.out.println("addSponsor: pId=" + pId + ", fullName=" + fullName + ", age=" + age + ", nation=" + nation + ", nickName=" + nickName);

            if (pId != null && fullName != null && nation != null && nickName != null) {

                isUpdated = jEuroTournament.addSponsor(pId, fullName, age, nation, nickName);

                MyFileLogWriter.writeToFileInSeparateLine("addSponsor returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added Sponsor " + pId + " to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding Sponsor " + pId + " to jEuroTournament");
                }
            } else {
                MyFileLogWriter.writeToFileInSeparateLine("addSponsor returns: invalid input pId , " + pId);
            }
        }
        // ================                Command            ================
        else if (command.equals("addMatchResult")) {
            if (tokens.length != 8) {
                System.out.println("Invalid input for addMatchResult: " + line);
                continue;
            }

            Team homeTeam = jEuroTournament.getTeams().get(tokens[1]);
            Team awayTeam = jEuroTournament.getTeams().get(tokens[2]);
            String matchId = tokens[3];
            int totalTime = 0;
            int homeTeamScore = 0;
            int awayTeamScore = 0;
            boolean penaltyEnd = Boolean.valueOf(tokens[5]);

            try {
                homeTeamScore = Integer.parseInt(tokens[6]);
                awayTeamScore = Integer.parseInt(tokens[7]);
                totalTime = Integer.parseInt(tokens[4]);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing integer value: " + e.getMessage());
            }

            System.out.println("addMatchResult: homeTeam=" + homeTeam + ", awayTeam=" + awayTeam +
                    ", match=" + matchId + ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore);

            if (homeTeam != null && awayTeam != null && matchId != null) {
                isUpdated = jEuroTournament.addMatchResult(homeTeam, awayTeam, matchId, homeTeamScore, awayTeamScore, totalTime, penaltyEnd);

                MyFileLogWriter.writeToFileInSeparateLine("addMatchResult returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added MatchResult to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding MatchResult to jEuroTournament");
                }
            } else {
                MyFileLogWriter.writeToFileInSeparateLine("addMatchResult returns: invalid input");
            }
        }

        // ================                Command            ================
        else if (command.equals("addTrophy")) {
            if (tokens.length != 4) {
                System.out.println("Invalid input for addTrophy: " + line);
                continue;
            }

            E_Trophy trophy = null;
            try {
                trophy = E_Trophy.valueOf(tokens[1]);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid trophy for addTrophy: " + e.getMessage());
                continue;
            }

            Object owner = null;
            switch (trophy) {
                case TEAM_OF_THE_YEAR:
                    owner = jEuroTournament.getTeams().get(tokens[2]);
                    break;
                case COACH_OF_THE_YEAR:
                    owner = jEuroTournament.getCoaches().get(tokens[2]);
                    break;
                case PLAYER_OF_THE_YEAR:
                    owner = jEuroTournament.getPlayers().get(tokens[2]);
                    break;
                case BEST_PlAYER_SHOOTS:
                    owner = jEuroTournament.getPlayers().get(tokens[2]);
                    break;
                case GOLDEN_GLOVE_PLAYER:
                    owner = jEuroTournament.getPlayers().get(tokens[2]);
                    break;
            }

            Date trophyWinningDate = null;
            try {
                trophyWinningDate = df.parse(tokens[3]);
            } catch (ParseException e) {
                System.out.println("Error parsing trophyWinningDate for addTrophy: " + e.getMessage());
            }

            System.out.println("addTrophy: trophy=" + trophy + ", owner=" + owner + ", trophyWinningDate=" + trophyWinningDate);

            if (trophy != null && owner != null && trophyWinningDate != null) {
                isUpdated = jEuroTournament.addTrophy(trophy, owner, trophyWinningDate);

                MyFileLogWriter.writeToFileInSeparateLine("addTrophy returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added Trophy to jEuroTournament");
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed adding Trophy to jEuroTournament");
                }
            } else {
                MyFileLogWriter.writeToFileInSeparateLine("addTrophy returns: invalid input");
            }
        }
            if (command.equals("addTeamToCoach")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addTeamToCoach: " + line);
                    continue;
                }

                String teamId = tokens[1];
                String coachId = tokens[2];

                System.out.println("addTeamToCoach: teamId=" + teamId + " coachId=" + coachId);

                isUpdated = jEuroTournament.addTeamToCoach(teamId, coachId);

                MyFileLogWriter.writeToFileInSeparateLine("addTeamToCoach returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added team " + teamId + " to coach " + coachId);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add team " + teamId + " to coach " + coachId);
                }
            }
 
            // ================                Command            ================
            else if (command.equals("addMatchToPlayer")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addMatchToPlayer: " + line);
                    continue;
                }

                String matchId = tokens[1];
                String playerId = tokens[2];

                System.out.println("addMatchToPlayer: matchId=" + matchId + " playerId=" + playerId);

                isUpdated = jEuroTournament.addMatchToPlayer(matchId, playerId);

                MyFileLogWriter.writeToFileInSeparateLine("addMatchToPlayer returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added match " + matchId + " to player " + playerId);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add match " + matchId + " to player " + playerId);
                }
            }
            // ================                Command            ================
            else if (command.equals("addTeamToSponsor")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addTeamToSponsor: " + line);
                    continue;
                }

                String teamId = tokens[1];
                String nickName = tokens[2];

                System.out.println("addTeamToSponsor: teamId=" + teamId + " nickName=" + nickName);

                isUpdated = jEuroTournament.addTeamToSponsor(teamId, nickName);

                MyFileLogWriter.writeToFileInSeparateLine("addTeamToSponsor returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added team " + teamId + " to sponsor " + nickName);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add team " + teamId + " to sponsor " + nickName);
                }
            }
            // ================                Command            ================
            else if (command.equals("addPlayerToTeam")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addPlayerToTeam: " + line);
                    continue;
                }

                String teamId = tokens[1];
                String playerId = tokens[2];

                System.out.println("addPlayerToTeam: teamId=" + teamId + " playerId=" + playerId);

                isUpdated = jEuroTournament.addPlayerToTeam(teamId, playerId);

                MyFileLogWriter.writeToFileInSeparateLine("addPlayerToTeam returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added player " + playerId + " to team " + teamId);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add player " + playerId + " to team " + teamId);
                }
            }
            // ================                Command            ================
            else if (command.equals("addMatchToTeam")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addMatchToTeam: " + line);
                    continue;
                }

                String matchId = tokens[1];
                String teamId = tokens[2];

                System.out.println("addMatchToTeam: matchId=" + matchId + " teamId=" + teamId);

                isUpdated = jEuroTournament.addMatchToTeam(matchId, teamId);

                MyFileLogWriter.writeToFileInSeparateLine("addMatchToTeam returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added match " + matchId + " to team " + teamId);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add match " + matchId + " to team " + teamId);
                }
            }
            // ================                Command            ================
            else if (command.equals("addCostumerToMatch")) {
                if (tokens.length != 3) {
                    System.out.println("Invalid input for addCostumerToMatch: " + line);
                    continue;
                }

                String costumerId = tokens[1];
                String matchId = tokens[2];

                System.out.println("addCostumerToMatch: costumerId=" + costumerId + " matchId=" + matchId);

                isUpdated = jEuroTournament.addCostumerToMatch(costumerId, matchId);

                MyFileLogWriter.writeToFileInSeparateLine("addCostumerToMatch returns:");
                if (isUpdated) {
                    MyFileLogWriter.writeToFileInSeparateLine("\tSuccessfully added customer " + costumerId + " to match " + matchId);
                } else {
                    MyFileLogWriter.writeToFileInSeparateLine("\tFailed to add customer " + costumerId + " to match " + matchId);
                }
            }
            // New methods for retrieving data
            if (command.equals("getEntityWithMostTrophies")) {
                Object entity = jEuroTournament.getEntityWithMostTrophies();
                MyFileLogWriter.writeToFileInSeparateLine("getEntityWithMostTrophies returns: " + entity);
            } else if (command.equals("getSponsorsOfGermany")) {
                Collection<Sponsor> sponsors = jEuroTournament.getSponsorsOfGermany();
                MyFileLogWriter.writeToFileInSeparateLine("getSponsorsOfGermany returns: " + sponsors);
            } else if (command.equals("getMatchWithMaxSoldOutTickets")) {
                Match match = jEuroTournament.getMatchWithMaxSoldOutTickets();
                MyFileLogWriter.writeToFileInSeparateLine("getMatchWithMaxSoldOutTickets returns: " + match);
            } else if (command.equals("getMostActivePlayer")) {
                Player player = jEuroTournament.getMostActivePlayer();
                MyFileLogWriter.writeToFileInSeparateLine("getMostActivePlayer returns: " + player);
            } else if (command.equals("getTheBestHomeMatch")) {
                Match match = jEuroTournament.getTheBestHomeMatch();
                MyFileLogWriter.writeToFileInSeparateLine("getTheBestHomeMatch returns: " + match);
            } else if (command.equals("getTheBesetCustomer")) {
                Collection<Customer> customers = jEuroTournament.getTheBesetCustomer();
                MyFileLogWriter.writeToFileInSeparateLine("getTheBesetCustomer returns: " + customers);
            } else if (command.equals("getTheMostPopularPosition")) {
                E_Position position = jEuroTournament.getTheMostPopularPosition();
                MyFileLogWriter.writeToFileInSeparateLine("getTheMostPopularPosition returns: " + position);
            } else if (command.equals("getTeamsBestHomeScore")) {
                Collection<Team> teams = jEuroTournament.getTeamsBestHomeScore();
                MyFileLogWriter.writeToFileInSeparateLine("getTeamsBestHomeScore returns: " + teams);
            } else if (command.equals("getTheMostFavoredTeam")) {
                Team team = jEuroTournament.getTheMostFavoredTeam();
                MyFileLogWriter.writeToFileInSeparateLine("getTheMostFavoredTeam returns: " + team);
            }
      

        }//~ end of clause - while input has next


        MyFileLogWriter.saveLogFile(); // save the output file
        input.close(); // close connection to input file
        System.out.println("[End Of process]");
        System.out.println("\n NOTICE:\n\t\"End of process\" " +
                        "does NOT mean that all your methods are correct.\n" +
                        "\n==>\t You NEED to check the \"output.txt\" file");
    }
}
