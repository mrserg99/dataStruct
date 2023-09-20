package Many2Many;//Подключаем пакет для работы с ManyToMany

//Класс отношения многие ко многим
public class ManyToMany {
    public Student students; // Ссылка на студентов
    public Course courses; //ссылка на курсы

    //Конструктор класса
    public ManyToMany() {
        //Далее происходит заполнение массивов студентов и курсов данными, с которыми будем работать в Test
        students = new Student(4);
        students.insert("Bob".toCharArray());
        students.insert("Alice".toCharArray());
        students.insert("Mary".toCharArray());
        students.insert("John".toCharArray());

        courses = new Course(3);
        courses.insert(1);
        courses.insert(2);
        courses.insert(3);

        students.print();
        courses.print();
        System.out.println();
    }

    //Метод, который добавляет студента на курс
    public void addStudentToCourse(char[] name, int courseNumber) {
        // Получаем элемент студента если он есть
        StudentElement student = students.member(name);
        // если студент не найден в списке студентов, то вернулся null, делаем return.
        if (student == null) return;
        // Получаем элемент курса если он есть
        CourseElement course = courses.member(courseNumber);
        // если курс не найден в списке курсов, то вернулся null, делаем return.
        if (course == null) return;

        //поверим есть ли студен уже на курсе, то делаем return
        if (onCourse(course, name)) {
            System.out.println("Имя уже присутствует");
            return;
        }

        //Если студента нет на курсе, то создадим новую Запись определяя какие данные передать в конструктор
        Record newRecord = new Record(course.next == null ? course : course.next, student.getNext() == null ? student : student.getNext());

        course.next = newRecord;
        student.next = newRecord;
    }

    //Метод печатает список курсов студента
    public void printCoursesOfStudent(char[] name) {
        // Получаем элемент студента если он есть
        StudentElement student = students.member(name);
        // если студент не найден в списке студентов, то вернулся null, делаем return.
        if (student == null) return;

        //Получим следующий элемент нашего student
        Connector obj = student.getNext();

        //Если полученный объект пустой, то делаем return
        if (obj == null) return;

        //Пока у нас есть следующий элемент
        while (obj.hasNext()) {
            //Будем печатать номера курсов
            //Получаем запись со стороны курса
            Connector p = ((Record) obj).nextCourse;

            //Пока есть запись
            while (p.hasNext())
                //будем обновлять запись
                p = ((Record) p).nextCourse;
            //Возвращаем результат приведя его к типу CourseElement
            System.out.print(((CourseElement) p).course + " ");

            //Переводим итератор на следующий элемент
            obj = ((Record) obj).nextStudent;
        }

        System.out.println();
    }

    //Метод печатает список студентов на курсе
    public void printStudentsOfCourse(int courseNumber) {
        // Получаем элемент курса если он есть
        CourseElement course = courses.member(courseNumber);
        // если курс не найден в списке курсов, то вернулся null, делаем return.
        if (course == null) return;

        //Получим следующий элемент нашего course
        Connector obj = course.next;
        //Если полученный объект пустой, то делаем return
        if (obj == null) return;

        //Пока у нас есть следующий элемент
        while (obj.hasNext()) {
            //Будем искать студентов в записях и печатать имена
            //Получаем запись со стороны студента
            Connector temp = ((Record) obj).nextStudent;

            //Пока есть запись
            while (temp.hasNext())
                //будем обновлять запись
                temp = ((Record) temp).nextStudent;
            //Возвращаем результат приведя его к типу CourseElement

            ((StudentElement) temp).printName();

            //Переводим итератор на следующий элемент
            obj = ((Record) obj).nextCourse;
        }

        System.out.println();
    }

