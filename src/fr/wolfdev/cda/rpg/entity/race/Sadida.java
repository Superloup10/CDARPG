package fr.wolfdev.cda.rpg.entity.race;

import fr.wolfdev.cda.rpg.entity.mob.Mob;

public class Sadida extends Race {
    public Sadida(String name) {
        super(name);
    }

    public void poison(Mob mob) {
        //Le mob est empoisonné.
        mob.setHealth(mob.getHealth() - 1);
        System.out.println("Vous avez empoisonné votre ennemi.");
        System.out.println("Il subit 1 de dégât à chaque fois qu'il attaque. ");
    }
}
