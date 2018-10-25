package Items;

import java.util.ArrayList;

public class Box extends Container{
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

    @Override
    OneItem takeItem() {
        return null;
    }

    @Override
    void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {

    }

    void openBox(){

    }

    void closeBox(){

    }

}
