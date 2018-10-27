package Items;

import java.util.ArrayList;
import java.util.Random;

public class Bag extends Container {
    public Bag(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    public Bag(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    public Bag(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String properties) {
        super(name, weight, newContainer, maxItems, maxWeight, properties);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(new Random().nextInt(getCurrentSize())); // take random
    }

    @Override
    public void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        super.removeItem();
        int index = new Random().nextInt(getCurrentSize() + 1);
        OneItem itemForDelete = getItemContainer().get(index);
        itemForDelete.itemRemoved(); // make isAdded = false

        System.out.println(itemForDelete + " has deleted!"); // maybe doesn't need
//        System.out.println(-getItemContainer().get(index).getWeight());

        changeWeight(-getItemContainer().get(index).getWeight());
        getItemContainer().remove(index);
    }

    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer {
        /*if (newItem == this) {
            throw new AddTheSameException("You're trying to the item the same item!");
        }*/
        addItem(newItem);
        changeWeight(newItem.getWeight());
//        calculateWeight();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

