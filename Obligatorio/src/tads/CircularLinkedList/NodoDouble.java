package tads.CircularLinkedList;

public class NodoDouble<T> {
    private T valor;
    private NodoDouble<T> siguiente;
    private NodoDouble<T> anterior;

    public NodoDouble(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public void setSiguiente(NodoDouble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDouble<T> getSiguiente() {
        return this.siguiente;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return this.valor;
    }

    public void setAnterior(NodoDouble<T> anterior) {
        this.anterior = anterior;
    }

    public NodoDouble<T> getAnterior() {
        return this.anterior;
    }
}


