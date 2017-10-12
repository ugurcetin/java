package Interface;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("Dog : eating");
    }

    @Override
    public void walk() {
        System.out.println("Dog : walking");
    }

}
