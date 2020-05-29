package BinaryTree;

import java.util.ArrayList;

public class BinaryTreeImp<T extends Comparable<T> > implements BinaryTree<T> {

    private BinaryTree<T> leftChild;

    private BinaryTree<T> rightChild;

    private T value;

    private int amount;

    public BinaryTreeImp(T oValue) throws InvalidValue {

        if (oValue == null) {

            throw new InvalidValue();

        }

        value = oValue;
        amount = 1;
    }

    public ArrayList<T> inOrder(){
        ArrayList <T> inOrder= new ArrayList <T>();
        if(getRightChild() != null){
            inOrder.addAll(getRightChild().inOrder());
        }
        inOrder.add(getRoot());
        if(getLeftChild() != null){
            inOrder.addAll(getRightChild().inOrder());
        }
        return inOrder;
    }


    public void add(T oElement) throws InvalidValue {

        if (oElement == null) {  throw new InvalidValue();

        }

        int nCompare = oElement.compareTo(getValue());

        if (nCompare == 0) {
            amount++;

        } else if (nCompare > 0) {

            if (hasRightChild()) {

                rightChild.add(oElement);

            } else {

                rightChild = new BinaryTreeImp<T>(oElement);

            }

        } else {

            if (hasLeftChild()) {

                leftChild.add(oElement);

            } else {

                leftChild = new BinaryTreeImp<T>(oElement);

            }

        }

    }


    public BinaryTree<T> delete(T oElement) {
        BinaryTree<T> oToReturn = this;

        if (oElement != null) {

            int nCompare = oElement.compareTo(getValue());

            if (nCompare > 0) {

                if (hasRightChild()) {

                    rightChild = rightChild.delete(oElement);

                }

            } else if (nCompare < 0) {

                if (hasLeftChild()) {

                    leftChild = leftChild.delete(oElement);

                }

            } else {

                if (amount > 1) {

                    amount-=1;

                } else {

                    if (!hasLeftChild() && !hasRightChild()) {
                        oToReturn = null;

                    } else {

                        if (hasRightChild()) {

                            T oMin = rightChild.getMinValue();

                            value = oMin;

                            rightChild = rightChild.delete(oMin);

                        } else {

                            T oMax = leftChild.getMaxValue();

                            value = oMax;

                            leftChild = leftChild.delete(oMax);

                        }
                    }
                }

            }


        }

        return oToReturn;
    }


    public boolean exists(T oElement) {
        boolean bReturn = false;

        if (oElement != null) {

            int nCompare = oElement.compareTo(getValue());

            if (nCompare == 0) {

                bReturn = true;

            }
            else if (nCompare > 0) {

                if (hasRightChild()) {

                    bReturn = rightChild.exists(oElement);

                }

            } else {

                if (hasLeftChild()) {

                    bReturn = leftChild.exists(oElement);

                }

            }

        }

        return bReturn;
    }

    public BinaryTree <T> find(T oElement) throws InvalidValue{

        BinaryTree <T> arbolBuscado = new BinaryTreeImp<T>(null);

        if(getRoot().compareTo(oElement) == 0){
            arbolBuscado=this;
        }
        if(getRoot().compareTo(oElement) < 0){
            arbolBuscado=this.getLeftChild().find(oElement);
        }
        if(getRoot().compareTo(oElement) > 0){
            arbolBuscado=this.getRightChild().find(oElement);
        }

        return arbolBuscado;

    }


    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }

    public BinaryTree<T> getRightChild() {
        return rightChild;
    }


    public int getHeight() {
        int nHeight = 1;
        int nRightHeight = 0;
        int nLeftHeight = 0;

        if (hasRightChild()) {

            nRightHeight = rightChild.getHeight();

        }

        if (hasLeftChild()) {

            nLeftHeight = leftChild.getHeight();

        }

        if (nRightHeight > nLeftHeight) {

            nHeight += nRightHeight;

        } else {

            nHeight += nLeftHeight;

        }

        return nHeight;
    }


    public T getValue() {
        return value;
    }

    public T getMinValue() {
        T oMin = value;

        if (hasLeftChild()) {

            oMin = leftChild.getMinValue();

        }

        return oMin;
    }


    public T getMaxValue() {
        T oMax = value;

        if (hasRightChild()) {

            oMax = rightChild.getMaxValue();

        }

        return oMax;
    }


    public T getRoot() {
        return getValue();
    }

    private boolean hasRightChild() {

        return rightChild != null;

    }

    private boolean hasLeftChild() {

        return leftChild != null;
    }


}


