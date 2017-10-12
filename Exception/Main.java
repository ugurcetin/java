package Exception;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Şeyma Yılmaz on 1.6.2017.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int a, b, result;
        try{
            /* hata olusabilecek kod ornegin 0'a bolme hatasi
             * dosya acilmaz, null pointer exception
             */
            System.out.println("try blogu");

            System.out.print("a : ");
            a = scanner.nextInt();
            System.out.println("b : ");
            b = scanner.nextInt();

            //result = a / b;
            //System.out.println("result : " + result);

            //Person person = null;
            //person.setAge(18);

        }
        catch(ArithmeticException e){
            /* try blogunda bir hata olmasinda bu blok calisir
             * ve uste e isminde tanimladigimiz (Exception referans degiskeni)
             * degiskene hata bilgiler yuklenir.
             */
            System.out.println("arithmetic exception olustu!");
        }
        catch (NullPointerException e) {
            System.out.println("null pointer exception olustu!");
        }
        catch (InputMismatchException e){

        }
        catch (Exception e){
            System.out.println("other exception : " + e.getMessage());
        }
        finally {
            /* her halukarda calismasini istedigimiz kodlar yazilir. */
        }

        System.out.println("diger kodlarimiz....");

        /* baska ornek */

        try{
            div(2, 0);
        }
        catch (Exception e){

        }

    }

    public static void div(int a, int b) throws ArithmeticException{

        int result = a / b;

    }



}