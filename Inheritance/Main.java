package Kalitim;

/**
 * Created by UGURCETIN on 14.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        Arac araba = new Arac("seat","ibiza","beyaz",2012,90);

        System.out.println("Arabanın markası:" + araba.getMarka() + "\n" +  "Arabanın modeli:" + araba.getModel() + "\n" +
                "Arabanın rengi:" + araba.getRenk() + "\n" + "Arabanın yılı:" + araba.getYili() + "\n" +
                "Arabanın beygir gücü:" + araba.getBeygir());

        araba.gazaBas();

        System.out.println("--------------------------");

        Audi Q7 = new Audi("Audi","Q7","siyah",2017,200,true);

        System.out.println("Arabanın markası:" + Q7.getMarka() + "\n" +  "Arabanın modeli:" + Q7.getModel() + "\n" +
                "Arabanın rengi:" + Q7.getRenk() + "\n" + "Arabanın yılı:" + Q7.getYili() + "\n" +
                "Arabanın beygir gücü:" + Q7.getBeygir() + "\n" + "Sunroof var mı:" + Q7.isSunroof());

        Q7.gazaBas();
    }
}
