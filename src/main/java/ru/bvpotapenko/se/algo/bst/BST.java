package ru.bvpotapenko.se.algo.bst;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int size; //Amount of children nodes

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
        int cmp = key.compareTo(node.key);
        Node nextNode = null;
        if(cmp == 0){
            return node.value;
        }else if(cmp < 0){
            nextNode = node.left;
        }else if(cmp > 0){
            nextNode = node.right;
        }
        return get(nextNode, key);
    }
}
