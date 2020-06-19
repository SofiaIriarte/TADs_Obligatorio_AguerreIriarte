package tads.LinkedList;

public class ListaCircular<T> implements List<T> {

    private Nodo<T> first = null;
    private Nodo<T> last;
    private int size=0;

    public void add(T value){

        Nodo<T> nuevo = new Nodo<>(value);

        if(size == 0){
            this.first = nuevo;
            this.last = this.first;
            nuevo.setNext(nuevo);
            nuevo.setPrevious(nuevo);
        }
        else{
            nuevo.setPrevious(this.last);
            this.last.setNext(nuevo);
            this.last = nuevo;
            nuevo.setNext(this.first);
        }
        size++;
    }

    /*public void remove(int position){

        while(position<0){
            position = position + size;
        }

        if(position>size){
            position = position - size;
        }

        remove(position);

    }*/

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

    public T getRound(int position){

        while(position<0){
            position = position + size;
        }

        if(position>size){
            position = position - size;
        }

        return get(position);

    }

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

    public int getSize(){
        return size;
    }
}

