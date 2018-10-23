package Items;

import java.util.*;

public class OneItem {
    private String name;
    private double weight;
    private Set<String> properties;
    private boolean isAdded;

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

    public OneItem(String name, int weight, String... properties) {
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

    Set<String> getProperties() {
        return properties;
    }

    // any alternatives?
    boolean isAdded() {
        return isAdded;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    // what will happen if the fields is empty?
    public void getInfo() {
        System.out.print("Name: " + getName() + ", ");
        if (weight != 0) {
            System.out.print("Weight: " + getWeight());
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

    Container(String name, String... properties) {
        super(name, properties);
        itemContainer = new ArrayList<>();
    }

    Container(String name, int maxItems, int maxWeight, String... properties) {
        this(name, properties);// this();// c!!!
        this.maxItems = maxItems;
        this.maxWeight = maxWeight;
    }

    Container(String name, ArrayList<OneItem> newContainer, String... properties) {
        super(name, properties);
        this.itemContainer = newContainer;
        this.currentSize = itemContainer.size();
    }

    List<OneItem> getItemContainer() {
        return itemContainer;
    }

    int getCurrentSize() {
        return currentSize;
    }

    // outOfBoundException??
    void addItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException {
        // make own Exception class!
        if (newItem.isAdded())
            throw new ItemAlreadyPlacedException();
        if (currentSize + 1 > maxItems)
            throw new ItemStoreException(newItem, this.getName() + " overflow! You're trying to put " + (currentSize + 1) +
                    " items in" + this.getName() + ", when the maximum is " + maxItems + ".");
        if (getWeight() + newItem.getWeight() > maxWeight)
            throw new ItemStoreException(newItem, this.getName() + " overweight! The weight would be " + (getWeight() +
                    newItem.getWeight()) + ", when the maximum is " + maxWeight + ".");

        currentSize++;
        itemContainer.add(newItem);
    }

    void removeItem() throws ItemIsEmptyException {
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        currentSize--;
        calculateWeight();
    }

    abstract void calculateWeight();

    abstract OneItem takeItem();

    abstract void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException;

    @Override
    public void getInfo() {
        super.getInfo();
//        System.out.println("Amount of items in container is " + currentSize);
        System.out.printf("items: " + getCurrentSize() + "\n\t");
        for (OneItem a :
                itemContainer) {
            a.getInfo();
        }
        System.out.println();
    }


    @Override
    public Iterator<OneItem> iterator() {
        Iterator<OneItem> it = new Iterator<OneItem>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize - 1 && itemContainer.get(currentIndex) != null; // currentSize - 1?
            }

            @Override
            public OneItem next() {
                return itemContainer.get(currentIndex++);
            }
        };
        return it;
    }

}

