package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addDish(dishType, dishName); // добавьте новое блюдо
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        ArrayList<String> listTypeForCombo = new ArrayList<>(); // список категорий для генерации комбинаций блюд

        while (!nextItem.isEmpty()) {
            if (dc.chekType(nextItem)) { //проверяем корректность введенной категории
                listTypeForCombo.add(nextItem);// добавляем категорию в список категорий для последующей генерации комбинаций
            } else {
                System.out.println("Данный тип отсутствует. Введите другой:");
            }
            
            nextItem = scanner.nextLine(); //считываем категории
        }

        // сгенерируйте комбинации блюд и выведите на экран
        ArrayList<ArrayList<String>> comboOptions = dc.generateCombo(numberOfCombos, listTypeForCombo);// генерируем варианты комбинаций
        dc.printOptionsCombo(comboOptions);// выводим варианты комбинаций
    }

}
