package functions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import crypto.CryptoException;
import crypto.CryptoUtils;
import gui.Gui;
import lock.Main;

public class Functions {
    
	
	public Functions()
	{
		
	}
	//--------------cookies--------------
	public static void deletecookies()
	{
		if (Files.exists(Main.source.toPath())) { // if the target folder exists, delete it first;
			
			 try {
				Files.deleteIfExists(Main.source.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	public static void savecookies(String name)
	{
		try {
			killchrome();
			TimeUnit.SECONDS.sleep(2);
			
			
			
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File dest = new File("cookies\\"+name);
		try {
			copyAnd_NOTDelete(Main.source, dest);
			 String key = "aaaaaaaaaaaaaaaa";
			 File encryptedFile = new File("cookies\\"+name);
			 CryptoUtils.encrypt(key, dest, encryptedFile);
		        Main.source.delete();
		} catch (IOException | CryptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void uplouadcookies(String name)
	{
		
		File dest = new File("cookies\\"+name);
		try {
			killchrome();
			TimeUnit.SECONDS.sleep(1);
			String key = "aaaaaaaaaaaaaaaa";
			 File encryptedFile = new File("cookies\\"+name);
			 CryptoUtils.decrypt(key, dest, encryptedFile);
			 copyAnd_NOTDelete(dest, Main.source);
			 CryptoUtils.encrypt(key, dest, encryptedFile);
			File theDir = new File("cookies");
			if(!theDir.exists())
			{
				theDir.mkdir();
			}
		} catch (IOException | InterruptedException | CryptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	///------------------------------------
 	public static void copyAndDelete(File source, File dest) throws IOException {
		if (Files.exists(dest.toPath())) { // if the target folder exists, delete it first;
			
			 Files.deleteIfExists(dest.toPath());
	    }
	    try {
	        Files.move(source.toPath(), dest.toPath());
	        Files.deleteIfExists(source.toPath());
	    } catch (IOException ignored) {
	    	Gui.textArea.append("\n error while upload");
	        //ignored.printStackTrace();
	    }
	    
	}
 	public static void copyAnd_NOTDelete(File source, File dest) throws IOException {
		if (Files.exists(dest.toPath())) { // if the target folder exists, delete it first;
			
			 Files.deleteIfExists(dest.toPath());
	    }
	    try {
	    	Files.copy(source.toPath(), dest.toPath());
	       
	        
	    } catch (IOException ignored) {
	    	Gui.textArea.append("\n error while upload");
	        ignored.printStackTrace();
	    }
	    
	}
	//-----chrome functions
	public static void killchrome() throws IOException {
		Process p = Runtime
                .getRuntime()
                .exec("TASKKILL /IM chrome.exe");
	    
	}
	public static void startchrome() throws IOException {
		 Process p3t = Runtime
	                .getRuntime()
	                .exec("cmd /c start chrome.exe");
	}
	public static void chromebyname(String name) throws IOException, InterruptedException
	{
		killchrome();
		TimeUnit.SECONDS.sleep(2);
		uplouadcookies(name);
		startchrome();
		
	}
	///------------------
	public static void deleteuser(String user,String passw)
	{
		
		if(Main.hmap.get(passw).getTextuser().equals(user))
		{
			String name=Main.hmap.get(passw).getName();
			File dest = new File("cookies\\"+name);
			if(Main.hmap.get(passw).getType()==1)
				{dest.delete();}
			Main.hmap.remove(passw);
			
			
			Gui.textArea.append("\n \n removed user! \n");
			Gui.textArea.append(System.lineSeparator());
			Save s=new Save();
			try {
				s.saveobj(Main.hmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			Gui.textArea.append("\n Error wrong details");
		}
	}
	
}
