package Items;

import java.util.*;

public class OneItem {
    private String name;
    private double weight;
    private Set<String> properties;
    private boolean isAdded;

    /*
    // make weight = something or comment
    public OneItem(String name, String... properties) {
        this.name = name;
        weight = 0;
        //or null?
        //weight = 1;
        // check this if statement
        if (properties.length > 0) {
            this.properties = new HashSet<>();
            this.properties.addAll(Arrays.asList(properties));
        }
    }
    */

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

    String getName() {
        return name;
    }

    double getWeight() {
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

    int getCurrentSize() {
        return currentSize;
    }

    void addItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        if (newItem.isAdded())
            throw new ItemAlreadyPlacedException();
        if (currentSize + 1 > maxItems)
            throw new ItemStoreException(newItem, this.getName() + " overflow! You're trying to put " + (currentSize + 1) +
                    " items in " + this.getName() + ", when the maximum is " + maxItems + ".");
        if (this.getWeight() + newItem.getWeight() > maxWeight)
            throw new ItemStoreException(newItem, this.getName() + " overweight! The weight would be " + (getWeight() +
                    newItem.getWeight()) + ", when the maximum is " + maxWeight + ".");

        newItem.itemAdded();
        currentSize++;
        itemContainer.add(newItem);
    }

    void removeItem() throws ItemIsEmptyException {
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        currentSize--;
    }

    // instead of calculating weight of all the container
    void changeWeight(double value) {
        setWeight(getWeight() + value);
    }

    /**
     * Polite version of findByName without exceptions
     * @param name
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

    // how to make a required null handler?

    /**
     * Use
     *
     * @param name
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
        ;
        System.out.println("The item hasn't found");
        return null;
    }

    // TOO slow, any another solutions?
    public void calculateWeight() {
        double calcWeight = 0;
        for (OneItem a : getItemContainer()) {
            calcWeight += a.getWeight();
        }
        this.setWeight(calcWeight);
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.print("items: " + getCurrentSize() + "\n\t");
        for (OneItem a :
                itemContainer) {
            a.getInfo();
        }
        System.out.println();
    }

    @Override
    public Iterator<OneItem> iterator() {
        return new Iterator<OneItem>() {
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

    abstract OneItem takeItem();

    abstract void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException;

}

