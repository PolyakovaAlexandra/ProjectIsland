package com.javarush.island.polyakova.services;

import com.javarush.island.polyakova.creature.animals.Animals;
import com.javarush.island.polyakova.island.IslandSize;

public class Remove {
    LiveCheck liveCheck;
    public Remove(LiveCheck liveCheck) {
        this.liveCheck = liveCheck;
    }

    public void removeOrStayAnimal(Animals animals) {
        int x = animals.getX();
        int y = animals.getY();

        if (!liveCheck.liveCheck(animals)) {
            IslandSize.getInstance().getGameField()[x][y].remove(animals);
        }
    }
}
