package LinkedList;

public class LinkedList<T> implements List<T> {

    private Nodo<T> first = null;
    private Nodo<T> last;
    private int size=0;

    @Override
    public void add(T value) {

        Nodo<T> nuevo = new Nodo<>(value);

        if(size == 0){
           this.first = nuevo;
           this.last = this.first;
        }
        else{
            nuevo.setPrevious(this.last);
            this.last.setNext(nuevo);
            this.last = nuevo;
        }
        size++;
    }

    @Override
    public void remove(int position) {

        try {

            if (0 > position || position > (size - 1)) {
                throw new NullPointerException();
            }

            Nodo<T> auxPrev;
            Nodo<T> auxNext;
            Nodo<T> auxDel = this.first;

            if(position == 0){
                auxNext = this.first.getNext();
                this.first = auxNext;
            }
            else if(position == (size-1)){
                auxPrev = this.last.getPrevious();
                auxPrev.setNext(null);
                this.last = auxPrev;
            }
            else{

                for(int i=0; i < position; i++){
                    auxDel = auxDel.getNext();
                }

                auxNext = auxDel.getNext();
                auxPrev = auxDel.getPrevious();

                auxPrev.setNext(auxNext);
                auxNext.setPrevious(auxPrev);
            }

            size--;
        }
        catch(NullPointerException e){
            System.out.println("No se elimino objeto por indice invalido (" +position +")");
        }
    }

    @Override
    public T get(int position) {

        Nodo<T> buscado = this.first;
        T value = null;

        try {

            if(position<0){
                throw new  NullPointerException();
            }

            for (int i=0; i < position; i++) {
                buscado = buscado.getNext();
            }
            value = buscado.getValue();
        }
        catch (NullPointerException e){
            System.out.println("Indice invalido (indx max=" +(size-1) +")");
        }
        return value;
    }

    public void addFirst(T value){
        Nodo<T> nuevo = new Nodo<>(value);

        if(size>0) {
            nuevo.setNext(this.first);
        }

        this.first = nuevo;

        size++;
    } //le falta para doublelink

    public void addLast(T value){
        add(value);
    }

}
