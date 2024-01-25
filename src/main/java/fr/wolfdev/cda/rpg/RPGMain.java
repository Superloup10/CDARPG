package fr.wolfdev.cda.rpg;

import fr.wolfdev.cda.rpg.entity.mob.Mob;
import fr.wolfdev.cda.rpg.entity.mob.Mobs;
import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Race;
import fr.wolfdev.cda.rpg.entity.race.Races;
import fr.wolfdev.cda.rpg.gameplay.DifficultyLevel;
import fr.wolfdev.cda.rpg.gameplay.RestRoom;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Classe principale du programme !
 *
 * @author Damien ANDRÉ, Léa BESSE, Gabin BUIGNET, Maxime PETIT, Benjamin D
 */
public class RPGMain {
    /**
     * Constante permettant de définir le nombre de mobs que l'on peut rencontrer lors d'une partie
     */
    public static final int MAX_MOB_ENCOUNTER = 10;

    /**
     * Variable statique correspondant au joueur
     */
    private static Player player;

    /**
     * Variable statique correspondant à la salle de repos
     */
    private static RestRoom restRoom;

    /**
     * Méthode principale de Java
     *
     * @param args Tableau d'arguments du programme
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            start(sc);
            while(player.getMobEncounterCount() < MAX_MOB_ENCOUNTER && !player.isDead()) {
                loop(sc);
            }
            if(player.getMobEncounterCount() >= MAX_MOB_ENCOUNTER) {
                endGame(sc);
            }
        }
        while(player.isDead());
        stop();
    }

    /**
     * Méthode de démarrage du jeu
     *
     * @param sc Scanner
     */
    private static void start(Scanner sc) {
        player = new Player();
        restRoom = new RestRoom(player);
        System.out.println("Bonjour jeune aventurier, êtes-vous bien certain de vouloir entrer dans ce donjon ?");
        System.out.println("Des dangers vous attendent, " + MAX_MOB_ENCOUNTER + " monstres vont se dresser contre vous, vous allez devoir les battre un par un.");
        System.out.println("Si vous êtes toujours partant (et prêts à mourir) ...");
        nameChoice(sc, player);
        raceChoice(sc, player);
        colorChoice(sc, player);
        System.out.println(player);
        difficultyChoice(sc);
    }

    /**
     * Permet de choisir le nom du joueur
     *
     * @param sc     Scanner
     * @param player Le joueur dont on veut attribuer le nom
     */
    private static void nameChoice(Scanner sc, Player player) {
        try {
            String choix;
            do {
                System.out.println("Voulez-vous choisir un nom ? (oui/non)");
                choix = sc.next().trim().toLowerCase();
                switch(choix) {
                    case "oui" -> {
                        String name;
                        do {
                            System.out.println("Entrez votre nom : ");
                            name = sc.next().trim();

                            if(name.isEmpty()) {
                                System.out.println("Nom incorrect. Veuillez saisir à nouveau.");
                            }
                            else {
                                player.setName(name);
                            }
                        }
                        while(name.isEmpty());
                        System.out.println("Votre nom : " + player.getName());
                    }
                    case "non" -> System.out.println("Votre nom : " + player.getName());
                    case "exit" ->  // TODO : Remove
                            System.exit(0);
                    default -> System.out.println("Réponse invalide. Veuillez répondre 'oui' ou 'non'.");
                }
            }
            while(!choix.equals("oui") && !choix.equals("non"));
        }
        catch(Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            sc.nextLine();
        }
    }

    /**
     * Permet de choisir la race du joueur
     *
     * @param sc     Scanner
     * @param player Le joueur dont on veut attribuer la race
     */
    private static void raceChoice(Scanner sc, Player player) {
        boolean errorRaceChoice;
        Races.registerRaces();
        List<Race> races = Races.getRaces();
        do {
            System.out.println("Veuillez choisir votre race parmi celles-ci : ");
            for(int i = 0; i < races.size(); i++) {
                System.out.println("- [" + (i + 1) + "] " + races.get(i).getName());
            }
            try {
                int raceChoice = sc.nextInt();
                errorRaceChoice = raceChoice < 1 || raceChoice > races.size();
                if(errorRaceChoice) {
                    System.err.println("Merci de bien vouloir choisir une race entre 1 et " + races.size() + " !");
                }
                else {
                    player.setRace(races.get(raceChoice - 1));
                }
            }
            catch(InputMismatchException e) {
                System.err.println("Merci de bien vouloir choisir une race entre 1 et " + races.size() + " !");
                sc.nextLine();
                errorRaceChoice = true;
            }
        }
        while(errorRaceChoice);
    }

