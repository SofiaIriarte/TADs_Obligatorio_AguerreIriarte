package tads.LinkedList ;

public interface List<L> extends Iterable<L> {

    public void add(L value);

    public void remove(int position);

    public Object get(int position);

    public int getSize();
}
