package tads.DoubleLinkedList;

public class NodoDoble<T> {
    private T value;
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;

    public NodoDoble(T value, NodoDoble<T> siguiente, NodoDoble<T> anterior) {
        this.value = value;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}