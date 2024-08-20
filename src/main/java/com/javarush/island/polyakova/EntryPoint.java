package com.javarush.island.polyakova;

import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.threads.PlantGrowthThread;
import com.javarush.island.polyakova.threads.SimulationThread;
import com.javarush.island.polyakova.services.Eat;
import com.javarush.island.polyakova.services.Move;
import com.javarush.island.polyakova.services.Reproduction;
import com.javarush.island.polyakova.threads.StatisticThread;

public class EntryPoint {
    public static void main(String[] args) {

        Thread thread = new SimulationThread(
                new Eat(),
                new Reproduction(new CreateCreature()),
                new Move());
        thread.start();

        PlantGrowthThread plantThread = new PlantGrowthThread();
        plantThread.start();

        /*StatisticThread statisticThread = new StatisticThread();
        statisticThread.start();*/
    }
    }
