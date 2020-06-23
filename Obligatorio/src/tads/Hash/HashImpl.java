package tads.Hash;

import tads.LinkedList.LinkedList;

import java.util.Iterator;

public class HashImpl<K, V> implements Hash<K, V> {

    private LinkedList<HashNode<K, V>>[] myHash;
    private int size;
    private HashNode<K, V> nodoAgregar;

    public LinkedList<HashNode<K, V>>[] getMyHash() {
        return myHash;
    }


    public HashImpl(int size) {

        myHash = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            myHash[i] = new LinkedList<HashNode<K, V>>();
        }

    }


    public void put(K key, V value) {
        nodoAgregar = new HashNode(key, value);
        this.colision(key, nodoAgregar);

    }


    public V find(K key) {

        V valueFind = null;
        int position = key.hashCode() % myHash.length;
        LinkedList<HashNode<K, V>> lista = myHash[position];
        if (!lista.isEmpty()) {
            for (HashNode<K, V> hashNode : lista) {
                if (hashNode.getKey().equals(key)) {
                    valueFind = hashNode.getValue();
                    break;
                }
            }
        }
        return valueFind;
    }


    public boolean contains(K key) {
        boolean encontrado = false;
        int b = key.hashCode() % myHash.length;
        LinkedList<HashNode<K, V>> nodoNext = myHash[b];
        if (nodoNext != null) {
            Iterator<HashNode<K, V>> iterator = nodoNext.iterator();
            while (iterator.hasNext() && !encontrado) {
                HashNode<K, V> temp = iterator.next();
                if (temp.getKey() == key) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    public void colision(K key, HashNode<K, V> nodoAgregar) {

        int position = key.hashCode() % myHash.length;
        LinkedList<HashNode<K, V>> listaTemp = myHash[position];
        if (listaTemp.isEmpty()) {
            listaTemp.add(nodoAgregar);
        } else {
            boolean elementRepeated = false;
            for (HashNode<K, V> hashNode : listaTemp) {
                if (key.equals(hashNode.getKey())) {
                    hashNode.setValue(nodoAgregar.getValue());
                    elementRepeated = true;
                    break;
                }

            }
            if (!elementRepeated) {
                listaTemp.add(nodoAgregar);
                size++;
            }
        }
    }

}