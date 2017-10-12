package Collections_HashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        hashMap.put(123, "Şeyma");
        hashMap.put(345, "Burak");
        hashMap.put(675, "Berkan");
        hashMap.put(675, "Umut");

        System.out.println();

        HashMap<String, Person> hashMap1 = new HashMap<>();
        Person person1 = new Person("Şeyma Yılmaz", 22);
        hashMap1.put("123456", person1);
        Person person2 = new Person("Burak Köken", 22);
        hashMap1.put("232312", person2);

        Person p = hashMap1.get("232312");
        System.out.println(p.getName() + ", " + p.getAge());

        hashMap1.remove("232312");

        hashMap1.containsKey("232312");

        System.out.println();

        /* linkedhashmap */
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(6, "Şeyma");
        linkedHashMap.put(2, "Burak");
        linkedHashMap.put(7, "Berkan");

        /* sortedMap */
        TreeMap treeMap = new TreeMap();
        treeMap.put(6, "Şeyma");
        treeMap.put(2, "Burak");
        treeMap.put(7, "Berkan");

        System.out.println();
    }
}
