import setpartiallyordered.PartiallyOrderedSet;

public class Main {
    public static void main(String[] args) {
        PartiallyOrderedSet set = new PartiallyOrderedSet();
        int[][] arr = new int[][]{
                {1, 2},
                {2, 4},
                {2, 10},
                {1, 3},
                {6, 3},
                {4, 6},
                {4, 8},
                {9, 4},
                {5, 8},
                {7, 9},
                {9, 10},
                {7, 5},
                {3, 5},
        };
        if (set.init(arr)){
            set.print();
        } else {
            System.out.println("Невозможно инициализировать список списков так как обнаружена пара не удовлетворяющая свойству иррефлексивности");
            System.exit(1);
        }
        set.sort();
        set.print();
    }
}
