package Items;

import java.util.ArrayList;

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

    @Override
    OneItem takeItem() {
        return null;
    }

    // you can make: if closed, then transform into a stack with two items, the first one is the closed box;
    @Override
    void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        if (checkIsBoxClosed()){
            System.out.println("The box is closed, can't add anything.");
            return;
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
