package com.javarush.island.polyakova;

import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.creature.threads.PlantGrowthThread;
import com.javarush.island.polyakova.creature.threads.SimulationThread;
import com.javarush.island.polyakova.services.Eat;
import com.javarush.island.polyakova.services.Move;
import com.javarush.island.polyakova.services.Reproduction;

public class EntryPoint {
    public static void main(String[] args) {

        //todo симуляция продолжает работать после того, как все умерли

        // ну да, потому что ты удалил условие остановки runnera - когда все животные умерли
        Thread thread = new SimulationThread(
                new Eat(),
                new Reproduction(new CreateCreature()),
                new Move());
        thread.start();

        PlantGrowthThread plantThread = new PlantGrowthThread();
        plantThread.start();
/*
        StatisticThread statisticThread = new StatisticThread();
        statisticThread.start();*/
    }
    }
}