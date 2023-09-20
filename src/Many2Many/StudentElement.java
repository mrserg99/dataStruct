package Many2Many;//Подключаем пакет для работы с ManyToMany

//Класс элемента студента.
//Наследуем от класса связки
public class StudentElement extends Connector {

  public Record next; // ссылка на запись
  public char[] studentName; //имя студента

  //Конструктор класса
  public StudentElement(char[] name, Record n) {
      //Заполняем переменные
      studentName = name;
      next = n;
  }

  //Метод получает ссылку на запись
  public Record getNext() {
      return next;
  }

  //Метод печати имени студента
  public void printName() {
      char[] name = this.studentName;
      int counter = 0;
      for (int i = 0; i < name.length; i++) {
          if (name[i] != '\u0000') {
              System.out.print(name[i]);
          } else counter++;
      }
      if (counter != 10) System.out.print(" ");
  }
}