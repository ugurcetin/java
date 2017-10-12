package Operatorler;

/**
 * Created by UGURCETIN on 16.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        boolean a=true;
        boolean b=false;

        System.out.println(true & false);
        System.out.println(a | b);
        System.out.println(a && b);//her ikiside true olursa, true döner
        System.out.println(a || b);//en az biri true olursa, true döner

        String ad="Mehmet";
        int sifre=1234;

        if(ad=="Mehmett" || sifre==12345)
            System.out.println("Giriş yapıldı");
        else
            System.out.println("Giriş başarısız");
    }
}
