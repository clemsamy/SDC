package utility;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import au.com.bytecode.opencsv.CSVReader;

public class COMPAREEXCEL {
	
public static int NoOfRecords;  

  public static void compareExcel(String filePath,String fileName1,String fileName2) throws IOException
  {
      try {
	       // get input excel files
	       //System.out.println("Welcome Excel World");
	       File file1 =    new File(filePath+"\\"+fileName1);
	       FileInputStream excellFile1 = new FileInputStream(file1);
	       File file2 =    new File(filePath+"\\"+fileName2);
	       FileInputStream excellFile2 = new FileInputStream(file2);
       
          // Create Workbook instance holding reference to .xlsx file
          XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
          XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

          // Get first/desired sheet from the workbook
          XSSFSheet sheet1 = workbook1.getSheetAt(0);
          XSSFSheet sheet2 = workbook2.getSheetAt(0);

          /*CellStyle style = workbook1.createCellStyle();
          Font font = workbook1.createFont();
          font.setColor(IndexedColors.RED.getIndex());
          style.setFont(font);*/
          
          // Compare sheets
          if(ExcelUtils.compareTwoSheets(sheet1, sheet2)) {
              System.out.println("\n\nThe two excel sheets are Equal");
              Variables.OutputBuffer = Variables.OutputBuffer + "\nThe two excel sheets are Equal.";
          } else {
              System.err.println("\n\nThe two excel sheets are Not Equal");
              Variables.OutputBuffer = Variables.OutputBuffer + "\nThe two excel sheets are NOT Equal.";
          }
          Utils.writeFileCompareOutput(Config.Path_MRCFieldOutput,"OnPage-"+Config.File_CompareOutput,Variables.OutputBuffer);
          //ExcelUtils.colorExcelRow(workbook1);
          
          //close files
          workbook1.close();
          workbook2.close();
          excellFile1.close();
          excellFile2.close();

      } catch (Exception e) 
      {
          e.printStackTrace();
      }
  }  

