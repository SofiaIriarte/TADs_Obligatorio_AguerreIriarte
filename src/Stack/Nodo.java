public class Nodo<T> {

    Nodo siguiente=null;
    T valor=null;

    public Nodo( T valor){

        siguiente=null;
        this.valor=valor;
    }


    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente=siguiente;
    }

    public void setValor(T valor){
        this.valor=valor;
    }

    public Nodo <T> getSiguiente(){
        return siguiente;
    }

    public T getValor(){
        return valor;
    }



}
