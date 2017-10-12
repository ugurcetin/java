package Collections_HashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Ali");
        hashSet.add("Mehmet");
        hashSet.add("Fatma");
        hashSet.add("Mehmet");

        String s = "Fatma";
        if(hashSet.add(s)){
            System.out.println("Fatma listede yok");
        }
        else{
            System.out.println("kisilerin listesi : ");
            for(String isim : hashSet){
                System.out.println("isim : " + isim);
            } System.out.println("Fatma listede zaten var");
        }

        if(hashSet.contains(s)){
            System.out.println("contains() -> fatma listede var");
        }
        else{
            System.out.println("contains() -> fatma listede yok");
        }

        System.out.println("isim sayisi : " + hashSet.size());

    }
}
