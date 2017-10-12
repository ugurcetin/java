package Exception;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class MyException extends Exception {

    @Override
    public String getMessage() {
        return "My Exception :)";
    }
}
