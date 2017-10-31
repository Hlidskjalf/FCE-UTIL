import java.text.SimpleDateFormat;
import java.util.Date;

public class ITTBuilder extends App {
    /**
     * ITTBuilder class is designed to create a custom ITT file for the addition of a single
     * item into a POS system without the need for Pricebook processing. The user will be prompted
     * to enter all of the item specific details and a single <ITTDetail> will be created for the
     * site.
     */

    //TODO: Break down the steps and create functions. Leverage the UpdateStores script for pushing the files

    public static void createFile() {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        System.out.println("ITT" + timeStamp + ".xml");
    }
}
