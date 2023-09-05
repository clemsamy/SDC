package utility;
 
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.Reporter;

 
//public class SendMailSSLWithAttachment {
public class Email {
 
	//public static void sendEmailWithAttachment() {
	public static void main(String[] args) {
 
		// Create object of Property file
		Properties props = new Properties();
 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
	    //props.put("mail.smtp.host", "smtp-mail.outlook.com");
		
		props.put("mail.smtp.starttls.enable", "true");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		//props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", "587");
 
		// This will handle the complete authentication
		//Session session = Session.getDefaultInstance(props,
		Session session = Session.getInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 					
					//return new PasswordAuthentication("SDC_Automation@outlook.com", "SDCLogin123$");
					return new PasswordAuthentication(Config.Email_Sender, Config.Email_Password);
 
					}
 
				});
				//session.setDebug(true);
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session); 
			// Set the from address
			message.setFrom(new InternetAddress(Config.Email_Sender));			
			//message.setFrom(new InternetAddress("SDC_Automation@outlook.com")); 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(Config.Email_Recipients));   ///Receiver address
            // Add the subject link
			message.setSubject(Config.Email_Subject);
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart(); 
			// Set the body of email
			messageBodyPart1.setText("This is message body");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 
			// Mention the file which you want to send
			String filename = Config.ZipFileName ; 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename); 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source)); 
			// set the file
			filename = Config.File_EmailReport; 
			messageBodyPart2.setFileName(filename);

			// Create another object to add another content
			MimeBodyPart messageBodyPart3 = new MimeBodyPart(); 
			// Mention the file which you want to send
			String filename2 = Config.Path_EmailReport2 ; 
			// Create data source and pass the filename
			DataSource source2 = new FileDataSource(filename2); 
			// set the handler
			messageBodyPart3.setDataHandler(new DataHandler(source2)); 
			filename2 = Config.File_EmailReport2; 
			messageBodyPart3.setFileName(filename2);
						
			 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart(); 
			// add body part 1
			multipart.addBodyPart(messageBodyPart3); 
			// add body part 2
			multipart.addBodyPart(messageBodyPart2); 
			// add body part 3
			multipart.addBodyPart(messageBodyPart1); 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("\n=====Test Report Email Sent Successfully=====");
			Log.info("\n=====Test Report Email Sent Successfully=====");
			Reporter.log("\n=====Test Report Email Sent Successfully=====");
 
		} catch (MessagingException e) { 
			throw new RuntimeException(e); 
		}
 
	}
 
}