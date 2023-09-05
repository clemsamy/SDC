package utility;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

//import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

/*
 * Dependencies: Apache POI Library from http://poi.apache.org/
 */
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.io.Files;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
 
import java.io.Reader; 
import java.util.List; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff; 
import org.custommonkey.xmlunit.Diff; 
import org.custommonkey.xmlunit.Difference; 
import org.custommonkey.xmlunit.XMLUnit;
//import org.xml.sax.SAXException;

public class ConvertCSVToEXCEL 
{
	 
 
  private static void convertSelectedSheetInXLXSFileToCSV(File xlsxFile, int sheetIdx) throws Exception 
  {
	  
		  FileInputStream fileInStream = new FileInputStream(xlsxFile);
	      int written=0;
	      //int count =0;     
	      int isExists,wholenumber=2;
	      int intFieldNumber = 1;
	      String str998="";
	      String actual2[]={""};
	      String strheading[]={""};
	      String strFieldName = null;
	      String strheadingtemp=null;

	      // Open the xlsx and get the requested sheet from the workbook
	      XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
	      XSSFSheet selSheet = workBook.getSheetAt(sheetIdx);

	      // Iterate through all the rows in the selected sheet
	      Iterator<Row> rowIterator = selSheet.iterator();
	      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv")));
	      //StringBuffer sb = new StringBuffer();
	      bwr.write("Field Number,Field Name,Sub-Field Number,Sub-Field Name,Extracted Value");
	      bwr.write("\r\n");
	      while (rowIterator.hasNext()) 
	      {
	          Row row = rowIterator.next();
	          
	          // Iterate through all the columns in the row and build ","
	          // separated string
	          Iterator<Cell> cellIterator = row.cellIterator();
	          StringBuffer sb = new StringBuffer();
	          //i=0; 	       
	          //count=0;
			  while (cellIterator.hasNext()) 
	          {
	          	  Cell cell = cellIterator.next();	   
	          	  //count =0;          	  
	              System.out.println("Cellval= "+cell.getStringCellValue());
	              if (sb.length() != 0) 
	              {
	            	  sb.append(",");
	              }
	              switch (cell.getCellType()) {
	              case STRING:
	                  sb.append(cell.getStringCellValue());
	                  break;
	              case NUMERIC:
	                  sb.append(cell.getNumericCellValue());
	                  break;
	              case BOOLEAN:
	                  sb.append(cell.getBooleanCellValue());
	                  break;
	              case BLANK:
	                  sb.append(",");
	                  break;                      
	              default:
	            	  //sb.append(cell.getBooleanCellValue() + ",");
	                  //break;             
	              }
	          }
	          String temp = sb.toString();	          
	          //*****To remove the blank space record or the heading record from the copied file
	          str998="";
	          isExists = temp.indexOf(",");
	          System.out.println("isExists= " + isExists);
	          System.out.println("Temp= " + temp);
	          
	          if (isExists != -1)
	          {
	        	  String actual=temp.substring(0, temp.indexOf(",")); 
	        	  strheading = temp.split(",");
	           	  System.out.println("Actual= " +actual);
	           	  if (actual.equals("64.1")||actual.equals("64.2")||actual.equals("64.3")||actual.equals("64.4")||actual.equals("64.5")||actual.equals("64.6"))
	           	  {  
	           		  written++;               		  
	           	  }
	           	  actual2 = actual.split("\\.");
	        	  //System.out.println("Actual Length= "+ actual2.length);
	        	  wholenumber = actual2.length;
	        	  //System.out.println("Index= "+FieldNumtemp);	      	  
	        	  if (actual2.length > 1)
	        	  {
	        		  str998 = actual2[1];
	        		  intFieldNumber = Integer.valueOf(actual2[0]);
	        		  //System.out.println("FullString= " + actual+" Substring1= "+actual2[0]+" Substring2= "+actual2[1]);
	        	  }
	        		  System.out.println("Wholenumber or not= "+strheading[0].indexOf("."));
	        		  if (strheading[0].indexOf(".")== -1)  //*** checking it is a decimal number or not
	        		     strFieldName = strheading[1];
	        
	          }
	          else if(isExists == -1 )
	          {
	        	   strFieldName = temp;	        	  
	          }
	                       
	          if (isExists != -1 && !str998.equals("998") && wholenumber > 1 && (written == 0 || written <= 6))
	          {
	        	  bwr.write(intFieldNumber+","+strFieldName+",");
	        	  bwr.write(temp);
	        	  bwr.write("\r\n");	        	  
	          }                   
	          if (written == 12)
	       		  written =0;               			  
	    	 
	        //*****To remove the blank space record or the heading record from the copied file
	        
	     }
	      workBook.close();
		  //flush the stream
	      bwr.flush();
		  //close the stream
		  bwr.close();    
     
   } 

