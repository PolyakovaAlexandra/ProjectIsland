package com.javarush.island.polyakova.creature;

import com.javarush.island.polyakova.creature.animals.herbivores.*;
import com.javarush.island.polyakova.creature.animals.predators.*;
import com.javarush.island.polyakova.creature.plants.Grass;

public class CreateCreature {
    public IslandCreatureType createAnimal(int x, int y, CreatureType creatureType) {

        return mapAnimalOnIsland(x, y, creatureType);
    }

    public IslandCreatureType mapAnimalOnIsland(int x, int y, CreatureType creatureType) {
        return switch (creatureType) {
            case WOLF -> new Wolf(x, y);
            case SNAKE -> new Snake(x, y);
            case FOX -> new Fox(x, y);
            case BEAR -> new Bear(x, y);
            case EAGLE -> new Eagle(x, y);
            case HORSE -> new Horse(x, y);
            case DEER -> new Deer(x, y);
            case RABBIT -> new Rabbit(x, y);
            case MOUSE -> new Mouse(x, y);
            case GOAT -> new Goat(x, y);
            case SHEEP -> new Sheep(x, y);
            case BOAR -> new Boar(x, y);
            case BUFFALO -> new Buffalo(x, y);
            case DUCK -> new Duck(x, y);
            case CATERPILLAR -> new Caterpillar(x, y);
            case PLANT -> new Grass(x, y);
            default -> throw new IllegalArgumentException("Unknown entity type");
        };
    }
}
