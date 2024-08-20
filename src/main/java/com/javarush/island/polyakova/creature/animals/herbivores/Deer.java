package com.javarush.island.polyakova.creature.animals.herbivores;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Deer extends Herbivores {
    public Deer(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.PLANT,100);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.DEER;

    }
}
