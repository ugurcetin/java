package Donguler;

/**
 * Created by UGURCETIN on 16.07.2017.
 */
public class Switch {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);

        switch(i){
            case 1:
                System.out.println("Pazartesi");
                break;
            case 2:
                System.out.println("Salý");
                break;
            case 3:
                System.out.println("Çarþamba");
                break;
            case 4:
                System.out.println("Perþembe");
                break;
            case 5:
                System.out.println("Cuma");
                break;
            case 6:
                System.out.println("Cumartesi");
                break;
            case 7:
                System.out.println("Pazar");
                break;
            default:
                System.out.println("Hatalý sayý");
                break;
        }

        for(int j = 0; j<args.length;j++){
            System.out.println(args[j]);
        }
    }
}
