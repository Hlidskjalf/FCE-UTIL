import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ITTBuilder {
    /**
     * ITTBuilder class is designed to create a custom ITT file for the addition of a single
     * item into a POS system without the need for Pricebook processing. The user will be prompted
     * to enter all of the item specific details and a single <ITTDetail> will be created for the
     * site. The methods createFile is generic, and is reused in SEMBuilder.java.
     */

    static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    static String placeholder = "";
    static String placeholder2 = "";
    static String placeholder3 = "";
    static String xml = "";

    //TODO: Break down the steps and create functions. Leverage the UpdateStores script for pushing the files

    public static void dataBuilder() throws IOException {

        System.out.println("Enter 1: ");
        Scanner input = new Scanner(System.in);
        placeholder = input.next().toString();
        System.out.println("Enter 2: ");
        placeholder2 = input.next().toString();
        System.out.println("Enter 3: ");
        placeholder3 = input.next().toString();
        xml += "Line " + placeholder + "\n";
        xml += "Line " + placeholder2 + "\n";
        xml += "Line " + placeholder3 + "\n";
        xml += "Line " + placeholder2 + "\n";
        xml += "Line " + placeholder3 + "\n";
        xml += "Line two";

        buildFile();

    }

    public static void buildFile() {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xml/ITT" + timeStamp + ".xml"), StandardCharsets.UTF_8))) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

