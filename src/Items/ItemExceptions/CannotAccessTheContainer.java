package Items.ItemExceptions;

public class CannotAccessTheContainer extends Exception{
    public CannotAccessTheContainer(String message){
        super(message);
    }

    // handles cases, when the container is closed
    public CannotAccessTheContainer(String message, String name){
        super(message);
        System.err.println(message + "The " + name + " is closed.");
    }
}
