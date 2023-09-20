package setlinkedlist;


public class SetLinkedList {

    // внутренний класс-элемент
    private class Atom {
        public int value; // значение элемента
        public Atom next;// ссылка на следующий элемент

        //конструктор

        public Atom(int val) {
            // Инициализация переменных value и next=null
            this.value = val;
            this.next = null;
        }


        private Atom copy(Atom A) {

            // Создаём переменную Atom itemA которая равна нашему A
            Atom itemA = A;
            //Создаём переменную Atom itemC в которую будем записывать новые скопированные элементы
            Atom itemC;
            //Создаём новый элемент класса Atom с параметрами itemA.value. Присваиваем его нашему itemC
            itemC = new Atom(itemA.value);
            //Передвинем itemA на следующий элемент next
            itemA = itemA.next;
            //Создаём переменную Atom beginC в которую запишем itemC и потом будем возвращать как результат
            Atom beginC = itemC;

            //Пока itemA != null (В цикле):
            while (itemA != null) {

                //Создаём новый элемент класса Atom с параметрами itemA.value и присваиваем его нашему itemC.next
                itemC.next = new Atom(itemA.value);
                //Сдвигаем itemC и itemA на следующий элемент next
                itemA = itemA.next;
                itemC = itemC.next;
            }

            //Возвращаем наше начало beginC
            return beginC;

        }


        private Atom union(Atom A, Atom B) {
            Atom itemA = A; // элемент, который рассматриваем на данный момент во множестве A
            Atom itemB = B; // элемент, который рассматриваем на данный момент во множестве B

            /*Проверим условие первой вставки элемента во множества С (единожды).
                То есть проверим этот случай до прохода по элементам (цикла):*/

            /*Сначала сравниваем элементы itemA и itemB.*/
            //Если itemA.value < itemB.value, то создадим новый Atom itemC и в конструктор передадим itemA.value,
            //а дальше сдвинем itemA на следующий Atom itemA.next
            Atom itemC;
            if (itemA.value < itemB.value) {
                itemC = new Atom(itemA.value);
                itemA = itemA.next;
            }
            //Или если itemA.value > itemB.value то создадим новый Atom itemC и в конструктор передадим itemB.value,
            //а дальше сдвинем itemB на следующий Atom itemB.next
            else if (itemA.value > itemB.value) {
                itemC = new Atom(itemB.value);
                itemB = itemB.next;
            }
            //Если оба условия неверны, то наши элементы равны и мы
            //создаём новый Atom, передаём в него itemA, а после сдвигаем itemA, itemB и itemC на следующий элемент
            else {
                itemC = new Atom(itemA.value);
                itemA = itemA.next;
                itemB = itemB.next;
            }

            //После проверки, запомним начало списка Atom beginC = itemC, чтобы потом его вернуть.
            Atom beginC = itemC;

            //Начнём проходиться одновременно по элементам множества A и В, пока одно из них не закончится (В цикле).
            while (itemA != null && itemB != null) {
                //При этом будем сравнивать элементы itemA.value и itemB.value(В теле цикла):
                //Если itemA.value < itemB.value, то мы создаём новый Atom, передаём в него itemA и присваиваем новый атом в itemC,
                //а после сдвигаем itemA и itemC на следующий элемент.
                if (itemA.value < itemB.value) {
                    itemC.next = new Atom(itemA.value);
                    itemA = itemA.next;
                    itemC = itemC.next;
                }
                //или если itemA.value > itemB.value то мы создаём новый Atom, передаём в него itemB и присваиваем новый атом в itemC,
                //а после сдвигаем itemB и itemC на следующий элемент.
                else if (itemA.value > itemB.value) {
                    itemC.next = new Atom(itemB.value);
                    itemB = itemB.next;
                    itemC = itemC.next;
                }
                //Если оба условия неверны, то наши элементы равны и мы создаём новый Atom, передаём в него itemA и присваиваем новый атом в itemC.next,
                //а после сдвигаем itemA, itemB и itemC на следующий элемент
                else {
                    itemC.next = new Atom(itemA.value);
                    itemA = itemA.next;
                    itemB = itemB.next;
                    itemC = itemC.next;
                }
            }


            //Когда проход(цикл) завершится нужно проверить, какой из списков закончился, чтобы скопировать оставшиеся элементы
            //Если закончился А (itemA == null), то вызываем метод copy для itemB,
            //и результат присваиваем в itemC.next после чего возвращаем beginC
            if (itemA == null) {
                itemC.next = copy(itemB);
            }
            //В противном случае вызываем метод copy для itemА и результат присваиваем в itemC.next
            //после чего возвращаем beginC
            else {
                itemC.next = copy(itemA);
            }
            return beginC;

        }

