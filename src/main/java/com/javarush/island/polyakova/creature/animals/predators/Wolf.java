package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Wolf extends Animals {
    public Wolf(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.HORSE, 10);
        this.getEdibleSpecies().put(CreatureType.SNAKE, 10);
        this.getEdibleSpecies().put(CreatureType.DEER, 15);
        this.getEdibleSpecies().put(CreatureType.RABBIT, 60);
        this.getEdibleSpecies().put(CreatureType.MOUSE, 80);
        this.getEdibleSpecies().put(CreatureType.GOAT, 60);
        this.getEdibleSpecies().put(CreatureType.SHEEP, 70);
        this.getEdibleSpecies().put(CreatureType.BOAR, 15);
        this.getEdibleSpecies().put(CreatureType.BUFFALO, 10);
        this.getEdibleSpecies().put(CreatureType.DUCK, 40);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.WOLF;
    }
}
