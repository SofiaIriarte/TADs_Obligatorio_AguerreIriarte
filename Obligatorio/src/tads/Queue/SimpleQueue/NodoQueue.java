package tads.Queue.SimpleQueue;

public class NodoQueue<T> {

  protected   NodoQueue<T> siguiente=null;
  protected   T valor=null;

    public NodoQueue( T valor){

        siguiente=null;
        this.valor=valor;

    }


    public void setSiguiente(NodoQueue<T> siguiente){
        this.siguiente=siguiente;
    }


    public void setValor(T valor){
        this.valor=valor;
    }

    public NodoQueue<T> getSiguiente(){
        return siguiente;
    }

    public T getValor(){
        return valor;
    }

}