package fr.wolfdev.cda.rpg.entity.race;

import java.util.Random;

public class Ecaflip extends Race {
    public Ecaflip(String name) {
        super(name);
    }

    public void randomBuff() {
        //L'Ecaflip se buff, au hasard parmis une selection de buff

        Random rd = new Random();
        int randomNumber = rd.nextInt(6);

        switch(randomNumber) {
            case 0 -> {
                this.defense = (int)(this.defense + (this.defense * (20.0D / 100.0D)));
                System.out.println("Votre défense a été augmentée de 20 %.");
                System.out.println("Votre défense est maintenant de " + this.defense + ".");
            }
            case 1 -> {
                this.strength = (int)(this.strength + (this.strength * (20.0D / 100.0D)));
                System.out.println("Votre force a été augmentée de 20 %.");
                System.out.println("Votre force est maintenant de " + this.strength + ".");
            }
            case 2 -> {
                this.intelligence = (int)(this.intelligence + (this.intelligence * (20.0D / 100.0D)));
                System.out.println("Votre intelligence a été augmentée de 20 %.");
                System.out.println("Votre intelligence est maintenant de " + this.intelligence + ".");
            }
            case 3 -> {
                this.criticalDamage = this.criticalDamage + (this.criticalDamage * (5.0D / 100.0D));
                System.out.println("Votre pourcentage de coup critique a été augmentée de 5 %.");
                System.out.println("Votre pourcentage de coup critique est maintenant de " + this.criticalDamage + ".");
            }
            default -> {
                System.out.println("Vous attendez votre buff de puissance ! ...");
                System.out.println("...");
                System.out.println("Rien ne se passe.");
            }
        }
    }
}
