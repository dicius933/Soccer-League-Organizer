import com.teamtreehouse.model.Menu;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

public class LeagueManager {

  public static void main(String[] args) {

    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    Team team = new Team();
    Menu menu = new Menu(team);
    menu.run();
    // Team
  }

}
