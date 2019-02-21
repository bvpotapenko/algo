package ru.bvpotapenko.se.algo.hash;

public class Main {
    public static void main(String[] args) {
        ChainingHashTable<String, String> ht = new ChainingHashTable();
        ht.put("A", "A");
        ht.put("¢", "A2"); //Collision
        ht.put("B", "B");
        ht.put("£", "B2"); //Collision
        ht.put("C", "C");
        ht.put("¤", "C2"); //Collision
        System.out.println(ht);
        System.out.println();
        System.out.println("Has B? " + ht.contains("B"));
        System.out.println("Delete B: " + ht.delete("B"));
        System.out.println("Has B? " + ht.contains("B"));
        System.out.println(ht);
        System.out.println();
        System.out.println("Capacity: " + ht.getCapacity());
        System.out.println("Ensure capacity 102 ");
        ht.ensureCapacity(9974);
        System.out.println("Capacity: " + ht.getCapacity());
        System.out.println(ht);
    }
}
