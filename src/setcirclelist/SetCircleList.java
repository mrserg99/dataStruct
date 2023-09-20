package setcirclelist; // Реализация АТД множества на циклическом связном списке

import def.MySet;

public class SetCircleList implements MySet {

    // внутренний класс-элемент
    private class Atom {
        public int value; // значение элемента
        public Atom next; // ссылка на хвост


        public Atom(int val) {
            // Инициализация переменных value и next=null
            next = null;
            value = val;
        }

        //Конструктор
        private Atom(int v, Atom n) {
            value = v;//Инициализация переменных value и next
            next = n;
        }

        private void copy(Atom from, Atom to, Atom tailItem) {
            while (from != tailItem) {
                to.next = new Atom(from.value, to.next);
                from = from.next;
                to = to.next;
            }
        }

        private void insertLastElement(Atom element, Atom tail) {
            Atom tmp = tail.next;
            while (tmp != tail) {
                if (tmp.next.value > element.value) {
                    tmp.next = new Atom(element.value, tmp.next);
                    break;
                }
                tmp = tmp.next;
            }
        }

        private void printAtoms() {

            Atom tmp = this.next;
            System.out.print("{ ");
            while (tmp.value != this.value) {
                System.out.print(tmp.value + ", ");
                tmp = tmp.next;
            }

            System.out.print(this.value);
            System.out.println(" }");
        }

        private Atom union(Atom A, Atom B) {
            /*TODO  copy  убрать. */

            Atom itemA = A.next; // элемент, который рассматриваем на данный момент во множестве A
            Atom itemB = B.next; // элемент, который рассматриваем на данный момент во множестве B
            Atom itemC;
            Atom tailC;

            if (A.value > B.value || A.value == B.value) {
                itemC = new Atom(A.value);
                itemC.next = itemC;
                tailC = itemC;
            } else {
                itemC = new Atom(B.value);
                itemC.next = itemC;
                tailC = itemC;
            }


            //Будем идти по множествам и определять порядок вставки
            while (itemA != A && itemB != B) {
                //Если элемент множества А меньше чем B
                if (itemA.value < itemB.value) {

                    //Копируем элемент itemA и вставляем его в itemC.next
                    itemC.next = new Atom(itemA.value, itemC.next);
                    //Сдвигаем itemA и itemC на следующие элементы
                    itemA = itemA.next;
                    itemC = itemC.next;

                }
                //Иначе если элемент множества B меньше чем A
                else if (itemA.value > itemB.value) {

                    //Копируем элемент itemB и вставляем его в itemC.next
                    itemC.next = new Atom(itemB.value, itemC.next);
                    //Сдвигаем itemB и itemC на следующие элементы
                    itemB = itemB.next;
                    itemC = itemC.next;

                }
                //Иначе элементы равны
                else {

                    //Копируем элемент itemA и вставляем его в itemC.next
                    itemC.next = new Atom(itemA.value, itemC.next);
                    //Сдвигаем itemA, itemB и itemC на следующие элементы
                    itemA = itemA.next;
                    itemB = itemB.next;
                    itemC = itemC.next;

                }

            }

            //Если закончилось множество B
            if (itemB == B) {
                // То нужно докопировать множество А
                copy(itemA, itemC, A);

            } else {
                // Иначе нужно докопировать множество B
                copy(itemB, itemC, B);

            }
            // В конце нужно обработать вставку меньшего из двух хвостов в итоговое множество.
            // Если хвост множества А не был выбран хвостом итогового множества
            if (A.value != tailC.value) {
                // Вставляем этот элемент в итоговое множество.
                insertLastElement(A, tailC);
            } else {
                // Иначе вставляем элемент из В.
                insertLastElement(B, tailC);
            }
            //Возвращаем хвост нового множества
            return tailC;
        }


