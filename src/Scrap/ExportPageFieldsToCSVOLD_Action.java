package Scrap;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.*;


public class ExportPageFieldsToCSVOLD_Action extends BaseClass
{
	public ExportPageFieldsToCSVOLD_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		//********* Right click and save as clicked working************
		//WebElement element = driver.findElement(By.cssSelector("body > div.container.body-content > div > table > tbody > tr > td:nth-child(2) > a"));
		WebElement element = TransactionDetails_Page.btn_ExportFieldToCSV();
		Actions action = new Actions(driver);
		//File myFile = new File("Employee-Data.xlsx");
		//File myXLSXFile;
		//String NewCSVConvertedFile="";
		 
		action.contextClick(element).build().perform();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER); 
		
		Thread.sleep(5000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
		//System.out.println("Copied text file name= "+x);
		//System.out.println("Copied text file name length= "+x.length());
		//x.substring(arg0)
		
		//********* Right click and save as clicked working************
				
		//****Enter File Name and save 
		//StringSelection ss = new StringSelection("C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fields.csv");
		
		if (Env.equalsIgnoreCase("ST"))
		{
			StringSelection ss = new StringSelection(Config.Path_MRCFieldOutput+"Exported-"+Config.File_CSVExportSTFields);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		}
		else
		{
			StringSelection ss = new StringSelection(Config.Path_MRCFieldOutput+"Exported-"+Config.File_CSVExportUATFields);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		}
			
		//StringSelection ss = new StringSelection(Constant.Path_OutputFile+"Exported-"+Constant.File_CSVExportSTFields);
		//Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		//System.out.println("Path Copied");
		//native key strokes for CTRL, V and ENTER keys
		//Robot robot1 = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
						
		//System.out.println("Path pasted");
					
		//System.out.println("Step 8: Transaction Page Fields exported to CSV successfully");
		Log.info("Step 6: Transaction Page Fields exported to CSV successfully");	
		Reporter.log("Step 6: Transaction Page Fields exported to CSV successfully");	
		Thread.sleep(2000);			
	}	
}
