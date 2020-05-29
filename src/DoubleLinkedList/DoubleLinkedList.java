package DoubleLinkedList;

import CircularLinkedList.NodoDouble;

public class DoubleLinkedList<T> {
    private NodoDouble<T> primero;
    private NodoDouble<T> siguiente = null;
    private NodoDouble<T> anterior = null;

    public DoubleLinkedList() {
    }

    public void add(T valor) {
        NodoDouble<T> nodo = new NodoDouble(valor);

        NodoDouble nodoSinSiguiente;
        for(nodoSinSiguiente = this.primero; nodoSinSiguiente.getSiguiente() != null; nodoSinSiguiente = nodoSinSiguiente.getSiguiente()) {
        }

        this.anterior = nodoSinSiguiente;
        nodoSinSiguiente.setSiguiente(nodo);
    }

    public void remove(int position) {
        NodoDouble<T> actual = this.primero;
        int count = 0;

        while(position != count - 1) {
            if (actual.equals((Object)null)) {
                System.out.println("La lista esta vacia");
                break;
            }

            actual = actual.getSiguiente();
            ++count;
            if (position == count - 1 && actual.getSiguiente() != null) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                actual.getSiguiente().getSiguiente().setAnterior(actual.getAnterior());
            }
        }

    }
}

