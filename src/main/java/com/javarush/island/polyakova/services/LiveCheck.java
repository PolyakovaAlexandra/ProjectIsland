package com.javarush.island.polyakova.services;

import com.javarush.island.polyakova.creature.animals.Animals;

public class LiveCheck {
    public boolean liveCheck(Animals animals) {
        return animals.getSaturation() > 0;
    }
}
