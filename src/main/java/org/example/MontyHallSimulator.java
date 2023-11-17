package org.example;

import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallSimulator {
    public static void main(String[] args) {
        int totalSteps = 1000; // Общее количество шагов цикла
        int positiveResults = 0; // Количество позитивных результатов
        int negativeResults = 0; // Количество негативных результатов

        Map<Integer, Boolean> results = new HashMap<>(); // Шаг цикла -> Результат

        for (int step = 1; step <= totalSteps; step++) {
            boolean isPositiveResult = simulateMontyHall();
            results.put(step, isPositiveResult);

            if (isPositiveResult) {
                positiveResults++;
            } else {
                negativeResults++;
            }
        }

        // Вывод статистики
        System.out.println("Позитивные результаты: " + positiveResults);
        System.out.println("Негативные результаты: " + negativeResults);
        System.out.println("Процент позитивных результатов: " + ((double) positiveResults / totalSteps) * 100 + "%");
    }

    private static boolean simulateMontyHall() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int prizeDoor = randomDataGenerator.nextInt(1, 4); // Дверь с призом
        int chosenDoor = randomDataGenerator.nextInt(1, 4); // Выбранная игроком дверь

        // Открыть дверь с козырем, не выбранную игроком
        int hostOpens = 1;
        while (hostOpens == chosenDoor || hostOpens == prizeDoor) {
            hostOpens++;
        }

        // Используем java.util.Random для генерации случайных булевых значений
        Random random = new Random();
        boolean switchDoor = random.nextBoolean();

        // Определить, выиграл ли игрок
        if (switchDoor) {
            return chosenDoor != hostOpens;
        } else {
            return chosenDoor == prizeDoor;
        }
    }
}
