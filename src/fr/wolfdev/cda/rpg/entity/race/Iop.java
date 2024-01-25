package fr.wolfdev.cda.rpg.entity.race;

public class Iop extends Race {
    public Iop(String name) {
        super(name);
    }

    public void strengthBuff() {
        //La force du Iop augmente de 20%
        this.strength = (int)(this.strength + (this.strength * (20.0D / 100.0D)));
        System.out.println("Votre force a été augmentée de 20 %.");
        System.out.println("Votre force est maintenant de " + this.strength + ".");
    }
}
