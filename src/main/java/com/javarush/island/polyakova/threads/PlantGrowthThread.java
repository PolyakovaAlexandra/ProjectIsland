package com.javarush.island.polyakova.threads;

import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.island.IslandSize;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class PlantGrowthThread extends Thread{
    IslandSize islandSize = IslandSize.getInstance();
    private boolean running;
    private CreateCreature createCreature = new CreateCreature();

    public PlantGrowthThread() {
        this.running = true;
    }

    @Override
    public void run() {
//        long counter = 0;
        while (running) {
            // Генерируем новые растения на каждой клетке
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int x = 0; x < islandSize.getNumRows(); x++) {
                for (int y = 0; y < islandSize.getNumColumns(); y++) {
                    if (countOfPlantOnCellResolver(x, y) < CreatureType.PLANT.getMaxAmount()) {
                        int countOfNewPlants = ThreadLocalRandom.
                                current().
                                nextInt(0, (CreatureType.PLANT.getMaxAmount() - 1) / 5);
                        while (countOfNewPlants > 0) {
                            IslandCreatureType plant = createCreature.createAnimal(x, y, CreatureType.PLANT);
                            islandSize.getInstance().getGameField()[x][y].add(plant);
                            countOfNewPlants--;
                        }
                    }
                }
            }
            if (countOfPlantOnFieldResolver() <= CreatureType.PLANT.getMaxAmount()) {
                stopPlantGeneration();
                System.out.println("Генерация растений остановлена");
            }
        }
    }


    public void stopPlantGeneration() {
        running = false;
    }

    private int countOfPlantOnCellResolver(int x, int y) {
        List<IslandCreatureType> creaturesInCell = islandSize.getGameField()[x][y];
        return (int) creaturesInCell.stream()
                .filter(entity -> entity.getType() == CreatureType.PLANT)
                .count();
    }

    private int countOfPlantOnFieldResolver() {
        return (int) Arrays.stream(islandSize.getGameField())
                .flatMap(Arrays::stream)  // объединяем все списки в один поток
                .flatMap(List::stream)    // объединяем все элементы в один поток
                .filter(creature -> (creature).getType() == CreatureType.PLANT)  // фильтруем по типу
                .count();
    }
}
