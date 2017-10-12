package Donguler;

/**
 * Created by UGURCETIN on 19.07.2017.
 */
public class Foreach {

    public static void main(String[] args) {

        String [] kisiler = {"ugur","serhat","kerem"};

        for (int i=0; i<=2 ; i++){

            System.out.println(kisiler[i]);

        }

        System.out.println("-------------------");

        for (String i : kisiler){

            System.out.println(i);

        }
    }
}
