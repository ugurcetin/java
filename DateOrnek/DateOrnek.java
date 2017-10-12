package DateOrnek;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by UGURCETIN on 26.07.2017.
 */
public class DateOrnek {
    public static void main(String[] args) {

        Date now = new Date();
        //System.out.println(now);
        System.out.println(now.toString());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(df.format(now));
    }
}
