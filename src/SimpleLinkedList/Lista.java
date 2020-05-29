package SimpleLinkedList;

public interface Lista<T> {

    void add(T valor);
    void remove(int position);
    Nodo get(int position);
    boolean Buscar(T solicitado);
    void addFirst(Nodo<T> valor);
    void addLast(T valor);

}