        private Atom merge(Atom A, Atom B) {
            Atom itemC = null; // Создаем дополнительное множество С
            Atom begin = null;
            Atom itemA = A; // Одновременно идем двумя указателями по А и В:
            Atom itemB = B;
            while (itemA != null && itemB != null) { // проверяем до конца
                if (itemA.value < itemB.value) { //Если элемент А меньше элемента В, то добавляем из А и идем к следующему в А.
                    if (itemC == null) {
                        itemC = new Atom(itemA.value);
                        begin = itemC;
                    } else {
                        itemC.next = new Atom(itemA.value);
                        itemC = itemC.next;
                    }
                    itemA = itemA.next;
                    continue;
                }
                if (itemA.value > itemB.value) {
                    if (itemC == null) {
                        itemC = new Atom(itemB.value);
                        begin = itemC;
                    } else {
                        itemC.next = new Atom(itemB.value);
                        itemC = itemC.next;
                    }
                    itemB = itemB.next; //Если элемент А больше элемента В, то добавляем из В и идем к следующему в В.

                }
                if(itemA.value == itemB.value){
                    return null;
                }
            }

            if (itemA == null && itemB != null)
                itemC.next = copy(itemB); // Если одно из множеств оказалось незаконченным, докопируем его в С
            else if (itemA != null) itemC.next = copy(itemA);

            return begin; // возвращаем новое множество
        }


        private Atom intersection(Atom A, Atom B) {


            Atom itemC = null; // Создаем дополнительное множество С
            Atom begin = null;
            Atom itemA = A; // Одновременно идем двумя указателями по А и В:
            Atom itemB = B;
            while (itemA != null && itemB != null) { // проверяем до конца
                if (itemA.value < itemB.value) {
                    itemA = itemA.next; //Если элемент А меньше элемента В, то идем к следующему в А.
                    continue;
                }
                if (itemA.value > itemB.value) {
                    itemB = itemB.next; //Если элемент А больше элемента В, то идем к следующему в В.
                    continue;
                }
                if (itemC == null) {
                    itemC = new Atom(itemA.value);
                    begin = itemC;
                } else {
                    itemC.next = new Atom(itemA.value);
                    itemC = itemC.next;
                }
                itemA = itemA.next;
                itemB = itemB.next;

            }
            return begin; // возвращаем новое множество
        }

        private Atom difference(Atom A, Atom B) {
            Atom itemC = null; // Создаем дополнительное множество С
            Atom begin = null;
            Atom itemA = A; // Одновременно идем двумя указателями по А и В:
            Atom itemB = B;
            while (itemA != null && itemB != null) { // проверяем до конца
                if (itemA.value < itemB.value) { //Если элемент А меньше элемента В, то добавляем из А и идем к следующему в А.
                    if (begin == null) {
                        itemC = new Atom(itemA.value);
                        begin = itemC;
                    } else {
                        itemC.next = new Atom(itemA.value);
                        itemC = itemC.next;
                    }
                    itemA = itemA.next;
                    continue;
                }
                if (itemA.value > itemB.value) {
                    itemB = itemB.next; //Если элемент А больше элемента В, то идем к следующему в В.
                    continue;
                }
                //Если элементы равны, идем к следующим одновременно
                itemA = itemA.next;
                itemB = itemB.next;

            }

            if (itemA != null) itemC.next = copy(itemA); // Если А не закончилось, докопируем его в С
            return begin; // возвращаем новое множество
        }


    }

