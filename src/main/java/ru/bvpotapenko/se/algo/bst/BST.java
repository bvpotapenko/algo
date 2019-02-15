package ru.bvpotapenko.se.algo.bst;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size; //Amount of nodes in the tree that grows from current node
        int height; //Distance from current node to it's farthest leaf;
        int level; //This node level from root. Is used for HW.

        public Node(Key key, Value value, int size, int height, int level) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = height;
            this.level = level;
        }
    }

    private Node root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.size;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (node == null) return null;
        final int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else { //cmp > 0)
            return get(node.right, key);
        }
    }

    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    public boolean containsValue(Value value) {
        return containsValue(root, value);
    }

    private boolean containsValue(Node node, Value value) {
        if (node == null) return false;
        if (node.value.equals(value)) {
            return true;
        }
        if (containsValue(node.left, value)) {
            return true;
        }
        return containsValue(node.right, value);
    }

    private Node put(Node node, Key key, Value value, int level) {
        if (node == null) {
            return new Node(key, value, 1, 0, level);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value, node.level + 1);
        } else { //cmp > 0
            node.right = put(node.right, key, value, node.level + 1);
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key can't be null");
        root = put(root, key, value, 0);
    }

    private Node min(Node node){
        if(node.left == null)
            return node;
        else
            return min(node.left);
    }

    private Node max(Node node){
        if(node.right == null)
            return node;
        else
            return max(node.right);
    }

    private Node deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }else{
            node.left = deleteMin(node.left);
        }
        return node;
    }
    public void deleteMin(){
        if(isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = deleteMin(root);
    }

    private Node deleteMax(Node node){
        if (node.right == null){
            return node.left;
        }else{
            node.right = deleteMax(node.left);
        }
        return node;
    }
    public void deleteMax(){
        if(isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = deleteMax(root);
    }

    private Node delete(Node node, Key key){
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = delete(node.left, key);
        }else if (cmp > 0){
            node.right = delete(node.right, key);
        }else{
             if (node.left == null) {
                 return node.right;
             }
            if (node.right == null) {
                return node.left;
            }
            Node tmp = node;
            node = max(node.left);
            node.left = deleteMax(node.left);
            node.right = tmp.right;
            tmp = null;
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = isLeaf(node) ? 0 : Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    public int height(){
        if(isEmpty())
            return 0;
        return height(root);
    }
    private int height(Node node){
        if(node == null) return 0;
        return node.height;
    }

    //Counts height for a given node
    private int countHeight(Node node){
        if (isLeaf(node)) return 0;
        int leftHeight = 0;
        int rightHeight = 0;
        if(node.left != null)
            leftHeight = 1 + countHeight(node.left);
        if(node.right != null)
            rightHeight = 1 + countHeight(node.right);
        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    private boolean isLeaf(Node node){
        if(node.left == null && node.right == null)
            return true;
        return false;
    }

    public boolean isBalanced(){
        //Empty tree is balanced
        if(isEmpty()) return true;
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){
        if(isLeaf(node)) return true;
        //With height 0 or 1 still balanced
        if(height(node) < 2) return true;
        //Height is gt 1 and one of subtrees is null means not balanced
        if(node.left == null || node.right == null)
            return false;
        //Left and right subtrees exist and their heights differ more then 1
        if(isBalanced(node.left) &&
                isBalanced(node.right) &&
                height(root.left) - height(root.right) <= 1){
            return true;
        }else
            return false;
    }

    public String toString(){
        if(isEmpty()) return "";
        return subTreeToString(root, false, root.left != null);
    }
    private String subTreeToString(Node node, boolean isRightBranch, boolean parentHasLeft){
        parentHasLeft = parentHasLeft || node.left != null;
        String tree = "["+ getFiller(" ", 2 - node.key.toString().length())
                + node.key + "]"; //"; " + node.value+"]";
        if(node.right != null){
            tree = tree + "--" + subTreeToString(node.right, true, parentHasLeft);
        }
        String spacing = "";
        String leftSibling = "";
        if(parentHasLeft) leftSibling = "|";
        if(isRightBranch) spacing = leftSibling + getFiller("       ", node.level - 1) + "|\n" +
                leftSibling + getFiller("       ", node.level - 1);

        if(node.left != null){
            tree = tree + "\n" +
                    (spacing.isEmpty() ? leftSibling+"\n" : spacing) +
                    subTreeToString(node.left, false, parentHasLeft);
        }
        return tree;
    }

    private String getFiller(String filler, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++)
            sb.append(filler);
        return sb.toString();
    }

    public String simpleToString(){
        if(isEmpty()) return "";
        return simpleToString(root, 0);
    }
    private String simpleToString(Node node, int marker){
        if (node == null) return "";
        String tree =
                "[" +node.key + "; " + node.value + "] -- ("+marker + simpleToString(node.right, marker+1) +
                "\n" + simpleToString(node.left, marker + 1) + marker+")";
        return tree;
    }
}
