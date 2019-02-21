package ru.bvpotapenko.se.algo.hash;

public class QuadraticProbingHashTable<K, V> {
        private int M = 97;
        private int size = 0;
        private Object[] keys = new Object[M];
        private Object[] values = new Object[M];

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int hash(K key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        public boolean contains(K key) {
            return get(key) != null;
        }

        public V get(K key) {
            if (key == null) throw new IllegalArgumentException("Key can't be Null");
            int step = 1;
            for (int i = hash(key); keys[i] != null; i = (i + step^2) % M) {
                if (((K) keys[i]).equals(key)) {
                    return (V) values[i];
                }
                step++;
            }
            return null;
        }

        public void put(K key, V value) {
            if (key == null) throw new IllegalArgumentException("Key can't be Null");
            if (size >= M - 1) throw new ArrayIndexOutOfBoundsException("Table is full");
            int i = hash(key);
            int step = 1;
            for (; keys[i] != null; i = (i + step^2) % M) {
                if (keys[i].equals(key)) {
                    values[i] = value;
                    return;
                }
                step++;
            }
            keys[i] = key;
            values[i] = value;
            size++;
        }
}
