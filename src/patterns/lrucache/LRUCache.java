package patterns.lrucache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K,V> {
    private final int capacity;
    private final DoubleLinkedList<K,V> dll;
    private final Map<K,Node<K,V>> map = new ConcurrentHashMap<K,Node<K,V>>();

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.dll = new DoubleLinkedList<K,V>();
    }

    public synchronized void put(K key, V value){
        Node<K,V> node = new Node<K,V>(key,value);
        if(map.size()>=capacity){
            Node<K,V> last = dll.removeLast();
            map.remove(last.key);
        }
        dll.addFirst(node);
        map.put(node.key,node);
    }

    public synchronized V get(K key){
        if(!map.containsKey(key)) return null;
        Node<K,V> node = map.get(key);
        dll.moveToTop(node);
        return node.value;
    }

    public synchronized void remove(Node<K,V> node){
        dll.remove(node);
        map.remove(node.key);
    }
}
