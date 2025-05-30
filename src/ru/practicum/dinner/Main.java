package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dinnerConstructor; // Заменил шаблонное название на более читаемое
    static Scanner scanner;

    public static void main(String[] args) {
        dinnerConstructor = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine().trim(); // trim() для удаления лишних пробелов. Возьму на вооружение. Спасибо)

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("До свидания!");
                    return;
                default: // обработка неизвестной команды
                    System.out.println("Неизвестная команда!");
            }
        }
    }

    private static void printMenu() { // печать меню
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() { // Запрос пользователю на добавление блюд и добавление блюд
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine().trim();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine().trim();

        dinnerConstructor.addDish(dishType, dishName); // добавляем введенное пользователем блюдо по типу в список блюд по типам
    }

    private static void generateDishCombo() { // генерация вариантов комбинаций блюд по введенным пользователем типов
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt(); // Количество вариантов для генерации
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine().trim(); // Типы блюд для генерации

        //реализуйте ввод типов блюд
        ArrayList<String> listTypeForCombo = new ArrayList<>(); // список категорий для генерации комбинаций блюд

        while (!nextItem.isEmpty()) {
            if (dinnerConstructor.chekType(nextItem)) { //проверяем корректность введенной категории
                listTypeForCombo.add(nextItem);// добавляем категорию в список категорий для последующей генерации комбинаций
            } else {
                System.out.println("Данный тип отсутствует. Введите другой:");
            }
            
            nextItem = scanner.nextLine().trim(); //считываем категории
        }

        // сгенерируйте комбинации блюд и выведите на экран
        ArrayList<ArrayList<String>> comboOptions = dinnerConstructor.generateCombo(numberOfCombos, listTypeForCombo);// генерируем варианты комбинаций
        dinnerConstructor.printOptionsCombo(comboOptions);// выводим варианты комбинаций
    }

}
