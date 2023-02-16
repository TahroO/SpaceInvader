package tests.hashes;

import java.util.HashMap;

public class HashTest {
    public static void main(String[] args) {
        Alien alien1 = new Alien("One");
        Alien alien2 = new Alien("Two");
        HashMap<Alien, String> graphics = new HashMap<>();
        graphics.put(alien1, "Alien 1 graphics object.");
        graphics.put(alien2, "Alien 2 graphics object.");
        System.out.println(graphics.get(alien1));
        System.out.println(graphics.get(alien2));
    }

    static class Alien {
        String name;

        public Alien(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            int hashCode = super.hashCode(); // 666;
            System.out.printf("Alien %S: %s%n", name, hashCode);
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Alien;
        }
    }
}
