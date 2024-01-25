package fr.wolfdev.cda.rpg.gameplay;

import fr.wolfdev.cda.rpg.entity.mob.Mob;
import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Races;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class CombatSystemTest {
    private Scanner sc;
    private Player player;
    private Mob mob;

    @BeforeEach
    void setUp() {
        sc = new Scanner(System.in);
        player = new Player("Superloup10");
        player.setRace(Races.OSAMODAS);
        mob = new Mob("Test", 1, 1, 0);
    }

    @Test
    void combatSystemTest() {
        int etage = 0;
        int niveau = 0;
        CombatSystem combatSystem = new CombatSystem();
        do {
            System.out.println("Quelle attaque souhaitez vous faire ? (atk1/atk2)");
            String atk = sc.next().trim();
            if(atk.equals("atk1")) {
                combatSystem.playerAttackOne(player, mob);
            }
            else {
                if(combatSystem.getAttackCooldown() == 0) {
                    combatSystem.playerAttackTwo(player, mob);
                    combatSystem.setAttackCooldown(2);
                }
                else {
                    System.out.println("Attaque en recharge, il reste " + combatSystem.getAttackCooldown() + " tours avant utilisation");
                    combatSystem.reduceCooldown();
                }
                System.out.println(atk + " choisis,le monstre subit " + combatSystem.getDamageFinalPlayer() + " dégats, il lui reste " + (mob.getHealth() - combatSystem.getDamageFinalPlayer()) + " vie");
            }
            System.out.println("Au tours du monstre d'attaquer");
            //combatSystem.mobRandomAttack(mob);
            //System.out.println("Il a fait " + mob.getNameAttack() + " et a infligé " + combatSystem.getDamageFinalMob());
            System.out.println("Il reste " + (player.getRace().getHealth() - combatSystem.getDamageFinalMob()) + " vie au joueur");
        }
        while(player.getRace().getHealth() > 0 && mob.getHealth() > 0);
        if(player.getRace().getHealth() == 0) {
            //combatSystem.setVictory(false);
        }
        else {
            if(mob.getHealth() == 0) {
                System.out.println("Monstre vaincu, vous avez gagné un niveau");
                etage++;
                niveau++;
            }
        }
    }
}
