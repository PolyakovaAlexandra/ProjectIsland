package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Fox extends Animals {
    public Fox(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.RABBIT, 70);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 90);
        this.getEdibleSpecies().put(CreatureType.CATERPILLAR, 40);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.FOX;
    }
}
