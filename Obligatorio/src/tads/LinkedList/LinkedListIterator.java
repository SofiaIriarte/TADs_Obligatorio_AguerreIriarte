package tads.LinkedList;

import java.util.Iterator;

public class LinkedListIterator<L> implements Iterator<L> {

    private Nodo<L> nodo;

    public LinkedListIterator(Nodo<L> nodo) {
        this.nodo = nodo;
    }

    @Override
    public boolean hasNext() {
        return (nodo != null);
    }

    @Override
    public L next() {
        L valueToReturn = nodo.getValue();
        nodo = nodo.getNext();
        return valueToReturn;
    }
}
