public class Nodo<N> {

    private N value;
    private Nodo<N> next;
    private Nodo<N> previous;

    public Nodo(N value){
        this.value = value;
    }

    public N getValue() {
        return value;
    }

    public void setValue(N value) {
        this.value = value;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public Nodo getPrevious() {
        return previous;
    }

    public void setPrevious(Nodo previous) {
        this.previous = previous;
    }
}
