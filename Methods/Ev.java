package Methods;

/**
 * Created by UGURCETIN on 9.07.2017.
 */
public class Ev {
    double en;
    double boy;
    double yukseklik;

    void hacimHesapla(){
        System.out.println("hacim = "+ en*boy*yukseklik);

    }
    public static void main(String[] args) {

        Ev ev = new Ev();
        ev.en = 5;
        ev.boy = 4;
        ev.yukseklik = 6;

        double hacim = ev.en*ev.boy*ev.yukseklik;
        System.out.println("Evin hacmi = " + hacim);

        ev.hacimHesapla();

        System.out.println("------------------------------");

        Ev ev2 = new Ev();
        ev2.en = 5;
        ev2.boy = 4;
        ev2.yukseklik = 10;

        hacim = ev2.en*ev2.boy*ev2.yukseklik;
        System.out.println("2.Evin hacmi = " + hacim);

    }
}
