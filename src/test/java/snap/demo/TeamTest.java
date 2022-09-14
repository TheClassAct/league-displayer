package snap.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
public class TeamTest
{
  Team fcTest;
  Team fcJozi;
  Team fcBarca;
  
  @BeforeAll
  public void before() {
    initTeams();
  }
  
  private void initTeams() {
    fcTest = new Team("FC Test");
    fcJozi = new Team("FC Jozi");
    fcBarca = new Team("FC Barcelona");
  }

  @Test
  public void testGetOrCreateTeam()
  {
    Team fcJozi2 = LeagueResults.getOrCreateTeam("FC Jozi");
    Team fcTest2 = LeagueResults.getOrCreateTeam("FC Test");
    Team newTeam = LeagueResults.getOrCreateTeam("I'm New");
    assertThat(Team.teamList.size()).isEqualTo(4);
  }
  
  @Test
  public void testAddTeamPoints()
  { 
    LeagueResults.addTeamPoints(4, 3, fcTest, fcBarca);
    LeagueResults.addTeamPoints(1, 1, fcJozi, fcBarca);
    LeagueResults.addTeamPoints(1, 5, fcTest, fcJozi);
    
    assertThat(fcTest.getPoints()).isEqualTo(3);
    assertThat(fcJozi.getPoints()).isEqualTo(4);
    assertThat(fcBarca.getPoints()).isEqualTo(1);
  }
  
  @Test
  public void testGetLeagueResults()
  {
    fcTest.setPoints(11);
    fcJozi.setPoints(11);
    fcBarca.setPoints(4);
    
    assertThat(LeagueResults.getLeagueResults()).isEqualTo("1. FC Jozi, 11 pts\n1. FC Test, 11 pts\n3. FC Barcelona, 4 pts\n4. I'm New, 0 pts\n");
    
  }
}
