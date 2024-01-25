package fr.wolfdev.cda.rpg.entity.mob;

import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Races;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.StringJoiner;

class MobTest {
    private Scanner sc;
    private Player player;
    private Mob mob;

    @BeforeEach
    public void setUp() {
        sc = new Scanner(System.in);
        player = new Player("Superloup10");
        player.setRace(Races.OSAMODAS);
        player.getRace().setHealth(10);
        player.getRace().setStrength(10);
        player.getRace().setDefense(5);
        player.getRace().setCriticalDamage(0.1D);
        player.getRace().setFumble(9);
        player.getRace().setInitiative(0);
        mob = new Mob("Test", 1, 1, 0);
        mob.setHealth(10);
        mob.setStrength(10);
        mob.setDefense(5);
        mob.setCriticalDamage(0.1D);
        mob.setFumble(4);
        mob.setInitiative(1);
    }

    @Test
    public void testMob() {
        Mob mob1 = new Mob();
        System.out.println(mob1);
        Mob mob2 = new Mob(7, 1, 3);
        System.out.println(mob2);
        Mob mob3 = new Mob("Test", 1, 1, 0);
        System.out.println(mob3);
    }

    @Test
    void attackToPlayer() {
        System.out.println(getStatus(mob));
        mob.attackTo(sc, player);
    }

    private String getStatus(Mob mob) {
        return new StringJoiner("\n", "", "")
                .add("Caractéristiques des combattants : ")
                .add("Nom : " + mob.getName() + " | " + player.getName())
                .add("Vie : " + mob.getHealth() + " | " + player.getRace().getHealth())
                .add("Force : " + mob.getStrength() + " | " + player.getRace().getStrength())
                .add("Intelligence : " + mob.getIntelligence() + " | " + player.getRace().getIntelligence())
                .add("Agilité : " + mob.getAgility() + " | " + player.getRace().getAgility())
                .add("Défense : " + mob.getDefense() + " | " + player.getRace().getDefense())
                .add("Pourcentage de dégâts critiques : " + mob.getCriticalDamage() + " | " + player.getRace().getCriticalDamage())
                .add("Pourcentage d'esquive : " + mob.getFumble() + " | " + player.getRace().getFumble())
                .add("Chance : " + mob.getChance() + " | " + player.getRace().getChance())
                .add("Initiative : " + mob.getInitiative() + " | " + player.getRace().getInitiative())
                .toString();
    }
}
