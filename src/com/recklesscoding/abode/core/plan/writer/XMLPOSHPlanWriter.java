package com.recklesscoding.abode.core.plan.writer;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.util.IWriter;
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
import java.io.File;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class XMLPOSHPlanWriter implements IWriter {

    @Override
    public void writeFile(String filePath) {
        savePlan(filePath);
    }

    private void savePlan(String filePath) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Plan");
            doc.appendChild(rootElement);

            Element actionPatternsElement = doc.createElement("ActionPatterns");
            rootElement.appendChild(actionPatternsElement);

            for (ActionPattern actionPattern:
            Plan.getInstance().getActionPatterns() ) {
                Element actionPatternElement = doc.createElement("ActionPattern");
                Attr attrAP = doc.createAttribute("name");
                attrAP.setValue(actionPattern.getNameOfElement());
                actionPatternElement.setAttributeNode(attrAP);

                for (ActionEvent actionEvent:
                     actionPattern.getActionEvents()) {
                    Element actionElement = doc.createElement("Action");
                    Attr attrA = doc.createAttribute("name");
                    attrA.setValue(actionEvent.getNameOfElement());
                    actionElement.setAttributeNode(attrA);
                    actionPatternElement.appendChild(actionElement);
                }

                actionPatternsElement.appendChild(actionPatternElement);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}