import Items.*;

import java.util.HashSet;

public class main {
    public static void main(String[] args) {
        OneItem abc = new OneItem("toy", 2, "red", "wide", "smart");
        Bag bag1 = new Bag("bag", 0.5, "grey", "doted");
        try {
            bag1.pushItem(abc);
            bag1.pushItem(new OneItem("cat", 5, "fluffy"));
            bag1.pushItem(new OneItem("table", 20));
            bag1.pushItem(new OneItem("chair", 8));
            bag1.getInfo();
//            bag1.takeItem(0).getInfo();


/*        for (OneItem a :
               bag1) {
           a.getInfo();
        }*/

            Bag bag2 = new Bag("bag", 1);
            bag1.pushItem(abc);
            bag2.getInfo();

            Shelf shelf1 = new Shelf("shelf", 1, 5, 35, "brown", "wooden");

            shelf1.pushItem(abc);
            shelf1.pushItem(new OneItem("brick", 5, "flat", "fusion"));
            shelf1.pushItem(new OneItem("brick", 8, "grey"));
            shelf1.pushItem(new OneItem("gold bar", 25, "expensive"));
            shelf1.pushItem(bag2);
            shelf1.getInfo();
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        }

/*        while (bag1.iterator().hasNext()){
            bag1.iterator().next().getInfo();
        }*/
    }
}
