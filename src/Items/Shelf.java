package Items;

public class Shelf extends Container{
    public Shelf(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    public Shelf(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, properties);
    }

    // Migrate to the abstract class?
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
        changeWeight(-getItemContainer().get(getCurrentSize() - 1).getWeight());
        getItemContainer().remove(getCurrentSize() - 1);
//        calculateWeight();
    }

    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        addItem(newItem);
        changeWeight(newItem.getWeight());
      //  getItemContainer().add(newItem);
//        calculateWeight();
    }
}
