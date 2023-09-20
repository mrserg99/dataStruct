package def;

public interface MySet {
    public MySet union(MySet b);
    public MySet intersection(MySet b);
    public MySet difference(MySet b);
    public MySet merge(MySet b);
    public boolean member(int x);
    public void makeNull();
    public void insert(int x);
    public void delete(int x);
    public MySet assign(MySet b);
    public int min();
    public int max();
    public boolean equal(MySet b);
    public MySet find(MySet b, int x);
    public void print();

}
