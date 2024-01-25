package fr.wolfdev.cda.rpg.entity.mob;

import java.util.ArrayList;
import java.util.List;

public final class Mobs {
    private static final int MAX_MOBS = 15;
    private static List<Mob> mobs = new ArrayList<>();

    private Mobs() {}

    /**
     * Cette méthode permet d'enregistrer les mobs en fonction du niveau de difficulté et du niveau du joueur
     *
     * @param playerLevel       Niveau du joueur
     * @param lvlDifficultyGame Niveau de difficulté
     */
    public static void registerMobs(int playerLevel, int lvlDifficultyGame) {
        for(int i = 0; i < MAX_MOBS; i++) {
            mobs.add(new Mob(playerLevel, lvlDifficultyGame));
        }
    }

    /**
     * Permet d'obtenir la liste des mobs
     *
     * @return la liste des mobs
     */
    public static List<Mob> getMobs() {
        return mobs;
    }
}
