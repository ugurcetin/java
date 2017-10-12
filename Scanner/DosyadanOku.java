package Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by UGURCETIN on 4.10.2017.
 */
public class DosyadanOku {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("text.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;

            while ((str = br.readLine()) != null) {
                System.out.println(str + "\n");
            }

            br.close();

        }

        catch (IOException e)
        {
            System.out.println("Dosya okunamadı");
        }

    }
}
