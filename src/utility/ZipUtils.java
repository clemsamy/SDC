package utility;

//Import all needed packages
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.io.Zip;

public class ZipUtils {

  /*private List <String> fileList;
  private static final String OUTPUT_ZIP_FILE = "C:\\TEMP\\Folder.zip";
  private static final String SOURCE_FOLDER = "C:\\TEMP\\OutputFiles"; // SourceFolder path

  public ZipUtils() {
      fileList = new ArrayList < String > ();
  }

  public static void main(String[] args) {
      ZipUtils appZip = new ZipUtils();
      appZip.generateFileList(new File(SOURCE_FOLDER));
      appZip.zipIt(OUTPUT_ZIP_FILE);
  }

  public void zipIt(String zipFile) {
      byte[] buffer = new byte[1024];
      String source = new File(SOURCE_FOLDER).getName();
      FileOutputStream fos = null;
      ZipOutputStream zos = null;
      try {
          fos = new FileOutputStream(zipFile);
          zos = new ZipOutputStream(fos);

          System.out.println("Output to Zip : " + zipFile);
          FileInputStream in = null;

          for (String file: this.fileList) {
              System.out.println("File Added : " + file);
              ZipEntry ze = new ZipEntry(source + File.separator + file);
              zos.putNextEntry(ze);
              try {
                  in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
                  int len;
                  while ((len = in .read(buffer)) > 0) {
                      zos.write(buffer, 0, len);
                  }
              } finally {
                  in.close();
              }
          }

          zos.closeEntry();
          System.out.println("Folder successfully compressed");

      } catch (IOException ex) {
          ex.printStackTrace();
      } finally {
          try {
              zos.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

  public void generateFileList(File node) {
      // add file only
      if (node.isFile()) {
          fileList.add(generateZipEntry(node.toString()));
      }

      if (node.isDirectory()) {
          String[] subNote = node.list();
          for (String filename: subNote) {
              generateFileList(new File(node, filename));
          }
      }
  }

  private String generateZipEntry(String file) {
      return file.substring(SOURCE_FOLDER.length() + 1, file.length());
  }  */
 
  
	//Method Two
	
	/*public static void main(String[] args) throws IOException 
	{
      String sourceFile = "C:\\TEMP\\OutputFiles";
      FileOutputStream fos = new FileOutputStream("C:\\TEMP\\dirCompressedNEW.zip");
      ZipOutputStream zipOut = new ZipOutputStream(fos);
      File fileToZip = new File(sourceFile);
      System.out.println("Filename= "+fileToZip.getName());
      zipFile(fileToZip, fileToZip.getName(), zipOut);
      
      zipOut.close();
      fos.close();
  }

  private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
      if (fileToZip.isHidden()) {
          return;
      }
      if (fileToZip.isDirectory()) {
          if (fileName.endsWith("/")) {
              zipOut.putNextEntry(new ZipEntry(fileName));
              zipOut.closeEntry();
          } else {
              zipOut.putNextEntry(new ZipEntry(fileName + "/"));
              zipOut.closeEntry();
          }
          File[] children = fileToZip.listFiles();
          for (File childFile : children) {
              zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
              System.out.println("Filename 1= "+fileName + "/" + childFile.getName());
          }
          return;
      }
      FileInputStream fis = new FileInputStream(fileToZip);
      ZipEntry zipEntry = new ZipEntry(fileName);
      zipOut.putNextEntry(zipEntry);
      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
          zipOut.write(bytes, 0, length);
      }
      fis.close();
  }*/
  
  /*public static void createZipFile(String FolderName)
	 {
	        //Zip zipfile = new Zip();
	        try 
	        {
	            Zip.zip(new File(FolderName));
	            System.out.println("Zip file created");
	            
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	 }
  public static void main(String[] args) throws IOException {
	  createZipFile("C:\\TEMP\\OutputFiles");
  }*/
	public static void main(String[] args) throws IOException 
	{
		//Utils.createZipFile(Config.Path_ZipFilesFolder, Config.ZipFileName);
		//createZipFile("C:\\TEMP\\OutputFiles","C:\\TEMP\\dirCompressedVeryNEW.zip");	
		//Utils.createZipFile(Config.Path_ZipFilesFolder, Config.Path_ZipFilesFolder+"\\\\"+Config.File_ZipFileName);		
		CreateADirectory("C:\\TEMP\\OutputFiles NEWW");
	}
	public static void createZipFile(String sourceFile, String zipOutFileName) throws IOException 
	 {
	      //String sourceFile = "C:\\TEMP\\OutputFiles";
	      FileOutputStream fos = new FileOutputStream(zipOutFileName);
	      ZipOutputStream zipOut = new ZipOutputStream(fos);
	      File fileToZip = new File(sourceFile);
	      zipFile(fileToZip, fileToZip.getName(), zipOut);
	      zipOut.close();
	      fos.close();
	 }

	  private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException 
	  //private static void zipFile(String sourceFile, String zipOutFileName) throws IOException
	  {		      
		  if (fileToZip.isHidden()) 
	          return;
	     
	      if (fileToZip.isDirectory()) 
	      {
	          if (fileName.endsWith("/")) {
	              zipOut.putNextEntry(new ZipEntry(fileName));
	              zipOut.closeEntry();
	          } else {
	              zipOut.putNextEntry(new ZipEntry(fileName + "/"));
	              zipOut.closeEntry();
	          }
	          File[] children = fileToZip.listFiles();
	          for (File childFile : children) {
	              zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
	          }
	          return;
	      }
	      FileInputStream fis = new FileInputStream(fileToZip);
	      ZipEntry zipEntry = new ZipEntry(fileName);
	      zipOut.putNextEntry(zipEntry);
	      byte[] bytes = new byte[1024];
	      int length;
	      while ((length = fis.read(bytes)) >= 0) {
	          zipOut.write(bytes, 0, length);
	      }
	      fis.close();
	  }
	  public static void CreateADirectory(String DirectoryName)
	    {
	        //project directory
	        //String workingDirectory = System.getProperty("user.dir");
	        //String  dir = workingDirectory + File.separator + DirectoryName;

		  String  dir = DirectoryName;  
		  System.out.println("Final file directory : " + dir);

	        //create a directory in the path

	        File file = new File(dir);

	        if (!file.exists()) {
	            file.mkdir();
	            System.out.println("Directory is created!");
	        } else {
	            System.out.println("Directory is already existed!");
	        }

	    }
	  
}