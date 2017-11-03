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

    public static void prepFile(String storenumber) throws IOException {

        // DOM Version

        POSCode = JOptionPane.showInputDialog("Enter the POS Code");
        merchCode = JOptionPane.showInputDialog("Enter the Merchandise Code");
        price = JOptionPane.showInputDialog("Enter the Unit Price");
        Desc = JOptionPane.showInputDialog("Enter the Item Description");
        itemID = JOptionPane.showInputDialog("Enter the Item ID");

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // NAXML-MaintenanceRequest block
            Document itt = docBuilder.newDocument();
            Element rootElement = itt.createElement("NAXML-MaintenanceRequest");
            Attr version = itt.createAttribute("version");
            version.setValue("3.4");
            Attr xmlns = itt.createAttribute("xmlns");
            xmlns.setValue("http://www.naxml.org/POSBO/Vocabulary/2003-10-16");
            Attr xmlns_vxt = itt.createAttribute("xmlns:vxt");
            xmlns_vxt.setValue("urn:vfi-sapphire:np.naxmlext.2005-06-24");
            rootElement.setAttributeNode(version);
            rootElement.setAttributeNode(xmlns);
            rootElement.setAttributeNode(xmlns_vxt);
            itt.appendChild(rootElement);


            // Transmission header block, appended to root
            Element transmissionHeader = itt.createElement("TransmissionHeader");
            rootElement.appendChild(transmissionHeader);

            // StoreLocationID tag
            Element storeLocationID = itt.createElement("StoreLocationID");
            storeLocationID.appendChild(itt.createTextNode(storenumber));
            transmissionHeader.appendChild(storeLocationID);

            // VendorName tag
            Element vendorName = itt.createElement("VendorName");
            vendorName.appendChild(itt.createTextNode("Broken Coin"));
            transmissionHeader.appendChild(vendorName);

            // VendorModelVersion tag
            Element vendorModelVersion = itt.createElement("VendorModelVersion");
            vendorModelVersion.appendChild(itt.createTextNode("MWS"));
            transmissionHeader.appendChild(vendorModelVersion);

            // End of TransmissionHeader block

            //ItemMaintenance block, appended to root
            Element itemMaintenance = itt.createElement("ItemMaintenance");
            rootElement.appendChild(itemMaintenance);

            // TableAction tag
            Element tableAction = itt.createElement("TableAction");
            Attr ttype = itt.createAttribute("type");
            ttype.setValue("update");
            tableAction.setAttributeNode(ttype);
            itemMaintenance.appendChild(tableAction);

            // RecordAction tag
            Element recordAction = itt.createElement("RecordAction");
            recordAction.setAttribute("type", "addchange");
            itemMaintenance.appendChild(recordAction);

            // ITTDetail block, appended to ItemMaintenance tag
            Element ittDetail = itt.createElement("ITTDetail");
            itemMaintenance.appendChild(ittDetail);

            // ittDetailRecordAction tag
            Element ittDetailRecordAction = itt.createElement("RecordAction");
            ittDetailRecordAction.setAttribute("type", "addchange");
            ittDetail.appendChild(ittDetailRecordAction);

            // ItemCode block, appended to ITTDetail tag
            Element itemCode = itt.createElement("ItemCode");
            ittDetail.appendChild(itemCode);

            // POSCodeFormat tag
            Element posCodeFormat = itt.createElement("POSCodeFormat");
            posCodeFormat.setAttribute("format", "upcA");
            itemCode.appendChild(posCodeFormat);

            // POSCode tag
            Element posCode = itt.createElement("POSCode");
            posCode.appendChild(itt.createTextNode(POSCode));
            itemCode.appendChild(posCode);

            // POSCodeModifier tag
            Element posCodeMod = itt.createElement("POSCodeModifier");
            posCodeMod.setAttribute("name", "1PK from CASE of 8");
            posCodeMod.appendChild(itt.createTextNode("0"));
            itemCode.appendChild(posCodeMod);

            // End of ItemCode block

            // ITTData block, appended to ITTDetail
            Element ittData = itt.createElement("ITTData");
            ittDetail.appendChild(ittData);

            // ActiveFlag tag
            Element activeFlag = itt.createElement("ActiveFlag");
            activeFlag.setAttribute("value", "yes");
            ittData.appendChild(activeFlag);

            // MerchandiseCode tag
            Element merchandiseCode = itt.createElement("MerchandiseCode");
            merchandiseCode.appendChild(itt.createTextNode(merchCode));
            ittData.appendChild(merchandiseCode);

            // RegularSellPrice tag
            Element regularSellPrice = itt.createElement("RegularSellPrice");
            regularSellPrice.appendChild(itt.createTextNode(price));
            ittData.appendChild(regularSellPrice);

            // Description tag
            Element description = itt.createElement("Description");
            description.appendChild(itt.createTextNode(Desc));
            ittData.appendChild(description);

            // ItemID tag
            Element itemIDtag = itt.createElement("ItemID");
            itemIDtag.appendChild(itt.createTextNode(itemID));
            ittData.appendChild(itemIDtag);

            // PaymentSystemsProductCode tag
            Element paymentSystemsProductCode = itt.createElement("PaymentSystemsProductCode");
            paymentSystemsProductCode.appendChild(itt.createTextNode("400"));
            ittData.appendChild(paymentSystemsProductCode);

            // SellingUnits tag
            Element sellingUnits = itt.createElement("SellingUnits");
            sellingUnits.appendChild(itt.createTextNode("1"));
            ittData.appendChild(sellingUnits);

            // TaxStrategyID tag
            Element taxStrategyID = itt.createElement("TaxStrategyID");
            taxStrategyID.appendChild(itt.createTextNode("1"));
            ittData.appendChild(taxStrategyID);

            // PriceRequiredFlag tag
            Element priceRequiredFlag = itt.createElement("PriceRequiredFlag");
            priceRequiredFlag.setAttribute("value", "no");
            ittData.appendChild(priceRequiredFlag);

            // End of ITTData block

            // Extension block, appended to ITTDetail
            Element extension = itt.createElement("Extension");
            ittDetail.appendChild(extension);

            // vxt:FlagArticleFoodStamp tag
            Element vxtFAFS = itt.createElement("vxt:FlagArticleFoodStamp");
            vxtFAFS.setAttribute("value", "no");
            extension.appendChild(vxtFAFS);

            // End of Extension block

            // End of ITTDetail block

            // End of ItemMaintenance block

            // End of NAXML-MaintenanceRequest block

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Data in the file to be written
            DOMSource source = new DOMSource(itt);

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
}

