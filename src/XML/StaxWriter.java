/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxWriter {

    public XMLEventWriter writer;
    public XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

    public void createNode(XMLEventWriter eventWriter, String name,
            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
       

    }

    public void createunderNode(XMLEventWriter eventWriter, String name,
            String value, int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");
// create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }

        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);

        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }

    public void createElementMetaAnfang(XMLEventWriter eventWriter, String name, int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent end1 = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");
// create Start node
        //  StartElement sElement = eventFactory.createStartElement("", "", name);
        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }

        StartElement sElement = eventFactory.createStartElement("", "", name);
//        eventWriter.add(sElement);
//        eventWriter.add(end);
    }

    public void createElementMetaEnde(XMLEventWriter eventWriter, String name, int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");
// create Start node
        //  StartElement sElement = eventFactory.createStartElement("", "", name);
        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }

        EndElement eElement = eventFactory.createEndElement("", "", name);
//        eventWriter.add(eElement);
//        eventWriter.add(end);

    }

    public void CreateMultiAttributeNode8(XMLEventWriter eventWriter, String CGSElementname, String name1, String value1, String name2, String value2, String name3, String value3, String name4,String value4,String name5,String value5, int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent zeilenumbruch = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");

        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }
        StartElement CGSElementStart = eventFactory.createStartElement("", "", CGSElementname);
        eventWriter.add(CGSElementStart);
        eventWriter.add(eventFactory.createAttribute(name1, value1));
        eventWriter.add(eventFactory.createAttribute(name2, value2));
        eventWriter.add(eventFactory.createAttribute(name3, value3));
        eventWriter.add(eventFactory.createAttribute(name4, value4));
        eventWriter.add(eventFactory.createAttribute(name5, value5));
//        eventWriter.add(eventFactory.createAttribute(name6, value6));
//        eventWriter.add(eventFactory.createAttribute(name7, value7));
//        eventWriter.add(eventFactory.createAttribute(name8, value8));
        EndElement eElement = eventFactory.createEndElement("", "", CGSElementname);
        eventWriter.add(eElement);
        eventWriter.add(zeilenumbruch);
        System.out.println(CGSElementStart + "" + eElement);
    }

    
     
    
    
    public void CreateMultiAttributeNode10(XMLEventWriter eventWriter, String CGSElementname, String name1, String value1, String name2, String value2, String name3, String value3, String name4, String value4,
            String name5, String value5, String name6, String value6,  int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent zeilenumbruch = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");

        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }
        StartElement CGSElementStart = eventFactory.createStartElement("", "", CGSElementname);
        eventWriter.add(CGSElementStart);
        eventWriter.add(eventFactory.createAttribute(name1, value1));
        eventWriter.add(eventFactory.createAttribute(name2, value2));
        eventWriter.add(eventFactory.createAttribute(name3, value3));
        eventWriter.add(eventFactory.createAttribute(name4, value4));
        eventWriter.add(eventFactory.createAttribute(name5, value5));
        eventWriter.add(eventFactory.createAttribute(name6, value6));
//        eventWriter.add(eventFactory.createAttribute(name7, value7));
//        eventWriter.add(eventFactory.createAttribute(name8, value8));
//        if ((name9 != null && value9 != null)&&(name10 != null && value10 != null)) {
//            eventWriter.add(eventFactory.createAttribute(name9, value9));
//             eventWriter.add(eventFactory.createAttribute(name10, value10));
//        }
        EndElement eElement = eventFactory.createEndElement("", "", CGSElementname);
        eventWriter.add(eElement);
        eventWriter.add(zeilenumbruch);
        System.out.println(CGSElementStart + "" + eElement);
    }
}
