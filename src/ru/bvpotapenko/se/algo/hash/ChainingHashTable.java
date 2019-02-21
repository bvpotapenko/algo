package ru.bvpotapenko.se.algo.hash;

import java.math.BigInteger;

public class ChainingHashTable<K, V> {
    private final int INITIAL_CAPACITY = 97;
    private int size = 0;
    private Object[] st = new Object[INITIAL_CAPACITY];
    private final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return st.length;
    }

    private int hash(K key) {
        return hashService(key, getCapacity());
    }
    //Used for rehash
    private int hashService(K key, int mod) {
        return (key.hashCode() & 0x7fffffff) % mod;
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key can't be Null");
        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null) {
            if (key.equals(x.key)) return x.value;
            x = x.next;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V put(K key, V value) {
        if (key == null) return null;
        int i = hash(key);
        if (st[i] == null) {
            st[i] = new Node(key, value, null);
        } else {
            Node x = (Node) st[i];
            while (x.next != null) {
                if (x.key.equals(key)) {
                    x.value = value;
                    return value;
                }
                x = x.next;
            }
            x.next = new Node(key, value, null);
        }
        size++;
        return value;
    }

    private void putCopy(Object[] st, K key, V value) {
        int i = hashService(key, st.length);
        st[i] = new Node(key, value, (Node) st[i]);
    }

    public void ensureCapacity(int minCapacity) {
        int minExpand = (st != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : INITIAL_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - st.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        BigInteger nextPrime = new BigInteger(String.valueOf(minCapacity)).nextProbablePrime();
        int newCapacity;
        try {
            newCapacity = nextPrime.intValueExact();
        } catch (ArithmeticException e) {
            newCapacity = MAX_ARRAY_SIZE;
        }
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        st = copyOf(st, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }


    private Object[] copyOf(Object[] arr, int newCapacity) {
        Object[] newSt = new Object[newCapacity];
        for (Object o : st) {
            Node x = (Node) o;
            while (x != null) {
                putCopy(newSt, x.key, x.value);
                x = x.next;
            }
        }
        return newSt;
    }

    /**
     * [
     * 	(65):
     * 		A -> A;
     * 		¢ -> A2;
     * 	(66):
     * 		B -> B;
     * 		£ -> B2;
     * 	(67):
     * 		C -> C;
     * 		¤ -> C2;
     * ]
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < st.length; i++) {
            if (st[i] != null)
                sb.append("\n\t").append("(").append(i).append("):");
            Node x = (Node) st[i];
            while (x != null) {
                sb.append("\n\t\t").append(x.key).append(" -> ").append(x.value).append(";");
                x = x.next;
            }
        }
        sb.append("\n").append("]");
        return sb.toString();
    }

    public V delete(K key) {
        if (key == null) throw new IllegalArgumentException("Key can't be Null");
        if(size == 0) return null;

        int i = hash(key);
        Node x = (Node) st[i];
        Node previous = x;
        if (x != null) {
            while (x.next != null && x.key != key) {
                previous = x;
                x = x.next;
            }
            if (key.equals(x.key)) {
                V value = x.value;
                if(st[i] == x){ //If we remove first item in the list
                    st[i] = x.next;
                }else{
                    previous.next = x.next;
                }
                x.value = null;
                x.next = null;
                size--;
                return value;
            }
        }
        return null;
    }
}
