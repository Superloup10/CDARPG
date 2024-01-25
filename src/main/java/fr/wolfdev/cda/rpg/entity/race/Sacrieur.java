package fr.wolfdev.cda.rpg.entity.race;

import fr.wolfdev.cda.rpg.entity.mob.Mob;

public class Sacrieur extends Race {
    public Sacrieur(String name) {
        super(name);
    }

    public void lifeSteal(Mob mob) {
        // Le sacrieur vol de la vie au monstre
        //Le sacrieur gagne de la vie égale à son attaque
        int stollenLife = this.strength;
        if(stollenLife > this.maxHealth) {
            this.health = this.maxHealth;
        }
        else {
            this.health += stollenLife;
        }

        //Le monstre perd de la vie égale à l'attaque du sacrieur
        mob.setHealth(mob.getHealth() - this.strength);

        System.out.println("Vous avez volé " + stollenLife + ".");
    }
}
