package com.javarush.island.polyakova.threads;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.creature.animals.Animals;
import com.javarush.island.polyakova.creature.animals.herbivores.Caterpillar;
import com.javarush.island.polyakova.creature.animals.herbivores.Herbivores;
import com.javarush.island.polyakova.creature.animals.predators.Predators;
import com.javarush.island.polyakova.creature.plants.Plant;
import com.javarush.island.polyakova.island.IslandSize;
import com.javarush.island.polyakova.services.Eat;
import com.javarush.island.polyakova.services.Move;
import com.javarush.island.polyakova.services.Reproduction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationThread extends  Thread{
    private final IslandSize islandSize = IslandSize.getInstance();
    private final Eat eat;
    private final Reproduction reproduction;
    private final Move moving;
    protected boolean running;
    int counter = 0;
    CreatureType[] array = CreatureType.values();

    public SimulationThread(Eat eat,
                                Reproduction reproduction,
                                Move moving) {
        this.eat = eat;
        this.reproduction = reproduction;
        this.moving = moving;
        this.running = true;

    }

    private boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private void stopSimulation() {
        running = false;
    }

    @Override
    public void run() {


        //todo что-то тут не так. Почему они перестают есть размножаться и умирать
        //почему то остаются те, кто уже должен был погибнуть от голода
        while (running) {
            System.out.println(countCreaturesInGameField());
            actEntity();
            System.out.println("цикл " + counter++);
            if ((areAllHerbivorousDead()||areAllPredatorsDead())==true)
                stopSimulation();
           /*try {
                System.out.println("зашли в ожидание" + this);
                this.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(countCreaturesInGameField() + " после");*/

        }
    }

    private void actEntity() {

        Arrays.stream(islandSize.getGameField())
                .parallel()
                .flatMap(Arrays::stream)
                .forEachOrdered(list -> {
                    list.parallelStream()
                            .filter(entity -> !(entity instanceof Plant))
                            .forEachOrdered(entity -> {
                                if (entity instanceof Animals) {
                                    if (((Animals) entity).getSaturation() < 0) {
                                        ((Animals) entity).die();
                                        return;
                                    }
                                    eat.eat(list, (Animals) entity);
                                    reproduction.reproduceAllAnimalOnCell(list, entity);
                                    moving.move((Animals) entity);
                                }
                            });
                });
    }

    // условие выхода из симуляции - все хищники мертвы
    private boolean areAllPredatorsDead() {
        for (int i = 0; i < islandSize.getNumRows(); i++) {
            for (int j = 0; j < islandSize.getNumColumns(); j++) {
                List<IslandCreatureType> creatureTypes = islandSize.getGameField()[i][j];
                for (IslandCreatureType creatureType : creatureTypes) {
                    if (creatureType instanceof Predators) {
                        return false;
                    }
                }
            }
        }
        System.out.println("Симуляция завершена, хищников не осталось на карте");
        return true;
    }

    // условие выхода из симуляции - все травоядные мертвы
    private boolean areAllHerbivorousDead() {
        for (int i = 0; i < islandSize.getNumRows(); i++) {
            for (int j = 0; j < islandSize.getNumColumns(); j++) {
                List<IslandCreatureType> entities = islandSize.getGameField()[i][j];
                for (IslandCreatureType entity : entities) {
                    if (entity instanceof Herbivores) {
                        if ((entity instanceof Caterpillar)) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        System.out.println("Симуляция завершена, травоядных не осталось на карте");
        return true;
    }

    public Map<String, Integer> countCreaturesInGameField() {
        HashMap<String, Integer> countOfCreaturesInGameField = new HashMap<>();


        //todo возможно добавлять в мапу не по типу, а по конкретному животному?
        for (CreatureType type : array) {
            countOfCreaturesInGameField.put(type.getIcon(), 0);
        }

        for (int i = 0; i < IslandSize.getInstance().getNumRows() - 1; i++) {
            for (int j = 0; j < IslandSize.getInstance().getNumRows() - 1; j++) {
                for (CreatureType type : array) {
                    long creaturesInCell = countCreaturesInCell(islandSize.getGameField()[i][j], type);
                    int creaturesInMap = countOfCreaturesInGameField.get(type.getIcon());
                    countOfCreaturesInGameField.put(type.getIcon(), (int) (creaturesInMap + creaturesInCell));
                }
            }
        }
        return countOfCreaturesInGameField;
    }

    public long countCreaturesInCell(List<IslandCreatureType> creatureTypes, CreatureType type) {
        return creatureTypes.stream()
                .filter(creatureType -> creatureType.getType() == type)
                .count();
    }
}
