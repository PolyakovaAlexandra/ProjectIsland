package com.javarush.island.polyakova.creature.plants;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;

public class Grass extends Plant{
    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public CreatureType getType() {
        return CreatureType.PLANT;
    }
}
