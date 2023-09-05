package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

    public class ExcelUtils {
                private static XSSFSheet ExcelWSheet;
                private static XSSFWorkbook ExcelWBook;
                private static XSSFCell Cell;
                private static XSSFRow Row;
                private static MissingCellPolicy xRow;
                //public static String OutputBuffer="";
            //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
            public static void setExcelFile(String Path,String SheetName) throws Exception {
                   try {
                       // Open the Excel file
                    FileInputStream ExcelFile = new FileInputStream(Path);
                    // Access the required test data sheet
                    ExcelWBook = new XSSFWorkbook(ExcelFile);
                    ExcelWSheet = ExcelWBook.getSheet(SheetName);
                    Log.info("Excel sheet opened");
                    } catch (Exception e){
                        throw (e);
                    }
            }
            //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
            public static String getCellData(int RowNum, int ColNum) throws Exception{
                   try{
                	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                      String CellData = Cell.getStringCellValue();
                      return CellData;
                      }catch (Exception e){
                        return"";
                      }
            }
            //This method is to write in the Excel cell, Row num and Col num are the parameters
            @SuppressWarnings("static-access")
			public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception    {
                   try{
                	   
                      Row  = ExcelWSheet.getRow(RowNum);
                    //Cell = Row.getCell(ColNum, Row.);
                      Cell = Row.getCell(ColNum, xRow.RETURN_BLANK_AS_NULL);
                    //Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
                    if (Cell == null) {
                        Cell = Row.createCell(ColNum);
                        Cell.setCellValue(Result);
                        } else {
                            Cell.setCellValue(Result);
                        }
          // Constant variables Test Data path and Test Data file name
                          FileOutputStream fileOut = new FileOutputStream(Config.Path_TestData + Config.File_TestData);
                          ExcelWBook.write(fileOut);
                          fileOut.flush();
                        fileOut.close();
                        }catch(Exception e){
                            throw (e);
                    }
                }
            
        	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
        		int i=0,CurrentRow=0;
        		try {
        			int rowCount = ExcelUtils.getRowUsed();
        			//System.out.println("Total Row= "+rowCount);
        			for ( i=1 ; i<=rowCount; i++){
        				if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
        					CurrentRow = i;
        					break;
        				}
        			}
        			//return i;        			
        			//System.out.println("i= "+i+"CurrentRow= "+CurrentRow);
        			return CurrentRow;
        				}catch (Exception e){
        			Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
        			throw(e);
        			}
        		}
        	
        	public static int getRowUsed() throws Exception {
        		try{
        			int RowCount = ExcelWSheet.getLastRowNum();
        			Log.info("Total number of Row used return as < " + RowCount + " >.");		
        			return RowCount;
        		}catch (Exception e){
        			Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : "+e.getMessage());
        			System.out.println(e.getMessage());
        			throw (e);
        		}

        	}
        	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception
        	 {   
        	    String[][] tabArray = null;        	 
        	    try
        	    {        	 
	        	    FileInputStream ExcelFile = new FileInputStream(FilePath);        	 
	        	    // Access the required test data sheet        	 
	        	    ExcelWBook = new XSSFWorkbook(ExcelFile);        	 
	        	    ExcelWSheet = ExcelWBook.getSheet(SheetName);
	        	    int startRow = 1;
	        	    int startCol = 1;        	 
	        	    int ci=0,cj=0;        	 
	        	    //int totalRows = 1;    
	        	    int totalRows = ExcelWSheet.getLastRowNum();
	        	    int totalCols = Config.Total_Columns;	        	    
	        	    tabArray=new String[totalRows][totalCols];        	 
	        	    for (int i=startRow;i<=totalRows;i++, ci++) 
	        	    {
	        	    	   cj=0;	        	    	 
	        	    	   for (int j=startCol;j<=totalCols;j++, cj++)
	        	    	   {	        	    	 
	        	    	    tabArray[ci][cj]=getCellData(i,j);	        	    	 
	        	    	    System.out.println(tabArray[ci][cj]);        	 
	        	    	   }
	        	    }
        	    }
        	 
	        	 catch (FileNotFoundException e)        	 
	        	 {        	 
	        		 System.out.println("Could not read the Excel sheet");        	 
	        		 e.printStackTrace();        	 
	        	 }
	        	 
	        	 catch (IOException e)       	 
	        	 {        	 
	        		 System.out.println("Could not read the Excel sheet");        	 
	        		 e.printStackTrace();        	 
	        	 }	        	 
        	    return(tabArray);        	 
        	 }  
        	 
        	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception
        	 {
	        	    String[][] tabArray = null;        	 
	        	    try
	        	    {        	 
		        	    FileInputStream ExcelFile = new FileInputStream(FilePath);        	 
		        	    // Access the required test data sheet        	 
		        	    ExcelWBook = new XSSFWorkbook(ExcelFile);        	 
		        	    ExcelWSheet = ExcelWBook.getSheet(SheetName);        	 
		        	    int startCol = 1;        	 
		        	    int ci=0,cj=0;        	 
		        	    int totalRows = 1;        	 
		        	    int totalCols = 2;        	 
		        	    tabArray=new String[totalRows][totalCols];        	 
		        	    for (int j=startCol;j<=totalCols;j++, cj++)        	 
		        	    {        	 
			        	    tabArray[ci][cj]=getCellData(iTestCaseRow,j);        	 
			        	    System.out.println(tabArray[ci][cj]);        	 
		        	    }        	 
		        	 }        	 
		        	 catch (FileNotFoundException e)        	 
		        	 {        	 
			        	 System.out.println("Could not read the Excel sheet");        	 
			        	 e.printStackTrace();        	 
		        	 }        	 
		        	 catch (IOException e)        	 
		        	 {        	 
			        	 System.out.println("Could not read the Excel sheet");        	 
			        	 e.printStackTrace();        	 
		        	 }        	 
		        	 return(tabArray);        	 
        	 }
          
         public static boolean compareTwoSheets_WORKING_XL_ROWBYROWCOMPARE(XSSFSheet sheet1, XSSFSheet sheet2) 
       	 {
       		  // TODO Auto-generated method stub
       		   int firstRow1 = sheet1.getFirstRowNum();
       	       int lastRow1 = sheet1.getLastRowNum();
       	       	       
       	       int firstRow2 = sheet2.getFirstRowNum();
       	       int lastRow2 = sheet2.getLastRowNum();
       	       int i=0; int HighestRowCount=0;
       	              
       	       boolean equalSheets = true;	       
       	       
       	       if((lastRow1 != lastRow2)) 
       	       {
       	    	   equalSheets = false;
       	    	   if (lastRow1 >= lastRow2)
       	    		   HighestRowCount = lastRow1;
       	    	   else
       	    		   HighestRowCount = lastRow2;
       	    	   //System.err.println("\nThe the number of records in " + sheet1.getSheetName() +":" + lastRow1);
       	    	   //System.err.println("\nThe the number of records in " + sheet2.getSheetName() +":" + lastRow2);	    	     	   
       	       }
       	       //else
       	       //{
       	    	   //System.out.println("\nThe the number of records in " + sheet1.getSheetName() +":" + lastRow1);
       	    	  // System.out.println("\nThe the number of records in " + sheet2.getSheetName() +":" + lastRow2);
       	      // }
       	      // Variables.OutputBuffer = Variables.OutputBuffer + "\n=========================================";
       	      // Variables.OutputBuffer = Variables.OutputBuffer + "\nST vs UAT MRC Fields Comparision Results";
       	      // Variables.OutputBuffer = Variables.OutputBuffer + "\n=========================================";
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe the number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe the number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
           	  
       	       //System.out.println("Row num: "+lastRow1);
       	              
           	   //for(i=firstRow1; i <= lastRow1; i++) {
           	   for(i=firstRow1; i <= HighestRowCount; i++) 
           	   {	          
       	           //System.out.println("\n\nComparing Row "+i);	          
       	           XSSFRow row1 = sheet1.getRow(i);
       	           XSSFRow row2 = sheet2.getRow(i);
       	           if(!compareTwoRows(row1, row2)) 
       	           {
       	               equalSheets = false;
       	               //System.err.println("Row "+(i+1)+" - Not Equal\n");
       	               Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";	               
       	               //break;
       	           } 
       	       }
       	       return equalSheets;
       	  }
    	 
         public static boolean compareTwoSheets_WORKING_WITHOUTCOLOR_CODE(XSSFSheet sheet1, XSSFSheet sheet2) 
       	 {
       		  // TODO Auto-generated method stub
              int firstRow1 = sheet1.getFirstRowNum();
   	       	  int lastRow1 = sheet1.getLastRowNum();
   	       	       
   	          int firstRow2 = sheet2.getFirstRowNum();
   	          int lastRow2 = sheet2.getLastRowNum();
   	          int i=0,j=0; int HighestRowCount=0,NextFileRowCount=0;
   	              
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
	           Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
         
       	       ArrayList<String> mylist = new ArrayList<String>();
        	
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
    	        	   
    	        	   switch (cell1.getCellType()) 
    	        	   {    	        	     
    	        	   		case STRING:		        		   
    		        	
    	        	   		   if (cell2.getStringCellValue().equals(cell1.getStringCellValue())) 
    		                   {	                	   
    		                	   //System.out.println("File1 Cell_S: "+cell1.getStringCellValue());
    		    	        	   //System.out.println("File2 Cell_S: "+cell2.getStringCellValue());		    	        	 
    		                	   if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,cell1.getStringCellValue()))
    		                	   {
    			                	   if(!compareTwoRows(row1, row2)) 
    			        	           {
    			        	               equalSheets = false;    			  
    			        	               if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n"))
    		                			   {
    		                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
    		                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			   }
    			        	           } 
    			                	   else
    			                	   {
    			                		   if (Utils.foundStringInaList(mylist,curr_File1_FieldNumber) && curr_FieldNumber_Found)
    			                		   {
    			                			   equalSheets = false;
    			                			   if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n"))
    			                			   {
    			                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n");
    			                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
    			                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
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
         public static boolean compareTwoSheets_PreviousVersion10(XSSFSheet sheet1, XSSFSheet sheet2) 
       	 {
       		  // TODO Auto-generated method stub
              int firstRow1 = sheet1.getFirstRowNum();
   	       	  int lastRow1 = sheet1.getLastRowNum();
   	       	       
   	          int firstRow2 = sheet2.getFirstRowNum();
   	          int lastRow2 = sheet2.getLastRowNum();
   	          int i=0,j=0; int HighestRowCount=0,NextFileRowCount=0;
   	          int prevErrorRow=0;
   	              
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
	           Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
         
       	       ArrayList<String> mylist = new ArrayList<String>();
        	
       	       for(i=firstRow1; i <= HighestRowCount; i++) 
       	       {	          
    	           //System.out.println("\n\nComparing Row "+i);	          
    	           XSSFRow row1 = sheet1.getRow(i);
    	           XSSFCell cell1 = row1.getCell(0);
    	           String curr_File1_FieldNumber = cell1.getStringCellValue();
    	           boolean curr_FieldNumber_Found = Utils.foundStringInaList(mylist,curr_File1_FieldNumber);
    	           String prevErrorLine="";
    	           
    	           if (!curr_FieldNumber_Found)	           
    	        	   mylist.add(curr_File1_FieldNumber); //this adds an element to the list.
    	           //System.out.println("String list= "+mylist);
    	           for(j=firstRow2; j <= NextFileRowCount; j++) 
    	           {
    	        	   XSSFRow row2 = sheet2.getRow(j);
    	        	   XSSFCell cell2 = row2.getCell(0);
    	        	   
    	        	   switch (cell1.getCellType()) 
    	        	   {    	        	     
    	        	   		case STRING:		        		   
    		        	
    	        	   		   if (cell2.getStringCellValue().equals(cell1.getStringCellValue())) 
    		                   {	                	   
    		                	   //System.out.println("File1 Cell_S: "+row2.getCell(1).getStringCellValue());
    		    	        	   //System.out.println("File2 Cell_S: "+row2.getCell(1).getStringCellValue());		    	        	 
    		                	   if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,cell1.getStringCellValue()))
    		                	   {
    			                	   if(!compareTwoRows(row1, row2)) 
    			        	           {
    			        	               equalSheets = false;    			  
    			        	               //if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n"))
    			        	               if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Not Equal\n"))
    		                			   {
    		                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
    		                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			      prevErrorRow = i+1;
    		                			      //Variables.notequalcolorlist.add(row1.getCell(0).toString());
    		                			      Variables.notequalcolorlist.add(String.valueOf((i+1)));
    		                			   }
    			        	           } 
    			                	   else
    			                	   {
    			                		   if (Utils.foundStringInaList(mylist,curr_File1_FieldNumber) && curr_FieldNumber_Found)
    			                		   {
    			                			   equalSheets = false;
    			                			   //if (!prevErrorLine.equals("\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n"))
    			                			   //System.out.println("\nPrev line="+prevErrorLine);
    			                			   //System.out.println("\\nCurr line="+"\nField Number= "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Duplicate Record\n");
    			                			   if (!prevErrorLine.equals("\nField Number= "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Duplicate Record\n"))
    			                			   {
    			                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n");
    			                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
    			                			      prevErrorLine = "\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
    			                			      prevErrorRow = i+1;
    			                			      //Variables.duplicatecolorlist.add(row1.getCell(0).toString());
    			                			      Variables.duplicatecolorlist.add(String.valueOf((i+1)));
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
         public static boolean compareTwoSheets_PreviousVersion20(XSSFSheet sheet1, XSSFSheet sheet2) 
       	 {
       		  // TODO Auto-generated method stub
              int firstRow1 = sheet1.getFirstRowNum();
   	       	  int lastRow1 = sheet1.getLastRowNum();
   	       	       
   	          int firstRow2 = sheet2.getFirstRowNum();
   	          int lastRow2 = sheet2.getLastRowNum();
   	          int i=0,j=0; int HighestRowCount=0,NextFileRowCount=0;
   	          int prevErrorRow=0;
   	              
   	          boolean equalSheets = true;	       
   	    
	   	       if((lastRow1 != lastRow2)) 
	   	       {
	   	    	   equalSheets = false;
	   	    	   if (lastRow1 > lastRow2)
	   	    	   {
	   	    		   HighestRowCount = lastRow1;
	   	    	       NextFileRowCount =  lastRow2;
	   	    	       Variables.strWorkbook = "ST";
	   	    	   }
	   	    	   else
	   	    	   {
	   	    		   HighestRowCount = lastRow2;
	   	    	       NextFileRowCount =  lastRow1;
	   	    	       Variables.strWorkbook = "UAT";
	   	    	   }   	    	  	    	     	   
	   	       }
	   	       else
	   	       {
	   	    	   HighestRowCount = lastRow2;
	       	       NextFileRowCount = lastRow2;
	       	       Variables.strWorkbook = "ST";
	   	       }
	           Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
         
       	       ArrayList<String> mylist = new ArrayList<String>();
        	
       	       for(i=firstRow1; i <= HighestRowCount; i++) 
       	       {	          
    	           //System.out.println("\n\nComparing Row "+i);	          
    	           XSSFRow row1 = sheet1.getRow(i);
    	           XSSFCell cell1 = row1.getCell(0);
    	           String curr_File1_FieldNumber = cell1.getStringCellValue();
    	           boolean curr_FieldNumber_Found = Utils.foundStringInaList(mylist,curr_File1_FieldNumber);
    	           String prevErrorLine="";
    	           
    	           if (!curr_FieldNumber_Found)	           
    	        	   mylist.add(curr_File1_FieldNumber); //this adds an element to the list.
    	           //System.out.println("String list= "+mylist);
    	           for(j=firstRow2; j <= NextFileRowCount; j++) 
    	           {
    	        	   XSSFRow row2 = sheet2.getRow(j);
    	        	   XSSFCell cell2 = row2.getCell(0);
    	        	   
    	        	   switch (cell1.getCellType()) 
    	        	   {    	        	     
    	        	   		case STRING:		        		   
    		        	
    	        	   		   if (cell2.getStringCellValue().equals(cell1.getStringCellValue())) 
    		                   {	                	   
    		                	   //System.out.println("File1 Cell_S: "+row2.getCell(1).getStringCellValue());
    		    	        	   //System.out.println("File2 Cell_S: "+row2.getCell(1).getStringCellValue());		    	        	 
    		                	   if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,cell1.getStringCellValue()))
    		                	   {
    			                	   if(!compareTwoRows(row1, row2)) 
    			        	           {
    			        	               equalSheets = false;    			  
    			        	               //if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n"))
    			        	               if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Not Equal\n"))
    		                			   {
    		                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
    		                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			      prevErrorRow = i+1;
    		                			      //Variables.notequalcolorlist.add(row1.getCell(0).toString());
    		                			      Variables.notequalcolorlist.add(String.valueOf((i+1)));
    		                			   }
    			        	           } 
    			                	   else
    			                	   {
    			                		   if (Utils.foundStringInaList(mylist,curr_File1_FieldNumber) && curr_FieldNumber_Found)
    			                		   {
    			                			   equalSheets = false;
    			                			   //if (!prevErrorLine.equals("\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n"))
    			                			   //System.out.println("\nPrev line="+prevErrorLine);
    			                			   //System.out.println("\\nCurr line="+"\nField Number= "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Duplicate Record\n");
    			                			   if (!prevErrorLine.equals("\nField Number= "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Duplicate Record\n"))
    			                			   {
    			                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Records\n");
    			                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
    			                			      prevErrorLine = "\nField Number= "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Duplicate Record\n";
    			                			      prevErrorRow = i+1;
    			                			      //Variables.duplicatecolorlist.add(row1.getCell(0).toString());
    			                			      Variables.duplicatecolorlist.add(String.valueOf((i+1)));
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
         public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2) //**WORKING - Finding right duplicates and file swap based on records
       	 {
       		  // TODO Auto-generated method stub
        	  XSSFSheet FirstSheet = null,SecondSheet=null;
        	  int firstRow2 = sheet2.getFirstRowNum();
    	      int lastRow2 = sheet2.getLastRowNum();
    	       
        	  int firstRow1 = sheet1.getFirstRowNum();
   	       	  int lastRow1 = sheet1.getLastRowNum();
   	       	       
   	       	  //System.out.println("file1 Rows="+lastRow1);
   	          //System.out.println("file2 Rows="+lastRow2);
   	          int i=0,j=0; int HighestRowCount=0,NextFileRowCount=0;
   	          //int prevErrorRow=0;
   	          int CurrFile2RowStart=0;
   	              
   	          boolean equalSheets = true;	       
   	    
	   	       if((lastRow1 != lastRow2)) 
	   	       {
	   	    	   equalSheets = false;
	   	    	   if (lastRow1 > lastRow2)
	   	    	   {
	   	    		   HighestRowCount = lastRow1;
	   	    	       NextFileRowCount =  lastRow2;
	   	    	       Variables.strWorkbook = "ST";
	   	    	       FirstSheet = sheet1;
	   	    	       SecondSheet = sheet2;
	   	    	   }
	   	    	   else
	   	    	   { 	   	    		   
	   	    		   HighestRowCount = lastRow2;
	   	    	       NextFileRowCount =  lastRow1;
	   	    	       Variables.strWorkbook = "UAT";
	   	    	       FirstSheet = sheet2;
	   	    	       SecondSheet = sheet1;
	   	    	   }   	    	  	    	     	   
	   	       }
	   	       else
	   	       {
	   	    	   //equalSheets = false;
	   	    	   HighestRowCount = lastRow2;
	       	       NextFileRowCount = lastRow2;
	       	       Variables.strWorkbook = "ST";
	       	       FirstSheet = sheet1;
	    	       SecondSheet = sheet2;
	   	       }
	   	       /*System.out.println("Highest rowcount= "+HighestRowCount);
	   	       System.out.println("NextFile rowcount= "+NextFileRowCount);
	   	       System.out.println("Filename= "+Variables.strWorkbook);*/
	   	       
	           Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
       	       Variables.OutputBuffer = Variables.OutputBuffer+"\nThe number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
         
       	       ArrayList<String> mylist = new ArrayList<String>();
        	   int SecondFilerowStartPos;
       	       for(i=firstRow1; i < HighestRowCount; i++) 
       	       {	          
    	           //System.out.println("\n\nComparing Row "+i);	          
    	           //XSSFRow row1 = sheet1.getRow(i);
    	           XSSFRow row1 = FirstSheet.getRow(i);    	           
    	           XSSFCell cell1 = row1.getCell(0);
    	           String curr_File1_FieldNumber = cell1.getStringCellValue();
    	           
    	           boolean curr_FieldNumber_Found = Utils.foundStringInaList(mylist,curr_File1_FieldNumber);
    	           if (!curr_FieldNumber_Found)	           
    	        	   mylist.add(curr_File1_FieldNumber); //this adds an element to the list if the field was not in the list.
    
    	           //String prevErrorLine="";
    	           
    	           if(CurrFile2RowStart==0)
    	        	   SecondFilerowStartPos=firstRow2;
    	           else 
    	        	   SecondFilerowStartPos=CurrFile2RowStart;
    	           //System.out.println("String list= "+mylist);
    	           //System.out.println("\nstart row="+CurrFile2RowStart);
    	           if (CurrFile2RowStart>NextFileRowCount)
    	        	   SecondFilerowStartPos = NextFileRowCount;
    	           for(j=SecondFilerowStartPos; j <= NextFileRowCount; j++) 
    	           {
    	        	   //XSSFRow row2 = sheet2.getRow(j);
    	        	   XSSFRow row2 = SecondSheet.getRow(j);
    	        	   XSSFCell cell2 = row2.getCell(0);
    	        	   //System.out.println("i="+(i)+"j="+(j));
    	        	   switch (cell1.getCellType()) 
    	        	   {    	        	     
    	        	   		case STRING:
    	        	   		   if (cell1.getStringCellValue().equals(cell2.getStringCellValue())) 
    		                   {	                	   
    	        	   			   CurrFile2RowStart = j+1;
    		                	   if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,cell1.getStringCellValue())) //**checking field ignorance
    		                	   {
    			                	   if(!compareTwoRows(row1, row2)) 
    			        	           {    			                		   
    			        	               equalSheets = false;
    		                			   Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n";
    		                			   Variables.notequalcolorlist.add(String.valueOf((i+1)));
    		                	       } 
    			             	   }//Second if close
    		                   }//first if close
    	        	   		   else
    	        	   		   {
    	        	   		  	  equalSheets = false;
	                		      if(Variables.strWorkbook.equals("ST"))
	                		    	  Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Missing record in UAT page\n";
	                		      else
	                		    	  Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Missing record in ST page\n";
	                			  Variables.duplicatecolorlist.add(String.valueOf((i+1)));
	                			    /*if (!prevErrorLine.equals("\nField Number: "+row1.getCell(0)+ " (Row "+prevErrorRow+")"+" - Missing in other file\n"))
	                		   		{
	                				  equalSheets = false; 
	                			      //System.out.println("\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Not Equal\n");
	                			      Variables.OutputBuffer = Variables.OutputBuffer+"\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Missing in other file\n";
	                			      prevErrorLine = "\nField Number: "+row1.getCell(0)+ " (Row "+(i+1)+")"+" - Missing in other file\n";
	                			      prevErrorRow = i+1;
	                			      //Variables.notequalcolorlist.add(row1.getCell(0).toString());
	                			      Variables.notequalcolorlist.add(String.valueOf((i+1)));
	                			   }*/
    	        	   		   }
    		                   break;  //switch case break.
    	        	   }//Switch close
    	        	   if (CurrFile2RowStart!=0)
    	        		   break;
    	           }//j loop close
       	       	}//i loop close
    		    return equalSheets;
       	 }
         public static boolean compareTwoRows(XSSFRow row1, XSSFRow row2) 
       	  {
       			// TODO Auto-generated method stub
       		  	 int i=0;
       			 if((row1 == null) && (row2 == null)) {
       			           return true;
       			       } else if((row1 == null) || (row2 == null)) {
       			           return false;
       			       }       	      
       	       
       		   int firstCell1 = row1.getFirstCellNum();
       	       int lastCell1 = row1.getLastCellNum();
       	       boolean equalRows = true;
       	      
       	       // Compare all cells in a row
       	       for(i=firstCell1; i <= lastCell1; i++) {
       	           XSSFCell cell1 = row1.getCell(i);
       	           XSSFCell cell2 = row2.getCell(i);
       	           if(!compareTwoCells(cell1, cell2)) {
       	               equalRows = false;
       	               //System.err.println("       Cell "+i+" - Not Equal");
       	               
       	               Variables.OutputBuffer = Variables.OutputBuffer+"\n       Cell "+i+" - Not Equal";
       	               /*CellStyle colorStyle = workbook.createCellStyle();
       	               colorStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
       	               colorStyle.setFillForegroundColor(XSSFColor.RED.index);*/
       	               //break;
       	           }
       	       }
       	       return equalRows;
       	   }

         public static boolean compareTwoCells(XSSFCell cell1, XSSFCell cell2) 
       	  {
       		 if((cell1 == null) && (cell2 == null)) 
       		 {
       		     //System.out.println("I am in both null");
       			 return true;
       		 } 
       		 else if((cell1 == null) || (cell2 == null)) 
       		 {
       			 //System.out.println("I am in cell1 or cell2 null");  
       			 return false;
       		 }
       		      
       	       boolean equalCells = false;
       	       CellType type1 = cell1.getCellType();
       	       CellType type2 = cell2.getCellType();
       	              
       	       if (type1 == type2) 
       	       {
       	          switch (cell1.getCellType()) {
       	              // case XSSFCell.CELL_TYPE_FORMULA:
     	               case STRING:
     	           	       //System.out.println("String1_1 len= " +cell1.getStringCellValue().length()+" String1_1 val= " +cell1.getStringCellValue());
	                	   //System.out.println("String2_2 len= " +cell2.getStringCellValue().length()+" String2_2 val= " +cell2.getStringCellValue());       	             
	               
       	                   if (cell1.getStringCellValue().equals(cell2
       	                           .getStringCellValue())) {
       	                       equalCells = true;
       	                       //System.out.println("cell-1 val="+cell1.getStringCellValue());
       	                       //System.out.println("cell-2 val="+cell2.getStringCellValue());
       	                   }
       	                   else
       	                   {
       	                	  // System.out.println("String1_1 len= " +cell1.getStringCellValue().length()+" String1_1 val= " +cell1.getStringCellValue());
       	                	  // System.out.println("String2_2 len= " +cell2.getStringCellValue().length()+" String2_2 val= " +cell2.getStringCellValue());       	             
       	                   }
       	                   break;    
     	               case BLANK:
       	                   //if (cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
       	                   if (cell2.getCellType() == cell1.getCellType()) {
       	                       equalCells = true;
       	                   }
       	                   break;
       	               case FORMULA:
       	                   if (cell1.getCellFormula().equals(cell2.getCellFormula())) {
       	                       equalCells = true;
       	                   }
       	                   break;
       	               //case XSSFCell.CELL_TYPE_NUMERIC:
       	               case NUMERIC:
       	            	 /*if (cell1.getNumericCellValue()==33 || cell1.getNumericCellValue()==33.1 || cell1.getNumericCellValue()==34)
     	            	   {
     	            		   System.out.println("\nString-1 len= " +cell1.getStringCellValue().length()+" String1 val= " +cell1.getStringCellValue());
     	            		   System.out.println("\nString-2 len= " +cell1.getStringCellValue().length()+" String2 val= " +cell1.getStringCellValue());
     	            	   }*/
     	                  
       	                   if (cell1.getNumericCellValue() == cell2
       	                           .getNumericCellValue()) {
       	                       equalCells = true;
       	                   }
       	                   break;
       	               //case XSSFCell.CELL_TYPE_STRING:
                    //case XSSFCell.CELL_TYPE_BLANK:
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
       	            	  /*if (cell1.getStringCellValue().equals("33") || cell1.getStringCellValue().equals("33.1") || cell1.getStringCellValue().equals("34"))
      	            	   {
      	            		   System.out.println("\nString1 len= " +cell1.getStringCellValue().length()+" String1 val= " +cell1.getStringCellValue());
      	            		   System.out.println("\nString2 len= " +cell1.getStringCellValue().length()+" String2 val= " +cell1.getStringCellValue());
      	            	   }*/
      	                   
       	                   if (cell1.getStringCellValue().equals(
       	                           cell2.getStringCellValue())) {
       	                       equalCells = true;
       	                   }
       	                   break;
       	               }
       	       } 
       	       else 
       	         return false;
       	       
       return equalCells;
  }
  public static void colorExcelRow(XSSFWorkbook workbook,String fileName) throws IOException
  {
        	   int i,j;
        	   XSSFCellStyle my_style =  workbook.createCellStyle();
        	   XSSFFont my_font=workbook.createFont();
        	   
        	   
        	   //my_font.setBoldweight(XSSFFont.);               
               my_font.setColor(XSSFFont.COLOR_RED);               
               my_style.setFont(my_font);
               
               //System.out.println("\nNot equal rows: "+Variables.notequalcolorlist.size());
               //System.out.println("\nDuplicate rows: "+Variables.duplicatecolorlist.size());
               //for (i=0;i<Variables.notequalcolorlist.size();i++)
               //{
                   //XSSFRow row = workbook.getSheetAt(0).getRow(i);
            	 for (String string : Variables.notequalcolorlist) 
                 {                  
            		 	
                   XSSFRow row = workbook.getSheetAt(0).getRow((Integer.parseInt(string))-1);
	               for(j=0;j<row.getLastCellNum();j++)
	               {
	            	   XSSFCell cell = row.getCell(j);
	            	   cell.setCellStyle(my_style);
	               }
                 }
            	 for (String string : Variables.duplicatecolorlist) 
                 {                  
            		 	
                   XSSFRow row = workbook.getSheetAt(0).getRow((Integer.parseInt(string))-1);
	               for(j=0;j<row.getLastCellNum();j++)
	               {
	            	   XSSFCell cell = row.getCell(j);
	            	   cell.setCellStyle(my_style);
	               }
                 }
		           /*CellStyle cellStyle = workbook.createCellStyle();
	           Font font = workbook.createFont();
	           font.setColor(IndexedColors.RED.index);
	           cellStyle.setFont(font);
	           //cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.index);
	           //cellStyle.setFillForegroundColor(IndexedColors.RED.index);	           
	           //cellStyle.setFillPattern(FillPatternType.THIN_VERT_BANDS);
	           //cellStyle.setFillPattern(FillPatternType.THIN_VERT_BANDS);
	           XSSFRow row = workbook.getSheetAt(0).getRow(10);
	           row.setRowStyle(cellStyle);	           
	           XSSFCell cell = row.getCell(0);
	           cell.setCellStyle(cellStyle);
	           cell = row.getCell(1);
	           cell.setCellStyle(cellStyle);
	           cell = row.getCell(2);
	           cell.setCellStyle(cellStyle);*/

	            //FileOutputStream fileOut = new FileOutputStream(Config.Path_MRCFieldOutput+Config.File_OutputSTFields);
	            FileOutputStream fileOut = new FileOutputStream(Config.Path_MRCFieldOutput+fileName);	            
	            workbook.write(fileOut);
	            fileOut.close();        	  
     }
  public static void writeExcelRow(String filePath,String fileName,String sheetName,List<WebElement> allRows) throws IOException
  {
	   	
	 	File file =    new File(filePath+"\\"+fileName);
	  	Workbook SDCWorkBook = new XSSFWorkbook();
	  	FileOutputStream outputStream = new FileOutputStream(file);
	  	//SDCWorkBook.write(outputStream);
	  	//outputStream.close();	         
        
        Sheet sheet = SDCWorkBook.createSheet(sheetName);
        //int rowCount = sheet.getLastRowNum()- sheet.getFirstRowNum();
        //Row row = sheet.getRow(0);    
        //Row newRow = sheet.createRow(rowCount+1);
        int noRows = allRows.size();
        //Log.info("No. of fields in Transaction view Page displayed: "+allRows.size());
        //System.out.println("No. of fields in Transaction view Page displayed: "+allRows.size());
        //System.out.println("NoROWS Inside: "+noRows);
        
	  	for (int i=0; i<noRows; i++)
	    {
	        WebElement webRow = allRows.get(i);
	        //Get all cell values in each row
	        List<WebElement> allCells = webRow.findElements(By.tagName("td"));
	        //System.out.println("AllCells Inside: "+allCells.size());

	        if(allCells.size() > 1)
	        {
	            //HSSFRow excelRow = sheet.createRow(i);
	            Row excelRow = sheet.createRow(i);          
	            for (int j=0; j<allCells.size(); j++) 
	            {
	                WebElement webCell = allCells.get(j);
	                String text = webCell.getText();
	                //if(text.length()>2)
	                if(text.length()!=0)
	                {
	                    Cell excelCell = excelRow.createCell(j);
	                    excelCell.setCellValue(webCell.getText());
	                    //excelCell.s
	                }                   
	            }
	        }
	    }
	  	//outputStream.close();  
	    //FileOutputStream outputStream = new FileOutputStream(file);
	    SDCWorkBook.write(outputStream);
	    outputStream.close();
  }
          
  }