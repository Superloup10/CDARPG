package fr.wolfdev.cda.rpg.entity.player;

import fr.wolfdev.cda.rpg.entity.race.Race;
import fr.wolfdev.cda.rpg.entity.race.Races;
import fr.wolfdev.cda.rpg.entity.race.Xelor;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;

public class Player {
    private List<String> nameList = List.of("Scarfayss le Balafré",
            "Bobo le Balourd",
            "Gertrude Tournicoquil",
            "Rufus Farfadet",
            "Grompf le Gaffeur",
            "Hortense Barbouille",
            "Zigouille la Zozoteuse",
            "Grignoteur de Chaussettes",
            "Bernadette Frimousse",
            "Gluglu le Gribouilleur",
            "Gédéon Patachon"); // La liste contenant le nom qui sera utilisé pour nommer les monstres.

    private List<String> colorList = List.of("Bleu cyan",
            "Bleu ciel",
            "Bleu foncer",
            "Bleu turquoise",
            "Bleu clair",
            "Bleu cobalt",
            "Bleu marine",
            "Bleu roi",
            "Bleu indigo",
            "Bleu nuit",
            "Lilas"); // La liste contenant les couleurs pour la tombe.
    private String name;
    private Race race;
    private String color;
    private int mobEncounterCount;
    private int statsPoints;
    private final Map<String, String> raceStats = new LinkedHashMap<>();
    private int level;

    public Player() {
        Random rd = new Random();
        this.name = nameList.get(rd.nextInt(nameList.size()));
        this.race = Races.NONE;
        this.color = colorList.get(rd.nextInt(colorList.size()));
        this.mobEncounterCount = 0;
        this.level = 1;
    }

    public Player(String name) {
        this(name, Races.NONE, "", 1);
    }

