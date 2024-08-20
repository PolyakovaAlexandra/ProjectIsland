package com.javarush.island.polyakova.services;

import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

import java.util.List;

public class Reproduction {
    private final LiveCheck liveCheck = new LiveCheck();
    private final Remove remover = new Remove(liveCheck);
    private final CreateCreature createCreature;

    public Reproduction(CreateCreature createCreature) {
        this.createCreature = createCreature;
    }

    public void reproduceAllAnimalOnCell(List<IslandCreatureType> creatureTypes, IslandCreatureType creatureType) {

        if (canReproduce(creatureType, creatureTypes))
            reproduceOneAnimal(creatureType, creatureTypes);
    }

    private boolean canReproduce(IslandCreatureType creatureType, List<IslandCreatureType> creatureTypes) {
        if (!(creatureType instanceof Animals)) return false;
        if (((Animals) creatureType).isReproduced()) return false;

        if (!liveCheck.liveCheck((Animals) creatureType)) {
            ((Animals) creatureType).die();
            return false;
        }
        int count = countSameTypeAnimals(creatureType, creatureTypes);
        return count > 1 && count < creatureType.getType().getMaxAmount();
    }

    //todo create one class for all counter
    private int countSameTypeAnimals(IslandCreatureType creatureType, List<IslandCreatureType> creatureTypes) {
        return (int) creatureTypes.stream()
                .filter(e -> e.getType() == creatureType.getType())
                .count();
    }

    private void reproduceOneAnimal(IslandCreatureType creatureType, List<IslandCreatureType> creatureTypes) {

        for (IslandCreatureType islandCreatureType : creatureTypes) {
            if (islandCreatureType.getType() == CreatureType.PLANT) {
                break;
            }
            Animals firstParent = (Animals) creatureType;
            Animals secondParent = (Animals) islandCreatureType;

            if (firstParent != secondParent
                    && firstParent.getType() == secondParent.getType()
                    && firstParent.getGender() != secondParent.getGender()
                    && !(secondParent.isReproduced())
                    && Math.random() > 0.5) {

                Animals newBornEntity =
                        (Animals)createCreature.createAnimal(firstParent.getX(), firstParent.getY(), firstParent.getType());
                newBornEntity.setReproduced(true);
                firstParent.setReproduced(true);
                secondParent.setReproduced(true);
                creatureTypes.add(newBornEntity);
            }
        }
    }
}
