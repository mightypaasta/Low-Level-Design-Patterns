package patterns.lrucache;

public class DoubleLinkedList<K,V> {
    private Node<K,V> head;
    private Node<K,V> tail;

    public DoubleLinkedList(){}

    public void addFirst(Node<K,V> node){
        if(head==null){
            head = node;
        }else{
            head.prev = node;
            node.next = head;
            head = node;
        }
        if(tail==null){
            tail = node;
        }

    }

    public void moveToTop(Node<K,V> node){
        Node<K,V> prevnode = node.prev;
        Node<K,V> nextNode = node.next;
        if(prevnode!=null){
            prevnode.next = nextNode;
        }else{
            return;
        }
        if(nextNode!=null){
            nextNode.prev = prevnode;
        }else{
            tail = prevnode;
        }
        Node<K,V> topNode = head;
        topNode.prev = node;
        node.next = topNode;
        head = node;
    }

    public Node<K,V> removeLast(){
        Node<K,V>last = tail;
        Node<K,V> secondLastNode = tail.prev;
        secondLastNode.next = null;
        tail = secondLastNode;
        return last;
    }

    public void remove(Node<K,V> target){
        if(head == target && tail==target){
            head = null;
            tail = null;
        }else if(head==target){
            head =head.next;
        }else if(tail==target){
            tail = tail.prev;
        }else{
            Node<K,V> node = head;
            while(target!=node){
                node = node.next;
            }
            if(node!=null){
                Node<K,V> prevNode = node.prev;
                Node<K,V> nextNode = node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
        }

    }

}
