package Interface;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("Cat : eating");
    }

    @Override
    public void walk() {
        System.out.println("Cat : walking");
    }

}
