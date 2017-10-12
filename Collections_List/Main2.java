package Collections_List;

import java.util.ArrayList;

/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main2 {

    public static void main(String[] args) {
        ArrayList<Person> kisiListesi = new ArrayList<>();

        Person person1=new Person();
        person1.setName("Şeyma");
        person1.setAge(23);

        Person person2=new Person("burak",22);

        kisiListesi.add(person1);
        kisiListesi.add(person2);
        kisiListesi.add(new Person("berkan", 22));

        person1.setName("Şeyma Yılmaz");

        for(Person person:kisiListesi){
            System.out.println("kisi isim: " + person.getName());
            System.out.println("kisi yas: " +  person.getAge());
        }

        System.out.println("Kişi sayısı: " + kisiListesi.size());
        //kisiListesi.clear();
        //kisiListesi.remove(1);
        //kisiListesi.remove(person1);
        System.out.println("son durumda kalan : " + kisiListesi.size() );

        /*
         * Person p = kisiListesi.get(1);
         * System.out.println("p isim : " + p.getName());
         * System.out.println("p yas : " + p.getAge());
         */
        if(kisiListesi.isEmpty()){
            System.out.println("liste bos");
        }
        else{
            System.out.println("listede kisi var, kisi sayisi : " + kisiListesi.size());
        }

        Person person4 = new Person("Şeyma Yılmaz", 23);
        kisiListesi.add(person4);
        Person person5 = new Person("Şeyma Yılmaz", 23);
        System.out.println("person 4 var mi : " + kisiListesi.contains(person4));
        System.out.println("person 5 var mi : " + kisiListesi.contains(person5));

    }
    }

