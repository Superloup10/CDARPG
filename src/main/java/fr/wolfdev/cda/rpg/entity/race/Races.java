package fr.wolfdev.cda.rpg.entity.race;

import java.util.List;

/**
 * La classe <code>Races</code> est une classe utilitaire qui contient des constantes représentant différentes races.
 * Elle fournit également des méthodes pour enregistrer les races et obtenir la liste des races disponibles.
 *
 * @author Damien ANDRÉ
 */
public final class Races {
    private static List<Race> races;
    public static final Race NONE = new Race("None") {};
    public static final Race CRA = new Cra("Crâ");
    public static final Race IOP = new Iop("Iop");
    public static final Race ECAFLIP = new Ecaflip("Ecaflip");
    public static final Race ENIRIPSA = new Eniripsa("Eniripsa");
    public static final Race FECA = new Feca("Féca");
    public static final Race XELOR = new Xelor("Xelor");
    public static final Race SRAM = new Sram("Sram");
    public static final Race OSAMODAS = new Osamodas("Osamodas");
    public static final Race SADIDA = new Sadida("Sadida");
    public static final Race SACRIEUR = new Sacrieur("Sacrieur");

    private Races() {}

    /**
     * Cette méthode enregistre les différentes races disponibles dans une liste.
     */
    public static void registerRaces() {
        races = List.of(CRA, IOP, ECAFLIP, ENIRIPSA, FECA, XELOR, SRAM, OSAMODAS, SADIDA, SACRIEUR);
    }

    /**
     * Permet d'obtenir la liste des races disponibles.
     *
     * @return La liste des races disponibles.
     */
    public static List<Race> getRaces() {
        return races;
    }
}
