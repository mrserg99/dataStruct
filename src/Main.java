import setarray.SetArray;
import setcirclelist.SetCircleList;
import setlinkedlist.SetLinkedList;

public class Main {
    public static void main(String[] args) {
        testUnion();
        testIntersection();
        testDifference();
        testMerge();
        testMember();
        testMakeNull();
        testInsert();
        testDelete();
        testAssign();
        testMin();
        testMax();
        testEqual();
        testFind();
    }
    
    private static void testUnion(){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("UNION Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

//        SetArray C = A.union(B);
//        SetCircleList C = A.union(B);
        SetLinkedList C = A.union(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
    }

    private static void testIntersection(){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("INTERSECTION Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

//        SetArray C = A.intersection(B);
//        SetCircleList C = A.intersection(B);
        SetLinkedList C = A.intersection(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
    }

    private static void testDifference() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("DIFFERENCE Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

//        SetArray C = A.difference(B);
//        SetCircleList C = A.difference(B);
        SetLinkedList C = A.difference(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();

    }

    private static void testMerge() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MERGE Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");

//        SetArray C = A.merge(B);
//        SetCircleList C = A.merge(B);
        SetLinkedList C = A.merge(B);
        if (C != null) {
            C.print();
        } else {
            System.out.println("Множества пересекаются");
        }
    }

    private static void testMember() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MEMBER Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.println("Member 10? | " + A.member(10));
        System.out.println("Member 12? | " + A.member(12));

    }

    private static void testMakeNull() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MAKENULL Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.makeNull();
        System.out.print("Set A after makenull | ");
        A.print();
    }

    private static void testInsert(){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("INSERT Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.insert(45);
        A.insert(85);
        A.insert(99);
        System.out.print("Set A after insert | ");
        A.print();
    }

    private static void testDelete() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("DELETE Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.delete(45);
        A.delete(0);
        A.delete(44);
        System.out.print("Set A after delete | ");
        A.print();

    }

    private static void testAssign() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("ASSIGN Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        A = A.assign(B);
        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

    }

    private static void testMin() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MIN Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        int min = A.min();
        System.out.println("Min A | " + min);
    }

    private static void testMax() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MAX Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        int max = A.max();
        System.out.println("Max A | " + max);
    }

    private static void testEqual() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("EQUAL Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

//        SetArray C = new SetArray(-10, 50);
//        SetCircleList C = new SetCircleList();
        SetLinkedList C = new SetLinkedList();
        C.insert(-8);
        C.insert(-3);
        C.insert(4);
        C.insert(21);
        C.insert(49);

        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
        System.out.println("A == B | " + A.equal(B));
        System.out.println("B == C | " + B.equal(C));

    }

    private static void testFind() {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("FIND Test");

//        SetArray A = new SetArray(0, 100); // двоичные вектор
//        SetCircleList A = new SetCircleList(); // кольцевой связный список
        SetLinkedList A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

//        SetArray B = new SetArray(-10, 50);
//        SetCircleList B = new SetCircleList();
        SetLinkedList B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        System.out.print("Where is -3 | ");

//        SetArray res = A.find(B, -3);
//        SetCircleList res = A.find(B, -3);
        SetLinkedList res = A.find(B, -3);

        res.print();

        System.out.print("Where is 45 | ");
        res = A.find(B, 45);
        res.print();
    }
}
