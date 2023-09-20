package Many2Many; //Подключаем пакет для работы с ManyToMany

//Класс связка между тремя другими
public abstract class Connector {
  //Опишем метод hasNext, чтобы потом переопределять его в наследуемом классе
  public boolean hasNext()
  {
      return false;
  }
}
