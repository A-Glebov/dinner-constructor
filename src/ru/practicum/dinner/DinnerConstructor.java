package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    HashMap<String, ArrayList<String>> listOfDishesByCategory = new HashMap<>(); // хэш-мап для хранения списков блюд по категориям
    Random random = new Random();

    void addDish(String type, String name) { // Добавление блюда в список блюд по категориям. В качестве аргументов принимает тип блюда и наименование блюда
        // При замене блока if - else на switch возникает предупреждение (Selector type of 'boolean' is not supported at language level '24) по рекомендации яндекс используется SDK 24-corretto
        if (!chekType(type)) { //  если категория отсутствует добавляем
            ArrayList<String> listOfDishes = new ArrayList<>();
            listOfDishes.add(name);
            listOfDishesByCategory.put(type, listOfDishes); //добавляем тип и блюдо
        } else {
            for (String typeName : listOfDishesByCategory.keySet()) {
                if (type.equals(typeName)) {
                    listOfDishesByCategory.get(type).add(name); //если категория есть добавляем в нее блюдо
                }

            }
        }
    }


    boolean chekType(String type) { //проверка наличия категории
        return listOfDishesByCategory.containsKey(type);
    }


    ArrayList<ArrayList<String>> generateCombo(int numberOfCombos, ArrayList<String> listTypeForCombo) { // генерирует количество комбинаций(numberOfCombos) наименований блюд по списку типов блюд (listTypeForCombo)
        ArrayList<ArrayList<String>> listCombo = new ArrayList<>(); //список ля хранения различных комбинаций блюд

        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> combo = new ArrayList<>(); //список для записи одной комбинации

            for (String type : listTypeForCombo) {
                ArrayList<String> listDishes = listOfDishesByCategory.get(type); //получаем список блюд в категории
                int index = random.nextInt(listDishes.size()); //получаем псевдослучайное число для получения блюда по индексу
                String dish = listDishes.get(index); //записываем случайно выбранное блюдо в переменную
                combo.add(dish); //добавляем в список
            }

            listCombo.add(combo); //добавляем список для записи одной комбинации в список ля хранения различных комбинаций блюд
        }

        return listCombo; // список возможных комбинаций из блюд этих типов (listTypeForCombo)
    }

    void printOptionsCombo(ArrayList<ArrayList<String>> listCombo) { // Печать вариантов комбинаций блюд. В качестве параметра
        for (int i = 0; i < listCombo.size(); i++) {                   // список типов(категорий) блюд введенных пользователем
            System.out.println("Комбо " + (i + 1));
            System.out.println(listCombo.get(i));
        }

    }

}
