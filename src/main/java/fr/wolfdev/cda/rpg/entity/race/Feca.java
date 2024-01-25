package fr.wolfdev.cda.rpg.entity.race;

public class Feca extends Race {
    public Feca(String name) {
        super(name);
    }

    public void defenseBuff() {
        //La défense du Feca augmente de 20%
        this.defense = (int)(this.defense + (this.defense * (20.0D / 100.0D)));
        System.out.println("Votre défense a été augmentée de 20 %.");
        System.out.println("Votre défnde est maintenant de " + this.defense + ".");
    }
}
