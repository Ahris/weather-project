package uk.ac.bangor.cs.eeuab1.project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLParsing {
	
    String forecast;
    static String geoNameID;

    public String getElementData(String url, String e1, String e2) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = f.newDocumentBuilder();
        Document doc = db.parse(url);
        NodeList items = doc.getElementsByTagName(e1);

        for (int i = 0; i < items.getLength(); i++) {
            Node n = items.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) { // Node = Datatype for XML || Element = item within tags
                continue;
            }
            Element e = (Element)n;
            NodeList descList = e.getElementsByTagName(e2);
            Element descElement = (Element) descList.item(0);
            Node descNode = descElement.getChildNodes().item(0);
            forecast = descNode.getNodeValue();
        }
        return forecast;
    }

    public String getLocation(String url) throws ParserConfigurationException, SAXException, IOException {

        String returnURL = "";
        String geoid = "";
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = f.newDocumentBuilder();
        Document doc = db.parse(url);
        NodeList items = doc.getElementsByTagName("geoname");

        for (int i = 0; i < items.getLength(); i++) {
            Node n = items.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element e = (Element) n;
            NodeList titleList = e.getElementsByTagName("geonameId");
            Element titleElement = (Element) titleList.item(0);
            Node titleNode = titleElement.getChildNodes().item(0);
            geoid = titleNode.getNodeValue();
            returnURL = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/"+geoid;
            geoNameID = geoid;
        }
        return returnURL;
    }

    public String getWeatherIcon(String getUrl) throws MalformedURLException, IOException {

        String path = ("M:\\\\Year 2\\\\Java 2\\\\project2\\\\img");
        URL url = new URL(getUrl);
        InputStream stream = url.openStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                if (thisLine.contains("Sunny")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\sunny.img");
                } else if (thisLine.contains("Cloudy") || thisLine.contains("Thick Cloud")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\cloudy.img");
                } else if (thisLine.contains("Light Cloud")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\light_clouds.img");
                } else if (thisLine.contains("Windy")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\windy.img");
                } else if (thisLine.contains("Heavy rain")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\heavy_rain.img");
                } else if (thisLine.contains("Light Rain") || thisLine.contains("Rain Showers")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\light_showers.img");
                } else if (thisLine.contains("Snow")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\snow.img");
                } else if (thisLine.contains("Lightning")) {
                    path = ("M:\\Year 2\\Java 2\\project2\\img\\lightning.img");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public String readXML(String getUrl) throws MalformedURLException, IOException {
        URL url = new URL(getUrl);
        InputStream stream = url.openStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                System.out.println(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getUrl;
    }

    public List<String> Split(String data) {

        String[] items = data.split(", ");
        List<String> itemList = new ArrayList<>();

        for (String item : items) {
            itemList.add(item);
        }

        // Combining the strings into one for display then removing the excess
        itemList.add(", ");
        String c = itemList.get(7);
        String j = itemList.get(5);
        c = c.concat(j);
        itemList.set(5, c);
        itemList.remove(7);

        // Replacing the string and concatenating
        String i = itemList.get(4);
        j = itemList.get(5);
        i = i.concat(j);
        itemList.set(4, i);
        itemList.remove(5);
        return itemList;
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String test = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2644037";
        XMLParsing xml = new XMLParsing();
        System.out.println(xml.getWeatherIcon(test));
        System.out.println(xml.readXML(test));
        System.out.println(xml.getElementData(test, "item", "title"));
        String desc = xml.getElementData(test, "item", "description");
        System.out.println(xml.Split(desc));
    }
}

