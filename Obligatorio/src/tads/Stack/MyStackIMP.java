package tads.Stack;

import java.util.EmptyStackException;

public class MyStackIMP<T> implements MyStack<T> {
    Nodo<T> ultimo=null;

    public void pop() throws EmptyStackException {
        if(ultimo==null){ throw new EmptyStackException();
        }else{
            ultimo=ultimo.getSiguiente();
        }
        }

     public T top() throws EmptyStackException {
        if (ultimo==null){throw new EmptyStackException();
        }
        else{
            return ultimo.getValor();
        }
     }


     public void push(T element) throws EmptyStackException  {
         Nodo<T> temp = new Nodo<>(element);
        temp.setSiguiente(ultimo);
        ultimo=temp;
        if(element.equals(null)){
            throw new EmptyStackException();
        }
     }


     public boolean isEmpty() {
         boolean vacio=false;
         if(ultimo!=null){
             vacio=false;
         }else{
             vacio=true;
         }
         return vacio;
     }


     public void makeEmpty() throws EmptyStackException {
        while(ultimo!=null){
            ultimo=null;
        }
     }
 }