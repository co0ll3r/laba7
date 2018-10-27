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
    public void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        //   getItemContainer().remove(index);
        super.removeItem();
        OneItem itemRem = getItemContainer().get(getCurrentSize());
        //There could be some problems!
/*        if (itemRem instanceof Box)
            ((Box) itemRem).openBox();*/
        changeWeight(-(itemRem.getWeight()));
        getItemContainer().remove(getCurrentSize());
//        calculateWeight();
    }

    // make flat constraints
    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer {
        if (newItem == this) {
            throw new AddTheSameException("You're trying to the item the same item!");
        }
        for (String a :
                newItem.getProperties()) {
            if (a.equalsIgnoreCase("flat")) {
/*                if (newItem instanceof Box){
                    ((Box) newItem).closeBox();
                }*/
                addItem(newItem);
                changeWeight(newItem.getWeight());
                return;
            }
        }
        System.err.println("Can't add not flat item!");
        //  getItemContainer().add(newItem);
//        calculateWeight();
//        if (getProperties().contains("flat")) {  // Doesn't work
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
