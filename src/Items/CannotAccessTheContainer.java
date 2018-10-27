package Items;

public class CannotAccessTheContainer extends Exception{
    CannotAccessTheContainer(String message){
        super(message);
    }

    // handles cases, when the container is closed
    CannotAccessTheContainer(String message, String name){
        super(message);
        System.err.println(message + "The " + name + " is closed.");
    }
}
