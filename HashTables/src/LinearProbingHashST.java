
import java.util.LinkedList;
import java.util.Queue;

public class LinearProbingHashST <K, V> implements HashTable<K, V>{
    private static final int INIT_CAPACITY = 4;
    private int n; // number of key-value pairs in the symbol table
    private int m; //size of linear probing table
    private K[] keys;
    private V[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity){
        m = capacity;
        n = 0;
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean contains(K key){
        if(key == null) throw new IllegalArgumentException("Argument to contains() is null");
        return get(key) !=null;
    }

    private int hash(K key){
        int temp = key.hashCode() % m;
        return temp;
    }

    public V get(K key) throws IllegalArgumentException{
        if(key == null) throw new IllegalArgumentException("Argument to get() is null");
        for(int i = hash(key); keys[i] != null; i = (i+1)% m){
            if(keys[i].equals(key)){
                return vals[i];
            }
        }
        return null;
    }

    public void put(K key, V val) throws IllegalArgumentException{
        if(key == null) throw new IllegalArgumentException("Argument to put() is null");
        if(val == null){
            delete(key);
            return;
        }
        if(n >= m/2) resize(2*m);
        int i;
        for(i = hash(key); keys[i]!=null; i++){
            if(keys[i] == key){
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void delete(K key) throws IllegalArgumentException{
        if(key == null) throw new IllegalArgumentException("Argument to delete() is null");
        if(!contains(key)) return;
        int i = hash(key);
        for(i = hash(key); keys[i]!= null; i = (i+1)%m){
            if(keys[i].equals(key)){
                keys[i] = null;
                vals[i] = null;
            }
        }
        i= (i+1) % m;
        while(keys[i] != null){
            K keyToRehash = keys[i];
            V valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i+1) % m;
        }
        n--;

    }

    private void resize(int capacity) throws IllegalArgumentException{
        LinearProbingHashST<K, V> temp = new LinearProbingHashST<K,V>(capacity);
        for(int i = 0; i< m; i++){
            if(keys[i] != null){
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public Iterable<K> keys(){
        Queue<K> queue = new LinkedList<K>();
        for(int i = 0; i < m; i++){
            if(keys[i] != null) queue.add(keys[i]);
        }
        return queue;
    }
}
