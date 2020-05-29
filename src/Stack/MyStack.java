package Stack;

public interface MyStack <T>{
    void pop() throws EmptyStackException ;
    T top() throws EmptyStackException ;
    void push(Nodo<T> element) throws EmptyStackException ;
    boolean isEmpty();
    void makeEmpty() throws EmptyStackException;
}