        private Atom merge(Atom A, Atom B) {
            Atom itemA = A.next; // элемент, который рассматриваем на данный момент во множестве A
            Atom itemB = B.next; // элемент, который рассматриваем на данный момент во множестве B
            Atom itemC;
            Atom tailC;


            if (A.value > B.value) {
                itemC = new Atom(A.value);
                itemC.next = itemC;
                tailC = itemC;
            } else {
                itemC = new Atom(B.value);
                itemC.next = itemC;
                tailC = itemC;
            }


            //Будем идти по множествам и определять порядок вставки
            while (itemA != A && itemB != B) {
                if (itemA.value == itemB.value) {
                    return null;
                }
                //Если элемент множества А меньше чем B
                if (itemA.value < itemB.value) {

                    //Копируем элемент itemA и вставляем его в itemC.next
                    itemC.next = new Atom(itemA.value, itemC.next);
                    //Сдвигаем itemA и itemC на следующие элементы
                    itemA = itemA.next;
                    itemC = itemC.next;

                }
                //Иначе если элемент множества B меньше чем A
                else {

                    //Копируем элемент itemB и вставляем его в itemC.next
                    itemC.next = new Atom(itemB.value, itemC.next);
                    //Сдвигаем itemB и itemC на следующие элементы
                    itemB = itemB.next;
                    itemC = itemC.next;

                }
            }

            //Если закончилось множество B
            if (itemB == B) {
                // То нужно докопировать множество А
                copy(itemA, itemC, A);

            } else {
                // Иначе нужно докопировать множество B
                copy(itemB, itemC, B);

            }
            // В конце нужно обработать вставку меньшего из двух хвостов в итоговое множество.
            // Если хвост множества А не был выбран хвостом итогового множества
            if (A.value != tailC.value) {
                // Вставляем этот элемент в итоговое множество.
                insertLastElement(A, tailC);
            } else {
                // Иначе вставляем элемент из В.
                insertLastElement(B, tailC);
            }
            //Возвращаем хвост нового множества
            return tailC;
        }


        private Atom intersection(Atom A, Atom B) {
            /*TODO copy  убрать */

            Atom itemA = A.next;// элемент, который рассматриваем на данный момент во множестве А
            Atom itemB = B.next; // элемент, который рассматриваем на данный момент во множестве B

            Atom itemC = null; // переменная, в которой будем держать последний добавленный элемент итогового множества

            // В цикле начинаем искать пересечения множества
            while (itemA != A && itemB != B) {
                // Если элементы множеств равны
                if (itemA.value == itemB.value) {
                    // Если итоговое множество пустое
                    if (itemC == null) {
                        // Начинаем его инициализировать.
                        // Создаём первый элемент
                        itemC = new Atom(itemA.value);
                        // Закольцовываем список.
                        itemC.next = itemC;
                    } else {
                        // иначе добавляем новый элемент в множество
                        itemC.next = new Atom(itemA.value, itemC.next);
                        //передвигаем итератор
                        itemC = itemC.next;
                    }
                    // сдвигаем итераторы в множестве А и В.
                    itemA = itemA.next;
                    itemB = itemB.next;
                }
                //Иначе если не равны, то принимаем решение в каком множестве сдвинуть итератор
                else if (itemA.value < itemB.value) {
                    itemA = itemA.next;
                } else {
                    itemB = itemB.next;
                }
            }
            // Возвращаем итератор итогового множества, который равен последнему добавленному, следовательно, равен хвосту множества.
            return itemC;
        }


