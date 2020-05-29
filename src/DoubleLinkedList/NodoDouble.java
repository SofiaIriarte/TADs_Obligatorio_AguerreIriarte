package DoubleLinkedList;

public class NodoDouble<T> {
    private T valor;
    private CircularLinkedList.NodoDouble<T> siguiente;
    private CircularLinkedList.NodoDouble<T> anterior;

    public NodoDouble(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public void setSiguiente(CircularLinkedList.NodoDouble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public CircularLinkedList.NodoDouble<T> getSiguiente() {
        return this.siguiente;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return this.valor;
    }

    public void setAnterior(CircularLinkedList.NodoDouble<T> anterior) {
        this.anterior = anterior;
    }

    public CircularLinkedList.NodoDouble<T> getAnterior() {
        return this.anterior;
    }
}

