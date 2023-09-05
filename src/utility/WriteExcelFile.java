package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



//import SDCLogin.HSSFRow;


public class WriteExcelFile {

    public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{

        //Create an object of File class to open xlsx file

        File file =    new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook SDCWorkBook = null;

        //Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

        //If it is xlsx file then create object of XSSFWorkbook class

        	SDCWorkBook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of XSSFWorkbook class

        	SDCWorkBook = new HSSFWorkbook(inputStream);

        }    

    //Read excel sheet by sheet name    

    Sheet sheet = SDCWorkBook.getSheet(sheetName);

    //Get the current count of rows in excel file

    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

    //Get the first row from the sheet

    Row row = sheet.getRow(0);

    //Create a new row and append it at last of sheet

    Row newRow = sheet.createRow(rowCount+1);

    //Create a loop over the cell of newly created Row

    for(int j = 0; j < row.getLastCellNum(); j++){

        //Fill data in row

        Cell cell = newRow.createCell(j);

        cell.setCellValue(dataToWrite[j]);

    }

    //Close input stream

    inputStream.close();

    //Create an object of FileOutputStream class to create write data in excel file

    FileOutputStream outputStream = new FileOutputStream(file);

    //write data in the excel file

    SDCWorkBook.write(outputStream);

    //close output stream

    outputStream.close();
	
    }
      
  //Copying the extracted data in to excel row by row
  //public void writeExcelRow(String filePath,String fileName,String sheetName,List<WebElement> allRows,int noRows) throws IOException
  public void writeExcelRow_old(String filePath,String fileName,String sheetName,List<WebElement> allRows) throws IOException
  {
	   	
	  	File file =    new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook SDCWorkBook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx"))
        {
        	SDCWorkBook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls"))
        {
            SDCWorkBook = new HSSFWorkbook(inputStream);
        }
	  		  	
        Sheet sheet = SDCWorkBook.getSheet(sheetName);
        //int rowCount = sheet.getLastRowNum()- sheet.getFirstRowNum();
        //Row row = sheet.getRow(0);    
        //Row newRow = sheet.createRow(rowCount+1);
        int noRows = allRows.size();
        System.out.println("No. of fields in Transaction view Page displayed: "+allRows.size());
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
	  	inputStream.close();  
	    FileOutputStream outputStream = new FileOutputStream(file);
	    SDCWorkBook.write(outputStream);
	    outputStream.close();
  } 
  public void writeExcelRow(String filePath,String fileName,String sheetName,List<WebElement> allRows) throws IOException
  {
	   	
	  	/*File file =    new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook SDCWorkBook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx"))
        {
        	SDCWorkBook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls"))
        {
            SDCWorkBook = new HSSFWorkbook(inputStream);
        }*/
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
        Log.info("No. of fields in Transaction view Page displayed: "+allRows.size());
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
     
    public static void main(String...strings) throws IOException
    {
       
    }

}
