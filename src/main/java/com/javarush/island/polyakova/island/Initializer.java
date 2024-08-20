package com.javarush.island.polyakova.island;

import com.javarush.island.polyakova.creature.CreateCreature;
import com.javarush.island.polyakova.creature.IslandCreatureType;
import com.javarush.island.polyakova.island.IslandSize;

import java.util.Scanner;


public class Initializer {
    private final CreateCreature createCreature;
    private final IslandSize islandSize = IslandSize.getInstance();
    public Initializer(CreateCreature createCreature) {
        this.createCreature = createCreature;
    }

    public static int initSizeOfFieldX() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfFieldX=0;
        System.out.println("Добро пожаловать в симуляцию жизни острова!");
        while (true){
            System.out.println("Введите желаемую ширину(x) острова в диапазоне от 3 до 10 клеток");
            if (scanner.hasNextInt()){
                sizeOfFieldX=scanner.nextInt();
                break;}
            else if (sizeOfFieldX < 3 || sizeOfFieldX > 10) {
                System.out.println("Попробуйте снова, напоминаю: введите число от 3 до 10:");
            }
        }
        return sizeOfFieldX;
    }

    public static int initSizeOfFieldY() {
        Scanner scanner = new Scanner(System.in);
        int sizeOfFieldY = 0;
        while (true){
            System.out.println("Введите желаемую высоту(y) острова в диапазоне от 3 до 10 клеток");
            if (scanner.hasNextInt()){
                sizeOfFieldY=scanner.nextInt();
                break;}
            else if (sizeOfFieldY < 3 || sizeOfFieldY > 10) {
                System.out.println("Попробуйте снова, напоминаю: введите число от 3 до 10:");
            }
        }
        //System.out.println("Симуляция запущена, поле размером " + sizeOfFieldX + " x " + sizeOfFieldY + ", наслаждайтесь:)");
        return sizeOfFieldY;
    }

}
