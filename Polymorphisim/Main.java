package Polimorfizim;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by UGURCETIN on 17.07.2017.
 */
public class Main {

    public static void main(String[] args) {

        Person person = new Person();
        Student student = new Student();
        Employee employee = new Employee();

        Person p = student;
        Person e = employee;

        Object o = student;
        Object o1 = employee;
        Object o2 = person;


        /*Student student = new Student();
        student.setName("uğur");
        student.setAge(22);
        student.setDepartment("Computer");
        student.setNo("g130910020");


        Person person = new Person();

        Person p = student;

        Employee e = new Employee();

        deneme((Student) p);

        Object object = person;
        Object object2 = student;

        Person person2 = (Person) object;

        List liste = new ArrayList();
        liste.add(student);
        liste.add(person);

        for(Object eleman : liste){

            if(eleman instanceof Student){
                Student s1 = (Student) eleman;
            }
            else if(eleman instanceof Person){
                Person p1 = (Person) eleman;
            }

        }

    }

    public static void deneme(Student p){

    }
*/
    }

}

