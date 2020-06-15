package tads.SimpleLinkedList;

public class Nodo<T> {
    private T valor;
    private T anterior;
    private T siguiente;

    public Nodo(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public void setSiguiente(T Siguiente) {
        this.siguiente = this.siguiente;
    }

    public T getSiguiente() {
        return this.siguiente;
    }

    public void setAnterior(T anterior) {
        this.anterior = anterior;
    }

    public T getAnterior() {
        return this.anterior;
    }
}
