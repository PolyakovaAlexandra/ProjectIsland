package com.javarush.island.polyakova.creature.animals;

//import com.javarush.island.polyakova.island.IslandField;

import com.javarush.island.polyakova.creature.CreatureType;
import com.javarush.island.polyakova.creature.IslandCreatureType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public abstract class Animals implements IslandCreatureType {

    private int reproduceTrying;
    private final Gender gender;
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;
    private boolean reproduced = false;
    private double saturation;
    private final Map<CreatureType, Integer> edibleSpecies = new HashMap<>();

    public Animals(int x, int y) {
        this.x = x;
        this.y = y;
        this.reproduceTrying = 3;
        this.saturation = this.getType().getFullSaturation() ;
        Random random = new Random();
        int randomValue = random.nextInt(100);
        this.gender = randomValue < 60 ? Gender.MALE : Gender.FEMALE;
    }

    public Gender getGender() {
        return gender;
    }

    public int getReproduceTrying() {
        return reproduceTrying;
    }

    public void setReproduceTrying(int reproduceTrying) {
        this.reproduceTrying = reproduceTrying;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isReproduced() {
        return reproduced;
    }

    public void setReproduced(boolean b) {
        this.reproduced = b;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double newSaturation) {
        this.saturation = newSaturation;
    }


    public Map<CreatureType, Integer> getEdibleSpecies() {
        return this.edibleSpecies;
    }

    public void hunger() {

        if (this.getSaturation() > 0) {

            this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() * 0.55);

            if (this.getSaturation() <= 0) {
                this.die();
            }
        } else {
            this.die();
        }
    }

    public void die() {
        int oldIslandEntityX = this.getX();
        int oldIslandEntityY = this.getY();

        IslandField.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].remove(this);
    }

    @Override
    public String toString() {
        return this.getType() + "(" + this.getX() + ", " + this.getY() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals that = (Animals) o;
        return x == that.x && y == that.y && reproduced == that.reproduced && Double.compare(saturation, that.saturation) == 0 && Objects.equals(islandField, that.islandField) && Objects.equals(edibleSpecies, that.edibleSpecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandField, x, y, reproduced, saturation, edibleSpecies);
    }
}
