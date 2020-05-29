package Heap;

import java.util.Arrays;

public class HeapMax<K extends Comparable<K>,V> {

    private Nodo<K,V>[] array = new Nodo[10];
    private int cantidadValues = 0;

    public HeapMax(int size){
        if(size>0) {
            this.array = new Nodo[size];
        }
        else this.array = new Nodo[1];
    }

    public void agregar(K key,V value)throws KeyYaExiste {

        Nodo<K,V> nodo = new Nodo(key,value);

        for(int i=0;i<cantidadValues;i++){
            if(array[i].getKey() == key){
                throw new KeyYaExiste();
            }
        }

        if(array.length < this.cantidadValues+1){// +1 pa que no de null pointer en comparacion de hijos
            Nodo<K,V>[] array2 = Arrays.copyOf(this.array, this.array.length*2);
            this.array = array2;
        }

        addNodoFinal(nodo);

        cantidadValues++;

    }

    public V obtenerYEliminar(){

        Nodo<K,V> returnNode = array[0];

        array[0] = array[cantidadValues -1]; //el ultimo nodo pasa a ser el primero
        boolean complete = false;
        int i=0; //usado para el indice, empieza con 0

        while(!complete){

            //primero encontrar el hijo mas grande
            if(getRightChildFromIndex(i) == null||(getLeftChildFromIndex(i) != null && getLeftChildFromIndex(i).getKey().compareTo(getRightChildFromIndex(i).getKey()) > 0)){

                //si es mas grande el izquierdo o es nulo el derecho
                if(getLeftChildFromIndex(i) != null && getLeftChildFromIndex(i).getKey().compareTo(array[i].getKey()) > 0){
                    //si el izquierdo es mas grande que el padre
                    siftDownLeft(i);
                    i = (2*i)+1;//nuevo indice
                }
                else{
                    //si no es mas grande que el padre
                    complete = true;
                }
            }

            else{

                //si es mas grande el derecho
                if(getRightChildFromIndex(i).getKey().compareTo(array[i].getKey()) >0 ){
                    siftDownRigth(i);
                    i = (2*i)+2;
                }
                else{
                    complete = true;
                }
            }

        }

        this.array[cantidadValues-1] = null;
        this.cantidadValues --;

        return returnNode.getValue();

    }

    public int getSize(){
        return this.cantidadValues;
    }

    private Nodo getParentFromIndex(int indice){

        int parentIndex;

        if(indice > 0) {
           parentIndex = (indice - 1) / 2;
        }
        else {
            parentIndex = 0;
        }
        return array[parentIndex];
    }

    private Nodo getLeftChildFromIndex(int indice){ //hijos en 2k+1 y 2k+2
            int childIndex = (2 * indice + 1);
            return array[childIndex];
    }
    private Nodo getRightChildFromIndex(int indice){
        if(indice < cantidadValues){
            int childIndex = (2 * indice + 2);
            return array[childIndex];
        }
        else{
            return null;
        }
    }

    private void siftUp(int indice){

        if(indice != 0) {
            Nodo aux = getParentFromIndex(indice);
            array[(indice - 1) / 2] = array[indice];
            array[indice] = aux;
        }

    }

    private void siftDownLeft(int indice){
        Nodo<K,V> aux = getLeftChildFromIndex(indice);
        array[2*indice+1] = array[indice];
        array[indice] = aux;
    }

    private void siftDownRigth(int indice){
        Nodo<K,V> aux = getRightChildFromIndex(indice);
        array[2*indice+2] = array[indice];
        array[indice] = aux;
    }

    private void addNodoFinal(Nodo<K,V> nodo) {//es para usar en otra funcion

        int indice = cantidadValues;//entra al while con el indice del ultimo en el array
        array[indice] = nodo;//agrega el dato en el indice final
        K key = nodo.getKey();


        if (indice > 0) {
            while (getParentFromIndex(indice).getKey().compareTo(key) < 0) {//
                siftUp(indice);
                indice = (indice - 1) / 2;
            }
        }
    }
}
