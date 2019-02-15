package ru.bvpotapenko.se.algo.bst;

import java.util.ArrayList;
import java.util.List;
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

    private Node min(Node node) {
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        else
            return max(node.right);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = deleteMin(node.left);
        }
        return node;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = deleteMin(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = deleteMax(node.left);
        }
        return node;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = deleteMax(root);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
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

    public void delete(Key key) {
        root = delete(root, key);
    }

    public int height() {
        if (isEmpty())
            return 0;
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    //Counts height for a given node
    private int countHeight(Node node) {
        if (isLeaf(node)) return 0;
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null)
            leftHeight = 1 + countHeight(node.left);
        if (node.right != null)
            rightHeight = 1 + countHeight(node.right);
        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public boolean isBalanced() {
        //Empty tree is balanced
        if (isEmpty()) return true;
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (isLeaf(node)) return true;
        //With height 0 or 1 still balanced
        if (height(node) < 2) return true;
        //Height is gt 1 and one of subtrees is null means not balanced
        if (node.left == null || node.right == null)
            return false;
        //Left and right subtrees exist and their heights differ more then 1
        return isBalanced(node.left) &&
                isBalanced(node.right) &&
                height(root.left) - height(root.right) <= 1;
    }

    public String toString() {
        if (isEmpty()) return "";
        return subTreeToString(root, false, new ArrayList<Integer>(), 0);
    }

    /**
     *
     * @param node  - a tree root to be drawn
     * @param isRightBranch - if this branch a child of a right branch we draw it with offset
     * @param parentLefts - a set of positions where parental nodes have left branches.
     *                    We use this positions to add vertical delimiters "|"/
     * @param hTab - horizontal offset from the root for current node
     *
     */
    private String subTreeToString(Node node, boolean isRightBranch, List<Integer> parentLefts, int hTab) {
        //Add a mark that this node has left branch
        if(node.left != null && !parentLefts.contains(hTab)){
            parentLefts.add(hTab);
        }
        //Nodes must be of the same width "[xxx]"
        //if node's value's less than 3 we add blank space: [  3], [ 99], [ -1];
        String tree = "[" + getFiller(" ", 3 - node.key.toString().length(), null)
                + node.key + "]"; //"; " + node.value+"]";
        if (node.right != null) {
            tree = tree + "--" + subTreeToString(node.right, true, parentLefts, hTab + 1);
        }
        String spacing = "";
        String filler = getFiller("      ", hTab, parentLefts);
        //For right branches and their subtrees we add offset filled with the filler
        if (isRightBranch) spacing = filler + "|\n" + filler;

        if (node.left != null) {
            tree = tree + "\n" +
                    (spacing.isEmpty() ? "|\n" : spacing) +
                    subTreeToString(node.left, isRightBranch, parentLefts, hTab);
        }
        return tree;
    }

    /**
     * This method prepares a line with branches lines above current node
     * @param filler - pattern to be repeated @times - times
     * @param times - how many times to repeat filler
     * @param delimeterPositions - positions for drawing left branches
     * @return - Console graphic tree representation
     */
    private String getFiller(String filler, int times, List<Integer> delimeterPositions) {
        if(times == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            if(delimeterPositions != null && delimeterPositions.contains(i)){
                sb.append("|");
            }
            sb.append(filler);
        }
        return sb.toString();
    }
}
