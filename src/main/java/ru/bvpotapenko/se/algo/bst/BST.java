package ru.bvpotapenko.se.algo.bst;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int size; //Amount of nodes in the tree that grows from current node

        public Node(Key key, Value value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
    private Node root = null;

    public boolean isEmpty(){return root == null;}
    public int size(){return size(root);}

    private int size(Node node){
        if(node == null)
            return 0;
        else
            return node.size;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node node, Key key){
        if(key == null) throw new IllegalArgumentException("Null key");
        if(node == null) return null;
        final int cmp = key.compareTo(node.key);
        if(cmp == 0){
            return node.value;
        }else if(cmp < 0){
            return get(node.left, key);
        }else{ //cmp > 0)
            return get(node.right, key);
        }
    }
    public boolean containsKey(Key key){return get(key) != null;}
    public boolean containsValue(Value value){return false;} // TODO: 15-Feb-19

    private Node put(Node node, Key key, Value value){
        if(node == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0){
            node.value = value;
        }else if(cmp < 0){
            node.left = put(node.left, key, value);
        }else { //cmp > 0
            node.right = put(node.right, key, value);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }
    public void put(Key key, Value value){
        if(key == null) throw new IllegalArgumentException("Key can't be null");
        root = put(root, key, value);
    }
}
