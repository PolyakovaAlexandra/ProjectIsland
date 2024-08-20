package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Duck extends Predators {
    public Duck(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.SNAKE, 80);
        this.getEdibleSpecies().put(CreatureType.HORSE, 40);
        this.getEdibleSpecies().put(CreatureType.DEER, 80);
        this.getEdibleSpecies().put(CreatureType.RABBIT, 80);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 90);
        this.getEdibleSpecies().put(CreatureType.GOAT, 70);
        this.getEdibleSpecies().put(CreatureType.SHEEP, 70);
        this.getEdibleSpecies().put(CreatureType.BOAR, 50);
        this.getEdibleSpecies().put(CreatureType.BUFFALO, 20);
        this.getEdibleSpecies().put(CreatureType.DUCK, 10);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.DUCK;
    }
}