        private Atom difference(Atom A, Atom B) {
            /*TODO  copy убрать */
            Atom firstA = A.next; // первый элемент в множестве А
            Atom firstB = B.next; // первый элемент в множестве В
            Atom itemA = firstA; // элемент, который рассматриваем на данный момент во множестве А
            Atom itemB = firstB;// элемент, который рассматриваем на данный момент во множестве B
            Atom itemC = null; // Переменная, в которую будем
            while (itemA != A && itemB != B) {
                if (itemA.value == itemB.value) {
                    itemA = itemA.next;
                    itemB = itemB.next;
                } else if (itemA.value < itemB.value) {
                    if (itemC == null) {
                        itemC = new Atom(itemA.value);
                        itemC.next = itemC;
                    } else {
                        itemC.next = new Atom(itemA.value, itemC.next);
                        itemC = itemC.next;
                    }
                    itemA = itemA.next;
                } else {
                    itemB = itemB.next;
                }
            }

            //После выхода из цикла нужно проверить, какое множество закончилось.
            //Если закончилось множество B
            if (itemB == B) {
                while (itemA != firstA) {
                    if (itemA.value != itemB.value) {
                        if (itemC != null) {
                            itemC.next = new Atom(itemA.value, itemC.next);
                            itemC = itemC.next;
                        } else {
                            itemC = new Atom(itemA.value);
                            itemC.next = itemC;
                        }

                    }
                    itemA = itemA.next;
                }

            } else {
                while (itemB != firstB) {
                    if (itemA.value < itemB.value) {
                        if (itemC != null) {
                            itemC.next = new Atom(itemA.value, itemC.next);
                            itemC = itemC.next;
                        } else {
                            itemC = new Atom(itemA.value);
                            itemC.next = itemC;
                        }
                        break;
                    }
                    itemB = itemB.next;
                }
                if (itemB == firstB) {
                    if (itemC != null) {
                        itemC.next = new Atom(itemA.value, itemC.next);
                        itemC = itemC.next;
                    } else {
                        itemC = new Atom(itemA.value);
                        itemC.next = itemC;
                    }
                }
            }
            return itemC;

        }


        private boolean equal(Atom B) {

            //Заведём переменную Atom itemA = this.next, в которой будем хранить рассматриваемый элемент множества this
            Atom itemA = this.next;
            //Заведём переменную Atom itemВ B.next; в которой будем хранить рассматриваемый элемент множества В
            Atom itemB = B.next;
            //Пока значение itemA или itemB не будет равно последнему элементу в множестве (цикл)
            while (itemA != this && itemB != B) {
                //Сравниваем два элемента itemA.value и itemB.value
                //Если они не равны, то возвращаем false
                if (itemA.value != itemB.value) return false;

                //Сдвигаем значение itemA и itemB на следующие элементы next
                itemA = itemA.next;
                itemB = itemB.next;
            }

            /*После прохода(выход из цикла) нужно проверить стали ли itemA и itemB равны последним элементам множеств
            Если элементы itemA == this и itemB равны B и при этом itemA.value == itemB.value, то возвращаем true
            иначе возвращаем false*/
            return itemA == this && itemB == B;
        }

    }

    Atom tail;// ссылка на хвост

    //конструктор
    public SetCircleList() {
        // Инициализация переменной tail = null (пустое множество)
        tail = null;
    }

    public SetCircleList(SetCircleList other) {
        // Копируем элементы из other в новое множество
        Atom item = other.tail;
        Atom newSet = new Atom(item.value);
        tail = newSet;
        item = item.next;
        while (item != other.tail) {
            newSet.next = new Atom(item.value);
            newSet = newSet.next;
            item = item.next;
        }
        newSet.next = tail;

    }


    private Atom search(Atom start, Atom x) {
        Atom itemA = start; // элемент, который рассматриваем на данный момент во множестве start
        if (itemA.value == x.value) return tail;
        //Пока значение элемента не будет равно последнему элементу в множестве (цикл):
        while (itemA != tail) {
            //будем сравнивать элемент itemA.next.value с переданным X.value и
            //если itemA.next.value равняется Х.value, то возвращаем itemA.next
            if (itemA.next.value == x.value) return itemA;
                //иначе если itemA.next.value > x.value, то возвращаем itemA
            else if (itemA.next.value > x.value) return itemA;
            //после передвигаем itemA на следующий элемент next
            itemA = itemA.next;

        }
        //Если мы не вернули Atom из цикла, значит в start нет элемента X либо он равен концу
        // возвращаем Atom который равен последнему элементу
        return tail;

    }

