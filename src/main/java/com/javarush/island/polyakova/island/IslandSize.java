package com.javarush.island.polyakova.island;

import com.javarush.island.polyakova.Initializer;
import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IslandSize {
    public static final int sizeOfFieldX = Initializer.initSizeOfFieldX();
    public static final int sizeOfFieldY = Initializer.initSizeOfFieldX();
    private static final IslandSize instance = new IslandSize(sizeOfFieldX,sizeOfFieldY, new CreateCreature());
    private final CreateCreature createCreature;
    private final List<IslandCreatureType>[][] gameField;
    private final int numRows;
    private final int numColumns;

    @SuppressWarnings("unchecked")
    private IslandSize(int x, int y , CreateCreature createCreature) {
        numRows = y;
        numColumns = x;
        gameField = new CopyOnWriteArrayList[numRows][numColumns];
        this.createCreature = createCreature;
        createField();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public List<IslandCreatureType>[][] getGameField() {
        return gameField;
    }

    public static IslandSize getInstance() {
        return instance;
    }

    private void createField() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                gameField[x][y] = new CopyOnWriteArrayList<>();
                firstInitCreatures(x, y);
            }
        }
    }

    public void firstInitCreatures(int x, int y) {
        for (CreatureType type : CreatureType.values()) {
            int amountOfOneTypeOfEntity = ThreadLocalRandom.current().nextInt(1, type.getMaxAmount() + 1);
            while (amountOfOneTypeOfEntity > 0) {
                IslandCreatureType creature = createCreature.createAnimal(x,y,type);
                if (creature instanceof Animals) {
                    ((Animals) creature).setSaturation(creature.getType().getFullSaturation() * 0.5);
                }
                gameField[x][y].add(creature);
                amountOfOneTypeOfEntity--;
            }
        }
    }
}
