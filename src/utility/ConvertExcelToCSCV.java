package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConvertExcelToCSCV 
{
	 private static void convertSelectedSheetInXLXSFileToCSV_old(File xlsxFile, int sheetIdx) throws Exception {
		 
	        FileInputStream fileInStream = new FileInputStream(xlsxFile);
	 
	        // Open the xlsx and get the requested sheet from the workbook
	        XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
	        XSSFSheet selSheet = workBook.getSheetAt(sheetIdx);
	 
	        // Iterate through all the rows in the selected sheet
	        Iterator<Row> rowIterator = selSheet.iterator();
	        while (rowIterator.hasNext()) {
	 
	            Row row = rowIterator.next();
	 
	            // Iterate through all the columns in the row and build ","
	            // separated string
	            Iterator<Cell> cellIterator = row.cellIterator();
	            StringBuffer sb = new StringBuffer();
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                if (sb.length() != 0) {
	                    sb.append(",");
	                }
	                 
	                // If you are using poi 4.0 or over, change it to
	                // cell.getCellType
	                switch (cell.getCellType()) {
	                case STRING:
	                	if (cell.getStringCellValue().contains(","))
			            	  sb.append("\""+cell.getStringCellValue()+"\"");
	                    //sb.append(cell.getStringCellValue());
	                    break;
	                case NUMERIC:
	                    sb.append(cell.getNumericCellValue());
	                    break;
	                case BOOLEAN:
	                    sb.append(cell.getBooleanCellValue());
	                    break;
	                default:
	                }
	            }
	            System.out.println(sb.toString());
	        }
	        workBook.close();
	    }

	 public static void convertSelectedSheetInXLXSFileToCSV(File xlsxFile,String NewCSVFile) throws Exception   //** Need to fix the issue, in the extracted value when . is there in the amount, it doesn't display correct
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
		      XSSFSheet selSheet = workBook.getSheetAt(0);

		      // Iterate through all the rows in the selected sheet
		      Iterator<Row> rowIterator = selSheet.iterator();
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv")));
		      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(NewCSVFile)));
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(Constant.Path_OutputFile+"Converted-"+Constant.File_CSVExportSTFields)));
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
		            	  if (cell.getStringCellValue().contains(","))
			            	  sb.append("\""+cell.getStringCellValue()+"\"");
		            	  else
		            		  sb.append(cell.getStringCellValue());
		                  break;
		              case NUMERIC:
		            	  if (cell.getStringCellValue().contains(","))
			            	  sb.append("\""+cell.getNumericCellValue()+"\"");		            	  
		            	  else
		                      sb.append(cell.getNumericCellValue());
		                  break;
		              case BOOLEAN:
		            	  if (cell.getStringCellValue().contains(","))
		            		  sb.append("\""+cell.getBooleanCellValue()+"\"");
		            	  else
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
		          //bwr.write(temp);
	        	  //bwr.write("\n");
		     }
		    //bwr.write("Sub-Field Number,Sub-Field Name,Extracted Value");
		      workBook.close();
			  //flush the stream
		      bwr.flush();
			  //close the stream
			  bwr.close();   
	  }
	 public static void CompareCSVFiles(String filename1,String filename2) throws FileNotFoundException, IOException 
	 { 
			  //List countries = new ArrayList<>(); 
			  int NumberOfDifferences=0,i,j;
			  int mismatch =0,file1count=0,file2count=0,Highestloopcount=0;			  
			  
			  BufferedReader file1tmp = new BufferedReader(new FileReader(filename1));
			  BufferedReader file2tmp = new BufferedReader(new FileReader(filename2));
			 
			  while(file1tmp.readLine()!= null)
			      file1count++;
			  while(file2tmp.readLine()!= null)
			      file2count++;		
			  
			  System.out.println("Number of lines in file1: "+file1count);
			  System.out.println("Number of lines in file2: "+file2count);
			  
			  BufferedReader file1 = new BufferedReader(new FileReader(filename1));
			  BufferedReader file2 = new BufferedReader(new FileReader(filename2));
			
			  if (file1count >= file2count)
				  Highestloopcount = file1count;
			  else
				  Highestloopcount = file2count;
			  
			  String file1_line = file1.readLine(); // Reading header, Ignoring in file 1
			  String file2_line = file2.readLine(); // Reading header, Ignoring in file 2
			  
			  //while ((file1_line = file1.readLine()) != null && !file1_line.isEmpty() && (file2_line = file2.readLine())!= null) 
			  for (j=0;j<Highestloopcount;j++)
			  { 
				  mismatch=0;
				  /*if ((file1_line = file1.readLine())==null && (file2_line = file2.readLine())==null)
					  break;
				  else*/{ 
				  if ((file1_line = file1.readLine())==null)
					  mismatch=1;
				  if ((file2_line = file2.readLine())==null)
					  mismatch=1;
				  }
				  if (mismatch==0)
				  {
					  if (!file1_line.equals(file2_line))
					  {
						  mismatch = 0;
						  String[] file1_fields = file1_line.split(",");
						  String[] file2_fields = file2_line.split(",");
						  
						  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
							  mismatch = 0;
						  //System.out.println("\nFile 1 columns found: "+file1_fields.length);
						  //System.out.println("File 2 columns found: "+file2_fields.length);
						  //System.out.println("\nCurrent Row found: "+"\n"+file1_line+'\n'+file2_line);
						  if (file1_fields.length != file2_fields.length)
							  mismatch=1;
						  else
						  {
							  for (i=0;i<file1_fields.length;i++)
							  {
								  //System.out.println("file1_fields= "+file1_fields[i]);							  
								  if (!file1_fields[i].equals(file2_fields[i]))
									  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
										  mismatch = 0;
								  else		
									  mismatch = 1;	  
							  }
						  }
					  }
				  }  
					  if (mismatch == 1 && (file1_line!=null || file2_line!=null))
					  {
						  System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
						  NumberOfDifferences++;					  
					  }			  
				  
			  } 
			  file1.close(); 
			  file2.close(); 
			  
			  System.out.println("\nNumber of Differences found in two files: "+ (NumberOfDifferences));
		      if (NumberOfDifferences == 0)
		    	  System.out.println("No Differences found in two files");		   
	  }
	 public static void CompareCSVFiles_old(String filename1,String filename2) throws FileNotFoundException, IOException 
	 { 
			  //List countries = new ArrayList<>(); 
			  int NumberOfDifferences=0,i;
			  int mismatch =0;
			  
			  //BufferedReader file1 = new BufferedReader(new FileReader("C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fields.csv"));
			  //BufferedReader file2 = new BufferedReader(new FileReader("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv"));

			  BufferedReader file1 = new BufferedReader(new FileReader(filename1));
			  BufferedReader file2 = new BufferedReader(new FileReader(filename2));
			  
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
			  
			  System.out.println("\nNumber of differences found in two files: "+ NumberOfDifferences);
		      if (NumberOfDifferences == 0)
		    	  System.out.println("No Differences found in two files");		   
	  }   
	 
	 public static void CompareCSVFiles_NEW(String filename1,String filename2) throws FileNotFoundException{
		 
	        ArrayList<String> valuesInSheetOne = new ArrayList<String>();
	        ArrayList<String> valuesInSheetTwo = new ArrayList<String>();
	 
	        //File sheet1 = new File(System.getProperty("user.dir") + "\\src\\sheet-1.csv");
	        //File sheet2 = new File(System.getProperty("user.dir") + "\\src\\sheet-2.csv");
	 
	        File sheet1 = new File(filename1);
	        File sheet2 = new File(filename2);
	 
	        Scanner scannerForSheetOne = new Scanner(sheet1);
	        Scanner scannerForSheetTwo = new Scanner(sheet2);
	 
	        scannerForSheetOne.nextLine();
	        while (scannerForSheetOne.hasNextLine()) {
	            valuesInSheetOne.add(scannerForSheetOne.nextLine().split(",")[1]);
	            //valuesInSheetOne.add(scannerForSheetOne.nextLine().split(",")[1]);
	            //valuesInSheetOne.add(scannerForSheetOne.nextLine().split(",")[2]);
	            //valuesInSheetOne.add(scannerForSheetOne.nextLine().split(",")[3]);
	        }
	 
	        scannerForSheetTwo.nextLine();
	        while (scannerForSheetTwo.hasNextLine()) {
	            valuesInSheetTwo.add(scannerForSheetTwo.nextLine().split(",")[1]);
	            //valuesInSheetTwo.add(scannerForSheetTwo.nextLine().split(",")[1]);
	            //valuesInSheetTwo.add(scannerForSheetTwo.nextLine().split(",")[2]);
	            //valuesInSheetTwo.add(scannerForSheetTwo.nextLine().split(",")[3]);
	        }
	 
	        int iteration = 0;
	 
	        System.out.printf("%-6s%-6s%-6s\n","Sheet-1", "    ", "Sheet-2");
	        System.out.println();
	 
	        for (String value : valuesInSheetOne) {
	            if(valuesInSheetOne.get(iteration).compareTo(valuesInSheetTwo.get(iteration)) != 0) {
	                System.out.printf("%-7s%-6s%-7s\n",valuesInSheetOne.get(iteration), " --> ", valuesInSheetTwo.get(iteration));
	            }
	            iteration++;
	        }
	    }
	 public static void main(String[] args) throws Exception {
	        
		     File myFile = new File("C:\\TEMP\\OutputFiles\\ST-Page-Fields.xlsx");
	        //int sheetIdx = 0; // 0 for first sheet
	 
	        //convertSelectedSheetInXLXSFileToCSV(myFile,"C:\\TEMP\\OutputFiles\\Converted-ST-Page-Fields-CSV.csv");
	        CompareCSVFiles("C:\\TEMP\\OutputFiles\\Exported-ST-Page-Fields-CSV.csv","C:\\TEMP\\OutputFiles\\Converted-ST-Page-Fields-CSV.csv");
	        //CompareCSVFiles_NEW("C:\\TEMP\\OutputFiles\\Exported-ST-Page-Fields-CSV.csv","C:\\TEMP\\OutputFiles\\Converted-ST-Page-Fields-CSV.csv");
	    }
}
