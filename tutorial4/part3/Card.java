package tutorial4.part3;

// Convert the following array program into ArrayList version:
/*
public class Card {
    public static void main(String[] args) {
        int[] deck = new int[52];
        String[] suits = {"Spade", "Heart", "Diamond", "Club"};

        // Initialize cards
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i;
        }

        // Shuffle the cards.
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }

        // Display the card(s) that is heart from the first five cards.
        for (int i = 0; i < 5; i++) {
            String suit = suits[deck[i] / 13];
            if (suit.equals("Heart")) {
                System.out.println("Card number " + deck[i] + " is " + suit);
            }
        }
    }
}
*/

import java.util.*;

/**
 * Key concepts from this tutorial exercise:
 * 1. import java.util.*; to use ArrayList.
 * 2. ArrayList<Integer> deck = new ArrayList<>(); to create an ArrayList of Integer.
 * 3. an_array[index] => an_arrayList.get(index)
 * 4. an_array[index] = value => an_arrayList.set(index, value)
 * 5. an_array.length => an_arrayList.size()
 * 6. How to program swap() for ArrayList:
    for (int i = 0; i < an_arrayList.size(); i++) {
        int index = (int) (Math.random() * an_arrayList.size());
        int temp = an_arrayList.get(i);
        an_arrayList.set(i, an_arrayList.get(index));
        an_arrayList.set(index, temp);
    }
 */
public class Card {
    public static void main(String[] args) {
        ArrayList<Integer> deck = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();
        suits.add("Spade");
        suits.add("Heart");
        suits.add("Diamond");
        suits.add("Club");

        // Initialize cards
        for (int i = 0; i < 52; i++) {
            deck.add(i);
        }

        // Shuffle the cards.
        for (int i = 0; i < deck.size(); i++) {
            int index = (int) (Math.random() * deck.size());
            int temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
        }

        // Display the card(s) that is heart from the first five cards.
        for (int i = 0; i < 5; i++) {
            String suit = suits.get(deck.get(i) / 13);
            if (suit.equals("Heart")) {
                System.out.println("Card number " + deck.get(i) + " is " + suit);
            }
        }
    }
}