    @Override
    // объединение множества А с множеством В
    public MySet union(MySet b) {
        //Создаём новое множество С, в которое запишем результат,
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем пустое множество С
        if (this.tail == null && ((SetCircleList) b).tail == null) return new SetCircleList();
        //Если только this пустое, то мы вызываем копирующий конструктор для множества В и присваиваем результат в С
        if (this.tail == null) {
            return new SetCircleList(((SetCircleList) b));
        }
        //Если только B пустое, то мы вызываем копирующий конструктор для множества this и присваиваем результат в С
        if (((SetCircleList) b).tail == null) {
            return new SetCircleList(this);
        }
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
            значит мы передали два одинаковых множества (this = A и B=A),
         	для множества this вызовем копирующий конструктор и присвоим результат в С*/

        if (this == ((SetCircleList) b)) {
            return new SetCircleList(this);
        }

        //После проверок вызываем метод union для this.tail и передадим в параметры В.tail и результат записываем в С
        SetCircleList C = new SetCircleList();
        C.tail = tail.union(tail, ((SetCircleList) b).tail);

        //Возвращаем С
        return C;
    }

    @Override
    // пересечение множества А с множеством В
    public MySet intersection(MySet b) {
        //Создаём новое множество С, в которое запишем результат,
        //Проверим:
        //Если множество this или В пустые, то мы возвращаем пустое множество С.
        if (this.tail == null || ((SetCircleList) b).tail == null) return new SetCircleList();
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
         значит мы передали два одинаковых множества (this = A и B=A),
         для множества this вызовем копирующий конструктор и присвоим результат в С.*/
        if (this == ((SetCircleList) b)) return new SetCircleList(this);

        /* После проверок вызываем метод intersection для this.tail и в параметры передадим В.tail
          и результат записываем в С*/
        SetCircleList C = new SetCircleList();
        C.tail = tail.intersection(tail, ((SetCircleList) b).tail);

        //Возвращаем С
        return C;
    }

    @Override
    // разность множества А с множеством В
    public MySet difference(MySet b) {
        //Создаём новое множество С, в которое запишем результат,
        //Проверим:
        //Если множество А и В пустые или только А пустое, то мы возвращаем пустое множество С
        if (tail == null) return new SetCircleList();
        //Если только B пустое, то мы вызываем копирующий конструктор для множества A и присваиваем результат в С
        if (((SetCircleList) b).tail == null) return new SetCircleList(this);
        /*если this == B, то есть ссылки указывают на один и тот же объект,
                значит мы передали два одинаковых множества(this = A и B = A), мы возвращаем пустое множество С*/
        if (this == ((SetCircleList) b)) return new SetCircleList();

        /*После проверок вызываем метод difference для this.tail и в параметры передадим В.tail
        и результат записываем в С*/
        SetCircleList C = new SetCircleList();
        C.tail = tail.difference(tail, ((SetCircleList) b).tail);
        return C;
    }

    @Override
    // слияние множества А с множеством В
    public MySet merge(MySet b) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем пустое множество С
        if (tail == null && ((SetCircleList) b).tail == null) return new SetCircleList();
        /*Если только this пустое, то мы вызываем копирующий конструктор для множества В
         и присваиваем результат в С, затем вернём С*/
        if (tail == null) return new SetCircleList(((SetCircleList) b));
        /*если только B пустое, то мы вызываем копирующий конструктор для множества this
         и присваиваем результат в С, затем вернём С*/
        if (((SetCircleList) b).tail == null) return new SetCircleList(this);
        /*если this == B, то есть ссылки указывают на один и тот же объект,
         значит мы передали два одинаковых множества (this = A и B=A), возвращаем пустое множество С*/
        if (this == ((SetCircleList) b)) return new SetCircleList();

