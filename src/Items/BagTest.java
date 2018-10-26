package Items;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @org.junit.jupiter.api.Test
    void constructorsTest() {

    }

    @org.junit.jupiter.api.Test
    void putInTheSame(){
        Bag bag1 = new Bag("bag1", 0.5, "white");
        Bag bag2 = new Bag("bag1", 0.5, "white");
        Bag bag3 = new Bag("bag1", 0.5, "white");
        Bag bag4 = new Bag("bag1", 0.5, "white");
        Bag bag5 = new Bag("bag1", 0.5, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            assertThrows(AddTheSameException.class, () -> bag1.pushItem(bag1));
            bag1.pushItem(bag2);
            bag2.pushItem(bag1);
        } catch (ItemAlreadyPlacedException | ItemStoreException e) {
            e.printStackTrace();
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }
        bag1.getInfo();
    }

    @org.junit.jupiter.api.Test
    void calculateWeight() {
        Bag bag1 = new Bag("bag1", 0.5, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("brick", 5, "red"));
            bag1.pushItem(new OneItem("brick", 5, "grey"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }
        assertEquals(12.5, bag1.getWeight());
        try {
            bag1.removeItem();
            bag1.removeItem();
        } catch (ItemIsEmptyException e) {
            e.printStackTrace();
        }
        assertEquals(0.5, bag1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void takeItem() {
        Bag bag1 = new Bag("bag1", 1, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("ball", 5, "red"));
            bag1.pushItem(new OneItem("book", 1, "grey"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }

        bag1.takeItem();
        bag1.takeItem();
        try {
            bag1.removeItem();
        } catch (ItemIsEmptyException e) {
            e.getMessage();
        }
        bag1.takeItem();
    }

    @org.junit.jupiter.api.Test
    void TestExceptions() {
        OneItem uniqueItem = new OneItem("key", 0.05, "rare", "golden");
        var item2 = new OneItem("handle", 0.03, "oiled");
        OneItem item3 = new OneItem("desk", 10, "brown");
        OneItem item4 = new OneItem("fork", 0.01, "copper");
        Bag bag1 = new Bag("bag1", 1, 2, 10);
        Bag bag2 = new Bag("bag2", 0.5, 2, 5, "weak");

        try {
            bag1.pushItem(uniqueItem);
            bag1.pushItem(item2);
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }

        // difference?
        assertThrows(ItemStoreException.class, () -> bag1.pushItem(item4));
        assertThrows(ItemAlreadyPlacedException.class, () -> bag2.pushItem(uniqueItem));
        assertThrows(ItemAlreadyPlacedException.class, () -> bag2.pushItem(item2));
        assertThrows(ItemStoreException.class, () -> {
            bag2.pushItem(item3);
        });

        try {
            bag2.removeItem();
        } catch (ItemIsEmptyException a) {
            System.err.println(a.getMessage());
        }

        assertThrows(ItemIsEmptyException.class, bag2::removeItem);

        bag1.getInfo();
        bag2.getInfo();
    }

    @org.junit.jupiter.api.Test
    void pushAndRemoveItem() {
        Bag bag1 = new Bag("bag1", 1, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("bar", 5, "silver"));
            bag1.pushItem(new OneItem("vase", 1, "transparent"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }
        try {
            bag1.removeItem();
            bag1.removeItem();
            bag1.getInfo();
            bag1.removeItem();
            bag1.getInfo();
            bag1.removeItem();
            bag1.pushItem(new OneItem("fork", 0.005, "copper"));
        } catch (ItemIsEmptyException e) {
            System.err.print(e.getMessage());
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException e) {
            e.printStackTrace();
        }
        assertEquals(0, bag1.getCurrentSize());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        OneItem a = new OneItem("Chair", 4, "comfortable", "low");
        System.out.println(a.toString());
        assertEquals("Name: Chair; Weight: 4,00; Not added; properties: [low, comfortable].", a.toString());
    }

    @org.junit.jupiter.api.Test
    void findAndIteratorTest() {
        OneItem item1 = new OneItem("potato", 3, "fresh");
        OneItem item2 = new OneItem("milk", 1, "cheap");
        OneItem item3 = new OneItem("bread", 0.5, "warm");
        ArrayList<OneItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        Bag bag = new Bag("bag", 0.01, items, 7, 15, "black");
        try {
            // double search!! slow?
            if (bag.containItem("sad"))
                bag.findByName("sad").getInfo();
            if (bag.containItem("potato"))
                bag.findByName("potato").getInfo();
        } catch (ItemIsEmptyException e) {
            e.printStackTrace();
        }

        // Iterator test!
        System.out.println();
        for (OneItem a :
                bag) {
            a.getInfo();
        }
        System.out.println();
        System.out.println( bag.toString());
    }
}
