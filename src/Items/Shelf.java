package Items;

public class Shelf extends Container {
    public Shelf(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    public Shelf(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(getCurrentSize());
    }

    @Override
    public void removeItem() throws ItemIsEmptyException {
        //   getItemContainer().remove(index);
        super.removeItem();
        changeWeight(-(getItemContainer().get(getCurrentSize()).getWeight()));
        getItemContainer().remove(getCurrentSize());
//        calculateWeight();
    }

    // make flat constraints
    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
/*        for (String a :
                newItem.getProperties()) {
            if (a.equals("flat")){*/
        if (getProperties().contains("flat")) {
            addItem(newItem);
            changeWeight(newItem.getWeight());
            return;
        }
      //  getItemContainer().add(newItem);
//        calculateWeight();
    }
}
