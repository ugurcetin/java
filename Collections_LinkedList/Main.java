package Collections_LinkedList;
import java.util.*;
/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();
        arrayList.add("Şeyma");
        arrayList.add("Burak");
        arrayList.add("Berkan");

        System.out.println(arrayList.get(2));
        /* ... */

        LinkedList linkedList = new LinkedList();
        linkedList.add("Şeyma");
        linkedList.add("Burak");
        linkedList.add("Berkan");

        System.out.println(linkedList.getFirst());
    }

}
