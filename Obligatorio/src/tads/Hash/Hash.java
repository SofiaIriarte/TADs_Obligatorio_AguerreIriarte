package tads.Hash;

public interface Hash<K,V> {

    public void put(K key, V value);
    public V find(K key);
    public boolean contains(K key);
    public void remove(K key)throws ElementoNoExiste;


}