  public static void CompareCSVFiles() throws FileNotFoundException, IOException 
  { 
	  //List countries = new ArrayList<>(); 
	  int NumberOfDifferences=0,i;
	  int mismatch =0;
	  
	  BufferedReader file1 = new BufferedReader(new FileReader("C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fields.csv"));
	  BufferedReader file2 = new BufferedReader(new FileReader("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv"));
	  
	  String file1_line = file1.readLine(); // Reading header, Ignoring in file 1
	  String file2_line = file2.readLine(); // Reading header, Ignoring in file 2
	  
	  while ((file1_line = file1.readLine()) != null && !file1_line.isEmpty() && (file2_line = file2.readLine())!= null) 
	  { 
		  //mismatch = 0;
		  if (!file1_line.equals(file2_line))
		  {
			  mismatch = 0;
			  String[] file1_fields = file1_line.split(",");
			  String[] file2_fields = file2_line.split(",");
			  
			  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
				  mismatch = 0;
			  
			  
			  //System.out.println("file1_fields length= "+file1_fields.length);
			  //System.out.println("file1_line_last char= "+file1_line.charAt(file1_line.length()-1));
			  
			  for (i=0;i<file1_fields.length;i++)
			  {
				  //System.out.println("file1_fields= "+file1_fields[i]);
				  if (!file1_fields[i].equals(file2_fields[i]))
					  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
						  mismatch = 0;
				  else		
					  mismatch = 1;	  
			  }
			  
			  //System.out.println("Differences Found. Line= "+ file1_fields[2]);
			  /*System.out.println("Differences Found in Files: ");
			  System.out.println("Differences Found in File 1: "+ file1_line);
			  System.out.println("Differences Found in File 2: "+ file2_line);*/
			  //System.out.println("mismatch="+mismatch);
			  if (mismatch == 1)
			  {
				  System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
				  NumberOfDifferences++;
			  }
			  
			  //file1_line.charAt(file1_line.length() - 1) == ','
		  }
		  
		  /* *String FieldNumber = fields[0]; 
		  String capital = fields[1]; 
		  String currency = fields[2]; 
		  //Country nation = new Country(name, capital, currency); 
		  //countries.add(nation);*/ 
	  } 
	  file1.close(); 
	  file2.close(); 
	  
	  System.out.println("No. Differences found in two files: "+ NumberOfDifferences);
      if (NumberOfDifferences == 0)
    	  System.out.println("No Differences found in two files");
	  //return countries; 
 }
  
  /* * Method to read CSV file using CSVParser from Apache Commons CSV */ 
    
