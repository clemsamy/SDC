package SDCAutomation;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObjects.SignedIn_Page;

import utility.COMPAREEXCEL;
import utility.FluentWaitNew;
import utility.HighLight;
import utility.ReadExcelFile;
import utility.Utils;
import utility.WriteExcelFile;


@SuppressWarnings("unused")
public class SDCSTVsUATFieldCompare 
{
	public static void main(String[] args) throws IOException, InterruptedException
	{                
        //**** COMPARE TWO EXCEL Files(ST & UAT on page fields files)*************** WORKING
        COMPAREEXCEL objExcelFile2 = new COMPAREEXCEL();
        objExcelFile2.compareExcel("C:\\TEMP","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");        
        //objExcelFile2.compareExcel(System.getProperty("user.dir")+"\\src\\testData","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");
        //objExcelFile2.compareExcel("C:\\Clem\\TEMP","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");
        
        System.out.println("\nStep 8: Excel comparision successfully completed");    
        //**** COMPARE TWO EXCEL Files*************** WORKING 
	}	
}
