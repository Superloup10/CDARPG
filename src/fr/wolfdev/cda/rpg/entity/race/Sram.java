package fr.wolfdev.cda.rpg.entity.race;

public class Sram extends Race {
    public Sram(String name) {
        super(name);
    }

    public void chanceBuff() {
        //La chance du Sram augmente de 1.
        this.chance += 1;
        if(this.chance > maxChance) {
            maxChance = chance;
        }
        System.out.println("Votre chance a été augmentée de .");
        System.out.println("Votre chance est maintenant de " + this.chance + ".");
    }
}
