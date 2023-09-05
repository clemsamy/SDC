package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;		
import java.util.Properties;
public class  SQLConnector 
{				
    	public static void  main1(String[] args) throws  ClassNotFoundException, SQLException {													
				//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
                //String dbUrl = "jdbc:mysql://localhost:3036/emp";		  // sdc-sql.sdc-nonprod.net,14333			
                
    			//String dbUrl = "jdbc:mysql://sdc-sql.sdc-nonprod.net:14333//SDC_DATA";
    			//String dbUrl = "jdbc:mysql://10.0.1.20:1433//SDC_DATA";
                //String dbUrl   = "jdbc:sqlserver://sdc-sql.sdc-nonprod.net:14333;databaseName=SDC_DATA;integratedSecurity=true";
                //String dbUrl   = "jdbc:sqlserver://sdc-sql.sdc-nonprod.net:14333;databaseName=SDC_DATA";
    			//String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=true;Domain=AD;Trusted_Connection=yes";    //***** WORKING (AD Windows Authentication)    			
    			//String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=true";	//***** WORKING (Without Windows Auth and connecting with username,password)    		
            	//String dbUrl   = "jdbc:jtds:sqlserver://<ur_server:port>;UseNTLMv2=true;Domain=AD;Trusted_Connection=yes";
            	//String dbUrl   = "jdbc:sql://sdc-sql.sdc-nonprod.net:14333/SDC_DATA";
            	//String dbUrl   = "jdbc:microsoft:sqlserver://sdc-sql.sdc-nonprod.net:14333;DatabaseName=SDC_DATA";                                           
            	//String dbUrl = "jdbc:mysql://localhost:3306//SDC_DATA";
                //String dbUrl = "jdbc:mysql://sdc-sql.sdc-nonprod.net:14333";
    			//String username = "SDC-NONPROD\\clement.arockiasamy";
			
       	    //Class.forName("com.mysql.jdbc.Driver");	
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Class.forName("com.sql.jdbc.Driver");
			//Class.forName("");
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		
    		
    		//****Approach - 1  (Manually connect/disconnect VPN)
		
    	/*		//******ST Database details
    		    String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=true";	//***** WORKING (Without Windows Auth and connecting with username,password)
    			String username = "SdcAutoTest";
				String password = "ut*W8K*Qju";  //ST DB Pwd				
				String query = "SELECT Detail FROM [SDC_DATA].[SDCR].[Artefacts] where ArtefactName = 'ACORD ENDORSEMENT XML' And TransactionID = '20e7714e-e5ad-4e93-9576-511a69729ef3'"; //ST DB TranID
		*/	
				
				//******UAT Database details
    		    String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.uat.sdc-nonprod.net:1433;UseNTLMv2=true";  //***** IN Progress (AD Windows Authentication-No username or password needed)
    			String username = "SdcAutoTest";
    			String password = "Cz$663g%r6";  //UAT DB Pwd				
				String query = "SELECT Detail FROM [SDC_DATA].[SDCR].[Artefacts] where ArtefactName = 'ACORD ENDORSEMENT XML' And TransactionID = 'EA3FDE4C-D419-4F33-8E6D-945EF6313212'"; //ST DB TranID
		  			
				//Load jdbc driver
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				
           		//Create Connection to DB		
            	Connection con = DriverManager.getConnection(dbUrl,username,password);
            	
          
          		//Create Statement Object		
        	   Statement stmt = con.createStatement();				
       
       			// Execute the SQL Query. Store results in ResultSet		
         		ResultSet rs= stmt.executeQuery(query);							
         
         		// While Loop to iterate through all data and print results		
			    while (rs.next())
				{
			        		//String Status = rs.getString(1);								        
                            //String RequestID = rs.getString(2);
                            //String DocumentID = rs.getString(3);
                            String DetailXML = rs.getString(1);
                            //System. out.println("Status= "+Status+"RequestID= "+RequestID+"DocuementID= "+DocumentID);		
                            System. out.println("DetailXML= "+DetailXML);
                }
         		//System. out.println("DetailXML= "+rs.getString(1));
      			 // closing DB Connection		
      			con.close();		
      		
      			
      			
    	/* //****New approach - 2
      			//String host = "azuregateway-22d973fa-86f8-4281-97e9-988808a748e5-b8fb8cb8fa71.vpn.azure.com";
    		    //String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.uat.sdc-nonprod.net:1433;UseNTLMv2=true";	//***** WORKING (Without Windows Auth and connecting with username,password)
      			//String host = "azuregateway-22d973fa-86f8-4281-97e9-988808a748e5-b8fb8cb8fa71.vpn.azure.com";
      			//String host = "sdc-sql.uat.sdc-nonprod.net:1433";
      		/*	
    			String host = "sdc-sql.sdc-nonprod.net:14333";  //****ST host
				String database = "SDC_DATA";
				String user = "SdcAutoTest";
				String password = "ut*W8K*Qju";				
				String query = "SELECT Detail FROM [SDC_DATA].[SDCR].[Artefacts] where ArtefactName = 'ACORD ENDORSEMENT XML' And TransactionID = '20e7714e-e5ad-4e93-9576-511a69729ef3'";  //ST DB tranID
		
	   			
      			String host = "sdc-sql.uat.sdc-nonprod.net:1433";    //****UAT host 
				String database = "SDC_DATA";
				String user = "SdcAutoTest";
				String password = "Cz$663g%r6";				
				String query = "SELECT Detail FROM [SDC_DATA].[SDCR].[Artefacts] where ArtefactName = 'ACORD ENDORSEMENT XML' And TransactionID = 'EA3FDE4C-D419-4F33-8E6D-945EF6313212'";  //UAT DB tranID
	        
				// check that the driver is installed
				try
				{
					//Class.forName("org.mariadb.jdbc");
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
				}
				catch (ClassNotFoundException e)
				{
					throw new ClassNotFoundException("MariaDB JDBC driver NOT detected in library path.", e);
				}
				
				System.out.println("MariaDB JDBC driver detected in library path.");

				Connection connection = null;

				// Initialize connection object
				try
				{
					//String url = String.format("jdbc:mariadb://%s/%s", host, database);	
					String url = String.format("jdbc:jtds:sqlserver://%s/%s", host, database);
					//Connection con = DriverManager.getConnection(dbUrl,username,password);
					System.out.println("url="+url);
					//String dbUrl   = "jdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=true";	//***** WORKING (Without Windows Auth and connecting with username,password)
					
					// Set connection properties.
					Properties properties = new Properties();
					properties.setProperty("user", user);
					properties.setProperty("password", password);
					properties.setProperty("useSSL", "true");
					properties.setProperty("verifyServerCertificate", "true");
					properties.setProperty("requireSSL", "false");
					
					// get connection
					connection = DriverManager.getConnection(url, properties);
					
				}
				catch (SQLException e)
				{
					throw new SQLException("Failed to create connection to database", e);
				}
				if (connection != null) 
				{ 
					System.out.println("Successfully created connection to database.");		
					// Perform some SQL queries over the connection.
					try
					{	
						Statement statement = connection.createStatement();
						//ResultSet results = statement.executeQuery("SELECT * from inventory;");
						ResultSet results = statement.executeQuery(query);
						System.out.println("Successfully executed query."+results.next());
						boolean status = results.next();
						while(results.next())
						//while(true)
						{
							System.out.println("Successfully running inside while loop."+results.next());
		                    String DetailXML = results.getString(1);                    		
		                    System. out.println("DetailXML= "+DetailXML);

						}
					}
					catch (SQLException e)
					{
						throw new SQLException("Encountered an error when executing given sql statement", e);
					}		
				}
				else {
					System.out.println("Failed to create connection to database.");	
				}
				System.out.println("Execution finished.");  
				  			
			*/
	}
    public static void  UATmain(String[] args) throws  ClassNotFoundException, SQLException 
    {	
		// Initialize connection variables.
		//String host = "mydemoserver.mysql.database.azure.com";
		String host = "azuregateway-22d973fa-86f8-4281-97e9-988808a748e5-b8fb8cb8fa71.vpn.azure.com";		
		String database = "SDC_DATA";
		String user = "SdcAutoTest";
		String password = "Cz$663g%r6";
		String query = "SELECT Detail FROM [SDC_DATA].[SDCR].[Artefacts] where ArtefactName = 'ACORD ENDORSEMENT XML' And TransactionID = 'EA4F5832-7E5A-4E5C-81F0-3F6528A9AAA9'"; //UAT DB tranID

		// check that the driver is installed
		try
		{
			Class.forName("org.mariadb.jdbc");
		}
		catch (ClassNotFoundException e)
		{
			throw new ClassNotFoundException("MariaDB JDBC driver NOT detected in library path.", e);
		}
		
		System.out.println("MariaDB JDBC driver detected in library path.");

		Connection connection = null;

		// Initialize connection object
		try
		{
			String url = String.format("jdbc:mariadb://%s/%s", host, database);

			// Set connection properties.
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", password);
			properties.setProperty("useSSL", "true");
			properties.setProperty("verifyServerCertificate", "true");
			properties.setProperty("requireSSL", "false");
			
			// get connection
			connection = DriverManager.getConnection(url, properties);
		}
		catch (SQLException e)
		{
			throw new SQLException("Failed to create connection to database", e);
		}
		if (connection != null) 
		{ 
			System.out.println("Successfully created connection to database.");		
			// Perform some SQL queries over the connection.
			try
			{	
				Statement statement = connection.createStatement();
				//ResultSet results = statement.executeQuery("SELECT * from inventory;");
				ResultSet results = statement.executeQuery(query);
				while (results.next())
				{
					/*String outputString = 
						String.format(
							"Data row = (%s, %s, %s)",
							results.getString(1),
							results.getString(2),
							results.getString(3));
					System.out.println(outputString);*/					
                    String DetailXML = results.getString(1);                    		
                    System. out.println("DetailXML= "+DetailXML);

				}
			}
			catch (SQLException e)
			{
				throw new SQLException("Encountered an error when executing given sql statement", e);
			}		
		}
		else {
			System.out.println("Failed to create connection to database.");	
		}
		System.out.println("Execution finished.");
	}
    public static void  main(String[] args) throws IOException, AWTException, InterruptedException
    {
    	//Runtime.getRuntime().exec("cmd /c start /B C:\\TEMP\\InputFiles\\VPNBatch");
    	Runtime.getRuntime().exec("cmd /c start /B rasphone "+Config.UATVPNConnectionName);
    	//Runtime.getRuntime().exec("cmd /c start /B ipconfig|find/i \"SDC-uat\" && rasphone -h SDC-uat || rasphone SDC-uat");
    	
    	Thread.sleep(300);
    	Robot robot = new Robot();
    	robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER); 
		Thread.sleep(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER); 
				
		//Runtime.getRuntime().exec("cmd /c start /B rasphone -h "+Config.UATVPNConnectionName);  		
    }
}