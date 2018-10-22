package Items;

public class Shelf extends Container{

    public Shelf(String name, int maxItems, int maxWeight, String... properties) {
        super(name, properties);
    }

    // Migrate to the abstract class
    @Override
    void calculateWeight() {
        int calcWeight = 0;
        for(OneItem a : getItemContainer()){
            calcWeight += a.getWeight();
        }
        this.setWeight(calcWeight);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(getCurrentSize() - 1);
    }

    // Remove index from the signature
    @Override
    public void removeItem(int index) {
     //   getItemContainer().remove(index);
        getItemContainer().remove(getCurrentSize() - 1);
        calculateWeight();
    }

    @Override
    public void pushItem(OneItem newItem) {
        addItem(newItem);
      //  getItemContainer().add(newItem);
        calculateWeight();
    }
}
