package fr.wolfdev.cda.rpg.entity.player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static List<String> deadPlayers = new ArrayList<>();

    public static void displayDeadPlayers(Player player) {
        if(player.isDead()) {
            deadPlayers.add(player.getName());
        }
        deadPlayers.forEach(System.out::println);
    }
}