        //После проверок вызываем метод merge для this.tail
        //и передадим в параметры В.tail и результат записываем в С
        SetCircleList C = new SetCircleList();
        C.tail = tail.merge(tail, ((SetCircleList) b).tail);
        if (C.tail == null){
            return null;
        } else {
            //Возвращаем С
            return C;
        }
    }

    @Override
    //  принадлежность атома к множеству
    public boolean member(int x) {
        //Проверим:
        //Если множество this пустое, то возвращаем false
        if (tail == null) return false;


        //Если все проверки пройдены, то
        /*создадим переменную Atom isFind и присвоим ей значение, которое вернётся при
        вызове метода search для this.tail c параметром x*/
        Atom isFind = this.search(tail.next, new Atom(x));
        //Если вернулся Atom, где .next.value == x.value, то значит x присутствует в множестве и возвращаем true
        //Иначе возвращаем false
        return isFind.next.value == x;
    }

    @Override
    // делаем множество А пустым
    public void makeNull() {
        this.tail = null;
    }

    @Override
    // добавляем атом х в множество А
    public void insert(int x) {
        //Рассмотрим случай первой вставки в пустое множество
        /*Если конец множества равен null, то мы создаём новый элемент со значениями Atom X
        и присваиваем его конец множества и tail.next
        Прокидываем ссылки между головой и хвостом.
        Делаем return
        */
        if (tail == null) {
            tail = new Atom(x);
            tail.next = tail;
            return;
        }
        //Рассмотрим случай второй вставки в множество где только 1 элемент
        //Если начало и конец множества равны, то нужно понять куда вставить новый элемент(в голову или хвост)
        if (tail == tail.next) {
            //Если элемент Х больше чем хвост, то изменяем значение хвоста на новое value
            if (x > tail.value) {
                tail.next = new Atom(tail.value);
                tail.value = x;
            }
            //Иначе изменяем значение головы на новое value
            else {
                tail.next = new Atom(x);
            }
            tail.next.next = tail;
            //Делаем return
            return;
        }


        //Проверим меньше ли наш элемент X первого элемента множества this:
        //Если X.value < this.tail.next.value
        if (x < tail.next.value) {

            // то мы создаём переменную класса Atom tmp, в которую записываем this.tail.next.
            Atom tmp = tail.next;
            //Затем создаём новый элемент класса Atom newItem с параметрами x.value
            Atom newAtom = new Atom(x);
            //присваиваем в начало нашего множества новый элемент, а в next нового элемента присваиваем нашу временную переменную tmp
            tail.next = newAtom;
            newAtom.next = tmp;
            //Обновляем хвост
            tail = newAtom;
            //Делаем return
            return;
        }


        // Создадим переменную itemA
        Atom itemA = tail.next; // элемент, который рассматриваем на данный момент во множестве this


        //Определим куда нужно вставить наш новый элемент
        Atom newAtom = new Atom(x);
        Atom place = this.search(itemA, newAtom);
        if (place.value == tail.value) {
            Atom tmp = tail.next;
            tail.next = newAtom;
            newAtom.next = tmp;
            tail = newAtom;
        } else {

            //Запомним следующий элемент
            Atom tmp = place.next;
            //Присвоим A.next значение нового элемента
            place.next = newAtom;
            //А новый элемент пусть ссылается на tmp
            newAtom.next = tmp;
        }
    }

    @Override
    // удаляем атом х из множества А
    public void delete(int x) {
        //Проверим:
        //Если множество А пустое, то мы делаем return
        if (tail == null) return;
        /*Создадим переменную Atom isFind и присвоим ей значение метода search
        если множество this не содержит Х, то isFind.next.value != X.value и мы делаем return*/
        Atom isFind = this.search(tail.next, new Atom(x));
        if (isFind.next.value != x) return;

        /*Проверим не нужно ли удалить нам элемент из множества в котором один элемент (tail.next=tail)
        Если начало и конец множества равны, то мы обнуляем множество
        делаем return*/
        if (tail.next == tail) {
            this.makeNull();
            return;
        }


        //из метода search вернулся Atom где .next.value == X.value
        // для того, чтобы удалить значение X.value
        // сначала проверим равенство isFind.next и tail
        // Если они равны, то tail = tail.next, а isFind.next = tail
        if (isFind.next == tail) {
            isFind.next = tail.next;
            tail = isFind;
        }
        //Иначе присвоим isFind.next значение isFind.next.next
        else isFind.next = isFind.next.next;
    }

    @Override
    // присваиваем множеству А множество В
    public MySet assign(MySet b) {
        //Проверяем множество B на пустоту
        //Если пустое то обнулим множество this
        if (((SetCircleList) b).tail == null) {
            this.makeNull();
            return this;
        }
        /*Если this == B, то есть ссылки указывают на один и тот же объект,
         значит мы передали два одинаковых множества (this = A и B=A), делаем return*/
        if (this == ((SetCircleList) b)) return this;
        //Вызовем копирующий конструктор с параметром B и значение присвоим концу множества this
        SetCircleList C = new SetCircleList(((SetCircleList) b));
        tail = C.tail;
        return C;
    }

    @Override
    // минимальное значение атома х в множестве А
    public int min() {
        /*Проверим множество this на пустоту.
        Если оно пустое, то возвращаем ошибку*/
        if (tail == null) System.out.println("В множестве нет чисел"); //throw new MyException("В множестве нет чисел");
        // первый элемент множества
        return tail.next.value;
    }

    @Override
    // максимальное значение атома х в множестве А
    public int max() {
        /*Проверим множество this на пустоту.
        Если оно пустое, то возвращаем ошибку*/
        if (tail == null)
            System.out.println("В множестве нет чисел");  //throw new MyException("В множестве нет чисел");
        //возвращаем последний элемент множества
        return tail.value;
    }

    @Override
    // эквивалентность множества А и В
    public boolean equal(MySet b) {
        //Проверим:
        //Если множество this и В пустые, то мы возвращаем true
        if (tail == null && ((SetCircleList) b).tail == null) return true;
        //Если только this пустое или только В пустое, то мы возвращаем false
        if (tail == null || ((SetCircleList) b).tail == null) return false;
        //Если первые элементы множеств или последние отличаются, то возвращаем false
        if (tail.value != ((SetCircleList) b).tail.value || tail.next.value != ((SetCircleList) b).tail.next.value) return false;
        //После проверок вызываем метод equal на this.tail и передадим в параметры В.tail и возвращаем результат
        return tail.equal(((SetCircleList) b).tail);
    }

    @Override
    // поиск атома х в непересекающихся множествах А и В
    public MySet find(MySet b, int x) {
            //Проверим:
            //Если множество this и В пустые, то мы возвращаем null
            if (tail == null && ((SetCircleList) b).tail == null) return new SetCircleList();

        /*Далее если множество this не пустое, то для него мы вызываем метод search,
        чтобы проверить есть ли в нём элемент Х*/
            //Если есть, то возвращаем множество this
            if (tail != null && this.search(tail.next, new Atom(x)).next.value == x) return this;


        /*Далее если множество В не пустое, то для него мы вызываем метод search,
        чтобы проверить есть ли в нём элемент Х
        Если есть, то возвращаем множество В*/
            if (((SetCircleList) b).tail != null
                    && ((SetCircleList) b).search(((SetCircleList) b).tail.next, new Atom(x)).next.value == x)
                return ((SetCircleList) b);
            //Возвращаем пустое множество раз нигде нет элемента X
            return new SetCircleList();
    }

    @Override
    public void print() {
        if (tail == null) {
            System.out.println("{}");
            return;
        }
        Atom tmp = tail.next;
        System.out.print("{");
        while (tmp != tail) {
            System.out.print(tmp.value + ", ");
            tmp = tmp.next;
        }

        System.out.print(tail.value);
        System.out.println("}");
    }
}
