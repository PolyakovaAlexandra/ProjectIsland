package com.javarush.island.polyakova.creature.animals.herbivores;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;


public class Horse extends Animals {
    public Horse(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(CreatureType.PLANT, 100);
    }
    @Override
    public CreatureType getType(){return CreatureType.HORSE;}
}
