package com.javarush.island.polyakova.services;

import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Eat {
    private final LiveCheck liveCheck = new LiveCheck();
    private final Remove remover = new Remove(liveCheck);

    public void eat(List<IslandCreatureType> creatureTypes, Animals animals) {

        if (!liveCheck.liveCheck(animals)) {
            animals.die();
            return;
        }

        if (animals.getSaturation() < 0) {
            animals.die();
            return;
        }
        for (IslandCreatureType lunch : creatureTypes) {

            double lunchWeight = lunch.getType().getWeight();
            if (animals != lunch && animals.getSaturation() < animals.getType().getFullSaturation()) {

                if (tryToEat(animals, lunch)) {
                    animals.setSaturation(Math.min(animals.getSaturation()
                            + lunchWeight, animals.getType().getFullSaturation()));
                    creatureTypes.remove(lunch);
                }
            }
        }
    }
    public boolean tryToEat(Animals eating, IslandCreatureType lunch) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        if (eating.getEdibleSpecies().containsKey(lunch.getType())) {
            return chance >= eating.getEdibleSpecies().get(lunch.getType());
        } else {
            return false;
        }
    }
}
