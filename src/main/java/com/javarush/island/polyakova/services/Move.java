package com.javarush.island.polyakova.services;

import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;
import com.javarush.island.polyakova.island.IslandSize;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Move {
    private final LiveCheck liveCheck = new LiveCheck();
    private final Remove remover = new Remove(liveCheck);

    public void move(Animals animals) {
        if (!liveCheck.liveCheck(animals)) {
            animals.die();
            return;
        }

        if (animals.getSaturation() < 0) {
            animals.die();
            return;
        }

        int steps = animals.getType().getMaxMove();
        int newX = animals.getX();
        int newY = animals.getY();

        while (steps-- > 0) {
            int direction = ThreadLocalRandom.current().nextInt(4);

            if (direction == 0) {
                newX++; // east
            } else if (direction == 1) {
                newX--; // west
            } else if (direction == 2) {
                newY++; // south
            } else {
                newY--; // north
            }

            if (isValidPosition(newX, newY)) {
                moveCreature(animals, newX, newY);
            }

        }
        animals.setReproduced(false);
        animals.setReproduceTrying(3);
        animals.hunger();
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0
                && x < IslandSize.getInstance().getNumRows()
                && y >= 0
                && y < IslandSize.getInstance().getNumColumns();
    }

    private void moveCreature(Animals islandCreature, int x, int y) {


        int oldIslandEntityX = islandCreature.getX();
        int oldIslandEntityY = islandCreature.getY();

        if (countOfEntityResolver(x, y, islandCreature.getClass()) < islandCreature.getType().getMaxAmount()) {

            IslandSize.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].remove(islandCreature);

            islandCreature.setX(x);
            islandCreature.setY(y);
            IslandSize.getInstance().getGameField()[x][y].add(islandCreature);
        }
    }

    private int countOfEntityResolver(int x, int y, Class<?> targetClass) {
        List<IslandCreatureType> entitiesInCell = IslandSize.getInstance().getGameField()[x][y];
        return (int) entitiesInCell.stream().filter(targetClass::isInstance).count();
    }

    public Remove getRemover() {
        return remover;
    }
}
