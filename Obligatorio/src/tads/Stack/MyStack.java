package tads.Stack;

import java.util.EmptyStackException;

public interface MyStack <T>{
    void pop() throws EmptyStackException;
    T top() throws EmptyStackException ;
    void push(T element) throws EmptyStackException ;
    boolean isEmpty();
    void makeEmpty() throws EmptyStackException;
}