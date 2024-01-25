package fr.wolfdev.cda.rpg.gameplay;

import java.util.Scanner;

/**
 * Class qui permet à l'utilisateur de choisir le niveau de difficulté
 */
public class DifficultyLevel {
    // ATTRIIBUTS
    /**
     * Difficulté choisie par le joueur
     */
    protected String choicePlayerDifficulty;

    /**
     * Niveau de difficulté du jeu
     */
    protected int lvlDifficultyGame;

    // CONSTRUCTEURS

    public DifficultyLevel() {
        this.lvlDifficultyGame = 0;
        this.choicePlayerDifficulty = " ";
    }

    // METHODES
    public String getChoicePlayerDifficulty() {
        return choicePlayerDifficulty;
    }

    public void setChoicePlayerDifficulty(String choicePlayerDifficulty) {
        this.choicePlayerDifficulty = choicePlayerDifficulty;
    }

    public int getLvlDifficultyGame() {
        return lvlDifficultyGame;
    }

    public void setLvlDifficultyGame(int lvlDifficultyGame) {
        this.lvlDifficultyGame = lvlDifficultyGame;
    }

    public void choiceLvlByUser(Scanner sc) {
        // Je m'assure que l'utilisateur rentre la bonne valeur, sinon je boucle jusqu'à ce qu'elle soit correct
        do {
            choicePlayerDifficulty = sc.next();

            // Je compare le choix de l'utilisateur afin d'obtenir le niveau du jeu
            switch(choicePlayerDifficulty) {
                case "Ballade" -> lvlDifficultyGame = 1;
                case "Normal" -> lvlDifficultyGame = 2;
                case "Cauchemardesque" -> lvlDifficultyGame = 3;
                default -> {
                    System.out.println("Saisie invalide, veuillez rentrer un niveau de difficulté existant Ballade / Normal / Cauchemardesque");
                    System.out.print("--> ");
                }
            }
        }
        while(!choicePlayerDifficulty.equals("Ballade") && !choicePlayerDifficulty.equals("Normal") && !choicePlayerDifficulty.equals("Cauchemardesque"));

        System.out.println("Vous venez de choisir le niveau de difficulté : " + choicePlayerDifficulty + ".");
    }
}
