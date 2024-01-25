package fr.wolfdev.cda.rpg.gameplay;

import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Races;

import java.util.Scanner;

public class RestRoomTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player("Gabinks");
        player.setRace(Races.CRA);
        RestRoom restRoom = new RestRoom(player);
        player.setLevel(5);
        restRoom.choice(sc);
    }
}
