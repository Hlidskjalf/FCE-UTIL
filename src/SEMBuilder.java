import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SEMBuilder extends App {
    /**
     * ITTBuilder class is designed to create a custom ITT file for the addition of a single
     * item into a POS system without the need for Pricebook processing. The user will be prompted
     * to enter all of the item specific details and a single <ITTDetail> will be created for the
     * site. The methods createFile is generic, and is reused in SEMBuilder.java.
     */

    //TODO: Break down the steps and create functions. Leverage the UpdateStores script for pushing the files

    public static void createFile() throws IOException {

        /**
         * Method createFile handles the task of writing the SEM file to be used. The file name must be
         * unique, so a date/time stamp is used. The actual data that is written to the file is from the
         * buildFile method, and passed into the Writer class instance.
         */
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xml/SEM" + timeStamp + ".xml"), StandardCharsets.UTF_8))) {
            writer.write(buildFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String buildFile() {

        //TODO expand on this
        String xml = "";
        xml += "Line one\n";
        xml += "Line two";
        return xml;
    }
}
