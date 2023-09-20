package Many2Many;//Подключаем пакет для работы с ManyToMany

//Класс студентов
public class Student {
    public StudentElement[] array; // Массив студентов
    public final static int len = 10; // Максимальная длинна имени

    //Конструктор
    public Student(int a) {
        // Создаём массив студентов
        array = new StudentElement[a];
    }


    //Метод добавления студента в массив
    public void insert(char[] name) {
        // Найдём значение hash функции от имени
        int key = hash(name);
        int j = 0;
        //Проверим есть ли значение по такому ключу
        if (array[key] == null) {
            // Если нет, то записываем имя
            array[key] = new StudentElement(name, null);
        } else {
            //Иначе уже есть значения по данному хэшу. Нужно получить уникальный хэш.
            //Получим ключ увеличив предыдущий хэш на 1
            int newKey = hash(key + 1);
            //В цикле, пока хэш не повторится, будем искать первое пустое место по новому хэшу
            while (newKey != key) {
                //Если по новому хэшу нет значений, то записываем наше имя
                if (array[newKey] == null) {
                    array[newKey] = new StudentElement(name, null);
                    break;
                }
                //если место уже занято, то проверим не занято ли оно уже нужным нам именем. (equals)
                else if (isEquals(array[key].studentName, name)) {
                    //Если совпадает, то добавлять ничего не нужно. Делаем return
                    return;
                }

                //Если по новому ключу уже есть какое-то значение, которое не удовлетворяет требованиям, то обновляем ключ
                newKey = hash(newKey + 1);
            }
        }
    }

    //Метод позволяет найти студента в массиве студентов
    public StudentElement member(char[] name) {
        //Ищем ключ, по которому расположено имя.
        int place = search(name);
        //Если имени нет, то возвращаем null
        if (place == -1) return null;
            //Иначе возвращаем значение по этому ключу
        else return array[place];
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
        return sum % array.length;
    }

    private int hash(int h) {
        return h % array.length;
    }

    //Метод проверки совпадения имён
    public static boolean isEquals(char[] a, char[] b) {
        //Пока количество итераций меньше максимальной длинны имени И итератор меньше длины первого имени И итератор меньше длины второго имени
        for (int i = 0; i < len && i < a.length && i < b.length; i++) {
            //Сравниваем буквы
            if (a[i] != b[i])
                //Если не равны, то возвращаем false
                return false;
        }
        //Если всё совпало, то возвращаем true
        return true;
    }

    //Метод поиска ключа имени
    private int search(char[] name) {
        //Определяем значение хэша имени
        int place = hash(name);
        int j = 0;
        //Если значение по ключу совпадает с нужным именем, то возвращаем этот ключ
        if (isEquals(array[place].studentName, name)) return place;

        //Иначе начинаем искать имя обновляя ключ
        int start = place;
        place = hash(place + 1);
        //Пока ключ не начал повторяться или значение по ключу не равно null
        while (array[place] != null || place != start) {
            //Будем сравнивать значение по ключу с именем.
            if (isEquals(array[place].studentName, name)) {
                //Если значения равны, то возвращаем этот ключ
                return place;
            }
            //Иначе обновляем ключ
            place = hash(place + 1);
        }
        //Если ничего не нашли, то возвращаем -1
        return -1;
    }

    //Метод печати массива студентов
    public void print() {
        for (int i = 0; i < array.length; i++) {
            array[i].printName();
        }
        System.out.println();
    }
}