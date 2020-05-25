package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
        }
    }

    Node head = new Node();
    Node tail = head;
    Map<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            //删除node并将其放在队头
            remove(node);
            add(node);

            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            add(node);

            return;
        }

        if (map.size()<capacity){
            Node node = new Node(key, value);
            map.put(key, node);
            add(node);
        }
        else {
            Node removeNode = tail;
            tail = tail.prev;
            tail.next = null;
            map.remove(removeNode.key);
            Node node = new Node(key, value);
            map.put(key, node);
            add(node);
        }
    }

    private void remove(Node node){
        if (node == tail){
            tail = node.prev;
            tail.next = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    private void add(Node node){
        if (head.next == null){
            head.next = node;
            node.prev = head;
            node.next = null;
            tail = node;
        }
        else {
            node.next = head.next;
            node.prev = head;
            node.next.prev = node;
            head.next = node;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }

}
