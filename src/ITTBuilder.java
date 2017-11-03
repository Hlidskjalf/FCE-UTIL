import javax.swing.*;
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
    static String POSCode = "";
    static String merchCode = "";
    static String price = "";
    static String Desc = "";
    static String itemID = "";
    static String xml = "";

    //TODO: Break down the steps and create functions. Leverage the UpdateStores script for pushing the files

    public static void prepFile() throws IOException {


        POSCode = JOptionPane.showInputDialog("Enter the POS Code");

        merchCode = JOptionPane.showInputDialog("Enter the Merchandise Code");

        price = JOptionPane.showInputDialog("Enter the Unit Price");

        itemID = JOptionPane.showInputDialog("Enter the Item ID");

        Desc = JOptionPane.showInputDialog("Enter the Item Description");

        xml += "<?xml version=\"1.0\" standalone=\"no\" ?>\n";
        xml += "<NAXML-MaintenanceRequest version=\"3.4\" xmlns=\"http://www.naxml.org/POSBO/Vocabulary/2003-10-16\" xmlns:vxt=\"urn:vfi-sapphire:np.naxmlext.2005-06-24\">\n";
        xml += "\t<TransmissionHeader>\n";
        xml += "\t\t<StoreLocationID>REPLACE WITH STORENUMBER</StoreLocationID>\n";
        xml += "\t\t<VendorName>Broken Coin</VendorName>\n";
        xml += "\t</TransmissionHeader>\n";
        xml += "\t<ItemMaintenance>\n";
        xml += "\t\t<TableAction type=\"update\"/>\n";
        xml += "\t\t<RecordAction type=\"addchange\"/>\n";
        xml += "\t\t<ITTDetail>\n";
        xml += "\t\t\t<RecordAction type=\"addchange\"/>\n";
        xml += "\t\t\t<ItemCode>\n";
        xml += "\t\t\t\t<POSCodeFormat format=\"upcA\"/>\n";
        xml += "\t\t\t\t<POSCode>" + POSCode + "</POSCode>\n";
        xml += "\t\t\t\t<POSCodeModifier name=\"1PK from CASE of 8\">" + 0 + "</POSCodeModifier>\n";
        xml += "\t\t\t</ItemCode>\n";
        xml += "\t\t\t<ITTData>\n";
        xml += "\t\t\t\t<ActiveFlag value=\"yes\"/>\n";
        xml += "\t\t\t\t<MerchandiseCode>" + merchCode + "</MerchandiseCode>\n";
        xml += "\t\t\t\t<RegularSellPrice>" + price + "</RegularSellPrice>\n";
        xml += "\t\t\t\t<Description>" + Desc + "</Description>\n";
        xml += "\t\t\t\t<ItemID>" + itemID + "</ItemID>\n";
        xml += "\t\t\t\t<PaymentSystemsProductCode>400</PaymentSystemsProductCode>\n";
        xml += "\t\t\t\t<SellingUnits>1</SellingUnits>\n";
        xml += "\t\t\t\t<TaxStrategyID>1</TaxStrategyID>\n";
        xml += "\t\t\t\t<PriceRequiredFlag value=\"no\"/>\n";
        xml += "\t\t\t</ITTData>\n";
        xml += "\t\t\t<Extension>\n";
        xml += "\t\t\t\t<vxt:FlagArticleFoodStamp value=\"no\"/>\n";
        xml += "\t\t\t</Extension>\n";
        xml += "\t\t</ITTDetail>\n";
        xml += "\t</ItemMaintenance>\n";
        xml += "<NAXML-MaintenanceRequest>\n";

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

