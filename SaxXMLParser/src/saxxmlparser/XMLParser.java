/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxxmlparser;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Kolbe
 */
public class XMLParser {

    private static TreeNode curr;

    public static TreeNode load(File xmlFile) throws Exception {
        TreeNode root = new TreeNode("root");
        curr = root;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    //create new tree node
                    TreeNode hold = new TreeNode(qName);
                    //set parent of node
                    hold.setParent(curr);
                    //check for attribute values
                    attributes.getLength();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String idString = attributes.getValue(i);
                        hold.addAttrbute(idString);
                    }
                    curr = hold;
                }

                @Override

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase(curr.getKey())) {
                        curr.getParent().addChild(curr);
                        curr = curr.getParent();
                    } else {
                        throw new SAXException("Improper closing tag in XML. Current tag: " + curr.getKey() + " Tag found: " + qName);
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    String chars = new String(ch, start, length);
                    curr.setValue(chars);
//                            double value = 0.0;
//                            try {
//                                value = Double.parseDouble(gradeString);
//                            } catch (NumberFormatException e) {
//                                throw new SAXException("Value could not be converted");
//                            }
                }

            };
            saxParser.parse(xmlFile.getAbsoluteFile(), handler);

        } catch (Exception e) {
            throw e;
        }
        return root;
    }
}
