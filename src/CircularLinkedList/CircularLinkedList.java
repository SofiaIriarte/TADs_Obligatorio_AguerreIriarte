package CircularLinkedList;

public class CircularLinkedList<T> {
    private NodoDouble<T> primero;
    private NodoDouble<T> siguiente = null;
    private NodoDouble<T> anterior = null;
    private NodoDouble<T> end;

    public CircularLinkedList() {
    }

    public void add(T valor) throws ExceptionListaVacia {
        NodoDouble<T> agregar = new NodoDouble(valor);
        if (this.primero.equals((Object)null)) {
            throw new ExceptionListaVacia();
        } else {
            NodoDouble nodoSiguiente;
            for(nodoSiguiente = this.primero.getSiguiente(); nodoSiguiente.getSiguiente() != null && nodoSiguiente != this.primero; nodoSiguiente = nodoSiguiente.getSiguiente()) {
            }

            if (nodoSiguiente.equals(this.primero)) {
                NodoDouble<T> ultimo = this.end;
                ultimo = this.primero.getAnterior();
                ultimo.setSiguiente(agregar);
                agregar.setAnterior(ultimo);
                agregar.setSiguiente(this.primero);
                this.primero.setAnterior(agregar);
            }

        }
    }

    public void remove(int position) {
        NodoDouble<T> actual = this.primero;
        NodoDouble<T> before = null;
        int count = 0;

        while(position != count) {
            if (actual.equals(this.primero)) {
                count = 0;
            }

            if (actual.equals((Object)null)) {
                System.out.println("La lista esta vacia");
                break;
            }

            actual = actual.getSiguiente();
            ++count;
            if (position == count && actual != null) {
                actual.setSiguiente((NodoDouble)null);
                actual.setAnterior((NodoDouble)null);
                before = actual.getAnterior();
                before.setSiguiente(actual.getSiguiente());
            }
        }

    }
}