  public static void CompareXMLFiles() throws FileNotFoundException,SAXException, IOException
  {
	// reading two xml file to compare in Java program 
	  FileInputStream fis1 = new FileInputStream("C:\\Clem\\TEMP\\XMLs\\MRCXML-Downloaded Source 1.xml"); 
	  FileInputStream fis2 = new FileInputStream("C:\\Clem\\TEMP\\XMLs\\MRCXML-Downloaded Source 2.xml"); 
	  
	  // using BufferedReader for improved performance 
	  BufferedReader source = new BufferedReader(new InputStreamReader(fis1)); 
	  BufferedReader target = new BufferedReader(new InputStreamReader(fis2)); 
	  
	  //configuring XMLUnit to ignore white spaces 
	  XMLUnit.setIgnoreWhitespace(true); 
	   
	  
	  //comparing two XML using XMLUnit in Java 
	  @SuppressWarnings("unchecked")
	  List<Difference> differences = compareXMLN(source,target); 
	  
	  //showing differences found in two xml files 
	  printDifferences(differences);
	  	  
  }
  public static List compareXMLN(Reader source, Reader target) throws SAXException, IOException
  { 
	  //creating Diff instance to compare two XML files 
	  Diff xmlDiff = new Diff(source, target); 
	  
	  //for getting detailed differences between two xml files 
	  DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
	  //DetailedDiff detailXmlDiff = new DetailedDiff(XMLUnit.compareXML(source, target)); 
	  
	  return detailXmlDiff.getAllDifferences(); 
  }

  public static void printDifferences(List<Difference> differences)
  {
	  int totalDifferences = differences.size(); 
	  System.out.println("==============================="); 
	  System.out.println("Total differences : " + totalDifferences); 
	  System.out.println("================================"); 
	  
	  for(Difference difference:differences)
	  { 
		  System.out.println(difference);
	  }
  
  }
  
  public static void CompareXMLFilesNew() throws Exception
  {
	// reading two xml file to compare in Java program 
	//  File fis1 = new File("C:\\Clem\\TEMP\\MRCXML-Downloaded Source 1.xml"); 
	//  File fis2 = new File("C:\\Clem\\TEMP\\MRCXML-Downloaded Source 2.xml");	
	
	  //File fis1 = new File("C:\\TEMP\\MRCXML-Copied.xml"); 
	  //File fis2 = new File("C:\\TEMP\\MRCXML-Downloaded.xml");		
		
	  //XML xmlfile1 = new XMLDocument(new File("C:\\TEMP\\MRCXML-Copied.xml"));
	  XML xmlfile1 = new XMLDocument(new File("C:\\TEMP\\MRCXML-Downloaded.xml"));	
	  String fullsource = xmlfile1.toString();	
	  String sourcetemp =fullsource.replace("rlc:", "");
	  String source = sourcetemp.replace("/rlc:", "");
	  
	  XML xmlfile2 = new XMLDocument(new File("C:\\TEMP\\MRCXML-Downloaded.xml"));
	  //XML xmlfile2 = new XMLDocument(new File("C:\\TEMP\\MRCXML-MRC4.xml"));
	  String target = xmlfile2.toString();	  
	  
	  assertXMLEquals(source,target);
	
  }  
  public static void assertXMLEquals(String expectedXML, String actualXML) throws Exception 
  {
      XMLUnit.setIgnoreWhitespace(true);
      XMLUnit.setIgnoreAttributeOrder(true);
      

      DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

      List<Difference> allDifferences = diff.getAllDifferences();
      
      int totalDifferences = allDifferences.size();
	  System.out.println("===============================");
	  System.out.println("Total differences : " + totalDifferences);
	  System.out.println("================================");
	  
	  for(Difference difference:allDifferences)
	  { 
		  System.out.println(difference);
	  }          
  }  
  public static void main(String[] args) throws Exception 
 {
          //**** TO call the convert excelt o CSV file function....
    	  	/*File myFile = new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields.xlsx");
          	int sheetIdx = 0; // 0 for first sheet
          	convertSelectedSheetInXLXSFileToCSV(myFile, sheetIdx);*/    //*** WORKING
          //**** TO call the convert excelt o CSV file function....
	  		//readCSV();
	  
    	  //CompareCSVFiles();	   //*** WORKING
    	  
    	  //CompareXMLFiles();     //*** STill in error and in fixing
	  
    	  CompareXMLFilesNew();    //*** WORKING
	       
 }

}


