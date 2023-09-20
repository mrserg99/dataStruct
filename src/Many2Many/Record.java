package Many2Many; //Подключаем пакет для работы с ManyToMany

//Класс Записи.
//Наследуем класс от класса связки
public class Record extends Connector {
 public Connector nextCourse; // ссылка на курс
 public Connector nextStudent; // ссылка на студента

 //конструктор класса
 public Record(Connector course, Connector student) {
     //Заполняем переменные
     nextCourse = course;
     nextStudent = student;
 }


 public boolean hasNext() {
     return true;
 }
}