package com.parse.xmlfile;

import com.example.electronictest.R;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import com.parse.xmlfile.ParseXmlFile;

public class ParseXmlFile {
    public void parsXmlFile(XmlPullParser parser)
    {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("");

            Node root = document.getDocumentElement();
            NodeList testSets = root.getChildNodes();
            for (int i = 0; i < testSets.getLength(); ++i) {
                Node testSet = testSets.item(i);
                if (testSet.getNodeType() != Node.TEXT_NODE) {
                    NodeList testSetProps = testSet.getChildNodes();
                    for (int j = 0; j < testSetProps.getLength(); ++j) {
                        Node testProd = testSetProps.item(j);
                        if (testProd.getNodeType() != Node.TEXT_NODE) {
                            //System.out.println("1: " + testProd.getNodeType());
                        }
                    }
                }
            }
        }
        catch(ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
