package Items;

public class ItemStoreException extends Exception {
    ItemStoreException(){
        super();
    }
    ItemStoreException(String message){
        super(message);
    }
    ItemStoreException(OneItem item, String message){
        super(message);
        System.err.println(item.getName() + " cannot be added to the container, because of overflow!");
    }
    ItemStoreException(String message, OneItem item){
        super(message);
        System.err.println(item.getName() + " with weight - " + item.getWeight() + ", cannot be added! Too heavy.");
    }
    ItemStoreException(String message, Throwable err){
        super(message, err);
    }
}
