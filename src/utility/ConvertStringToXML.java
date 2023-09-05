package utility;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Result;
//import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ConvertStringToXML
{

	public static void convertStringToXML(String xmlSourceString,int XMLType) throws ParserConfigurationException, SAXException, IOException, TransformerException 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(new InputSource(new StringReader(xmlSourceString)));
	    // Use a Transformer for output
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    
	    //Source src = new DOMSource(doc);
	    //Result dest = new StreamResult( new File( "xmlFileName.xml" ) );
	    
	    DOMSource source = new DOMSource(doc);
	    StreamResult result;
	    if (XMLType == 0)
	    {
	    	result = new StreamResult(new File(Config.Path_XMLFileOutput+Config.File_XMLDirectEndorsement));
	    	transformer.transform(source,result);
	    }
	    if (XMLType == 1)
	    {
	    	result = new StreamResult(new File(Config.Path_XMLFileOutput+Config.File_XMLEndorsement));
	    	transformer.transform(source,result);
	    }
	    if (XMLType == 2)
	    {
	    	result = new StreamResult(new File(Config.Path_XMLFileOutput+Config.File_XMLSection1));
	    	transformer.transform(source,result);
	    }
	    if (XMLType == 3)
	    {
	    	result = new StreamResult(new File(Config.Path_XMLFileOutput+Config.File_XMLSection2));
	    	transformer.transform(source,result);
	    }
	    //transformer.transform(source,result);
	}
	
}
