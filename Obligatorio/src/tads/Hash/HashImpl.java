package tads.Hash;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class HashImpl<K,V> implements Hash<K,V> {

    private LinkedList<HashNode>[] myHash;
    private int size;
    private HashNode<K,V> nodoAgregar;


    public  HashImpl(int size) {

        myHash = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            myHash[i] = new LinkedList<HashNode>();
        }

    }


    public void put(K key, V value) {

        nodoAgregar=new HashNode(key,value);
        this.colision(key, nodoAgregar);

    }


    public V find(K key) {

        V valueFind=null;
        int b=key.hashCode();
        HashNode<K,V> nodoNext=myHash[b].getFirst();
        while(nodoNext.getKey()!=key){
            nodoNext=nodoNext.getNodoSiguiente();
        }
        if(nodoNext.getKey()==key){
            valueFind=nodoNext.getValue();
        }
        return valueFind;
    }


    public boolean contains(K key) {
        boolean encontrado=false;
        V valueFind=null;
        int b=key.hashCode();
        HashNode<K,V> nodoNext=myHash[b].getFirst();
        while(nodoNext.getKey()!=key){
            nodoNext=nodoNext.getNodoSiguiente();
        }
        if(nodoNext.getKey()==key){
            encontrado=true;
        }
        return encontrado;
    }

    public void remove(K key)throws ElementoNoExiste {

        if (!this.contains(key)) {
            throw new ElementoNoExiste();
        }
        V valueRemove = null;
        int b = key.hashCode();
        HashNode<K, V> nodoNext = myHash[b].getFirst();
        while (nodoNext.getKey() != key) {
            nodoNext = nodoNext.getNodoSiguiente();
        }
        if (nodoNext.getKey() == key) {

            HashNode<K, V> anterior = nodoNext.getNodoAnterior();
            anterior.setNodoSiguiente(nodoNext.getNodoSiguiente());
        }

    }

    public void colision(K key , HashNode<K,V> nodoAgregar) {

        int position = key.hashCode();

        HashNode<K,V> nodoAux=myHash[position].getFirst();
        HashNode<K, V> nodoActual = nodoAux.getNodoSiguiente();

        if (nodoAux != null) {

            while (nodoActual.getNodoSiguiente() != null) {

                nodoActual = nodoActual.getNodoSiguiente();

            }

            nodoActual.setNodoSiguiente(nodoAgregar);
        }
        if (nodoAux== null) {

            myHash[position].add(nodoAgregar);


        }



    }
}
