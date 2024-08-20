package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Boar extends Predators {
    public Boar(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 50);
        this.getEdibleSpecies().put(CreatureType.CATERPILLAR, 90);
        this.getEdibleSpecies().put(CreatureType.PLANT, 100);

    }

    @Override
    public CreatureType getType() {
        return CreatureType.BOAR;
    }
}
