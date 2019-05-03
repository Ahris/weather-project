package uk.ac.bangor.cs.eeuab1.project2;

import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StAX {

    public static void StAXCreator(String dateRan, String term, String found, String geoNameID) {
        try {
            String path = "M:\\Year 2\\Java 2\\project2\\StAX.xml";  // Did not use java.io.file may cause issues
            Writer fileWriter = new FileWriter(path);
            
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(fileWriter);
            
            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("weatherSearches");
            writer.writeCharacters("\n\t");
            writer.writeStartElement("search");
            writer.writeAttribute("date", dateRan);
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("term");
            writer.writeCharacters(term);
            writer.writeEndElement();
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("found");
            writer.writeCharacters(found);
            writer.writeEndElement();
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("geoNameID");
            writer.writeCharacters(geoNameID);
            writer.writeEndElement();
            writer.writeCharacters("\n\t");
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close(); // Flushes and closes the stream

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }
}
