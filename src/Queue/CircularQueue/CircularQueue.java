package Queue.CircularQueue;

import java.util.Arrays;

public class CircularQueue <T> implements MyQueue<T> {

    private int tamañoActual;
    private T[] circularElem;
    private int tamañoMax;
    private int primero=0; // posicion del primero elemento
    private int ultimo=0;  //posicion del ultimo elemento

    public CircularQueue (int maxSize) {
        this.tamañoMax = maxSize;
        circularElem = (T[]) new Object[this.tamañoMax];
        tamañoActual = 0;
        //primero = -1;
        //ultimo = -1;
    }

    public T getFirst() throws EmptyQueueException{
        if (circularElem.length<1){
            throw new EmptyQueueException();
        }
        return circularElem[primero];
    }

    public void enqueue(T agregar) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException();
        }
        else {
            ultimo = (ultimo + 1) % circularElem.length;
            circularElem[ultimo] = agregar;
            tamañoActual++;

            if (primero == -1) {
                primero = ultimo;
            }
        }
    }

    public T dequeue() throws EmptyQueueException {
        T deQueuedElement;
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            deQueuedElement = circularElem[primero];
            circularElem[primero] = null;
            primero = (primero + 1) % circularElem.length;
            tamañoActual--;
        }
        return deQueuedElement;
    }

    public boolean isFull() {
        return (tamañoActual == circularElem.length);
    }

    public boolean isEmpty() {
       if(tamañoActual == 0){
        return false;
       }else{
           return true;
       }
    }

    @Override
    public String toString() {
        return "CircularQueue [" + Arrays.toString(circularElem) + "]";
    }

}






