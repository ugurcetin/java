package Referans;

/**
 * Created by UGURCETIN on 22.06.2017.
 */
public class Main2 {

    public static void main(String[] args) {

        Employee e1 = new Employee();
        Employee e2 = e1;

        e1.fullname = "Uğur Çetin";
        e1.age = 22;
        e1.salary = 2000;

        System.out.println("İşçinin adı: " + e1.fullname + "\n" +
                "İşçinin yaşı: " + e1.age + "\n" +
                "İşçinin maaşı: " + e1.salary + "\n");

        System.out.println("------------------------");


        System.out.println("İşçinin adı: " + e2.fullname + "\n" +
                "İşçinin yaşı: " + e2.age + "\n" +
                "İşçinin maaşı: " + e2.salary + "\n");


        System.out.println("------------------------" + "\n" + "İşçi 2 nin yeni değerleri" + "\n");

        e2.fullname = "Burak Köken";
        e2.age = 22;
        e2.salary = 5000;

        System.out.println("İşçinin adı: " + e2.fullname + "\n" +
                "İşçinin yaşı: " + e2.age + "\n" +
                "İşçinin maaşı: " + e2.salary + "\n");


        System.out.println("------------------------" + "\n" + "İşçi 1 in değerleri" + "\n");

        System.out.println("İşçinin adı: " + e1.fullname + "\n" +
                "İşçinin yaşı: " + e1.age + "\n" +
                "İşçinin maaşı: " + e1.salary + "\n");

    }
}
