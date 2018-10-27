package Items;

import Items.ItemExceptions.*;

import java.util.*;

public class OneItem {
    private String name;
    private double weight;
    private Set<String> properties;
    private boolean isAdded;

    public OneItem(String name, double weight, String... properties) {
        this.name = name;
        this.weight = weight;
        if (properties.length > 0) {
            this.properties = new HashSet<>();
            this.properties.addAll(Arrays.asList(properties));
        }
    }

    void itemAdded() {
        isAdded = true;
    }

    void itemRemoved() {
        isAdded = false;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    Set<String> getProperties() {
        return properties;
    }

    // any alternatives?
    boolean isAdded() {
        return isAdded;
    }

    // what will happen if the fields is empty?
    public void getInfo() {
        System.out.print("Name: " + getName());

        if (weight > 0) { // Is it works for double?
            System.out.print(", Weight: " + getWeight());
        }

        if (properties != null) {
            System.out.print(", Properties: { ");
            for (String e :
                    getProperties()) {
                System.out.print(e + " ");
            }
            System.out.print("}");
        }

        System.out.print("; ");
    }

    // properties are shown with brackets [], fix it?
    @Override
    public String toString() {
        return String.format("Name: %s; Weight: %.2f; " + (isAdded ? "Already added" : "Not added") +
                "; properties: %s.", name, weight, properties.toString());
    }
}

abstract class Container extends OneItem implements Iterable<OneItem> {
    private ArrayList<OneItem> itemContainer;
    private int currentSize;
    private int maxItems = 10;
    private int maxWeight = 15;
    private boolean isContainerClosed = false;

    Container(String name, double weight, String... properties) {
        super(name, weight, properties);
        itemContainer = new ArrayList<>();
    }

    Container(String name, double weight, int maxItems, int maxWeight, String... properties) {
        this(name, weight, properties);// this();// c!!!
        this.maxItems = maxItems;
        this.maxWeight = maxWeight;
    }

    Container(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String... properties) {
        this(name, weight, maxItems, maxWeight, properties);
        this.itemContainer = newContainer;
        this.currentSize = itemContainer.size();
    }

    List<OneItem> getItemContainer() {
        return itemContainer;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    void addItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, CannotAccessTheContainer, AddTheSameException {
        if (newItem == this)
            throw new AddTheSameException("You're trying to add an item the same item!");
        if(checkIsContainerClosed())
            throw new CannotAccessTheContainer("You can't add this item. ", this.getName());
        if (newItem.isAdded())
            throw new ItemAlreadyPlacedException();
        if (currentSize >= maxItems)
            throw new ItemStoreException(newItem, this.getName() + " overflow! You're trying to put " + (currentSize + 1) +
                    " items in " + this.getName() + ", when the maximum is " + maxItems + ".");
        if (this.getWeight() + newItem.getWeight() > maxWeight)
            throw new ItemStoreException(this.getName() + " overweight! The weight would be " + (getWeight() +
                    newItem.getWeight()) + ", when the maximum is " + maxWeight + ".", newItem);

            newItem.itemAdded();
            currentSize++;
            itemContainer.add(newItem);
            // not sure about exact this implementation
            if (newItem instanceof Container)
                ((Container) newItem).closeContainer();
    }

    void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        if (checkIsContainerClosed())
            throw new CannotAccessTheContainer("You can't delete this item. ", this.getName());
        // not sure about exact this implementation
        if (getItemContainer().get(getCurrentSize() - 1) instanceof Container)
            ((Container) getItemContainer().get(getCurrentSize())).openContainer();
        currentSize--;
    }

    // instead of calculating weight of all the container
    void changeWeight(double value) {
        setWeight(getWeight() + value);
    }

    /**
     * Polite version of findByName without exceptions
     *
     * @param name  String not null
     * @return true if the container has the equal name, otherwise return false, even if the container is empty
     */
    public boolean containItem(String name) {
        if (getCurrentSize() == 0)
            return false;
        for (OneItem a :
                getItemContainer()) {
            if (name.equals(a.getName())) {
                return true;
            }
        }
        return false;
    }

    // make an exception

    /**
     * Use
     *
     * @param name String not null
     * @return OneItem or NULL if the container doesn't have an item with such the name
     */
    public OneItem findByName(String name) throws ItemIsEmptyException {
        // or return null is better?
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        for (OneItem a :
                getItemContainer()) {
            //equalsIgnoreCase - alternative
            if (name.equals(a.getName())) {
                System.out.println("The item has found");
                return a;
            }
        }
        System.out.println("The item hasn't found");
        return null;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.print("items: " + getCurrentSize() + "\n\t");
        for (OneItem a :
                itemContainer) {
            a.getInfo();
        }
        if (getCurrentSize() != 0)
            System.out.println();
    }

    /**
     * three methods to resolve the problem,
     * when you're trying to add an item to a container, that's holds in an another container.
     *
     */
    public void openContainer() {
        isContainerClosed = false;
    }

    public void closeContainer() {
        isContainerClosed = true;
    }
    boolean checkIsContainerClosed() {
        return isContainerClosed;
    }
     /** */

    @Override
    public Iterator<OneItem> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && itemContainer.get(currentIndex) != null; // currentSize - 1?
            }

            @Override
            public OneItem next() {
                return itemContainer.get(currentIndex++);
            }
        };
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" Current size: %d; MaxItems: %d; MaxWeight: %d; Items in the container: %s",
                        getCurrentSize(), maxItems, maxWeight, getItemContainer().toString());
    }

    abstract OneItem takeItem() throws CannotAccessTheContainer;

    abstract void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer;

}

