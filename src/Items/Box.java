package Items;

import java.util.ArrayList;
import java.util.Random;

public class Box extends Container {
    private boolean isBoxClosed = false;

    Box(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    Box(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    Box(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String... properties) {
        super(name, weight, newContainer, maxItems, maxWeight, properties);
    }

    // Maybe make some abstract or interface?
    // it's copy of the takeItem() from the Bag class
    @Override
    OneItem takeItem() {
        return getItemContainer().get(new Random().nextInt(getCurrentSize())); // take random
    }

    // it's copy of the removeItem() from the Bag class
    @Override
    void removeItem() throws ItemIsEmptyException {
        super.removeItem();
        int index = new Random().nextInt(getCurrentSize() + 1);
        OneItem itemForDelete = getItemContainer().get(index);
        itemForDelete.itemRemoved(); // make isAdded = false

        System.out.println(itemForDelete + " has deleted!"); // maybe doesn't need

        changeWeight(-getItemContainer().get(index).getWeight());
        getItemContainer().remove(index);
    }

    // you can make: if closed, then transform into a stack with two items, the first one is the closed box;
    @Override
    void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        if (checkIsBoxClosed()){
            System.out.println("The box is closed, can't add anything.");
        } else {
            addItem(newItem);
            changeWeight(newItem.getWeight());
        }
    }

    void openBox() {
        isBoxClosed = false;
    }

    void closeBox() {
        isBoxClosed = true;
    }

    boolean checkIsBoxClosed() {
        return isBoxClosed;
    }

}
