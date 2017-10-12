package JOptionPane;
import javax.swing.JOptionPane;
/**
 * Created by UGURCETIN on 16.07.2017.
 */
public class J1 {
    public static void main(String[] args) {
        String girilen = JOptionPane.showInputDialog("ilk sayıyı girin :");
        int sayi1 = Integer.valueOf(girilen);
        String girilen2 = JOptionPane.showInputDialog("ikinci sayıyı girin :");
        int sayi2 = Integer.valueOf(girilen2);
        int toplam = 0;

        if (sayi1 < sayi2) {
            for (int i = sayi1; i <= sayi2; i++) {
                toplam += i;
            }
            JOptionPane.showMessageDialog(null, toplam, "Sonuç", JOptionPane.DEFAULT_OPTION);

        } else {
            String message = "İLK SAYI, İKİNCİ SAYIDAN KÜÇÜK OLMAK ZORUNDADIR!";
            JOptionPane.showMessageDialog(null, message, "Uyarı", JOptionPane.DEFAULT_OPTION);

        }

    }
}
