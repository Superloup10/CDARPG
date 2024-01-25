package fr.wolfdev.cda.rpg.entity.race;

public class Eniripsa extends Race {
    public Eniripsa(String name) {
        super(name);
    }

    public void healing() {
        //Le Eniripsa se heal de 50 % de ses hp max
        int newHealth = (int)(this.health + (this.maxHealth * (50.0D / 100.0D)));
        this.health = Math.min(newHealth, this.maxHealth);
        System.out.println("Vous venez de vous soigner.");
        System.out.println("Votre vie est maintenant de " + this.health + ".");
    }
}
