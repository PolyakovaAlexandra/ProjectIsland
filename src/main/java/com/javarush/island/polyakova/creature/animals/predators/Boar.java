package com.javarush.island.polyakova.creature.animals.predators;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

public class Boar extends Animals {
    public Boar(int x, int y) {
        super(x, y);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.BOAR;
    }
}
