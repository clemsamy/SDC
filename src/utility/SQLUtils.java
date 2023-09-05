package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {
	
	public static String executeSQLQuery(String query,String Env) throws ClassNotFoundException, SQLException
	{
		String EmailedField = "";
		Connection con;
	  
		Class.forName("net.sourceforge.jtds.jdbc.Driver");   	
   	    if (Env.equalsIgnoreCase("ST"))
   	    	con = DriverManager.getConnection(Config.DBSTConnectionString,Config.DBSTUsername,Config.DBSTPassword);
   	    else
   	    	con = DriverManager.getConnection(Config.DBUATConnectionString,Config.DBUATUsername,Config.DBUATPassword);
	    Statement stmt = con.createStatement();
 		ResultSet rs= stmt.executeQuery(query);
 		
	    while (rs.next())
		    EmailedField = rs.getString(1);
            //System. out.println("EmailedField= "+EmailedField);       
 		
	    // closing DB Connection		
		con.close();
		//System. out.println("I am in SQL=" +EmailedField);
		return EmailedField;
	}
	public static boolean connectUATVPN() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException
	{
		boolean conn_status=false;
		String TransactionID="",query="";
		//Runtime.getRuntime().exec("cmd /c start /B C:\\TEMP\\InputFiles\\VPNBatch");
		//Runtime.getRuntime().exec("cmd /c start /B rasphone SDC-UAT");
		Runtime.getRuntime().exec("cmd /c start /B rasphone "+Config.UATVPNConnectionName);
    	Thread.sleep(2000);
    	Robot robot = new Robot();
    	robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER); 
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		
		query = "Select TransactionID from [SDC_DATA].[SDCR].[Transaction] where RequestID = '"+Variables.RequestID+"'";
		TransactionID = SQLUtils.executeSQLQuery(query,"UAT");		
		//System. out.println("I am in SQL and TranID=" +TransactionID);
		//System. out.println("UAT DB conncted successfully");
		if (TransactionID!="")
			conn_status=true;
		
		return conn_status;
	}
	public static boolean connectUATVPN_UsingBatchFile() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException //***WORKING Fine
	{
		boolean conn_status=false;
		String TransactionID="",query="";
		//Runtime.getRuntime().exec("cmd /c start /B C:\\TEMP\\InputFiles\\VPNBatch");
		//Runtime.getRuntime().exec("cmd /c start /B rasphone SDC-UAT");
		//Runtime.getRuntime().exec("cmd /c start /B rasphone "+Config.UATVPNConnectionName);
		Runtime.getRuntime().exec("cmd /c start /B " +Config.DIR_InputSource+"\\"+Config.DBConnectionBatchFile);
    	Thread.sleep(5000);
    	
		query = "Select TransactionID from [SDC_DATA].[SDCR].[Transaction] where RequestID = '"+Variables.RequestID+"'";
		TransactionID = SQLUtils.executeSQLQuery(query,"UAT");		
		//System. out.println("I am in SQL and TranID=" +TransactionID);
		System. out.println("UAT DB conncted successfully");
		if (TransactionID!="")
			conn_status=true;
		
		return conn_status;
	}
	public static void disconnectUATVPN() throws IOException
	{
		Runtime.getRuntime().exec("cmd /c start /B rasphone -h "+Config.UATVPNConnectionName);    
	}

}
