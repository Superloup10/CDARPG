package fr.wolfdev.cda.rpg.entity;

import java.util.Objects;

/**
 * Cette classe représente la classe mère de toutes les entités du jeu
 *
 * @author Damien ANDRÉ
 */
public abstract class Entity {
    /**
     * Nom de l'entité
     */
    protected String name;

    /**
     * Points de vie
     */
    protected int health;

    /**
     * Points de vie maximal
     */
    protected int maxHealth;

    /**
     * Points de force
     */
    protected int strength;

    /**
     * Points d'intelligence
     */
    protected int intelligence;

    /**
     * Points d'agilité
     */
    protected int agility;

    /**
     * Points de défense
     */
    protected int defense;

    /**
     * Pourcentage de dégâts critique
     */
    protected double criticalDamage;

    /**
     * Pourcentage d'esquive
     */
    protected int fumble;

    /**
     * Points de chance
     */
    protected int chance;

    /**
     * Points de chance maximal
     */
    protected int maxChance;

    /**
     * Points d'initiative
     */
    protected int initiative;

    /**
     * Permet de récupérer le nom de l'entité
     *
     * @return le nom de l'entité
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de définir le nom de l'entité
     *
     * @param name Nom que l'on veut définir
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permet de récupérer les points de vie
     *
     * @return les points de vie
     */
    public int getHealth() {
        return health;
    }

    /**
     * Permet de définir les points de vie
     *
     * @param health Points de vie que l'on veut définir
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Permet de récupérer les points de vie maximum
     *
     * @return les points de vie maximum
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Permet de définir les points de vie maximum
     *
     * @param maxHealth Points de vie maximum que l'on veut définir
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Permet de récupérer les points de force
     *
     * @return les points de forces
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Permet de définir les points de force
     *
     * @param strength Points de force que l'on veut définir
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Permet de récupérer les points d'intelligence
     *
     * @return les points d'intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Permet de définir les points d'intelligence
     *
     * @param intelligence Points d'intelligence que l'on veut définir
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * Permet de récupérer les points d'agilité
     *
     * @return les points d'agilité
     */
    public int getAgility() {
        return agility;
    }

    /**
     * Permet de définir les points d'agilité
     *
     * @param agility Points d'agilité que l'on veut définir
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * Permet de récupérer les points de défense
     *
     * @return les points de défense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Permet de définir les points de défense
     *
     * @param defense Points de défense que l'on veut définir
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Permet de récupérer le pourcentage de dégâts critiques
     *
     * @return le pourcentage de dégâts critiques
     */
    public double getCriticalDamage() {
        return criticalDamage;
    }

    /**
     * Permet de définir le pourcentage de dégâts critiques
     *
     * @param criticalDamage Pourcentage de dégâts critiques que l'on veut définir
     */
    public void setCriticalDamage(double criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    /**
     * Permet de récupérer le pourcentage d'esquive
     *
     * @return le pourcentage d'esquive
     */
    public int getFumble() {
        return fumble;
    }

    /**
     * Permet de définir le pourcentage d'esquive
     *
     * @param fumble Pourcentage d'esquive que l'on veut définir
     */
    public void setFumble(int fumble) {
        this.fumble = fumble;
    }

    /**
     * Permet de récupérer les points de chance
     *
     * @return les points de chance
     */
    public int getChance() {
        return chance;
    }

    /**
     * Permet de définir les points de chance
     *
     * @param chance Points de chance que l'on veut définir
     */
    public void setChance(int chance) {
        this.chance = chance;
    }

    /**
     * Permet de récupérer les points de chance maximum
     *
     * @return les points de chance maximum
     */
    public int getMaxChance() {
        return maxChance;
    }

    /**
     * Permet de définir les points de chance maximum
     *
     * @param maxChance Points de chance maximum que l'on veut définir
     */
    public void setMaxChance(int maxChance) {
        this.maxChance = maxChance;
    }

    /**
     * Permet de récupérer les points d'initiative
     *
     * @return les points d'initiative
     */
    public int getInitiative() {
        return initiative;
    }

    /**
     * Permet de définir les points d'initiative
     *
     * @param initiative Points d'initiative que l'on veut définir
     */
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    /**
     * Cette méthode vérifie si l'objet courant est égal à un autre objet.
     *
     * @param o L'objet à comparer avec l'objet courant.
     * @return {@code true} si les objets sont égaux, {@code false} sinon.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Entity entity = (Entity)o;
        return health == entity.health && maxHealth == entity.maxHealth && strength == entity.strength && intelligence == entity.intelligence && agility == entity.agility && defense == entity.defense && Double.compare(criticalDamage, entity.criticalDamage) == 0 && fumble == entity.fumble && chance == entity.chance && maxChance == entity.maxChance && initiative == entity.initiative && Objects.equals(name, entity.name);
    }

    /**
     * Cette méthode génère un code de hachage pour l'objet en utilisant certains de ses champs.
     *
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, health, maxHealth, strength, intelligence, agility, defense, criticalDamage, fumble, chance, maxChance, initiative);
    }
}
