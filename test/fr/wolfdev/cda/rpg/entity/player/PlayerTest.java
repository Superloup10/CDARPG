package fr.wolfdev.cda.rpg.entity.player;

import fr.wolfdev.cda.rpg.entity.race.Races;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class PlayerTest {
    @Test
    public void chooseStats() {
        Scanner sc = new Scanner(System.in);
        Player p1 = new Player();
        p1.setRace(Races.CRA);
        p1.setStatsPoints(10);
        p1.chooseStats(sc);
    }
}
