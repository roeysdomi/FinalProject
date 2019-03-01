package lock;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import communication.AppAlert;
import functions.Save;
import gui.Gui;
import gui.Popup;
import objects.Node;

public class Main {
     
	public static File source = new File("C:\\Users\\Roey\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies");
	public static HashMap<String, Node> hmap = new HashMap<String, Node>();
	public static Queue<String[]> fifo = new CircularFifoQueue<String[]>(8); 
	public static String manager="";
	static boolean firsttime=false;
	/**
	 * the main class where the program lunch from
	 * @param args
	 */
	public static void main(String[] args) {
		String libraryPath = "C:\\Users\\Roey\\eclipse-workspace\\opencv\\build\\java\\x64";
		System.setProperty("java.library.path", libraryPath);
		try {
		Field sysPath = ClassLoader.class.getDeclaredField("sys_paths");
		sysPath.setAccessible(true); 
		sysPath.set(null, null);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Save s=new Save();
		try {
			if(new File("myObjects").exists())
			Main.hmap=(HashMap<String, Node>) s.readobj_byname("myObjects");
		
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Popup popup = new Popup();
		if(!(new File("source").exists()&new File("m").exists()))
		{
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						firsttime=true;
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
		String f;
		try {
			manager=(String) s.readobj_byname("m");
			f = (String) s.readobj_byname("source");
			source=new File (f);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//-----------------
		AppAlert m1=new AppAlert();  
		Thread t1 =new Thread(m1);  
		t1.start(); 
		//-----------------
		Gui window = new Gui();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frame.setVisible(true);
					if(firsttime) {
					popup.frame.setVisible(true); firsttime=false;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
