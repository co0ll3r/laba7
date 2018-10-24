package Items;

import java.util.ArrayList;

public class Box extends Container{
    Box(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    Box(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    Box(String name, double weight, ArrayList<OneItem> newContainer, String... properties) {
        super(name, weight, newContainer, properties);
    }

    @Override
    void calculateWeight() {

    }

    @Override
    OneItem takeItem() {
        return null;
    }

    @Override
    void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {

    }

}
