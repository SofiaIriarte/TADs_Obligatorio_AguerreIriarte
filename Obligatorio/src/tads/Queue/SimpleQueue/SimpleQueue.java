package tads.Queue.SimpleQueue;

public class SimpleQueue<T> implements MyQueue<T> {

    private NodoQueue<T> primero=null;
    private NodoQueue<T> ultimo=null;

    @Override
    public void enqueue(T element) {
       NodoQueue <T> agregar=new NodoQueue<T>(element);

        if(ultimo.equals(null)){
            ultimo=agregar;
            primero=agregar;
        }else{
           ultimo.setSiguiente(agregar);
           ultimo=ultimo.getSiguiente();
        }

    }

    @Override
    public T dequeue() throws EmptyQueueException {
        NodoQueue<T> aux=primero;
        if(isEmpty()){
            throw new EmptyQueueException();
        }else{
            primero=aux.getSiguiente();
            if(primero==null){
                ultimo=null;
            }
        }
        return aux.getValor();
    }

    @Override
    public boolean isEmpty() {
        if (primero.equals(null)) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public T getFirst() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException();

        }else{
            return primero.getValor();
        }
    }
}
