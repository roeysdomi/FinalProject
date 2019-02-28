package communication;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import camera.Camera;
import camera.MultipartUtility;
import functions.Functions;
import gui.Gui;
import keyboard_func.Start;
import lock.Main;
import objects.Node;

public class AppAlert implements Runnable
{  
	public  HashMap<String, Node> hmap=Main.hmap;
	public boolean copy=false;
	
	/**
	 * class that run as a thread and listen to smartphone
	 */
	public void run()
	{  
		/**
		 * listen to smartphone and act by command
		 */
		boolean keep=true;
		Textnow txt=new Textnow();
		while(keep)
		{
			try {
				String []chat=txt.readlastmess();
				String usr=chat[0];
				String msg=chat[1];
				System.out.println(usr+" ,"+msg);
				if(msg.equals("Copy")) {copy=true;}
				if(msg.equals("Nocopy")) {copy=false;}
				
				if(copy)
				{
					String myString = "null";
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
				if(msg.contains("Kill:"))
				{
					msg=msg.replace("Kill:", "");
					Process p = Runtime
			                .getRuntime()
			                .exec("TASKKILL /IM "+msg+".exe");
				}
				if(msg.equals("Openchrome"))
				{
					System.out.println("openchrome");
					String name=findNodeByUSER(usr).getName();
					Functions.chromebyname(name);
					
				}
				if(msg.equals("Killchrome"))
				{
					System.out.println("killchrome");
					Functions.killchrome();
					TimeUnit.SECONDS.sleep(2);
					Functions.deletecookies();
					
				}
				if(msg.equals("Sleep"))
				{
					System.out.println("sleep");
					Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
				}
				if(msg.equals("Pic"))
				{
					
					txt.sendmess(usr, Camera.takepic());
				}
				if(msg.equals("Start"))
				{
					if(Gui.Startbut.getText().equals("START"))
					{
						Gui.Startbut.setText("STOP");
						Gui.textArea.append(System.lineSeparator());
						
						Gui.textArea.append("\n START PROGRAM ");
						Start s=new Start();
					    Thread t=new Thread(s);
					     t.start();
						
					     Gui.lblNewLabel.setIcon(new ImageIcon("images\\Final project2.png"));
					}
				}
				if(msg.equals("Screenshot"))
				{
					File g=new File("sanpshot.jpg");
					BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "jpg", new File("sanpshot.jpg"));
					txt.sendmess(usr, MultipartUtility.uplouadpic());
					
				}
				if(!msg.equals("ok"))
				{
					txt.sendmess(usr, "ok");
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (IOException | InterruptedException | HeadlessException | AWTException e) {
				
				
			}
		}
	} 
	
	public AppAlert()
	{
		
	}
	public Node findNodeByUSER(String user)
	{
		/**
		 * return node by username
		 */
		Node n=new Node();
		for(Node b:hmap.values())
		{
			if(b.getTextuser().equals(user))
			{
				n=b;
			}
			break;
		}
		return n;
	}
	
	
}
