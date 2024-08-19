package com.javarush.island.polyakova.creature.plants;

import com.javarush.island.polyakova.creature.IslandCreatureType;

import java.util.Objects;

public abstract class Plant implements IslandCreatureType {
    IslandField islandField = IslandField.getInstance();
    private final int x;
    private final int y;

    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        y = y;
    }

    public String toString() {
        return this.getType() + " (" + getX() + ", " + getY() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant that = (Plant) o;
        return x == that.x && y == that.y && Objects.equals(islandField, that.islandField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandField, x, y);
    }
}