    public Atom head; // ссылка на голову

    //конструктор
    public SetLinkedList() {
        // Инициализация переменной head (пустое множество)
        head = null;
    }

    // копирующий конструктор
    public SetLinkedList(SetLinkedList other) {
        // присвоим this.head значение метода copy other.head.copy()
        head = other.head.copy(other.head);

    }

    // метод поиска значения в множестве
    private Atom search(Atom start, int X) {
        Atom prevItem = start;  // элемент, который рассматриваем на данный момент во множестве А

        Atom itemA = prevItem.next;

        while (itemA != null) {
            //Будем сравнивать элемент itemA.next.value с переданным X.value и
            //если itemA.next.value равняется Х.value или itemA.next.value > x.value, то возвращаем itemA.
            if (itemA.value >= X) return prevItem;
            //После передвигаем itemA на следующий элемент next
            prevItem = itemA;
            itemA = itemA.next;
        }
        //Если мы не вернули Atom из цикла, значит в множестве нет элемента X -
        //возвращаем Atom который равен последнему элементу

        return prevItem;
    }

    
    // объединение множества А с множеством В
    public SetLinkedList union(SetLinkedList b) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем пустое множество С
        if (this.head == null && b.head == null) {
            return new SetLinkedList();

        }
        //если только this пустое, то мы вызываем копирующий конструктор для множества В и присваиваем результат в С
        else if (this.head == null) {
            return new SetLinkedList((SetLinkedList) b);
        }
        //если только B пустое, то мы вызываем копирующий конструктор для множества this и присваиваем результат в С
        else if (b.head == null) {
            return new SetLinkedList(this);
        }
        /*если this == B, то есть ссылки указывают на один и тот же объект,
        значит мы передали два одинаковых множества (this = B),
        для множества this вызовем копирующий конструктор и присвоим результат в С*/
        if (this == b) {
            return new SetLinkedList(this);
        }

        //После проверок вызываем метод union для первых элементов множеств this и В и результат записываем в С
        SetLinkedList C = new SetLinkedList();
        C.head = this.head.union(this.head, b.head);

