package tads.BinaryTree;

import java.util.ArrayList;

public interface BinaryTree <T extends Comparable<T>> {


    ArrayList <T> inOrder();

    void add(T oElement) throws InvalidValue;

    BinaryTree<T> delete(T oElement);

    boolean exists(T oElement);

    T getMinValue();

    T getMaxValue();

    int getHeight();

    T getRoot();

    BinaryTree<T> getLeftChild();

    BinaryTree<T> getRightChild();

    BinaryTree<T> find(T oElement) throws InvalidValue;

}
