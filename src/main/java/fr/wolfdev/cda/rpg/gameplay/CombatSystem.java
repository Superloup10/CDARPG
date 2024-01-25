package fr.wolfdev.cda.rpg.gameplay;

import fr.wolfdev.cda.rpg.entity.mob.Mob;
import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Cra;
import fr.wolfdev.cda.rpg.entity.race.Ecaflip;
import fr.wolfdev.cda.rpg.entity.race.Eniripsa;
import fr.wolfdev.cda.rpg.entity.race.Feca;
import fr.wolfdev.cda.rpg.entity.race.Iop;
import fr.wolfdev.cda.rpg.entity.race.Race;
import fr.wolfdev.cda.rpg.entity.race.Sacrieur;
import fr.wolfdev.cda.rpg.entity.race.Sadida;
import fr.wolfdev.cda.rpg.entity.race.Sram;

import java.util.Random;

public class CombatSystem {
    private int damageFinalPlayer;
    private int damageFinalMob;
    private int attackCooldown = 2;

    public void playerAttackSpecial(Player attacker, Mob defender) {
        Race race = attacker.getRace();
        switch(race.getName()) {
            case "Crâ": {
                ((Cra)race).criticalBuff();
                break;
            }
            case "Ecaflip": {
                ((Ecaflip)race).randomBuff();
                break;
            }
            case "Eniripsa": {
                ((Eniripsa)race).healing();
                break;
            }
            case "Sadida": {
                ((Sadida)race).poison(defender);
                break;
            }
            case "Iop": {
                ((Iop)race).strengthBuff();
                break;
            }
            case "Sacrieur": {
                ((Sacrieur)race).lifeSteal(defender);
                break;
            }
            case "Féca": {
                ((Feca)race).defenseBuff();
                break;
            }
            case "Sram": {
                ((Sram)race).chanceBuff();
                break;
            }
        }
    }

    public void playerAttackOne(Player attacker, Mob defender) {
        Random rd = new Random();
        int randomInt = rd.nextInt(10);
        if((attacker.getRace().getCriticalDamage() * 100) >= randomInt) {
            int damage = (int)((attacker.getRace().getCriticalDamage() * 100) * 2) - defender.getDefense();
            damageFinalPlayer = Math.max(damage, 0);
        }
        else if(defender.getFumble() >= randomInt) {
            System.out.println(defender.getName() + " a réussi à esquiver les dégâts.");
            damageFinalPlayer = 0;
        }
        else {
            int damage = (int)(attacker.getRace().getCriticalDamage() * 100) - defender.getDefense();
            damageFinalPlayer = Math.max(damage, 0);
        }
    }

    public void playerAttackTwo(Player attacker, Mob defender) {
        Random rd2 = new Random();
        int randomInt = rd2.nextInt(10);
        if(attacker.getRace().getStrength() == attacker.getRace().getStrength()) {
            int damage = attacker.getRace().getStrength() * 2 - defender.getDefense();
            damageFinalPlayer = Math.max(damage, 0);
        }
        else if(defender.getFumble() >= randomInt) {
            System.out.println(defender.getName() + " a réussi à esquiver les dégâts.");
            damageFinalPlayer = 0;
        }
    }

    public void reduceCooldown() {
        if(attackCooldown > 0) {
            attackCooldown--;
        }
    }

    public byte mobRandomAttack(Mob mob, int attackDamage1, int attackDamage2, Player defender) {
        Random rd = new Random();
        if(rd.nextInt(2) == 0) {
            if(rd.nextInt(5) == 0) {
                int damage = (int)(attackDamage1 * (1 + mob.getCriticalDamage())) - defender.getRace().getDefense();
                damageFinalMob = Math.max(damage, 0);
            }
            else {
                int damage = attackDamage1 - defender.getRace().getDefense();
                damageFinalMob = Math.max(damage, 0);
            }
            return 1;
        }
        else {
            if(rd.nextInt(5) == 0) {
                int damage = (int)(attackDamage2 * (1 + mob.getCriticalDamage())) - defender.getRace().getDefense();
                damageFinalMob = Math.max(damage, 0);
            }
            else {
                int damage = attackDamage2 - defender.getRace().getDefense();
                damageFinalMob = Math.max(damage, 0);
            }
            return 2;
        }
    }

    public int getDamageFinalPlayer() {
        return damageFinalPlayer;
    }

    public void setDamageFinalPlayer(int damageFinalPlayer) {
        this.damageFinalPlayer = damageFinalPlayer;
    }

    public int getDamageFinalMob() {
        return damageFinalMob;
    }

    public void setDamageFinalMob(int degatFinalM) {
        this.damageFinalMob = degatFinalM;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }
}
