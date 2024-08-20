package com.javarush.island.polyakova.creature.threads;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.island.IslandSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticThread extends Thread{
    IslandSize islandSize = IslandSize.getInstance();
    CreatureType[] array = CreatureType.values();
    private boolean running;

    public StatisticThread() {
        this.running = true;
    }

    public void stopStatisticThread() {
        running = false;
    }

    public void run() {
        //даем проинициализироваться всем обектам
        while (islandSize == null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (running) {
            System.out.println(countCreaturesInGameField());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!running) {
                stopStatisticThread();

            }
        }
    }

    public long countCreaturesInCell(List<IslandCreatureType> creatureTypes, CreatureType type) {
        return creatureTypes.stream()
                .filter(creatureType -> creatureType.getType() == type)
                .count();
    }

    public Map<String, Integer> countCreaturesInGameField() {
        HashMap<String, Integer> countOfEntityInGameField = new HashMap<>();

        for (CreatureType type : array) {
            countOfEntityInGameField.put(type.getIcon(), 0);
        }

        for (int i = 0; i < IslandSize.getInstance().getNumRows() - 1; i++) {
            for (int j = 0; j < IslandSize.getInstance().getNumRows() - 1; j++) {
                for (CreatureType type : array) {
                    long entitiesInCell = countCreaturesInCell(islandSize.getGameField()[i][j], type);
                    int entitiesInMap = countOfEntityInGameField.get(type.getIcon());
                    countOfEntityInGameField.put(type.getIcon(), (int) (entitiesInMap + entitiesInCell));
                }
            }
        }
        return countOfEntityInGameField;
    }
}
