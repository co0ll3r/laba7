package Items;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @org.junit.jupiter.api.Test
    void calculateWeight() {
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("brick", 5, "red"));
            bag1.pushItem(new OneItem("brick", 5, "grey"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        }
        assertEquals(12, bag1.getWeight());
        try {
            bag1.removeItem();
            bag1.removeItem();
        } catch (ItemIsEmptyException e) {
            e.printStackTrace();
        }
        assertEquals(0, bag1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void takeItem() {
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("ball", 5, "red"));
            bag1.pushItem(new OneItem("book", 1, "grey"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
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
        OneItem item2 = new OneItem("handle", 0.03, "oiled");
        OneItem item3 = new OneItem("desk", 10, "brown");
        OneItem item4 = new OneItem("fork", 0.01, "copper");
        Bag bag1 = new Bag("bag1", 2, 10);
        Bag bag2 = new Bag("bag2", 2, 5, "weak");

        try {
            bag1.pushItem(uniqueItem);
            bag1.pushItem(item2);
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
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
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        try {
            bag1.pushItem(item1);
            bag1.pushItem(new OneItem("bar", 5, "silver"));
            bag1.pushItem(new OneItem("vase", 1, "transparent"));
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
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
        }
        assertEquals(0, bag1.getCurrentSize());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        OneItem a = new OneItem("Chair", 4, "comfortable", "low");
        System.out.println(a.toString());
        assertEquals("Name: Chair; Weight: 4,00; Not added; properties: [low, comfortable].", a.toString());
    }
}
