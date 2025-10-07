package patterns.lrucache;

public class Node<K,V> {
    public K key;
    public V value;
    public Node<K,V> prev;
    public Node<K,V> next;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }
}
