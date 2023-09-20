import def.Dict;
import closedict.CloseDictionary;
import opendict.OpenDictionary;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Dict badGuys = getBadGuys();
        Dict goodGuys = getGoodGuys();

        Scanner in = new Scanner(System.in);
        String input = "S";

        while (input != "E") {

            char[] msg = input.toCharArray();
            if (msg[0] == 'E') break;
            switch (msg[0]) {
                case 'S' -> {
                    System.out.println("--------[ Статистика по партии ]--------\n|");
                    System.out.println("[ Bad guys ]");
                    badGuys.print();
                    System.out.println("\n[ Good guys ]");
                    goodGuys.print();
                }
                case 'F' -> {
                    if (msg.length - 2 <= 0) {
                        System.out.println("ОШИБКА!!! Введите имя.");
                    } else {
                        char[] name = new char[msg.length - 2];
                        for (int i = 0; i < msg.length - 2; i++) {
                            name[i] = msg[i + 2];
                        }
                        if (badGuys.member(name)) {
                            badGuys.delete(name);
                            goodGuys.insert(name);
                            System.out.println("Человек с именем " + String.copyValueOf(name) + " теперь в группе Хороших");
                        } else {
                            System.out.println("Человек с именем " + String.copyValueOf(name) + " не найден в группе Плохих");
                        }
                    }
                }
                case 'U' -> {
                    if (msg.length - 2 <= 0) {
                        System.out.println("ОШИБКА!!! Введите имя.");

                    } else {
                        char[] name = new char[msg.length - 2];
                        for (int i = 0; i < msg.length - 2; i++) {
                            name[i] = msg[i + 2];
                        }
                        if (goodGuys.member(name)) {
                            goodGuys.delete(name);
                            badGuys.insert(name);
                            System.out.println("Человек с именем " + String.copyValueOf(name) + " теперь в группе Плохих");
                        } else {
                            System.out.println("Человек с именем " + String.copyValueOf(name) + " не найден в группе Хороших");
                        }
                    }
                }
                case '?' -> {
                    if (msg.length - 2 <= 0) {
                        System.out.println("ОШИБКА!!! Введите имя.");

                    } else {
                        char[] name = new char[msg.length - 2];
                        for (int i = 0; i < msg.length - 2; i++) {
                            name[i] = msg[i + 2];
                        }
                        if (goodGuys.member(name)) {
                            System.out.println("Человек в группе Хороших");
                        } else if (badGuys.member(name)) {
                            System.out.println("Человек в группе Плохих");
                        } else {
                            System.out.println("Такого человека не существует");
                        }
                    }
                }
            }

            System.out.println("Введите команду:\t");
            input = in.nextLine();
        }
    }

    private static Dict getBadGuys() {
//        Dict list = new OpenDictionary();
        Dict list = new CloseDictionary();
        list.insert("Alexandr".toCharArray());
        list.insert("Nasty".toCharArray());
        list.insert("Petr".toCharArray());
        list.insert("Victor".toCharArray());
        list.insert("Wayne".toCharArray());
        list.insert("Angela".toCharArray());
        list.insert("Jay".toCharArray());
        list.insert("Manuel".toCharArray());
        list.insert("Donna".toCharArray());
        list.insert("Raymond".toCharArray());

        return list;
    }

    private static Dict getGoodGuys() {
//        Dict list = new OpenDictionary();
        Dict list = new CloseDictionary();
        list.insert("Joan".toCharArray());
        list.insert("Corey".toCharArray());
        list.insert("Vanessa".toCharArray());
        list.insert("Angel".toCharArray());
        list.insert("Jonathan".toCharArray());
        list.insert("Amy".toCharArray());

        return list;
    }
}
