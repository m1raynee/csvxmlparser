package com.m1raynee.csvxmlparser.parsing;

import com.m1raynee.csvxmlparser.model.AddressEntry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser implements FileParser {
    @Override
    public List<AddressEntry> parse(Path path) throws Exception {
        List<AddressEntry> entries = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(path.toFile());

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String city = element.getAttribute("city");
                String street = element.getAttribute("street");
                String house = element.getAttribute("house");
                String floorStr = element.getAttribute("floor");
                int floor = floorStr.isEmpty() ? 0 : Integer.parseInt(floorStr); // can be not int?

                entries.add(new AddressEntry(city, street, house, floor));
            }
        }
        return entries;
    }
}