package fr.wolfdev.cda.rpg.entity.player;

import fr.wolfdev.cda.rpg.entity.race.Races;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    public void test() {
        Player player1 = new Player("Rose");
        player1.setRace(Races.CRA);
        player1.getRace().setHealth(0);
        if(player1.isDead()) {
            System.out.println("Le joueur " + player1.getName() + " est bel et bien mort.");
        }
        else {
            System.out.println("Le joueur n'est pas mort !");
        }
        Players.displayDeadPlayers(player1);
        Player player2 = new Player("Ristou");
        player2.setRace(Races.ECAFLIP);
        player2.getRace().setHealth(0);
        if(player2.isDead()) {
            System.out.println("Le joueur " + player2.getName() + " est bel et bien mort.");
        }
        else {
            System.out.println("Le joueur n'est pas mort !");
        }
        Players.displayDeadPlayers(player2);
    }
}
