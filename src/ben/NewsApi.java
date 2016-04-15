package ben;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class NewsApi
{
    public String thisWeekNews;

    public NewsApi() 
    {
	try{
          URL url = new URL("http://www.forexfactory.com/ffcal_week_this.xml");
          URLConnection connection = url.openConnection();

          Document doc = parseXML(connection.getInputStream());
          NodeList eventNodes = doc.getElementsByTagName("event");

          for(int i=0; i<eventNodes.getLength();i++)
          {
//            System.out.println(eventNodes.item(i).getTextContent());
            NodeList childNodes = eventNodes.item(i).getChildNodes();
	    
            for(int n=0; n<childNodes.getLength();n++){
              System.out.println(childNodes.item(n).getTextContent());
            }
          }
        }
	catch(Exception e){
          e.printStackTrace();
        }
    }

    private Document parseXML(InputStream stream)
    throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }       

        return doc;
    }
}
