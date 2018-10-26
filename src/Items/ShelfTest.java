package Items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    @Test
    void calculateWeight() {
        Shelf shelf1 = new Shelf("shelf", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");
        try {
            shelf1.pushItem(item1);
            shelf1.pushItem(item2);
            shelf1.pushItem(item3);
            assertThrows(ItemStoreException.class, () -> shelf1.pushItem(item4));
            shelf1.pushItem(item6);
            assertThrows(ItemStoreException.class, () -> shelf1.pushItem(item5));
            assertThrows(ItemAlreadyPlacedException.class, () -> shelf1.pushItem(item1));
            shelf1.pushItem(new OneItem("spoon", 0.0015, "engraved", "small"));
            shelf1.pushItem(new OneItem("spoon", 0.0015, "engraved", "small", "flag"));
        } catch (ItemAlreadyPlacedException | ItemStoreException e) {
            e.printStackTrace();
        }
        assertEquals(11.451, shelf1.getWeight());
        try {
            shelf1.removeItem();
            assertEquals(8.451, shelf1.getWeight());
            shelf1.removeItem();
            // doesn't work without delta!
            assertEquals(4.451, shelf1.getWeight(), 0.00001);
            shelf1.removeItem();
            assertEquals(1.951, shelf1.getWeight(), 0.00001);
            shelf1.removeItem();
            assertEquals(1.5, shelf1.getWeight(), 0.00001);
        } catch (ItemIsEmptyException e) {
            e.printStackTrace();
        }
        shelf1.getInfo();
        assertThrows(ItemIsEmptyException.class, shelf1::removeItem);
    }

    @Test
    void addBox() {
        Shelf shelf1 = new Shelf("shelf", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");
        Box box1 = new Box("box1", 1, 3, 5, "small");
        try {
            box1.pushItem(item1);
            box1.pushItem(item2);
        } catch (ItemAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (ItemStoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeItem() {
    }

    @Test
    void pushItem() {
    }
}