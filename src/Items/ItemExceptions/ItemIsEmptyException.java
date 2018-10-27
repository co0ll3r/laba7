package Items.ItemExceptions;

public class ItemIsEmptyException extends Exception{
    public ItemIsEmptyException(){
        super("You can't delete items in an empty container!");
    }
}
