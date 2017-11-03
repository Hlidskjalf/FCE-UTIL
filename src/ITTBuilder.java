import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ITTBuilder {
    /**
     * ITTBuilder class is designed to create a custom ITT file for the addition of a single
     * item into a POS system without the need for Pricebook processing. The user will be prompted
     * to enter all of the item specific details and a single <ITTDetail> will be created for the
     * site.
     */

    static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    static String POSCode = "";
    static String merchCode = "";
    static String price = "";
    static String Desc = "";
    static String itemID = "";
    static String xml = "";

    /**
     * Method prepFile uses JOptionPane input dialogs to prompt the user for input of the required
     * pricebook variables, then builds the ITT file with the item specific variables.
     * @throws IOException
     */

    public static void prepFile() throws IOException {

        // DOM Version

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // NAXML-MaintenanceRequest block
            Document sem = docBuilder.newDocument();
            Element rootElement = sem.createElement("NAXML-MaintenanceRequest");
            Attr version = sem.createAttribute("version");
            version.setValue("3.4");
            Attr xmlns = sem.createAttribute("xmlns");
            xmlns.setValue("http://www.naxml.org/POSBO/Vocabulary/2003-10-16");
            Attr xmlns_vxt = sem.createAttribute("xmlns:vxt");
            xmlns_vxt.setValue("urn:vfi-sapphire:np.naxmlext.2005-06-24");
            rootElement.setAttributeNode(version);
            rootElement.setAttributeNode(xmlns);
            rootElement.setAttributeNode(xmlns_vxt);
            sem.appendChild(rootElement);


            // Transmission header block, appended to root
            Element transmissionHeader = sem.createElement("TransmissionHeader");
            rootElement.appendChild(transmissionHeader);

            // StoreLocationID tag
            Element storeLocationID = sem.createElement("StoreLocationID");
            storeLocationID.appendChild(sem.createTextNode("1099"));
            transmissionHeader.appendChild(storeLocationID);

            // VendorName tag
            Element vendorName = sem.createElement("VendorName");
            vendorName.appendChild(sem.createTextNode("Broken Coin"));
            transmissionHeader.appendChild(vendorName);

            // VendorModelVersion tag
            Element vendorModelVersion = sem.createElement("VendorModelVersion");
            vendorModelVersion.appendChild(sem.createTextNode("MWS"));
            transmissionHeader.appendChild(vendorModelVersion);

            // End of TransmissionHeader block

            //ItemMaintenance block, appended to root
            Element itemMaintenance = sem.createElement("ItemMaintenance");
            rootElement.appendChild(itemMaintenance);

            // TableAction tag
            Element tableAction = sem.createElement("TableAction");
            Attr ttype = sem.createAttribute("type");
            ttype.setValue("update");
            tableAction.setAttributeNode(ttype);
            itemMaintenance.appendChild(tableAction);

            // RecordAction tag
            Element recordAction = sem.createElement("RecordAction");
            recordAction.setAttribute("type", "addchange");
            itemMaintenance.appendChild(recordAction);

            // ITTDetail block, appended to ItemMaintenance tag
            Element ittDetail = sem.createElement("ITTDetail");
            itemMaintenance.appendChild(ittDetail);

            // ittDetailRecordAction tag
            Element ittDetailRecordAction = sem.createElement("RecordAction");
            ittDetailRecordAction.setAttribute("type", "addchange");
            ittDetail.appendChild(ittDetailRecordAction);

            // ItemCode block, appended to ITTDetail tag
            Element itemCode = sem.createElement("ItemCode");
            ittDetail.appendChild(itemCode);

            // POSCodeFormat tag
            Element posCodeFormat = sem.createElement("POSCodeFormat");
            posCodeFormat.setAttribute("format", "upcA");
            itemCode.appendChild(posCodeFormat);

            // End of ItemCode block

            // End of ITTDetail block

            // End of ItemMaintenance block

            // End of NAXML-MaintenanceRequest block

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Data in the file to be written
            DOMSource source = new DOMSource(sem);

            // Writing the file (set PATH here)
            StreamResult result = new StreamResult(new File("xml/ITT" + timeStamp + ".xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

/*  Manual version - human readable

        // JOptionPane dialogs to gather pricebook variables

        POSCode = JOptionPane.showInputDialog("Enter the POS Code");
        merchCode = JOptionPane.showInputDialog("Enter the Merchandise Code");
        price = JOptionPane.showInputDialog("Enter the Unit Price");
        itemID = JOptionPane.showInputDialog("Enter the Item ID");
        Desc = JOptionPane.showInputDialog("Enter the Item Description");

        // XML Build

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
    */

    /**
     * Method build file actually handles the creation of the file. This method should expand to placing the file into
     * the correct directory and running the FTP Script as well.
     */
    public static void buildFile() {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xml/ITT" + timeStamp + ".xml"), StandardCharsets.UTF_8))) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

