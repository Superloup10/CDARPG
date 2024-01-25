package fr.wolfdev.cda.rpg.gameplay;

import fr.wolfdev.cda.rpg.entity.player.Player;
import fr.wolfdev.cda.rpg.entity.race.Race;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RestRoom {
    private Player player;

    public RestRoom(Player player) {
        this.player = player;
    }

    public void choice(Scanner sc) {
        if(player != null) {
            if(player.getLevel() == 5) {
                Race pRace = player.getRace();
                boolean errorYesNo;
                System.out.println(player.getName() + " viens d'arriver dans la salle de repos.");
                do {
                    try {
                        System.out.println("Voulez-vous vous arrêtez à la salle de repos ? (oui/non)");
                        String choice = sc.next();
                        errorYesNo = !choice.matches("oui|non");
                        if(errorYesNo) {
                            System.err.println("Veuillez saisir oui ou non !");
                        }
                        else if(choice.matches("oui")) {
                            boolean errorPossibility;
                            do {
                                try {
                                    System.out.println("Bienvenue " + player.getName() + " en salle de repos. Ici, vous avez la possibilité de :" +
                                            "\n[1] Vous soignez intégralement" +
                                            "\n[2] Remontez votre statistique de chance à 100%" +
                                            "\n[3] Gagnez un point de caractéristique");
                                    System.out.println("Que voulez-vous faire ?");
                                    int choicePossibility = sc.nextInt();
                                    errorPossibility = choicePossibility < 1 || choicePossibility > 3;
                                    if(errorPossibility) {
                                        System.err.println("Veuillez saisir un choix entre 1 et 3.");
                                    }
                                    else {
                                        switch(choicePossibility) {
                                            case 1 -> {
                                                System.out.println("Vous avez décidé de remontez vos points de vie au maximum.");
                                                pRace.setHealth(pRace.getMaxHealth());
                                            }
                                            case 2 -> {
                                                System.out.println("Vous avez décidé de remontez votre chance au maximum.");
                                                pRace.setChance(pRace.getMaxChance());
                                            }
                                            case 3 -> {
                                                System.out.println("Vous avez décidé de gagnez un point de caractéristique.");
                                                player.setStatsPoints(player.getStatsPoints() + 1);
                                                player.chooseStats(sc);
                                            }
                                        }
                                    }
                                }
                                catch(InputMismatchException e) {
                                    System.err.println("Veuillez saisir un choix entre 1 et 3.");
                                    sc.nextLine();
                                    errorPossibility = true;
                                }
                            }
                            while(errorPossibility);
                        }
                        else if(choice.matches("non")) {
                            System.out.println(player.getName() + " ne s'est pas arrêter en salle de repos.");
                            return;
                        }
                    }
                    catch(InputMismatchException e) {
                        errorYesNo = true;
                        sc.nextLine();
                    }
                }
                while(errorYesNo);
            }
            else if(player.getLevel() < 5) {
                System.out.println(player.getName() + " n'est pas encore assez haut niveau pour rejoindre la salle de repos.");
            }
        }
        else {
            System.out.println("Aucun joueur trouvé pour l'emmener en salle de repos.");
        }
    }
}
