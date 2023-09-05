package utility;

//import static org.testng.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

//import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Difference;
//import org.custommonkey.xmlunit.DifferenceConstants;
//import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.XMLUnit;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
//import org.xmlunit.builder.DiffBuilder;
//import org.xmlunit.builder.Input;
//import org.xmlunit.diff.Diff;
//import org.xmlunit.diff.NodeMatcher;
//import org.xmlunit.util.Nodes;
//import org.xmlunit.util.Predicate;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import utility.IgnoreNamedElementsDifferenceListener;

public class XMLUtils
{
	public static void convertStringToXML(String xmlSourceString,String OutputFile) throws ParserConfigurationException, SAXException, IOException, TransformerException 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(new InputSource(new StringReader(xmlSourceString)));
	    // Use a Transformer for output
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    
	    DOMSource source = new DOMSource(doc);
	    StreamResult result;
	   	result = new StreamResult(new File(OutputFile));
	   	transformer.transform(source,result);	
	}
	public static boolean CompareXMLFiles(String filename1,String filename2) throws Exception
	{
		// reading two xml file to compare in Java program 
		//  File fis1 = new File("C:\\Clem\\TEMP\\MRCXML-Downloaded Source 1.xml"); 
		//  File fis2 = new File("C:\\Clem\\TEMP\\MRCXML-Downloaded Source 2.xml");
		
		  //File fis1 = new File("C:\\TEMP\\MRCXML-Copied.xml"); 
		  //File fis2 = new File("C:\\TEMP\\MRCXML-Downloaded.xml");	
			
		  //XML xmlfile1 = new XMLDocument(new File("C:\\TEMP\\MRCXML-Copied.xml"));
		  
		  XML xmlfile1 = new XMLDocument(new File(filename1));  //*** ST XML
		  /*String wholesource = xmlfile1.toString();	
		  String sourcetemp =wholesource.replace("rlc:", "");
		  String source = sourcetemp.replace("/rlc:", "");*/

		  String wholesource = xmlfile1.toString();	
		  String source =wholesource.replace("rlc:", "");
		  source = source.replace("/rlc:", "");
		  source = source.replace("ac:", "");
		  source = source.replace("/ac:", "");
		  source = source.replace("sdc1:", "");
		  source = source.replace("/sdc1:", "");		  
		  
		  XML xmlfile2 = new XMLDocument(new File(filename2));   //*** UAT XML
		  //XML xmlfile2 = new XMLDocument(new File("C:\\TEMP\\MRCXML-MRC4.xml"));
		 /* String wholetarget = xmlfile2.toString();	  
		  String sourcetemp2 =wholetarget.replace("rlc:", "");
		  String target = sourcetemp2.replace("/rlc:", "");*/
		  
		  String wholetarget = xmlfile2.toString();	  
		  String target =wholetarget.replace("rlc:", "");
		  target = target.replace("/rlc:", "");
		  target = target.replace("ac:", "");
		  target = target.replace("/ac:", "");
		  target = target.replace("sdc1:", "");
		  target = target.replace("/sdc1:", "");		 	 
		  
		  //if(assertXMLEquals_WITHOUT_IGNORE_LIST_WORKING(source,target))      // Without Ignore Working OK
	      if(assertXMLEquals(source,target))    // With Ignore -- DONE WORKING
	      //if(assertXMLEquals(target,source))    // With Ignore -- DONE WORKING  ST Vs UAT
			  return true;
		  else
			  return false;
	  }  
	  //public static boolean assertXMLEquals_WITHOUT_IGNORE_LIST_WORKING(String expectedXML, String actualXML) throws Exception 
	  public static boolean assertXMLEquals_WITHOUT_IGNORE_LIST_WORKING(String actualXML, String expectedXML) throws Exception
	  {	      
		  XMLUnit.setIgnoreWhitespace(true);
	      XMLUnit.setIgnoreAttributeOrder(true);
	      XMLUnit.setIgnoreComments(true);
	      XMLUnit.setIgnoreAttributeOrder(true);
	      
	      //DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));
	      DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(actualXML, expectedXML));
	      List<Difference> allDifferences = diff.getAllDifferences();	      
	      int totalDifferences = allDifferences.size();	      
	   
	      Variables.OutputBuffer = Variables.OutputBuffer + "\nTotal differences : " + totalDifferences+"\n";
	           
		  for(Difference difference:allDifferences)
		     Variables.OutputBuffer = Variables.OutputBuffer + "\n"+difference;
		            
		  if (totalDifferences ==0)
			  return true;
		  else
			  return false;
      }
	  //public static boolean assertXMLEquals(String expectedXML, String actualXML) throws Exception 
	  public static boolean assertXMLEquals(String actualXML, String expectedXML) throws Exception     //*** actualXML=ST,Exported,On-page,On-Page;expectedXML=UAT,Emailed,Downloaded,Emailed
	  {	      
		  XMLUnit.setIgnoreWhitespace(true);
	      XMLUnit.setIgnoreAttributeOrder(true);
	      XMLUnit.setIgnoreComments(true);
	      XMLUnit.setIgnoreAttributeOrder(true);	      
	      
	      DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));
	      //DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(actualXML, expectedXML));
	      //diff.overrideDifferenceListener(new IgnoreNamedElementsDifferenceListener("CreationDate","SentDate"));
	      diff.overrideDifferenceListener(new IgnoreNamedElementsDifferenceListener(Config.IgnoreXMLattributeList));
	  
	      List<Difference> allDifferences = diff.getAllDifferences();	   
	      int totalDifferences = allDifferences.size();
	            	      
	      Variables.OutputBuffer = Variables.OutputBuffer + "\nTotal differences : " + totalDifferences+"\n";
          
	  	  for(Difference difference:allDifferences)
	  	  {
	  	     Variables.OutputBuffer = Variables.OutputBuffer + "\n"+difference;
	  	     //System.out.println("Diference= "+difference);
	  	  }	  	            
	  	  if (totalDifferences ==0)
	  		  return true;
	  	  else
	  		  return false;		
	}
	
}
