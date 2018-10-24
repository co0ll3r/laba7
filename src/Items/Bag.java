package Items;

import java.util.ArrayList;
import java.util.Random;

public class Bag extends Container {
    public Bag(String name, String... properties) {
        super(name, properties);
    }

    public Bag(String name, int maxItems, int maxWeight, String... properties) {
        super(name, maxItems, maxWeight, properties);
    }

    public Bag(String name, ArrayList<OneItem> newContainer, String properties) {
        super(name, newContainer, properties);
    }

    // TOO slow, any another solutions?
    @Override
    public void calculateWeight() {
        double calcWeight = 0;
        for (OneItem a : getItemContainer()) {
            calcWeight += a.getWeight();
        }
        this.setWeight(calcWeight);
    }

    // make an exception for the case outOfBoundException
    public OneItem takeItem(int index) {
        try {
            return getItemContainer().get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index should be >= 0 and < currentSize");
            e.getStackTrace();
        }
        // need something else
        return getItemContainer().get(0);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(new Random().nextInt(getCurrentSize())); // take random
    }

    @Override
    void removeItem() throws ItemIsEmptyException {
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        int index = new Random().nextInt(getCurrentSize());
        OneItem itemForDelete = getItemContainer().get(index);
        itemForDelete.itemRemoved();
        System.out.println(itemForDelete + " has deleted!"); // maybe doesn't need
        super.removeItem();
        getItemContainer().remove(index);
        calculateWeight();
    }

    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        addItem(newItem);
        calculateWeight();
    }
}

