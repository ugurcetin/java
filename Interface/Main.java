package Interface;

/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Main {

    public static void main(String[] args) {

    /*
            Dog dog = new Dog();
            dog.eat();
            dog.walk();
            Cat cat = new Cat();
            cat.eat();
            cat.walk();
        */

        Animal animal1 = new Animal() {
            @Override
            public void eat() {
                System.out.println("ugur : eating");
            }

            @Override
            public void walk() {
                System.out.println("ugur : walking");
            }
        };
        animal1.eat();
        animal1.walk();

        Animal animal2 = new Animal() {
            @Override
            public void eat() {
                System.out.println("burak : eating");
            }

            @Override
            public void walk() {
                System.out.println("burak : walking");
            }
        };
        animal2.eat();
        animal2.walk();

    }
}