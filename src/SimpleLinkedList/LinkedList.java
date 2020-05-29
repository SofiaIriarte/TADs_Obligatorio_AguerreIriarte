package SimpleLinkedList;

public class LinkedList<T> implements Lista<T> {

    protected T first;
    protected Nodo<T> next;
    protected Nodo<T> last;
    protected int size;

    public void add(T valor) {
        Nodo<T> nodo = new Nodo(valor);

        Nodo nodoSinSiguiente;
        for(nodoSinSiguiente = (Nodo) this.first; nodoSinSiguiente.getSiguiente() != null; nodoSinSiguiente = (Nodo) nodoSinSiguiente.getSiguiente()) {
        }

        nodoSinSiguiente.setSiguiente(nodo);
    }

    public void remove(int position) {
        Nodo actual = (Nodo) this.first;
        int count = 0;

        while(position != count - 1) {
            if (actual.equals((Object)null)) {
                System.out.println("La lista esta vacia");
                break;
            }

            actual = (Nodo) actual.getSiguiente();
            ++count;
            if (position == count - 1 && actual.getSiguiente() != null) {
                actual.setSiguiente(((Nodo) actual.getSiguiente()).getSiguiente());
            }
        }

    }

    public Nodo get(int position) {
        Nodo actual = (Nodo) this.first;
        int count = 0;
        Object var4 = null;

        while(position != count) {
            if (actual.getSiguiente().equals((Object)null)) {
                System.out.println("No existen elementos en esa posicion");
            } else {
                actual = (Nodo) actual.getSiguiente();
                ++count;
                if (position == count) {
                    ;
                }
            }
        }

        return actual;
    }

    public boolean Buscar(T solicitado) {
        boolean pertenece = false;
        Nodo actual = (Nodo) this.first;
        if (actual.equals(solicitado) & actual != null) {
            pertenece = true;
        } else {
            while(actual != solicitado & actual != null) {
                if (solicitado.equals(actual.getSiguiente())) {
                    pertenece = true;
                }
            }
        }

        return pertenece;
    }

    public void addFirst(Nodo<T> valor) {
        valor.setSiguiente(this.first);
        this.first = (T) valor;
    }

    public void addLast(T valor) {
        this.last.setSiguiente(valor);
        this.last = (Nodo<T>) valor;
    }
}

