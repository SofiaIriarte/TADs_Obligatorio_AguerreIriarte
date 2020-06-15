package tads.Heap;

public class Nodo< K extends Comparable<K>,V>{

    private K key;
    private V value;


    public Nodo(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