    //Метод удаляет студента с курса
    public void removeStudentFromCourse(int courseNumber, char[] name) {
        // Получаем элемент студента если он есть
        StudentElement student = students.member(name);
        // если студент не найден в списке студентов, то вернулся null, делаем return.
        if (student == null) return;

        // Получаем элемент курса если он есть
        CourseElement course = courses.member(courseNumber);
        //Если полученный объект пустой, то делаем return
        if (course == null) return;

        //Для того чтобы удалить запись отношения, нужно найти предыдущую запись со стороны студента
        //если нужная нам запись является первой на данном курсе то делаем return
        Connector result = prevStudent(course, name);
        if (result == null) return;

        //Если у нас есть следующее значение
        if (result.hasNext()) {
            // То нужно переменную класса связки преобразовать к переменной класса Записи, чтобы можно было обратится к след. курсу
            Record record = (Record) result;
            //если нужная нам запись последняя в кольце курса, то ссылаемся на сам курс
            if (!record.nextCourse.hasNext()) record.nextCourse = course;
                //Иначе ссылаемся на следующий курс
            else record.nextCourse = ((Record) record.nextCourse).nextCourse;
        }
        //Если след записи нет, то
        else {
            //Если если найденный нами course имеет следующий элемент, у которого есть следующий элемент, то
            if (course.next.hasNext()) {
                // получаем следующий элемент
                Record nextRecord = course.next;
                //если у курса записи есть следующий элемент, то course.next = курсу записи.
                if (nextRecord.nextCourse.hasNext()) course.next = (Record) nextRecord.nextCourse;
                    //Иначе равно null
                else course.next = null;
            }
        }

        //Для того чтобы удалить запись отношения, нужно найти предыдущую запись со стороны курса
        //если нужная нам запись является первой у данного студента то делаем return
        result = prevCourse(student, courseNumber);
        if (result == null) return;

        //Если у нас есть следующее значение
        if (result.hasNext()) {
            Record record = (Record) result;
            //если нужная нам запись последняя в кольце студентов, то ссылаемся на самого студента
            if (!record.nextStudent.hasNext()) record.nextStudent = student;
                //иначе исключаем удаляемый элемент из цепочки
            else record.nextStudent = ((Record) record.nextStudent).nextStudent;
        }
        //Иначе если след. значения нет
        else {
            // получаем следующий элемент у student
            Record newRecord = student.getNext();
            //И если у студента записи нет след элемента, student.next = студенту записи
            if (newRecord.nextStudent.hasNext()) student.next = (Record) newRecord.nextStudent;
                //иначе равно null
            else student.next = null;
        }
    }

    //Удаляем студента со всех курсов
    public void removeStudent(char[] name) {
        //Получаем элемент студента
        StudentElement student = students.member(name);
        //Если элемент равен null, то делаем return
        if (student == null) return;

        //если студент никуда не записан, то делаем return
        if (student.getNext() == null) return;
        //Если записан, то получаем запись
        Connector obj = student.getNext();
        //Пока записи есть
        while (obj.hasNext()) {
            //найдём курс, на который записан студент
            CourseElement course;
            if (((Record) obj).nextCourse.hasNext()) {
                //Получаем запись со стороны курса
                Connector p = ((Record) ((Record) obj).nextCourse).nextCourse;

                //Пока есть запись
                while (p.hasNext())
                    //будем обновлять запись
                    p = ((Record) p).nextCourse;

                course = (CourseElement) p;
            } else {
                course = (CourseElement) ((Record) obj).nextCourse;
            }

            //Получим предыдущее значение
            Connector result = prevStudent(course, name);
            //Если предыдущее значение равно null, делаем continue.
            if (result == null) continue;

            //Если предыдущее значение не курс, но запись
            if (result.hasNext()) {
                //Создадим переменную, в которою запишем запись.
                Record record = (Record) result;
                //вычеркнем курс из цепочки
                record.nextCourse = ((Record) record.nextCourse).nextCourse;
            }
            //Иначе
            else {
                //если у студента есть другие регистрации на этот курс, то вычёркиваем курс из цепочки
                if (course.next.nextCourse.hasNext()) course.next = (Record) course.next.nextCourse;

                    // Иначе только 1 записью Удаляем её
                else course.next = null;
            }
            //Обновляем итератор
            obj = ((Record) obj).nextStudent;
        }
        //Удаляем студента
        student.next = null;
    }


