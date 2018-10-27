package Items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void addTheSame() {

    }

    @Test
    void takeItem() {
        Box box1 = new Box("box", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");
        try {
            box1.pushItem(item1);
            box1.pushItem(item2);
            box1.pushItem(item3);
            assertThrows(ItemStoreException.class, () -> box1.pushItem(item4));
            box1.pushItem(item6);
            box1.getInfo();
            assertThrows(ItemStoreException.class, () -> box1.pushItem(item5));
            assertThrows(ItemAlreadyPlacedException.class, () -> box1.pushItem(item1));
            assertThrows(ItemStoreException.class, () -> box1.pushItem
                    (new OneItem("spoon", 0.0015, "engraved", "small")));
            assertThrows(ItemStoreException.class, () -> box1.pushItem
                    (new OneItem("spoon", 0.0015, "engraved", "small", "flag")));
        } catch (ItemAlreadyPlacedException | ItemStoreException | AddTheSameException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        assertEquals(11.451, box1.getWeight());
        try {
            box1.removeItem();
            box1.removeItem();
            box1.removeItem();
            box1.removeItem();
        } catch (ItemIsEmptyException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
//        shelf1.getInfo();
        assertThrows(ItemIsEmptyException.class, box1::removeItem);
        assertEquals(1.5, box1.getWeight(), 0.000001);
    }

    @Test
    void closeBox() {
        Box box = new Box("crate", 1.1, 3, 15, "wooden");
        box.getInfo();
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("laptop", 2.5, "flat", "asus");
        var item5 = new OneItem("dvd-player", 4, "flat");
        try {
            box.pushItem(item1);
            box.pushItem(item2);
            box.pushItem(item3);
        } catch (ItemAlreadyPlacedException | ItemStoreException | AddTheSameException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        System.out.println();
        box.getInfo();
        box.closeContainer();
//        if (!box.checkIsBoxClosed())
        assertThrows(CannotAccessTheContainer.class, () -> box.takeItem().getInfo());
        assertThrows(CannotAccessTheContainer.class, () -> box.pushItem(item4));
        assertThrows(CannotAccessTheContainer.class, () -> box.pushItem(item5));
        assertThrows(CannotAccessTheContainer.class, box::removeItem);
        try {
            box.openContainer();
//            if (!box.checkIsBoxClosed())
            box.takeItem().getInfo();
        } catch (CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        assertEquals(8.051, box.getWeight(), 0.00001);
    }

    @Test
    void putOnShelf() {

    }
}