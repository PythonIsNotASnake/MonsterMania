package monster;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author Kejukedor
 */
public class Creator {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element root;

    public Creator() throws ParserConfigurationException, IOException, SAXException {
        this.factory = DocumentBuilderFactory.newInstance();
        this.builder = this.factory.newDocumentBuilder();
    }

    /**
     * XML-Konfigurationsdatei auswählen
     *
     * @return Dateipfad der XML-Datei
     */
    public String chooseFile() {
        String dataPath = null;
        JFileChooser chooser = new JFileChooser();
        int rueckgabeWert = chooser.showOpenDialog(null);
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            dataPath = chooser.getSelectedFile().getAbsolutePath();
            return dataPath;
        }
        return null;
    }

    /**
     * Erstellt Dokument aus XML-Datei
     *
     * @param dataPath Dateipfad der XML-Datei
     */
    public void buildDocument(String dataPath) throws IOException, SAXException {
        this.document = builder.parse(new File(dataPath));
        this.document.getDocumentElement().normalize();
    }

    /**
     * Findet Wurzelelement aus Document
     */
    public void findRoot() {
        this.root = this.document.getDocumentElement();
    }

    /**
     * Für Angriffe tagName auf 'attack' setzen
     *
     * @param tagName Attributname im Baum des Dokuments
     * @return
     */
    public NodeList searchByTagname(String tagName) {
        NodeList nList;
        return nList = this.document.getElementsByTagName(tagName);
    }

    /**
     * Erstellt einen Angriff aus den Daten des Knoten
     *
     * @param nList        Liste aus Knoten
     * @param attackNumber Nummer des Angriffs
     * @return
     */
    public Attack findAttack(NodeList nList, int attackNumber) {
        Node node = nList.item((attackNumber - 1));
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            String attackName = eElement.getElementsByTagName("attackName").item(0).getTextContent();
            String attackDmgText = eElement.getElementsByTagName("attackDmg").item(0).getTextContent();
            Integer attackDmg = Integer.valueOf(attackDmgText);
            String attackPointsText = eElement.getElementsByTagName("attackPoints").item(0).getTextContent();
            Integer attackPoints = Integer.valueOf(attackPointsText);

            Attack atk = new Attack(attackName, attackDmg, attackPoints);
            return atk;
        }
        return null;
    }

    /**
     * Sucht nach beliebigem Element
     *
     * @param root    Wurzelelement
     * @param tagName Suchbegriff
     * @return gibt gefundenes Element als String zurück
     */
    public String findOneElement(Element root, String tagName) {
        String oneElement = root.getElementsByTagName(tagName).item(0).getTextContent();
        return oneElement;
    }

    /**
     * @param root Wurzelelement
     * @return gibt den Wert der Lebenspunkte als Ganzzahl zurück
     */
    public int findLifepoints(Element root) {
        String lifePointsText = root.getElementsByTagName("life").item(0).getTextContent();
        Integer lifePoints = Integer.valueOf(lifePointsText);
        return (int) lifePoints;
    }

    /**
     * @param root Wurzelelement
     * @return gibt ein fertiges ImageIcon zurück
     */
    public ImageIcon findImage(Element root) {
        String imageText = root.getElementsByTagName("image").item(0).getTextContent();
        ImageIcon image = new ImageIcon(imageText);
        return image;
    }

    public Element getRoot() {
        return this.root;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Creator creat = new Creator();
        creat.buildDocument(creat.chooseFile());
        creat.findRoot();

        System.out.println(creat.getRoot().getElementsByTagName("life").item(0).getTextContent());
    }
}
