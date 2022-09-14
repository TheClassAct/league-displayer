package snap.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeagueResults
{
  public static void main(String[] args) throws FileNotFoundException 
  {
    Scanner scanFile = new Scanner(new File("C:\\Users\\thato.moeng\\Downloads\\teams.txt")); 
    while (scanFile.hasNextLine())
    {
      Scanner scanLine = new Scanner(scanFile.nextLine()).useDelimiter(",");
      String team1AndScore = scanLine.next();
      String team2AndScore = scanLine.next();
      generatePoints(team1AndScore, team2AndScore);
    }
    System.out.println(getLeagueResults());
  }
  
  public static void generatePoints(String team1AndScore, String team2AndScore)
  {
    
    Scanner scan1 = new Scanner(team1AndScore);
    Scanner scan2 = new Scanner(team2AndScore);
    String teamName1 = scan1.next();
    String teamName2 = scan2.next();
    while (!scan1.hasNextInt()) { teamName1 = teamName1 + " " + scan1.next(); }
    while (!scan2.hasNextInt()) { teamName2 = teamName2 + " " + scan2.next(); }
    int score1 = scan1.nextInt();
    int score2 = scan2.nextInt();
      
    Team team1 = getOrCreateTeam(teamName1);
    Team team2 = getOrCreateTeam(teamName2);
    
    addTeamPoints(score1, score2, team1, team2);
  }
  
  public static void addTeamPoints(int score1, int score2, Team team1, Team team2) {
    if (score1 > score2)
    {
      team1.setPoints(team1.getPoints() + 3);
    }
    else if (score1 < score2)
    {
      team2.setPoints(team2.getPoints() + 3);
    }
    else
    {
      team1.setPoints(team1.getPoints() + 1);
      team2.setPoints(team2.getPoints() + 1);
    }
  }

  public static Team getOrCreateTeam(String teamName) 
  {
    if (Team.teamList.containsKey(teamName))
    {
      return Team.teamList.get(teamName);
    }
    return new Team(teamName);
  }


  public static String getLeagueResults() 
  {
    String resultString = "";
    List<Team> sortedTeamList = new ArrayList<>(Team.teamList.values());
    Collections.sort(sortedTeamList);
    int rank = 1, pos = 1;
    for (Team team: sortedTeamList)
    {
      if (pos > 1 && sortedTeamList.get(pos - 2).getPoints() != team.getPoints())
      {
        rank = pos;
      }
      resultString += rank + ". " + team.getTeamName() + ", " + team.getPoints() + " pts\n";
      pos++;
    }
    return resultString;
  }
}