        //Возвращаем С
        return C;
    }

    
    // пересечение множества А с множеством В
    public SetLinkedList intersection(SetLinkedList b) {
        //Проверим:
        //если множество this или В пустые, то мы возвращаем пустое множество С.
        if (this.head == null || b.head == null) return new SetLinkedList();
        //Если this == B, то есть ссылки указывают на один и тот же объект,
        //значит мы передали два одинаковых множества(this = B),
        //для множества this вызовем копирующий конструктор и присвоим результат в С
        if (this == (SetLinkedList)b) return new SetLinkedList(this);

        //После проверок вызываем метод intersection для первых элементов множеств this и В
        //и результат записываем в С
        SetLinkedList C = new SetLinkedList();
        C.head = this.head.intersection(this.head, b.head);

        //Возвращаем С
        return C;
    }

    
    // разность множества А с множеством В
    public SetLinkedList difference(SetLinkedList b) {
        //Проверим:
        //Если множество this и В пустые или только this пустое, то мы возвращаем пустое множество С.
        if (this.head == null) return new SetLinkedList();
        //Если только B пустое, то мы вызываем копирующий конструктор с параметром множества A и присваиваем результат в С.
        if (b.head == null) return new SetLinkedList(this);
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
         значит мы передали два одинаковых множества(this = A и B = A), возвращаем пустое множество С*/
        if (this == b) return new SetLinkedList();

        //После проверок вызываем метод difference для первых элементов множеств А и В и результат записываем в С
        SetLinkedList C = new SetLinkedList();
        C.head = this.head.difference(this.head, b.head);

        //Возвращаем С
        return C;
    }

    
    // слияние множества А с множеством В
    public SetLinkedList merge(SetLinkedList b) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем пустое множество С
        if (this.head == null && b.head == null) return new SetLinkedList();
        //Если только this пустое, то мы вызываем копирующий конструктор с множеством B в параметрах
        //и присваиваем результат в С, затем вернём С
        if (this.head == null) return new SetLinkedList(b);
        /*Если только B пустое, то мы вызываем копирующий конструктор с множеством A в параметрах
           и присваиваем результат в С, затем вернём С*/
        if (b.head == null) return new SetLinkedList(this);
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
           значит мы передали два одинаковых множества(this = A и B = A), возвращаем пустое множество С*/
        if (this == b) return new SetLinkedList();


        //После проверок вызываем метод merge для первых элементов множеств А и В и результат записываем в С
        SetLinkedList C = new SetLinkedList();
        C.head = this.head.merge(this.head, b.head);
        if (C.head == null){
            return null;
        } else {
            //Возвращаем С
            return C;
        }
    }

    
    //  принадлежность атома к множеству
    public boolean member(int x) {
        //Проверим:
        //Если множество this пустое, то возвращаем false
        if (this.head == null) return false;
        //Если x.value == this.head.value, то возвращаем true
        if (x == this.head.value) return true;
        /*Если все проверки пройдены, то
           создадим переменную Atom isFind и присвоим ей значение, которое вернётся при
            вызове метода search с параметрами (this.head, x)*/
        Atom isFind = search(head, x);
        //Если вернулся Atom, где.next.value == x.value, то значит x присутствует в множестве и возвращаем true
        //Иначе возвращаем false
        return isFind.next.value == x;
    }

    
    // делаем множество А пустым
    public void makeNull() {
        this.head = null;
    }

    
    // добавляем атом х в множество А
    public void insert(int x) {
        //Проверим:
        //Если множество пустое
        if (this.head == null) {
            this.head = new Atom(x);
            return;
        }
        // Если х равен началу множества, то вставлять не нужно
        if (x == this.head.value) return;

        //Если необходимо вставить X в начало множества (x.value< this.head.value),
        //то создадим новый атом, присвоим ему в .next значение this.head, и обновим this.head на новый атом.
        if (x < this.head.value) {
            Atom newAtom = new Atom(x);
            newAtom.next = this.head;
            this.head = newAtom;
            return;
        }


        //Создадим переменную Atom isFind и присвоим ей значение метода this.head.search(this.head, x)
        Atom isFind = search(head, x);
        //если множество this уже содержит Х, то значение isFind.next.value == X.value и мы делаем return
        if (isFind.next != null && isFind.next.value == x) return;

        //Иначе isFind будет содержать в себе элемент множества, после которого следует вставить наш X.
        //Создадим новый Atom newItem со значением X.value
        Atom newItem = new Atom(x);
        //Сделаем, чтобы newItem ссылался на элемент, который стоит после isFind (newItem.next = isFind.next)
        newItem.next = isFind.next;
        //Сделаем, чтобы isFind ссылался на новый Atom newItem (isFind.next = newItem)
        isFind.next = newItem;
    }

    
    // удаляем атом х из множества А
    public void delete(int x) {
        //Проверим:
        //Если множество this пустое, то мы делаем return
        if (this.head == null) return;

        //Если необходимо удалить голову множества(this.head.value == x.value),
        if (this.head.value == x) {
            //То мы переопределяем this.head на this.head.next
            this.head = this.head.next;
        }

        //Создадим переменную Atom isFind и присвоим ей значение метода search
        Atom isFind = search(head, x);
        //если множество this не содержит Х, то isFind.next.value != X.value и мы делаем return
        if (isFind.next.value != x) return;

        // Иначе из метода search вернулся Atom где .next.value == X.value
        // и мы удаляем значение X.value из цепочки. присвоим isFind.next значение isFind.next.next
        isFind.next = isFind.next.next;
    }

    
    // присваиваем множеству А множество В
    public SetLinkedList assign(SetLinkedList b) {
        //Проверяем множество B на пустоту
        if (b.head == null) {
            //Если пустое, то обнулим множество А
            this.head = null;
            return this;
        }
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
           значит мы передали два одинаковых множества (this = A и B=A). Делаем return*/
        if (this == b) return this;


        //Далее в this.head присвоим значение B.head.copy(B.head)
        this.head = b.head.copy(b.head);
        return this;
    }

    
    // минимальное значение атома х в множестве А
    public int min() {
        //Проверим множество this на пустоту
        //Если оно пустое то возвращаем ошибку
        if (this.head == null)
            System.out.println("В множестве нет чисел");// throw new MyException("В множестве нет чисел");

        //возвращаем первый элемент множества (this.head)
        return this.head.value;
    }

    
    // максимальное значение атома х в множестве А
    public int max() {
        //Проверим множество this на пустоту
        //Если оно пустое то возвращаем ошибку
        if (this.head == null)
            System.out.println("В множестве нет чисел");//throw new MyException("В множестве нет чисел");

        // Определим переменную itemA в которой будем хранить рассматриваемый элемент множества this
        Atom prevItem = this.head;
        Atom itemA = prevItem.next;


        //Пока переменная itemA не пустое(цикл):
        while (itemA != null) {
            //Сдвигаем значение переменной itemA на следующий элемент next
            prevItem = itemA;
            itemA = itemA.next;
        }

        // возвращаем найденный конец множества
        return prevItem.value;
    }

    
    // эквивалентность множества А и В
    public boolean equal(SetLinkedList b) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем true
        if (this.head == null && b.head == null) return true;
        //Если множество this или В пустые, то мы возвращаем true
        if (this.head == null || b.head == null) return false;
        //Если первые элементы множеств отличаются, то возвращаем false
        if (this.head.value != b.head.value) return false;
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
          значит мы передали два одинаковых множества(this = A и B = A). Возвращаем true*/
        if (this == b) return true;

        //Заведём переменную itemA в которой будем хранить рассматриваемый элемент множества this
        Atom itemA = this.head;
        //Заведём переменную itemB в которой будем хранить рассматриваемый элемент множества В
        Atom itemB = b.head;
        //Пока у нас существуют элементы в множестве this и множестве В(цикл):
        while (itemA != null && itemB != null) {
            //Сравниваем два элемента itemA.value и itemB.value
            //Если они не равны, то возвращаем false
            if (itemA.value != itemB.value) return false;
            else {
                //сдвигаем значение itemA и itemB на следующие элементы next
                itemA = itemA.next;
                itemB = itemB.next;
            }
        }
        /*После прохода(выход из цикла) нужно проверить закончились ли элементы в обоих множествах
        Если элементы itemA и itemB равны null то возвращаем true
        иначе возвращаем false
        */
        return itemA == null && itemB == null;
    }

    
    // поиск атома х в непересекающихся множествах А и В
    public SetLinkedList find(SetLinkedList b, int x) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем null
        if (this.head == null && b.head == null) return null;

        //Далее если множество this не пустое, то для него мы вызываем метод search, чтобы проверить есть ли в нём элемент Х
        if (this.head != null) {
            if (head.value == x) return this;
            Atom isFind = search(head, x);
            //если есть, то возвращаем множество this
            if (isFind.next.value == x) return this;
        }


        //Далее если множество В не пустое, то для него мы вызываем метод search, чтобы проверить есть ли в нём элемент Х
        //Если есть, то возвращаем множество В
        if (b.head != null) {
            if (b.head.value == x) return b;
            Atom isFind = search(b.head, x);
            //если есть, то возвращаем множество this
            if (isFind.next.value == x) return b;
        }
        //Возвращаем null если нигде нет элемента Х
        return null;
    }

    
    public void print() {
        boolean firstValue = true;
        Atom tmp = this.head;
        System.out.print("{");
        while (tmp != null) {
            if(!firstValue) {
                System.out.print(", ");
            }
            firstValue = false;
            System.out.print(tmp.value);
            tmp = tmp.next;
        }
        System.out.println("}");
    }
}
