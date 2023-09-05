package SDCAutomation;

import java.io.IOException;

import utility.COMPAREEXCEL;


public class MainClass 
{
	public static void main(String[] args) throws IOException
	{
		//FieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal obj = new FieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal();
		//FieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal.fnFieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal();     //**Working
		
		//FieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal.getRecordsCountInCSV(downloadPath, csvFileName)
		//String csvFileName = "SDC-ST-Extracted-Page-Fileds.csv";
   		//String downloadPath = "C:\\Clem\\TEMP";
   		//COMPAREEXCEL objCSVFile = new COMPAREEXCEL();
		//objCSVFile.getRecordsCountInCSV(downloadPath,csvFileName);
		//COMPAREEXCEL.getRecordsCountInCSV(downloadPath, csvFileName);    //*** Working
		//COMPAREEXCEL.CopyMoveFile();
		
   		//FieldsOnPageShouldMatchTheFieldsInDownloadedFile.fnFieldsOnPageShouldMatchTheFieldsInDownloadedFile();
   		
		AcordDisplayedOnTestPortalShouldMatchAcordDisplayedOnBaselinedPortal.fnAcordDisplayedOnTestPortalShouldMatchAcordDisplayedOnBaselinedPortal();
	  		
	}	
}
