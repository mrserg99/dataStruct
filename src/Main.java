import def.MySet;
import setarray.SetArray;
import setcirclelist.SetCircleList;
import setlinkedlist.SetLinkedList;

public class Main {
    public static void main(String[] args) {
        MySet A = getAWithData();
        MySet B = getBWithData();
        testUnion(A, B);

        A = getAWithData();
        B = getBWithData();
        testIntersection(A, B);

        A = getAWithData();
        B = getBWithData();
        testDifference(A, B);

        A = getAWithData();
        B = getBWithData();
        testMerge(A, B);

        A = getAWithData();
        testMember(A);

        A = getAWithData();
        testMakeNull(A);

        A = getAWithData();
        testInsert(A);

        A = getAWithData();
        testDelete(A);

        A = getAWithData();
        B = getBWithData();
        testAssign(A, B);

        A = getAWithData();
        testMin(A);

        A = getAWithData();
        testMax(A);

        A = getAWithData();
        B = getBWithData();
        MySet C = getCWithData();
        testEqual(A, B, C);

        A = getAWithData();
        B = getBWithData();
        testFind(A, B);
    }
    
    private static void testUnion(MySet A, MySet B){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("UNION Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        MySet C = A.union(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
    }

    private static void testIntersection(MySet A, MySet B){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("INTERSECTION Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        MySet C = A.intersection(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
    }

    private static void testDifference(MySet A, MySet B) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("DIFFERENCE Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        MySet C = A.difference(B);
        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();

    }

    private static void testMerge(MySet A, MySet B) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MERGE Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        MySet C = A.merge(B);
        if (C != null) {
            C.print();
        } else {
            System.out.println("Множества пересекаются");
        }
    }

    private static void testMember(MySet A) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MEMBER Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.println("Member 10? | " + A.member(10));
        System.out.println("Member 12? | " + A.member(12));

    }

    private static void testMakeNull(MySet A) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MAKENULL Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.makeNull();
        System.out.print("Set A after makenull | ");
        A.print();
    }

    private static void testInsert(MySet A){
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("INSERT Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.insert(45);
        A.insert(85);
        A.insert(99);
        System.out.print("Set A after insert | ");
        A.print();
    }

    private static void testDelete(MySet A) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("DELETE Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        A.delete(45);
        A.delete(0);
        A.delete(44);
        System.out.print("Set A after delete | ");
        A.print();

    }

    private static void testAssign(MySet A, MySet B) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("ASSIGN Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        A = A.assign(B);
        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

    }

    private static void testMin(MySet A) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MIN Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        int min = A.min();
        System.out.println("Min A | " + min);
    }

    private static void testMax(MySet A) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("MAX Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        int max = A.max();
        System.out.println("Max A | " + max);
    }

    private static void testEqual(MySet A, MySet B, MySet C) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("EQUAL Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        System.out.print("\u001B[35m"+ "Set C | " + "\u001B[0m");
        C.print();
        System.out.println("A == B | " + A.equal(B));
        System.out.println("B == C | " + B.equal(C));

    }

    private static void testFind(MySet A, MySet B) {
        System.out.println("\u001B[35m"+ "-------------------------------" + "\u001B[0m");
        System.out.println("FIND Test");

        System.out.print("\u001B[35m"+ "Set A | " + "\u001B[0m");
        A.print();

        System.out.print("\u001B[35m"+ "Set B | " + "\u001B[0m");
        B.print();

        System.out.print("Where is -3 | ");
        MySet res = A.find(B, -3);
        res.print();

        System.out.print("Where is 45 | ");
        res = A.find(B, 45);
        res.print();
    }

    private static MySet getAWithData(){
//        MySet A = new SetArray(0, 100); // двоичные вектор
//        MySet A = new SetCircleList(); // кольцевой связный список
        MySet A = new SetLinkedList(); // связный список
        A.insert(0);
        A.insert(1);
        A.insert(10);
        A.insert(45);
        A.insert(85);
        A.insert(99);

        return A;
    }

    private static MySet getBWithData(){
//        MySet B = new SetArray(-10, 50);
//        MySet B = new SetCircleList();
        MySet B = new SetLinkedList();
        B.insert(-8);
        B.insert(-3);
        B.insert(0);
        B.insert(1);
        B.insert(4);
        B.insert(21);
        B.insert(49);

        return B;
    }

    private static MySet getCWithData(){
//        MySet C = new SetArray(-10, 50);
//        MySet C = new SetCircleList();
        MySet C = new SetLinkedList();
        C.insert(-8);
        C.insert(-3);
        C.insert(4);
        C.insert(21);
        C.insert(49);

        return C;
    }
}
