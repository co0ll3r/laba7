package Items;

public class Shelf extends Container{
    public Shelf(String name, String... properties) {
        super(name, properties);
    }

    public Shelf(String name, int maxItems, int maxWeight, String... properties) {
        super(name, properties);
    }

    // Migrate to the abstract class
    @Override
    void calculateWeight() {
        double calcWeight = 0;
        for(OneItem a : getItemContainer()){
            calcWeight += a.getWeight();
        }
        this.setWeight(calcWeight);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(getCurrentSize() - 1);
    }

    @Override
    public void removeItem() throws ItemIsEmptyException {
     //   getItemContainer().remove(index);
        super.removeItem();
        getItemContainer().remove(getCurrentSize() - 1);
        calculateWeight();
    }

    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        addItem(newItem);
      //  getItemContainer().add(newItem);
        calculateWeight();
    }
}