  private static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2) {
// TODO Auto-generated method stub
	   int firstRow1 = sheet1.getFirstRowNum();
       int lastRow1 = sheet1.getLastRowNum();
       
       
       int firstRow2 = sheet2.getFirstRowNum();
       int lastRow2 = sheet2.getLastRowNum();
              
       boolean equalSheets = true;
       
       if((lastRow1 != lastRow2)) 
       {
    	   equalSheets = false;
    	   System.err.println("\n\nThe the number of records in " + sheet1.getSheetName() +":" + lastRow1);
    	   System.err.println("\n\nThe the number of records in " + sheet2.getSheetName() +":" + lastRow2);
    	   //return equalSheets;    	   
       }
       else
       {
    	   System.out.println("\n\nThe the number of records in " + sheet1.getSheetName() +":" + lastRow1);
    	   System.out.println("\n\nThe the number of records in " + sheet2.getSheetName() +":" + lastRow2);
       }
       
       //System.out.println("Row num: "+lastRow1);
              
       for(int i=firstRow1; i <= lastRow1; i++) {
          
           //System.out.println("\n\nComparing Row "+i);
          
           XSSFRow row1 = sheet1.getRow(i);
           XSSFRow row2 = sheet2.getRow(i);
           if(!compareTwoRows(row1, row2)) {
               equalSheets = false;
               System.err.println("Row "+i+" - Not Equal");
               //break;
           } else {
               //System.out.println("Row "+i+" - Equal");
           }
       }
       return equalSheets;
  }
 
  public static boolean compareTwoSheets_NEW(XSSFSheet sheet1, XSSFSheet sheet2) 
	  {
		  // TODO Auto-generated method stub
		   int firstRow1 = sheet1.getFirstRowNum();
	       int lastRow1 = sheet1.getLastRowNum();
	       	       
	       int firstRow2 = sheet2.getFirstRowNum();
	       int lastRow2 = sheet2.getLastRowNum();
	       int i=0,j=0; int HighestRowCount=0,NextFileRowCount=0;
	       
	       ArrayList<String> mylist = new ArrayList<String>();
	       
	        /*Workbook workbook = new XSSFWorkbook();
		    Sheet sheet = workbook.createSheet("Color Test");
		    Row row = sheet.createRow(0);
		    
	        CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    Font font = workbook.createFont();
	        font.setColor(IndexedColors.RED.getIndex());
	        style.setFont(font);*/
	        //Sheet sheet = sheet1.createSheet("Color Test");
	        //CellStyle style = sheet1 
	              
	       boolean equalSheets = true;	       
	       
	       if((lastRow1 != lastRow2)) 
	       {
	    	   equalSheets = false;
	    	   if (lastRow1 > lastRow2)
	    	   {
	    		   HighestRowCount = lastRow1;
	    	       NextFileRowCount =  lastRow2;
	    	   }
	    	   else
	    	   {
	    		   HighestRowCount = lastRow2;
	    	       NextFileRowCount =  lastRow1;
	    	   }	    	  	    	     	   
	       }
	       else
	       {
	    	   HighestRowCount = lastRow2;
    	       NextFileRowCount = lastRow2;
	       }
	    	   
	       System.out.println("\nThe the number of records in " + sheet1.getSheetName() +":" + lastRow1);
    	   System.out.println("\nThe the number of records in " + sheet2.getSheetName() +":" + lastRow2);	 
    	
       for(i=firstRow1; i <= HighestRowCount; i++) 
   	   {	          
	           //System.out.println("\n\nComparing Row "+i);	          
	           XSSFRow row1 = sheet1.getRow(i);
	           XSSFCell cell1 = row1.getCell(0);	           
	           
	           String curr_File1_FieldNumber = cell1.getStringCellValue();
	           boolean curr_FieldNumber_Found = Utils.foundStringInaList(mylist,cell1.getStringCellValue());
	           String prevErrorLine="";
	           
	           if (!curr_FieldNumber_Found)	           
	        	   mylist.add(cell1.getStringCellValue()); //this adds an element to the list.
	           //System.out.println("String list= "+mylist);
	           for(j=firstRow2; j <= NextFileRowCount; j++) 
	           {
	        	   XSSFRow row2 = sheet2.getRow(j);
	        	   XSSFCell cell2 = row2.getCell(0);
	        	   
	        	   switch (cell1.getCellType()) {
	        	     
	        	   case STRING:		        		   
		        		   //cell1.setCellValue(curr_File1_FieldNumber);
		        		   
		                   if (cell2.getStringCellValue().equals(cell1.getStringCellValue())) 
		                   {	                	   
		                	   
		                	   //System.out.println("File1 Cell_S: "+cell1.getStringCellValue());
		    	        	   //System.out.println("File2 Cell_S: "+cell2.getStringCellValue());		    	        	 
		                	   if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,cell1.getStringCellValue()))
		                	   {
			                	   if(!compareTwoRows(row1, row2)) 
			        	           {
			        	               equalSheets = false;
			        	               //System.err.println("Row "+(i+1)+" - Not Equal\n");
			        	               if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n"))
		                			   {
		                			      System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
		                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
		                			      
		                			      /*cell1.setCellStyle(style);
		                			      FileOutputStream fos =new FileOutputStream(new File("C:\\TEMP\\OutputFile\\color.xlsx"));
		                				  workbook.write(fos);
		                				  fos.close();*/
		                			   }
			        	              // System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
			        	               //Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";	               
			        	               //break;
			        	           } 
			                	   else
			                	   {
			                		   if (Utils.foundStringInaList(mylist,curr_File1_FieldNumber) && curr_FieldNumber_Found)
			                		   {
			                			   equalSheets = false;               			  
			                			   
			                			   if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n"))
			                			   {
			                			      System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n");
			                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n";
			                			      //cell1.setCellStyle(style);
			                			   }
			                		   }
			                	   }
		                	   }
		                   }
		                   break;	           
		           	
	               }
	           }         
	        
	       }
	       return equalSheets;
	  }
  
  private static boolean compareTwoRows(XSSFRow row1, XSSFRow row2) 
  {
		// TODO Auto-generated method stub
		 if((row1 == null) && (row2 == null)) {
		           return true;
		       } else if((row1 == null) || (row2 == null)) {
		           return false;
		       }
      
       int firstCell1 = row1.getFirstCellNum();
       int lastCell1 = row1.getLastCellNum();
       boolean equalRows = true;
      
 	  // System.out.println("In CompareTwoRows-File1 Cell_S: "+row1.getCell(0));
	  // System.out.println("In CompareTwoRows-File2 Cell_S: "+row2.getCell(0));

       // Compare all cells in a row
       for(int i=firstCell1; i <= lastCell1; i++) {
           XSSFCell cell1 = row1.getCell(i);
           XSSFCell cell2 = row2.getCell(i);
           if(!compareTwoCells(cell1, cell2)) {
               equalRows = false;
               System.err.println("       Cell "+i+" - Not Equal");
               //break;
           } else {
               //System.out.println("       Cell "+i+" - Equal");
           }
       }
       return equalRows;
}

  private static boolean compareTwoCells(XSSFCell cell1, XSSFCell cell2) 
  {
	 if((cell1 == null) && (cell2 == null)) 
	 {
	       return true;
	 } 
	 else if((cell1 == null) || (cell2 == null)) 
	 {
	       return false;
	 }
	      
       boolean equalCells = false;
       CellType type1 = cell1.getCellType();
       CellType type2 = cell2.getCellType();
       if (type1 == type2) {
           if (cell1.getCellStyle().equals(cell2.getCellStyle())) {
               // Compare cells based on its type
               switch (cell1.getCellType()) {
              // case XSSFCell.CELL_TYPE_FORMULA:
               case FORMULA:
                   if (cell1.getCellFormula().equals(cell2.getCellFormula())) {
                       equalCells = true;
                   }
                   break;
               //case XSSFCell.CELL_TYPE_NUMERIC:
               case NUMERIC:
                   if (cell1.getNumericCellValue() == cell2
                           .getNumericCellValue()) {
                       equalCells = true;
                   }
                   break;
               //case XSSFCell.CELL_TYPE_STRING:
               case STRING:
                   if (cell1.getStringCellValue().equals(cell2
                           .getStringCellValue())) {
                       equalCells = true;
                   }
                   break;
               //case XSSFCell.CELL_TYPE_BLANK:
               case BLANK:
                   //if (cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
                   if (cell2.getCellType() == cell1.getCellType()) {
                       equalCells = true;
                   }
                   break;
               //case XSSFCell.CELL_TYPE_BOOLEAN:
               case BOOLEAN:
                   if (cell1.getBooleanCellValue() == cell2
                           .getBooleanCellValue()) {
                       equalCells = true;
                   }
                   break;
               //case XSSFCell.CELL_TYPE_ERROR:
               case ERROR:
                   if (cell1.getErrorCellValue() == cell2.getErrorCellValue()) {
                       equalCells = true;
                   }
                   break;
               default:
                   if (cell1.getStringCellValue().equals(
                           cell2.getStringCellValue())) {
                       equalCells = true;
                   }
                   break;
               }
           } else {
               return false;
           }
       } else {
           return false;
       }
       return equalCells;
  }
  public static int getRecordsCountInCSV(String downloadPath, String csvFileName) 
  {
		int lineNumberCount = 0;
		try {
			if (!csvFileName.isEmpty() || csvFileName != null) {
				String filePath =	downloadPath + System.getProperty("file.separator") + csvFileName;
				System.out.println(filePath);
				File file = new File(filePath);
				if (file.exists()) {
					System.out.println("File found :" +csvFileName);
					FileReader fr = new FileReader(file);
					LineNumberReader linenumberreader = new LineNumberReader(fr);
					while (linenumberreader.readLine() != null) {
						lineNumberCount++;
					}
					//To remove the header
					lineNumberCount=lineNumberCount-1;
					System.out.println("Total number of fields in downloaded csv file : " + (lineNumberCount));
					NoOfRecords = lineNumberCount;
					//System.out.println("Total number of fields in downloaded csv file public variable : " + (NoOfRecords));					
					linenumberreader.close();
				} else {
					System.out.println("File does not exists");
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineNumberCount;
  }	
	
  public static void ConvertCSVToEXCEL(String downloadPath, String csvFileName) 
  {
			int lineNumberCount = 0;
			CSVReader reader = null;
		    FileOutputStream fileOutputStream = null;
		    
		    //String input_file = "C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fileds.csv";
		    //String input_file = downloadPath + "\\" + csvFileName;
		    //System.out.println("FileName Passed = "+input_file); 
		    
		    XSSFWorkbook workBook = new XSSFWorkbook();
		    XSSFSheet sheet = workBook.createSheet("sheet1");		    
		       
		    int RowNum=0;
		    int newrow;
		    int LoopCount = 0;
		      
		    System.out.println("Converting the CSV file to .xlsx file..."); 
		  	
		    try
		    {
		    if (!csvFileName.isEmpty() || csvFileName != null) 
		    {
				String filePath =	downloadPath + System.getProperty("file.separator") + csvFileName;
				System.out.println(filePath);
				File file = new File(filePath);
			  
				if (file.exists()) 
				{
					 //xls file name
				    String xlsxFileAddress = null;
				    if(file.length() < 5){
				      System.out.println("Incorrect file name!");
				      System.exit(0);
				    } else {
				      xlsxFileAddress = csvFileName+".xlsx";
				    }
				  
					String[] nextLine;
					reader = new CSVReader(new FileReader(file),',');					
					System.out.println("File found :" +csvFileName);
					FileReader fr = new FileReader(file);
					LineNumberReader linenumberreader = new LineNumberReader(fr);
					while (linenumberreader.readLine() != null) 
					{
						System.out.println("Inside Loop:");
						lineNumberCount++;
					 	nextLine = reader.readNext();
					 	XSSFRow currentRow=sheet.createRow(RowNum++);
					    for(int i=0;i<nextLine.length;i++)
					    {
					    	System.out.println("Inside Loop 2:");
					    	currentRow.createCell(i).setCellValue(nextLine[i]);
					        newrow = i; 
					    }
					     
					}
					 fileOutputStream = new FileOutputStream(xlsxFileAddress);
				      workBook.write(fileOutputStream);
					//To remove the header
					lineNumberCount=lineNumberCount-1;
					System.out.println("Total number of fields in downloaded csv file : " + (lineNumberCount));
					NoOfRecords = lineNumberCount;
					System.out.println("Total number of fields in downloaded csv file public variable : " + (NoOfRecords));					
					linenumberreader.close();
				} else {
					System.out.println("File does not exists");
				}
			}
		    }
		    catch (Exception e1) {
			      //e.printStackTrace();
			      System.out.println("\nFile \"" + csvFileName + "\" does not exist!");
			    }
			    finally 
			    {
			      if (reader != null) 
			      {
			        try 
			        {
			          reader.close();
			          fileOutputStream.close();
			          System.out.println("Done");
			          System.out.println("Inside Loop 3:");
			        } catch (IOException e2) 
			        {
			          //e2.printStackTrace();
			          System.out.println("\nFile \"" + csvFileName + "\" can not be closed correctly!");
			        }
			      }
			    }
			    
			   
			    try {
			      System.out.print("\n...press [Enter] key to exit.");
			      System.in.read();
			      System.out.println("Inside Loop 4:");
			    } catch (IOException e3) {
			      // TODO Auto-generated catch block
			      //e3.printStackTrace();
			      System.out.println("Application is not close correctly!");
			    }    
		    
	
  }
  public static void convertXLXSFileToCSV(File xlsxFile, int sheetIdx) throws Exception 
  {
  	  
		  FileInputStream fileInStream = new FileInputStream(xlsxFile);
	      int written=0;
	      int isExists,wholenumber=2;
	      int intFieldNumber = 1;
	      //int count =0;
	      String str998="";
	      String actual2[]={""};
	      String strheading[]={""};
	      String strFieldName = null;
	      //String strheadingtemp=null;

	      // Open the xlsx and get the requested sheet from the workbook
	      XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
	      XSSFSheet selSheet = workBook.getSheetAt(sheetIdx);

	      // Iterate through all the rows in the selected sheet
	      Iterator<Row> rowIterator = selSheet.iterator();
	      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv")));
	      //StringBuffer sb = new StringBuffer();
	      bwr.write("Field Number,Field Name,Sub-Field Number,Sub-Field Name,Extracted Value");
	      bwr.write("\n");
	      while (rowIterator.hasNext()) 
	      {
	          Row row = rowIterator.next();
	          
	          // Iterate through all the columns in the row and build ","
	          // separated string
	          Iterator<Cell> cellIterator = row.cellIterator();
	          StringBuffer sb = new StringBuffer();
	     	  while (cellIterator.hasNext()) 
	          {
	          	  Cell cell = cellIterator.next();	   
	        	  //count =0;      	  
	              //System.out.println("Cellval= "+cell.getStringCellValue());
	              if (sb.length() != 0) 
	              {
	            	  //strFieldName = cell.getStringCellValue(); 
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
	     	  //if (count == 1)
	     		 //sb.append(",");
	          //System.out.println(sb.toString());
	          String temp = sb.toString();	          
	          //*****To remove the blank space record or the heading record from the copied file
	          str998="";
	          isExists = temp.indexOf(",");
	          //System.out.println("isExists= " + isExists);
	          //System.out.println("Temp= " + temp);
	          
	          if (isExists != -1)
	          {
	        	  String actual=temp.substring(0, temp.indexOf(",")); 
	        	  strheading = temp.split(",");
	           	  //System.out.println("Actual= " +actual);
	         	  if (actual.equals("64.1")||actual.equals("64.2")||actual.equals("64.3")||actual.equals("64.4")||actual.equals("64.5")||actual.equals("64.6"))
	           	  {  
	           		  written++;               		  
	           	  }
	           	  actual2 = actual.split("\\.");
	        	  //System.out.println("Actual Length= "+ actual2.length);
	        	  wholenumber = actual2.length;
	        	  if (actual2.length > 1)
	        	  {
	        		  str998 = actual2[1];
	        		  intFieldNumber = Integer.valueOf(actual2[0]);
	        		  //System.out.println("FullString= " + actual+" Substring1= "+actual2[0]+" Substring2= "+actual2[1]);
	        	  }
	        	  //System.out.println("Wholenumber or not= "+strheading[0].indexOf("."));
	        	  if (strheading[0].indexOf(".")== -1)  //*** checking it is a decimal number or not
	        	     strFieldName = strheading[1];
		              
	          }
	          else if(isExists == -1 )
	             strFieldName = temp;	        	  
	          	                       
	          if (isExists != -1 && !str998.equals("998") && wholenumber > 1 && (written == 0 || written <= 6))
	          {
	        	  bwr.write(intFieldNumber+","+strFieldName+",");
	        	  bwr.write(temp);
	        	  bwr.write("\n");
	          }                   
	          if (written == 12)
	       		  written =0;               			  
	    	 
	        //*****To remove the blank space record or the heading record from the copied file
	        
	     }
	    //bwr.write("Sub-Field Number,Sub-Field Name,Extracted Value");
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
			  if (!file1_line.equals(file2_line))
			  {
				  mismatch = 0;
				  String[] file1_fields = file1_line.split(",");
				  String[] file2_fields = file2_line.split(",");
				  
				  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
					  mismatch = 0;
				  
				  for (i=0;i<file1_fields.length;i++)
				  {
					  //System.out.println("file1_fields= "+file1_fields[i]);
					  if (!file1_fields[i].equals(file2_fields[i]))
						  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
							  mismatch = 0;
					  else		
						  mismatch = 1;	  
				  }
				  
				  if (mismatch == 1)
				  {
					  System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
					  NumberOfDifferences++;					  
				  }			  
			  }
		  } 
		  file1.close(); 
		  file2.close(); 
		  
		  System.out.println("\nNo. Differences found in two files: "+ NumberOfDifferences);
	      if (NumberOfDifferences == 0)
	    	  System.out.println("No Differences found in two files");		   
  }
  
  

  public static void main(String...strings) throws IOException
  {
	  //compareExcel(String filePath,String fileName1,String fileName2)
	  compareExcel("C:\\TEMP\\OutputFiles","ST-Page-Fields.xlsx","UAT-Page-Fields.xlsx");
  }

}