    /**
     * Permet de choisir la couleur du joueur
     *
     * @param sc     Scanner
     * @param player Le joueur dont on veut attribuer la couleur
     */
    private static void colorChoice(Scanner sc, Player player) {
        try {
            String color;
            String choice;
            do {
                System.out.println("Voulez-vous choisir une couleur ? (oui/non)");
                choice = sc.next().trim().toLowerCase();
                if(choice.equals("oui")) {
                    do {
                        System.out.println("Quelle est votre couleur préféré ?");
                        color = sc.next();
                        System.out.println("Votre couleur préféré est : " + color);
                        if(color.isEmpty()) {
                            System.out.println("couleur incorrect. Veuillez saisir à nouveau.");
                        }
                        player.setColor(color);
                    }
                    while(color.isEmpty());
                }
                else if(choice.equals("non")) {
                    System.out.println("Votre couleur : " + player.getColor());
                }
                else {
                    System.out.println("Réponse invalide. Veuillez répondre 'oui' ou 'non'.");
                }
            }
            while(!choice.equals("oui") && !choice.equals("non"));
        }
        catch(Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }

    /**
     * Permet de choisir le niveau de difficulté du jeu.
     *
     * @param sc Scanner
     */
    private static void difficultyChoice(Scanner sc) {
        System.out.println("----------------------------------------");
        System.out.println("Veuillez choisir un niveau de difficulté");
        System.out.println("----------------------------------------");
        System.out.println("--> Ballade");
        System.out.println("--> Normal");
        System.out.println("--> Cauchemardesque");
        System.out.print("--> ");

        DifficultyLevel difficultyLevel = new DifficultyLevel();
        difficultyLevel.choiceLvlByUser(sc);
        Mobs.registerMobs(player.getLevel(), difficultyLevel.getLvlDifficultyGame());
    }

    /**
     * Boucle principale du jeu
     *
     * @param sc Scanner
     */
    private static void loop(Scanner sc) {
        restRoom.choice(sc);
        mobEncounter(sc);
    }

    /**
     * Permet de rencontrer un mob aléatoirement et de décider si on l'affronte ou non
     *
     * @param sc Scanner
     */
    private static void mobEncounter(Scanner sc) {
        boolean errorUserChoice;
        Random rd = new Random();
        List<Mob> mobs = Mobs.getMobs().stream().filter(mob1 -> mob1.getHealth() != 0).toList();
        Mob mob = mobs.get(rd.nextInt(mobs.size()));
        boolean combatFinished = false;
        System.out.println("Vous venez de rencontrer " + mob.getName() + " !");
        do {
            System.out.println(getStatus(mob));
            System.out.println("Que voulez-vous faire (combattre / fuir) ?");
            String userChoice = sc.next();
            switch(userChoice) {
                case "combattre": {
                    errorUserChoice = false;
                    System.out.println("Vous avez décidé de combattre !");
                    combatFinished = mob.attackTo(sc, player);
                }
                break;
                case "fuir": {
                    errorUserChoice = false;
                    System.out.println("Vous avez décidé de fuir !");
                    System.out.println("Maintenant, est-ce que vous allez réussir à fuir ?");
                    player.setMobEncounterCount(player.getMobEncounterCount() + 1);
                    if(player.getRace().getChance() >= mob.getChance()) {
                        System.out.println("Vous avez bien réussi à fuir !");
                        player.getRace().setChance(player.getRace().getChance() - 1);
                        combatFinished = true;
                    }
                    else {
                        System.out.println("Vous n'avez pas réussi à fuir !");
                        combatFinished = mob.attackTo(sc, player);
                    }
                    break;
                }
                case null, default: {
                    errorUserChoice = true;
                    System.err.println("Merci de fournir une réponse valide !");
                    break;
                }
            }
        }
        while(errorUserChoice || !combatFinished);
    }

    /**
     * Permet d'afficher en même temps les caractéristiques d'un mob et du joueur.
     *
     * @param mob Le mob dont on veut les caractéristiques
     * @return Une chaîne de caractère
     */
    private static String getStatus(Mob mob) {
        final Race race = player.getRace();
        return new StringJoiner("\n", "", "")
                .add("Caractéristiques des combattants : ")
                .add("Nom : " + mob.getName() + " | " + player.getName())
                .add("Vie : " + mob.getHealth() + "/" + mob.getMaxHealth() + " | " + race.getHealth() + "/" + race.getMaxHealth())
                .add("Force : " + mob.getStrength() + " | " + race.getStrength())
                .add("Intelligence : " + mob.getIntelligence() + " | " + race.getIntelligence())
                .add("Agilité : " + mob.getAgility() + " | " + race.getAgility())
                .add("Défense : " + mob.getDefense() + " | " + race.getDefense())
                .add("Pourcentage de dégâts critiques : " + mob.getCriticalDamage() + " | " + race.getCriticalDamage())
                .add("Pourcentage d'esquive : " + mob.getFumble() + " | " + race.getFumble())
                .add("Chance : " + mob.getChance() + " | " + race.getChance())
                .add("Initiative : " + mob.getInitiative() + " | " + race.getInitiative())
                .toString();
    }

    /**
     * Méthode pour gérer la fin du jeu ou le New Game+
     *
     * @param sc Scanner
     */
    private static void endGame(Scanner sc) {
        System.out.println("Vous avez atteint le nombre maximal de rencontres avec les ennemis !");
        System.out.println("Voulez-vous démarrer un New Game+ ? (oui/non)");
        String choice = sc.next().trim().toLowerCase();
        if(choice.equals("oui")) {
            // Redémarrage du jeu
            System.out.println("Nouvelle partie lancée !");
        }
        else if(choice.equals("non")) {
            // Afficher un résumé des statistiques du joueur
            System.out.println("Résumé de vos statistiques : ");
            System.out.println(player);
            System.out.println("Merci d'avoir joué !");
        }
        else {
            System.out.println("Réponse invalide. Veuillez répondre 'oui' ou 'non'.");
            endGame(sc); // Demander à nouveau si le choix n'est pas valide
        }
    }

    /**
     * Méthode d'arrêt du jeu (Non implémentée)
     */
    private static void stop() {}
}
