package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Eagle extends Predators {
    public Eagle(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.FOX, 10);
        this.getEdibleSpecies().put(CreatureType.RABBIT, 90);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 90);
        this.getEdibleSpecies().put(CreatureType.DUCK, 80);
        this.getEdibleSpecies().put(CreatureType.SNAKE, 30);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.EAGLE;
    }
}
