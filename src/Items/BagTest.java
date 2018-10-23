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
        OneItem item2 = new OneItem("handle", 0.03, "usuall");
        OneItem item3 = new OneItem("desk", 10, "brown");
        OneItem item4 = new OneItem("fork", 0.01, "copper");
        Bag bag1 = new Bag("bag1", 2, 10);
        Bag bag2 = new Bag("bag2", 2, 5, "weak");
        // maxItems exception
        try {
            bag1.pushItem(uniqueItem);
            bag1.pushItem(item2);
            bag1.pushItem(item3);
            bag2.pushItem(item4);
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        }
        bag2.getInfo();
        // one item in container
        try {
            bag1.removeItem();
            bag2.addItem(uniqueItem);
            bag2.addItem(item2);
        } catch (ItemAlreadyPlacedException | ItemStoreException | ItemIsEmptyException a) {
            System.err.println(a.getMessage());
        }
        bag1.getInfo();
        bag2.getInfo();
        try {
            bag2.addItem(item2);
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        }
       // assertThrows();
        // overweight
        try {
            bag2.addItem(item3);
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        }
        bag1.getInfo();
        bag2.getInfo();
        assertEquals(1, bag1.getCurrentSize());
        assertEquals(1, bag2.getCurrentSize());
    }

    @org.junit.jupiter.api.Test
    void removeItem() {
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
    void pushItem() {
        /*        } catch (ItemAlreadyPlacedException e) {
            System.out.println("Error this item already added!");
            // System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (ItemStoreException a) {
            //   System.out.println("Store exception!");
            System.out.println(a.getMessage());
        }*/
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        OneItem a = new OneItem("Chair", 4, "comfortable", "low");
        System.out.println(a.toString());
        assertEquals("Name: Chair; Weight: 4,00; Not added; properties: [low, comfortable].", a.toString());
    }
}
