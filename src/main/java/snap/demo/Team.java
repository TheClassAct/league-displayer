package snap.demo;

import java.util.HashMap;
import java.util.Map;


public class Team implements Comparable<Team>{
  
  private String teamName;
  private int points;
  public static Map<String, Team> teamList = new HashMap<>();

  
  public Team(String teamName) {
    this.teamName = teamName;
    this.points = 0;
    teamList.put(teamName, this);
  }


  public String getTeamName() {
    return teamName;
  }
  
  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  @Override
  public int compareTo(Team o) {
    if (o.getPoints() - this.getPoints() == 0) { return this.getTeamName().compareTo(o.getTeamName()); }
    return o.getPoints() - this.getPoints();
  }
}