package fr.wolfdev.cda.rpg.entity.race;

import fr.wolfdev.cda.rpg.entity.Entity;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Cette classe représente une race
 */
public abstract class Race extends Entity {
    /**
     * Ce constructeur permet de créer une race en fournissant juste son nom
     *
     * @param name Nom de la race
     */
    public Race(String name) {
        this.name = name;
        this.initialize();
    }

    /**
     * Ce constructeur permet de créer une race entièrement personnalisable
     *
     * @param name           Nom de la race
     * @param health         Points de vie de la race
     * @param strength       Points de force de la race
     * @param intelligence   Points d'intelligence de la race
     * @param agility        Points d'agilité de la race
     * @param defense        Points de défense de la race
     * @param criticalDamage Pourcentage de dégâts critique
     * @param fumble         Pourcentage d'esquive
     * @param chance         Points de chance
     * @param initiative     Points d'initiative
     */
    public Race(String name, int health, int strength, int intelligence, int agility, int defense, double criticalDamage, int fumble, int chance, int initiative) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.defense = defense;
        this.criticalDamage = criticalDamage;
        this.fumble = fumble;
        this.chance = chance;
        this.initiative = initiative;
    }

    /**
     * Cette méthode sert à initialiser une race
     */
    public void initialize() {
        Random rd = new Random();
        this.health = 10;
        this.maxHealth = health;
        this.strength = rd.nextInt(10);
        this.intelligence = rd.nextInt(10);
        this.agility = rd.nextInt(10);
        this.defense = rd.nextInt(10);
        this.criticalDamage = rd.nextDouble(0.1D);
        this.fumble = rd.nextInt(10);
        this.chance = rd.nextInt(10);
        this.maxChance = chance;
        this.initiative = rd.nextInt(10);
    }

    public LinkedHashMap<String, Integer> getRaceStatistique() {
        return new LinkedHashMap<>() {{
            put("Vie", health);
            put("Force", strength);
            put("Intelligence", intelligence);
            put("Agilité", agility);
            put("Defense", defense);
            put("Dégâts Critique", (int)criticalDamage);
            put("Esquive", fumble);
            put("Chance", chance);
            put("Initiative", initiative);
        }};
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Race.
     *
     * @return Une chaîne de caractères représentant l'objet Race.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Race.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("health=" + health)
                .add("maxHealth=" + maxHealth)
                .add("strength=" + strength)
                .add("intelligence=" + intelligence)
                .add("agility=" + agility)
                .add("defense=" + defense)
                .add("criticalDamage=" + criticalDamage)
                .add("fumble=" + fumble)
                .add("chance=" + chance)
                .add("maxChance=" + maxChance)
                .add("initiative=" + initiative)
                .toString();
    }
}
