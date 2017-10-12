package Math;

import java.util.Random;
/**
 * Created by UGURCETIN on 18.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        for(int i = 0;i<=10;i++){
            System.out.println(Math.pow(2, i));
        }

        System.out.println("1024 karekökü :" + Math.sqrt(1024));

        System.out.println("-45 mutlak deðeri : " + Math.abs(-45));

        System.out.println("3.55 üst deðeri : " + Math.ceil(3.55));

        System.out.println("3.55 alt deðeri : " + Math.floor(3.55));

        System.out.println("35 ve 150 arasýndan büyük sayý : " + Math.max(35, 150));

        System.out.println("35 ve 150 arasýndan küçük sayý : " + Math.min(35, 150));

        System.out.println("Rasgele sayý : " + (int) ((Math.random())*(100)));

        Random r = new Random();

        int a = r.nextInt(100);
        float f = r.nextFloat();

        System.out.println("Random sýnýfýndan rasgele sayý : " + a);
        System.out.println("Random sýnýfýndan rasgele float sayý : " + f);

        System.out.println("1.5 radyan " + Math.toDegrees(1.5) + " derecedir.");
        System.out.println("45 derece " + Math.toRadians(45) + " radyandýr.");

        System.out.println("Sin 90 = " + Math.sin(Math.toRadians(90)));
        System.out.println("Cos 55 = " + Math.cos(Math.toRadians(55)));

        System.out.println("ArcSin 0.47 = " + Math.toDegrees(Math.asin(0.47)));
        System.out.println("ArcCos 0.57 = " + Math.toDegrees(Math.acos(0.57)));

        System.out.println("lne = " + Math.log(Math.E));
        System.out.println("log10(5) = " + Math.log10(5));
        System.out.println("ln(7+1) = " + Math.log1p(7));
    }
}