    //Удаляем курс у всех студентов.
    public void removeCourse(int courseNumber) {
        //Получаем элемент курса
        CourseElement course = courses.member(courseNumber);
        //Если элемент равен null, то делаем return
        if (course == null) return;

        //если у курса нет записей, то делаем return
        if (course.next == null) return;
        //Если есть, то получаем запись
        Connector obj = course.next;
        //Пока записи есть
        while (obj.hasNext()) {
            //найдём студента, который записан на курс.
            StudentElement student;
            if (((Record) obj).nextStudent.hasNext()) {
                //Получаем запись со стороны студента
                Connector temp = ((Record) ((Record) obj).nextStudent).nextStudent;

                //Пока есть запись
                while (temp.hasNext())
                    //будем обновлять запись
                    temp = ((Record) temp).nextStudent;
                //Возвращаем результат приведя его к типу CourseElement

                student = ((StudentElement) temp);
            } else {
                student = (StudentElement) ((Record) obj).nextStudent;
            }

            //Получим предыдущее значение
            Connector result = prevCourse(student, course.course);
            //Если предыдущее значение равно null, делаем continue.
            if (result == null) continue;

            //Если предыдущее значение не студент, но запись
            if (result.hasNext()) {
                //Создадим переменную, в которою запишем запись.
                Record record = (Record) result;
                //вычеркнем студента из цепочки
                record.nextStudent = ((Record) record.nextStudent).nextStudent;
            }
            //Иначе
            else {
                //если у курса есть другие регистрации со студентами, то вычёркиваем студента из цепочки
                if (student.next.nextStudent.hasNext()) student.next = (Record) student.next.nextStudent;

                    // Иначе только 1 записью Удаляем её
                else student.next = null;
            }
            //Обновляем итератор
            obj = ((Record) obj).nextCourse;
        }
        //Удаляем курс
        course.next = null;
    }


    //Метод для нахождения предыдущей записи студента
    private Connector prevStudent(CourseElement course, char[] name) {
        // получим следующую запись курса
        Connector p = course.next;
        // Сохраним курс
        Connector p2 = course;
        //Пока записи есть
        while (p.hasNext()) {

            //Будем проверять не нашли ли мы нужного студента

            //Получаем запись со стороны студента
            Connector temp = ((Record) p).nextStudent;

            //Пока есть запись
            while (temp.hasNext())
                //будем обновлять запись
                temp = ((Record) temp).nextStudent;
            //Возвращаем результат приведя его к типу CourseElement

            if (Student.isEquals(((StudentElement) temp).studentName, name))
                //Если нашли, то возвращаем предыдущее значение
                return p2;
            //Иначе обновляем итераторы

            p2 = p;
            p = ((Record) p).nextCourse;
        }
        //Если из цикла не вернули значение, то возвращаем null
        return null;
    }

    //Метод проверки есть ли студент на курсе
    private boolean onCourse(CourseElement course, char[] name) {
        //Получаем запись
        Connector p = course.next;
        //Если запись равна null, то возвращаем false
        if (p == null) return false;

        //Пока есть записи
        while (p.hasNext()) {
            //Если нашли нужного студента, то возвращаем true
            //Получаем запись со стороны студента
            Connector temp = ((Record) p).nextStudent;

            //Пока есть запись
            while (temp.hasNext())
                //будем обновлять запись
                temp = ((Record) temp).nextStudent;
            //Возвращаем результат приведя его к типу CourseElement

            if (Student.isEquals(((StudentElement) temp).studentName, name))
                return true;
            //иначе обновляем итератор
            p = ((Record) p).nextCourse;
        }
        //Если из цикла не вернули ответ, то возвращаем false
        return false;
    }

    //Метод для нахождения предыдущей записи курса
    private Connector prevCourse(StudentElement student, int course) {
        // получим следующую запись студента
        Connector p = student.getNext();
        //Сохраним текущую запись
        Connector p2 = student;

        //Пока записи есть
        while (p.hasNext()) {
            //Будем проверять нет ли в записи нужного нам курса

            Connector temp = ((Record) p).nextCourse;
            while (temp.hasNext())
                temp = ((Record) temp).nextCourse;

            if (((CourseElement) temp).course == course)
                //Если есть, то возвращаем предыдущее значение
                return p2;
            //Иначе обновляем итераторы
            p2 = p;
            p = ((Record) p).nextStudent;
        }
        //Если из цикла не вернули ответ, то возвращаем null
        return null;
    }
}