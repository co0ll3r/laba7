package Items;

public class ItemIsEmptyException extends Exception{
    ItemIsEmptyException(){
        super("You can't delete items in an empty container!");
    }
}
