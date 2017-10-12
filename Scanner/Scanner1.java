package Scanner;
import java.util.Scanner;
/**
 * Created by UGURCETIN on 16.07.2017.
 */
public class Scanner1 {

    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.println("int tipinde bir deðer girin");
        int a=s.nextInt();

        System.out.println("byte tipinde bir deðer girin");
        byte b=s.nextByte();

        System.out.println("string tipinde bir deðer girin");
        String c=s.next();

        System.out.println("Girdiðiniz deðer :"+a);
        System.out.println("Girdiðiniz deðer :"+b);
        System.out.println("Girdiðiniz deðer :"+c);

        System.out.println("------------------------------");

        Scanner s1=new Scanner(System.in);
        System.out.println("Faktöriyeli hesaplanacak olan sayıyı girin:");
        int sayi=s1.nextInt();
        long sonuc=1;
        for(int i=1;i<=sayi ;i++)
        {
            sonuc*=i;
        }
        System.out.println("sayımızın faktöriyeli: "+sonuc);
    }
}
