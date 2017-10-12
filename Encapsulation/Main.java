package encapsulation;

/**
 * Created by UGURCETIN on 28.10.2016.
 */
public class Main {

    public static void main(String[] args) {

        Kisi kisi = new Kisi();
        kisi.setYas(22);
        System.out.println(kisi.getYas());

        Ev akraba = new Ev();
        akraba.bilgisayar();
        System.out.println(akraba.fatura);
    }

}
