package Many2Many; //Подключаем пакет для работы с ManyToMany

//Класс элемента курса
public class CourseElement extends Connector {
  public Record next; // ссылка на запись
  public int course; // номер курса

  //Конструктор класса
  public CourseElement(int courseNumber, Record n) {
      //Заполняем переменные
      course = courseNumber;
      next = n;
  }
}