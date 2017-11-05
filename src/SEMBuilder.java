import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class SEMBuilder extends ITTBuilder {
    /**
     * ITTBuilder class is designed to create a custom ITT file for the addition of a single
     * item into a POS system without the need for Pricebook processing. The user will be prompted
     * to enter all of the item specific details and a single <ITTDetail> will be created for the
     * site. The methods createFile is generic, and is reused in SEMBuilder.java.
     */

        static String timeStamp = ITTBuilder.timeStamp;

        //TODO: Break down the steps and create functions. Leverage the UpdateStores script for pushing the files

    public static void prepFile() throws IOException {


        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Header and root
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


            // Transmission header
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

            //ItemMaintenance block
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
            Attr rtype = sem.createAttribute("type");
            rtype.setValue("addchange");
            recordAction.setAttributeNode(rtype);
            itemMaintenance.appendChild(recordAction);


            // shorten way
            // staff.setAttribute("id", "1");


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(sem);
            StreamResult result = new StreamResult(new File("xml/SEM" + timeStamp + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
