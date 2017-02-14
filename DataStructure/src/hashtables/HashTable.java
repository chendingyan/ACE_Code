package hashtables;

public interface HashTable<K,V> {
    public int size();
    public boolean isEmpty();
    public boolean contains(K key) throws IllegalArgumentException;
    public V get(K key) throws IllegalArgumentException;
    public void put(K key, V val) throws IllegalArgumentException;
    public void delete(K key) throws IllegalArgumentException;
    public Iterable<K> keys();
    // private int hash(K key);
    // private void resize(int capacity);
}
