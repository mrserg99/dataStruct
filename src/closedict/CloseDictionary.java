package closedict;

import def.Dict;

public class CloseDictionary implements Dict {
    private static final int B = 12;
    Person[] persons;//Список элементов

    private static class Person {
        private char[] name; // имя

        // Конструктор класса Person

        private Person(char[] name) {
            //Инициализируем переменные name и next
            this.name = name;
        }
        // Конструктор класса Person

        private Person() {
            //Инициализируем пустой объект
            this(null);
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

        //Метод, которые записывает имя в ячейку
        public void setName(char[] name) {
            int i;
            for (i = 0; i < name.length; i++) {
                this.name[i] = name[i];
            }
            for (; i < 10; i++) {
                this.name[i] = '\u0000';
            }
        }

        public void getName() {
            for (int i = 0; i < this.name.length; i++) {
                if (name[i] == '\u0000') break;
                System.out.print(name[i]);
            }
        }

    }

    //Конструктор класса Dictionary
    public CloseDictionary() {
        // Инициализация переменную persons и заполним её пустыми значениями
        persons = new Person[B];
        for (int i = 0; i < B; i++) {
            persons[i] = new Person();
        }
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

    private int hash(int h) {
        return h % B;
    }

    @Override
    //метод определяет есть ли в множестве нужное имя
    public boolean member(char[] x) {
        //определим значение хэш функции
        int key = hash(x);
        int j = 0; // Переменная, которая используется для повторного хэширования
        //И если значение не пустое
        if (persons[key].name != null) {
            //сравним его с присланным именем
            if (persons[key].equals(x))
                //Если имена совпали, то возвращаем true
                return true;
            //иначе обновляем ключ
            int newKey = hash(key + ++j);
            //В цикле, пока хэш не повторится, будем искать совпадения с нужным именем
            while (newKey != key) {
                //Если по новому ключу отсутствует значение, то дальше искать бесполезно. Возвращаем false
                if (persons[newKey].name == null)
                    return false;
                //Иначе сравним новое имя с нужным
                if (persons[newKey].equals(x))
                    //если совпали, делаем return true
                    return true;
                //Иначе обновляем ключ
                newKey = hash(key + ++j);
            }
        }

        //Если не вернули true, то возвращаем false
        return false;
    }

    @Override
    //Метод обнуления списка
    public void makeNull() {
        //в цикле проходимся по массиву и приравниванием к null.
        for (int i = 0; i < persons.length; i++)
            persons[i].name = null;
    }

    @Override
    //Метод добавления имени
    public void insert(char[] x) {
        // Найдём значение hash функции от имени
        int key = hash(x);
        int j = 0; // Переменная, которая используется для повторного хеширования
        //Проверим есть ли значение по такому ключу
        if (persons[key].name == null) {
            // Если нет, то записываем имя
            persons[key].name = new char[10];
            persons[key].setName(x);
            return;
        }
        //Иначе уже есть значения по данному хэшу. Нужно получить уникальный хэш.
        //Получим ключ увеличив предыдущий хэш на 1

        int newKey = hash(key + ++j);
        int deleteElement = -1;
        //В цикле, пока значение по хэшу не будет равно null, будем искать первое пустое место по новому хэшу
        while (persons[newKey].name != null) {
            //Если мы пошли уже по второму кругу в цикле, значит не нашли пустого места
            if (key == newKey) {
                //Проверим не нашли ли мы место, где был удалён элемент
                //Если не нашли, то не делаем вставки и вызываем return
                if (deleteElement == -1) return;
                //Если нашли, то вставляем в эту позицию наше значение
                persons[deleteElement].setName(x);
                return;
            }
            if (persons[newKey].equals(x)) {
                //Если совпадает, то добавлять ничего не нужно. Делаем return
                return;
            }
            //Если мы нашли ближайший удалённый элемент, то запишем его позицию
            if (deleteElement == -1 && persons[newKey].name == null) {
                deleteElement = newKey;
            }
            //Если по новому ключу уже есть какое-то значение, которое не удовлетворяет требованиям, то обновляем ключ
            newKey = hash(key + ++j);

        }
        //Если вышли из цикла, значит нашли новый ключ, по которому нет значений
        persons[newKey].name = new char[10];
        persons[newKey].setName(x);
    }

    @Override
    //Метод удаления имени
    public void delete(char[] x) {
        //Определим значение hash функции
        int key = hash(x);
        int j = 0; // Переменная, которая используется для повторного хэширования
        //Проверим есть ли значения с таким ключом
        //Если есть
        if (persons[key] != null) {
            //Проверим то ли это имя, которое нам нужно удалить
            if (persons[key].equals(x)) {
                //Если Да, то удаляем имя меняя значения на символ нуля
                persons[key].name = null;
            } else {
                //Иначе нужно найти нужное нам имя в других ячейках.
                //Обновляем ключ
                int newKey = hash(key + ++j);
                //В цикле, пока хэш не повторится, будем искать совпадения с нужным именем
                while (newKey != key) {
                    //Проверим
                    //Если мы получили новый ключ, по которому отсутствует какое-либо значение, то дальше искать бессмысленно.
                    //Делаем return
                    if (persons[newKey].name == null)
                        return;
                    // Иначе если нашли совпадения по имени, то удаляем это значение меняя его на символ нуля
                    if (persons[newKey].equals(x)) {
                        persons[newKey].name = null;
                    }
                    //Иначе обновляем ключ
                    newKey = hash(key + ++j);
                }
            }
        }
    }

    @Override
    public void print() {
        int k = 0;
        for (int i = 0; i < B; i++) {
            if (persons[i].name != null) {
                System.out.print((k + 1) + ") ");
                persons[i].getName();
                k += 1;
                System.out.println();
            }
        }
    }
}
