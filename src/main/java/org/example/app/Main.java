package org.example.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.example.zoo.*;
import org.example.animals.*;
import org.example.inventory.*;
import org.example.config.AppConfig;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Zoo zoo = ctx.getBean(Zoo.class);
        zoo.addThing(new Table(101));
        zoo.addThing(new Computer(102));
        Scanner sc = new Scanner(System.in);
        boolean work = true;

        while (work) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить животное");
            System.out.println("2. Вывести количество животных");
            System.out.println("3. Вывести суммарное потребление еды");
            System.out.println("4. Список дружелюбных животных");
            System.out.println("5. Инвентарь");
            System.out.println("0. Выход");
            System.out.print("> ");
            int cmd = Integer.parseInt(sc.nextLine());
            switch (cmd) {
                case 1 -> {
                    addAnimalMenu(zoo, sc);
                    System.out.println("Животное обработано.");
                }
                case 2 -> System.out.println("Всего животных: " + zoo.getAnimalsCount());
                case 3 -> System.out.println("Еды нужно в день: " + zoo.totalFood());
                case 4 -> {
                    var friendly = zoo.friendlyAnimals();
                    if (friendly.isEmpty()) {
                        System.out.println("Нет дружелюбных животных.");
                    } else {
                        friendly.forEach(a ->
                                System.out.println(a.getClass().getSimpleName() + " #" + a.getNumber())
                        );
                    }
                }
                case 5 -> {
                    var inv = zoo.getInventory();
                    if (inv.isEmpty()) {
                        System.out.println("Инвентарь пуст.");
                    } else {
                        inv.forEach(i ->
                                System.out.println(i.getClass().getSimpleName() + " #" + i.getNumber())
                        );
                    }
                }
                case 0 -> {
                    System.out.println("Выход из программы.");
                    work = false;
                }
                default -> System.out.println("Неизвестная команда.");
            }
        }
        sc.close();
    }

    private static void addAnimalMenu(Zoo zoo, Scanner sc) {
        System.out.println("Выберите тип животного:");
        System.out.println("1. Кролик");
        System.out.println("2. Обезьяна");
        System.out.println("3. Тигр");
        System.out.println("4. Волк");
        System.out.print("> ");
        int type = Integer.parseInt(sc.nextLine());
        System.out.print("Введите вес животного: ");
        int weight = Integer.parseInt(sc.nextLine());
        System.out.print("Введите инвентаризационный номер: ");
        int num = Integer.parseInt(sc.nextLine());

        boolean added = false;

        switch (type) {
            case 1 -> {
                System.out.print("Введите уровень дружелюбия: ");
                int fr = Integer.parseInt(sc.nextLine());
                added = zoo.addAnimal(new Rabbit(weight, num, fr));
            }
            case 2 -> {
                System.out.print("Введите уровень дружелюбия: ");
                int fr = Integer.parseInt(sc.nextLine());
                added = zoo.addAnimal(new Monkey(weight, num, fr));
            }
            case 3 -> added = zoo.addAnimal(new Tiger(weight, num));
            case 4 -> added = zoo.addAnimal(new Wolf(weight, num));
            default -> System.out.println("Неверный тип животного.");
        }

        if (!added) {
            System.out.println("Животное не добавлено (не прошло проверку здоровья).");
        } else {
            System.out.println("Животное успешно добавлено в зоопарк.");
        }
    }
}