    public Player(String name, Race race, String color, int level) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.level = level;
        this.mobEncounterCount = 0;
    }

    /**
     * Permet de déterminer si le joueur est mort ou non.
     *
     * @return un booléen
     */
    public boolean isDead() {
        if(this.race.equals(Races.XELOR) && this.race.getHealth() == 0) {
            if(!((Xelor)this.race).isAlreadyResurrected) {
                System.out.println("Vous êtes en vie et vous avez 5HP ! Vous venez d'utiliser le passif du Xelor : Resurrection");
                this.race.setHealth(5);
                ((Xelor)this.race).isAlreadyResurrected = true;
            }
        }
        else if(this.race.equals(Races.XELOR) && this.race.getHealth() != 0) {
            ((Xelor)this.race).isAlreadyResurrected = false;
        }
        return race.getHealth() == 0;
    }

    public void chooseStats(Scanner sc) { //Methode permettant de monter les caractéristiques du joueur
        boolean inputError;
        if(statsPoints > 0) {
            do {
                try {
                    do {
                        System.out.println("Vous avez " + this.statsPoints + " point(s) de caractéristique. Voulez-vous les attribuez maintenant ? (oui/non)");
                        String choiceYesOrNo = sc.next(); //Création de la demande au joueur pour savoir s'il veut attribuer ses points de competence ou non
                        inputError = !choiceYesOrNo.matches("oui|non"); //Redéfinition de cette variable stockant le boolean qui permet de boucler si la réponse ne match pas oui ou non
                        if(inputError) {
                            System.err.println("Merci de bien vouloir répondre oui ou non. Veuillez réessayer.");
                            sc.nextLine();
                        }
                        else if(Objects.equals(choiceYesOrNo, "oui")) { //Si le joueur choisi oui alors ce code sera éxécuté.
                            boolean errorChoiceStats;
                            reWriteStatsRace(); //Rafraichissement de la liste contenant les statistiques du joueur.
                            System.out.println("Vous avez " + this.statsPoints + " point(s) de caractéristique(s) à attribuer. Quelle caractéristique voulez-vous augmentez?");
                            String[] Keysarray = this.raceStats.keySet().toArray(new String[0]); //Stockage des clés de la list dans un tableau
                            for(int i = 0; i < Keysarray.length; i++) {
                                System.out.println("[" + (i + 1) + "] " + Keysarray[i] + " : " + this.raceStats.get(Keysarray[i])); //Affichage des statistiques du joueur avec un indice.
                            }
                            do {
                                try {
                                    int choice = sc.nextInt(); //Choix de la stats à augmenter.
                                    errorChoiceStats = choice < 1 || choice > this.raceStats.size();
                                    if(errorChoiceStats) {
                                        System.err.println("Merci de bien vouloir choisir une caractéristique entre 1 et " + this.raceStats.size() + " !");
                                    }
                                    else {
                                        boolean errorStatsPoint = false;
                                        do {
                                            if(this.statsPoints > 1) { //Condition qui vérifie si les points du joueur sont supérieurs à 1.
                                                try {
                                                    System.out.println("Combien de point voulez-vous attribué ? (Total : " + statsPoints + " )");
                                                    int pointAttributed = sc.nextInt();//J'effectue la demande de combien de point le joueur veut attribuer à la statistique choisie
                                                    errorStatsPoint = pointAttributed < 1 || pointAttributed > this.statsPoints;
                                                    if(errorStatsPoint) {
                                                        System.err.println("Vous ne pouvez pas attribuer un nombre inférieur à 1 ou un nombre supérieur à vos points de stats.");
                                                    }
                                                    else {
                                                        statsToAugment(choice, pointAttributed);//Appel de la methode qui permet d'effectuer l'augmentation de la statistique choisie via l'indice de la statistique choisi et les points attribués
                                                        this.statsPoints -= pointAttributed; //Je retire les points attribués à l'attribut statsPoints
                                                    }
                                                }
                                                catch(InputMismatchException e) {
                                                    System.err.println("Veuillez saisir un nombre valide entre 1 et " + this.statsPoints);
                                                    sc.next();
                                                    errorStatsPoint = true;
                                                }
                                            }
                                            else { //Si le joueur n'a que 1 point, alors je viens augmenter directement la statistique choisie.
                                                statsToAugment(choice, this.statsPoints);
                                                this.statsPoints = 0;
                                            }
                                        }
                                        while(errorStatsPoint);
                                    }
                                }
                                catch(InputMismatchException e) {
                                    System.err.println("Merci de bien vouloir choisir une caractéristique entre 1 et " + this.raceStats.size() + " !");
                                    sc.nextLine();
                                    errorChoiceStats = true;
                                }
                            }
                            while(errorChoiceStats);
                        }
                        else if(Objects.equals(choiceYesOrNo, "non")) { //Si le joueur répond non, alors on retourne le code pour quitter l'augmentation de statistique
                            return;
                        }
                    }
                    while(this.statsPoints != 0); //Tant que les points ne sont pas égale à 0 alors le code boucle.
                }
                catch(InputMismatchException e) {
                    inputError = true;
                    sc.nextLine();
                }
            }
            while(inputError);
        }
        else {
            System.out.println("Aucun point de caractéristique disponible.");
            sc.close();
        }
    }

    private void statsToAugment(int statName, int pointToAttribute) {
        switch(statName) {
            case 1 -> {
                this.race.setMaxHealth(this.race.getMaxHealth() + pointToAttribute); //Je viens re-définir la statistique du joueur pour lui ajouter autant de point que le joueur a demandé
                System.out.println("Vous avez augmenter votre MAXIMUM de point de vie de " + pointToAttribute + " !");
            }
            case 2 -> {
                this.race.setStrength(this.race.getStrength() + pointToAttribute);
                System.out.println("Vous avez augmenter votre force de " + pointToAttribute + " !");
            }
            case 3 -> {
                this.race.setIntelligence(this.race.getIntelligence() + pointToAttribute);
                System.out.println("Vous avez augmenter votre intelligence de " + pointToAttribute + " !");
            }
            case 4 -> {
                this.race.setAgility(this.race.getAgility() + pointToAttribute);
                System.out.println("Vous avez augmenter votre agilité de " + pointToAttribute + " !");
            }
            case 5 -> {
                this.race.setDefense(this.race.getDefense() + pointToAttribute);
                System.out.println("Vous avez augmenter votre defense de " + pointToAttribute + " !");
            }
            case 6 -> {
                this.race.setCriticalDamage(this.race.getCriticalDamage() + ((double)pointToAttribute / 10));
                System.out.println("Vous avez augmenter vos dégâts critique de " + pointToAttribute + " !");
            }
            case 7 -> {
                this.race.setFumble(this.race.getFumble() + pointToAttribute);
                System.out.println("Vous avez augmenter votre esquive de " + pointToAttribute + " !");
            }
            case 8 -> {
                this.race.setMaxChance(this.race.getMaxChance() + pointToAttribute);
                System.out.println("Vous avez augmenter votre MAXIMUM de chance de " + pointToAttribute + " !");
            }
            case 9 -> {
                this.race.setInitiative(this.race.getInitiative() + pointToAttribute);
                System.out.println("Vous avez augmenter votre initiative de " + pointToAttribute + " !");
            }
        }
        reWriteStatsRace();
    }

    private void writeStatsRace() {
        this.raceStats.put("Vie/Max Vie", this.getRace().getHealth() + "/" + this.getRace().getMaxHealth());
        this.raceStats.put("Force", String.valueOf(this.getRace().getStrength()));
        this.raceStats.put("Intelligence", String.valueOf(this.getRace().getIntelligence()));
        this.raceStats.put("Agilité", String.valueOf(this.getRace().getAgility()));
        this.raceStats.put("Défense", String.valueOf(this.getRace().getDefense()));
        this.raceStats.put("Dégat Critique", String.valueOf(this.getRace().getCriticalDamage()));
        this.raceStats.put("Esquive", String.valueOf(this.getRace().getFumble()));
        this.raceStats.put("Chance/Max Chance", this.getRace().getChance() + "/" + this.getRace().getMaxChance());
        this.raceStats.put("Initiative", String.valueOf(this.getRace().getInitiative()));
    }

    private void reWriteStatsRace() {
        this.raceStats.replace("Vie/Max Vie", this.getRace().getHealth() + "/" + this.getRace().getMaxHealth());
        this.raceStats.replace("Force", String.valueOf(this.getRace().getStrength()));
        this.raceStats.replace("Intelligence", String.valueOf(this.getRace().getIntelligence()));
        this.raceStats.replace("Agilité", String.valueOf(this.getRace().getAgility()));
        this.raceStats.replace("Défense", String.valueOf(this.getRace().getDefense()));
        this.raceStats.replace("Dégat Critique", String.valueOf(this.getRace().getCriticalDamage()));
        this.raceStats.replace("Esquive", String.valueOf(this.getRace().getFumble()));
        this.raceStats.replace("Chance/Max Chance", this.getRace().getChance() + "/" + this.getRace().getMaxChance());
        this.raceStats.replace("Initiative", String.valueOf(this.getRace().getInitiative()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
        race.initialize();
        writeStatsRace();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMobEncounterCount() {
        return mobEncounterCount;
    }

    public void setMobEncounterCount(int mobEncounterCount) {
        this.mobEncounterCount = mobEncounterCount;
    }

    public int getStatsPoints() {
        return statsPoints;
    }

    public void setStatsPoints(int statsPoints) {
        this.statsPoints = statsPoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
                .add("name=" + name)
                .add("race=" + race)
                .add("color=" + color)
                .add("level=" + level)
                .toString();
    }
}
