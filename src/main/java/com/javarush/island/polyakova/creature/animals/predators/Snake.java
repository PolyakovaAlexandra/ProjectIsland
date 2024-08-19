package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Snake extends Animals {
    public Snake(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.FOX, 14);
        this.getEdibleSpecies().put(CreatureType.RABBIT, 20);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 40);
        this.getEdibleSpecies().put(CreatureType.DUCK, 10);
        this.getEdibleSpecies().put(CreatureType.WOLF, 10);
        this.getEdibleSpecies().put(CreatureType.EAGLE, 5);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.SNAKE;
    }
}
