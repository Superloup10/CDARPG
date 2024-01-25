package fr.wolfdev.cda.rpg.entity.race;

public class Cra extends Race {
    public Cra(String name) {
        super(name);
    }

    public void intelligenceBuff() {
        //L'intelligence du Cra augmente de 20 %
        this.intelligence = (int)(this.intelligence + (this.intelligence * (20.0D / 100.0D)));
        System.out.println("Votre intelligence a été augmentée de 20 %.");
        System.out.println("Votre intelligence est maintenant de " + this.intelligence + ".");
    }

    public void criticalBuff() {
        //Le pourcentage de coup critique du Cra augmente de 5 %
        this.criticalDamage = this.criticalDamage + (this.criticalDamage * (5.0D / 100.0D));
        System.out.println("Votre pourcentage de coup critique a été augmentée de 5 %.");
        System.out.println("Votre pourcentage de coup critique est maintenant de " + this.criticalDamage + ".");
    }
}
