package fr.wolfdev.cda.rpg.entity.mob;

import fr.wolfdev.cda.rpg.entity.Entity;
import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.gameplay.CombatSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Mob extends Entity {
    private Random rd = new Random();
    private final CombatSystem combatSystem = new CombatSystem();
    private List<String> nameList = List.of("Scarfayss le Balafré",
            "Tiwa'Missou le Gateux",
            "Wokènrôl le Danseur",
            "Bistou le Quêteur", "Don Kizoth l'Obstiné",
            "Larvomatike le Propre",
            "Kilimanj'haro le Grimpeur",
            "Koalvissie le Chauve",
            "Gelanal le Huileux",
            "Tiwalpé le Dévêtu",
            "Tiwoflan le Lâche",
            "Bambuské le Camouflé",
            "Sarkastik L'incompris",
            "Picht le Brioché",
            "Aboudbra le Porteur"); // La liste contenant le nom qui sera utilisé pour nommer les monstres.
    private Map<String, Integer> attackList = new HashMap<>() {{
        put("Frappe draconique", rd.nextInt(1, 6));
        put("Furie stellaire", rd.nextInt(1, 6));
        put("Flèche infernale", rd.nextInt(1, 6));
        put("Lame foudroyante", rd.nextInt(1, 6));
        put("Éruption ténébreuse", rd.nextInt(1, 6));
        put("Rafale spectrale", rd.nextInt(1, 6));
        put("Éclair fulgurant", rd.nextInt(1, 6));
        put("Venin meurtrier", rd.nextInt(1, 6));
        put("Déferlante chaotique", rd.nextInt(1, 6));
        put("Séisme dévastateur", rd.nextInt(1, 6));
        put("Coup astral", rd.nextInt(1, 6));
        put("Regard malfaisant", rd.nextInt(1, 6));
        put("Distorsion chronique", rd.nextInt(1, 6));
        put("Tromperie perfide", rd.nextInt(1, 6));
        put("Souffle incandescent", rd.nextInt(1, 6));
    }};
    private int playerActualLevel;
    private int lvlDifficultyGame;
    private int manyFinishedGame; //Permet de récupérer le nombre de parties déjà terminées

    private String attackName1;
    private String attackName2;
    private int attackDamage1;
    private int attackDamage2;

    public Mob() {
        this(1, 1, 0);
    }

    public Mob(int playerActualLevel, int lvlDifficultyGame) {
        this(playerActualLevel, lvlDifficultyGame, 0);
    }

    public Mob(int playerActualLevel, int lvlDifficultyGame, int manyFinishedGame) {
        Random rd = new Random();
        this.name = nameList.get(rd.nextInt(nameList.size()));
        this.health = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.maxHealth = health;
        this.strength = ((rd.nextInt(1, 6)) * (playerActualLevel + manyFinishedGame / 10));
        this.intelligence = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.agility = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.defense = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.criticalDamage = ((rd.nextInt(6)) * (playerActualLevel + lvlDifficultyGame + (double)manyFinishedGame / 10));
        this.fumble = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.chance = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        this.maxChance = chance;
        this.initiative = ((rd.nextInt(1, 6)) * (playerActualLevel + lvlDifficultyGame + manyFinishedGame / 10));
        String[] keysArray = attackList.keySet().toArray(new String[0]);
        if(keysArray.length >= 2) {
            int index1 = rd.nextInt(keysArray.length);
            int index2 = rd.nextInt(keysArray.length - 1);
            if(index2 >= index1) {
                index2++;
            }
            attackName1 = keysArray[index1];
            attackName2 = keysArray[index2];
            attackDamage1 = attackList.get(attackName1) * (this.strength - this.playerActualLevel);
            attackDamage2 = attackList.get(attackName2) * (this.strength - this.playerActualLevel);
        }
    }

    public Mob(String name, int playerActualLevel, int lvlDifficultyGame, int manyFinishedGame) {
        this(playerActualLevel, lvlDifficultyGame, manyFinishedGame);
        this.name = name;
    }

    public Mob(String name, int playerActualLevel, int health, int strength, int intelligence, int agility, int defense, double criticalDamage, int fumble, int chance, int initiative) {
        this.name = name;
        this.playerActualLevel = playerActualLevel;
        this.health = health;
        this.maxHealth = health;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.defense = defense;
        this.criticalDamage = criticalDamage;
        this.fumble = fumble;
        this.chance = chance;
        this.maxChance = chance;
        this.initiative = initiative;
    }

    /**
     * Cette méthode permet d'attaquer un joueur
     *
     * @param sc     Intéraction avec le clavier
     * @param player Le joueur qui est en train de combattre
     * @return Renvoi si un combat est terminé ou non.
     */
    public boolean attackTo(Scanner sc, Player player) {
        if(player.getRace().getInitiative() >= this.initiative) {
            System.out.println(player.getName() + " attaque en premier");
            return attackPlayerFirst(sc, player);
        }
        else {
            System.out.println(this.name + " attaque en premier");
            return attackMobFirst(sc, player);
        }
    }

    /**
     * Cette méthode permet au joueur d'attaquer en premier
     *
     * @param sc     Intéraction avec le clavier
     * @param player Le joueur qui est en train de combattre
     * @return Renvoi si un combat est terminé ou non.
     */
    private boolean attackPlayerFirst(Scanner sc, Player player) {
        CombatSystem combatSystem = new CombatSystem();
        System.out.println("Quelle attaque souhaitez-vous faire ? (atk1/atk2/special)");
        String attackChoice = sc.next();
        switch(attackChoice) {
            case "atk1" -> {
                combatSystem.playerAttackOne(player, this);
                combatSystem.reduceCooldown();
            }
            case "atk2" -> {
                if(combatSystem.getAttackCooldown() <= 0) {
                    combatSystem.playerAttackTwo(player, this);
                    combatSystem.setAttackCooldown(2);
                }
                else {
                    System.out.println("Attaque en recharge, il reste " + combatSystem.getAttackCooldown() + " tours avant utilisation");
                    combatSystem.reduceCooldown();
                }
            }
            case "special" -> {
                combatSystem.playerAttackSpecial(player, this);
                combatSystem.reduceCooldown();
            }
        }
        System.out.println(attackChoice + " choisis, le monstre subit " + combatSystem.getDamageFinalPlayer() + " dégâts");
        if(this.health - combatSystem.getDamageFinalPlayer() > 0) {
            this.health -= combatSystem.getDamageFinalPlayer();
            System.out.println("Il lui reste " + this.health + " points de vie");
            System.out.println("Au tour du monstre d'attaquer");
            byte attackMobChoice = combatSystem.mobRandomAttack(this, this.attackDamage1, this.attackDamage2, player);
            System.out.println("Il a fait " + (attackMobChoice == 1 ? this.attackName1 : this.attackName2) + " et a infligé " + combatSystem.getDamageFinalMob() + " dégâts");
            if(player.getRace().getHealth() - combatSystem.getDamageFinalMob() > 0) {
                player.getRace().setHealth(player.getRace().getHealth() - combatSystem.getDamageFinalMob());
                System.out.println("Il reste " + player.getRace().getHealth() + " points de vie au joueur");
            }
            else {
                // On a perdu le combat !
                player.getRace().setHealth(0);
                System.out.println("Vous avez été tué par " + this.name + " !");
                return true;
            }
        }
        else {
            // On a gagné le combat !
            this.health = 0;
            System.out.println("Vous avez tué " + this.name + " !");
            player.setMobEncounterCount(player.getMobEncounterCount() + 1);
            player.setLevel(player.getLevel() + 1);
            player.setStatsPoints(player.getStatsPoints() + 1);
            player.chooseStats(sc);
            return true;
        }
        return false;
    }

    /**
     * Cette méthode permet au mob d'attaquer en premier
     *
     * @param sc     Intéraction avec le clavier
     * @param player Le joueur qui est en train de combattre
     * @return Renvoi si un combat est terminé ou non.
     */
    private boolean attackMobFirst(Scanner sc, Player player) {
        byte attackMobChoice = combatSystem.mobRandomAttack(this, this.attackDamage1, this.attackDamage2, player);
        System.out.println("Il a fait " + (attackMobChoice == 1 ? this.attackName1 : this.attackName2) + " et a infligé " + combatSystem.getDamageFinalMob() + " dégâts");
        if(player.getRace().getHealth() - combatSystem.getDamageFinalMob() > 0) {
            player.getRace().setHealth(player.getRace().getHealth() - combatSystem.getDamageFinalMob());
            System.out.println("Il reste " + player.getRace().getHealth() + " points de vie au joueur");
            System.out.println("Quelle attaque souhaitez-vous faire ? (atk1/atk2/special)");
            String attackChoice = sc.next();
            switch(attackChoice) {
                case "atk1" -> {
                    combatSystem.playerAttackOne(player, this);
                    combatSystem.reduceCooldown();
                }
                case "atk2" -> {
                    if(combatSystem.getAttackCooldown() <= 0) {
                        combatSystem.playerAttackTwo(player, this);
                        combatSystem.setAttackCooldown(2);
                    }
                    else {
                        System.out.println("Attaque en recharge, il reste " + combatSystem.getAttackCooldown() + " tours avant utilisation");
                        combatSystem.reduceCooldown();
                    }
                }
                case "special" -> {
                    combatSystem.playerAttackSpecial(player, this);
                    combatSystem.reduceCooldown();
                }
            }
            System.out.println(attackChoice + " choisis, le monstre subit " + combatSystem.getDamageFinalPlayer() + " dégâts");
            if(this.health - combatSystem.getDamageFinalPlayer() > 0) {
                this.health -= combatSystem.getDamageFinalPlayer();
                System.out.println("Il lui reste " + this.health + " points de vie");
            }
            else {
                // On a gagné le combat !
                this.health = 0;
                System.out.println("Vous avez tué " + this.name + " !");
                player.setMobEncounterCount(player.getMobEncounterCount() + 1);
                player.setLevel(player.getLevel() + 1);
                player.setStatsPoints(player.getStatsPoints() + 1);
                player.chooseStats(sc);
                return true;
            }
        }
        else {
            // On a perdu le combat !
            player.getRace().setHealth(0);
            System.out.println("Vous avez été tué par " + this.name + " !");
            return true;
        }
        return false;
    }

    public int getPlayerActualLevel() {
        return playerActualLevel;
    }

    public void setPlayerActualLevel(int playerActualLevel) {
        this.playerActualLevel = playerActualLevel;
    }

    public int getLvlDifficultyGame() {
        return lvlDifficultyGame;
    }

    public void setLvlDifficultyGame(int lvlDifficultyGame) {
        this.lvlDifficultyGame = lvlDifficultyGame;
    }

    @Override
    public String toString() {
        return "Mob{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", strength=" + strength +
                ", intelligence=" + intelligence +
                ", agility=" + agility +
                ", defense=" + defense +
                ", criticalDamage=" + criticalDamage +
                ", fumble=" + fumble +
                ", chance=" + chance +
                ", initiative=" + initiative +
                ", attackName1='" + attackName1 + '\'' +
                ", attackName2='" + attackName2 + '\'' +
                ", attackDamage1=" + attackDamage1 +
                ", attackDamage2=" + attackDamage2 +
                '}';
    }
}
