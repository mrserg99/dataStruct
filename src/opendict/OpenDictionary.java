package opendict;

import def.Dict;

import java.util.Arrays;

public class OpenDictionary implements Dict {

    private static final int B = 10; // Количество классов
    Person[] persons; //Список элементов

    private static class Person {
        private char[] name; // Имя человека
        private Person next; // Ссылка на следующий класс

        // Конструктор класса Person


        private Person(char[] name, Person next) {
            //Инициализируем переменные name и next
            this.name = name;
            this.next = next;
        }
        //Метод сравнения двух имён

        public boolean equals(char[] value) {
            //В цикле посимвольно сравним имена
            for (int i = 0; i < value.length && i < 10; i++)
                //Если символы отличаются, то возвращаем false
                if (name[i] != value[i])
                    return false;
            //Если все символы совпали, то возвращаем true
            return true;
        }

        public void getName() {
            for (int i = 0; i < this.name.length; i++) {
                if (name[i] == '\u0000') break;
                System.out.print(name[i]);
            }
        }
    }

    //Конструктор класса Dictionary
    public OpenDictionary() {
        // Инициализация переменную persons
        persons = new Person[B];
    }

    //Определим метод, который будет возвращать хэш от имени
    private int hash(char[] value) {
        //Создадим переменную sum типа int, в которую потом будем записывать сумму символов.
        int sum = 0;
        //Пройдёмся по символам имени и сложим их
        for (int i = 0; i < value.length; i++) {
            sum += value[i];
        }
        //Возвращаем полученную сумму разделив её на кол-во классов
        return sum % B;
    }

    //Поиск именем в списке коллизий.
    private Person search(int key, char[] name) {
        //получим значение по присланному ключу и сохраним в переменную чтобы потом идти по списку
        Person tmp = persons[key];
        //Заведём переменную, чтобы помнить предыдущее значение
        Person prev = null;
        //Пока список не закончился, будем искать совпадение по именам
        while (tmp != null) {
            //сравниваем имена (методом equals)
            if (tmp.equals(name))
                //Если имя совпадают, то возвращаем предыдущее значение
                return prev;
            //Иначе обновляем итераторы prev и tmp
            prev = tmp;
            tmp = tmp.next;
        }
        //Если в цикле не нашли совпадения, то возвращаем null
        return null;
    }

    @Override
    //метод определяет есть ли в множестве нужное имя
    public boolean member(char[] x) {
        //определим значение хэш функции
        int key = hash(x);
        // если по такому ключу есть значения
        if (persons[key] != null) {
            //То начнём поиск нужного имени.
            //Проверим подходит ли первое имя (equals)
            if (persons[key].equals(x))
                //Если совпадает, то возвращаем true
                return true;
            else {
                //Иначе ищем нужное имя в списке (search)
                Person isFind = search(key, x);
                //если из функции вернулось не null
                //сравним результат с null и вернём результат.
                return isFind != null;
            }
        }
        //Если нет имён с таким хэшом, то возвращаем true
        return false;
    }

    @Override
    //Метод обнуления списка
    public void makeNull() {
        //в цикле проходимся по массиву и приравниванием к null.
        for (int i = 0; i < persons.length; i++)
            persons[i] = null;
    }

    @Override
    //Метод добавления имени
    public void insert(char[] x) {
        // Найдём значение hash функции от имени

        int key = hash(x);
        System.out.println("Имя: " + String.copyValueOf(x) + " | HASH: " + key);
        //Проверим есть ли уже значения по этому ключу.
        //Если значения нет, то добавим значение с присланным name.
        if (persons[key] == null)
            //Добавляем значение.
            persons[key] = new Person(x, null);
            //Если по этому hash уже есть значение
        else {
            //Проверим нет ли такого имени среди имён-коллизий.
            if (search(key, x) == null) {
                //Если среди коллизий не нашли, то вставим в начало списка
                persons[key] = new Person(x, persons[key]);
            }
        }
    }

    @Override
    //Метод удаления имени
    public void delete(char[] x) {
        //Определим значение hash функции
        int key = hash(x);
        //Проверим есть ли значения с таким ключом
        //Если есть
        if (persons[key] != null) {
            //Проверим первое значение на соответствие с нужным нам именем
            if (persons[key].equals(x))
                //Если это первое значение в списке коллизий, то удаляем его
                persons[key] = persons[key].next;
            else {
                //Иначе найдём значение, которое стоит перед этим именем, если есть (методом search)
                Person isFind = search(key, x);
                //Если в списке коллизий нет этого имени, то результат будет null
                //Если null, то делаем return
                if (isFind == null)
                    return;
                //Иначе если такое имя есть, то результатом будем предыдущее имя.
                //Удалим следующее значение из списка. (isFind.next = isFind.next.next)
                isFind.next = isFind.next.next;
            }
        }
    }

    @Override
    public void print() {
        int k = 0;
        for (int i = 0; i < B; i++) {

            if (persons[i] != null) {
                System.out.print((k + 1) + ") ");
                persons[i].getName();
                Person tmp = persons[i].next;
                System.out.print(", ");
                while (tmp != null) {
                    tmp.getName();
                    tmp = tmp.next;
                    System.out.print(", ");
                }
                System.out.print("\b\b");
                k += 1;
                System.out.println();
            }

        }
    }
}
