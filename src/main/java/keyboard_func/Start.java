package keyboard_func;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import camera.Camera;
import communication.AppAlert;
import communication.Textnow;
import functions.Functions;
import gui.Gui;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lock.Main;
import objects.Node;

public class Start implements Runnable{
	//Functions fun=
	public HashMap<String, Node> hmap = Main.hmap;
    public String name ="";
    public int type=1;
	//----------------------------
	public   Queue<String[]> fifo = Main.fifo;
	public static boolean run = true;
	public static String lastname="";
	/**
	 * the same as setup class but with startkeyboard
	 */
	public void run()
	{
		startkeyboard();
	}
    public void startkeyboard()
	{
    	/**
    	 * check the password typing was made by suspect .
    	 */
		run=true;
			fifo=new CircularFifoQueue<String[]>(8);
			GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input

			System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
			for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet())
				System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
			
			keyboardHook.addKeyListener(new GlobalKeyAdapter() {
				@Override public void keyPressed(GlobalKeyEvent event) {
					
					//System.out.println(event);
					
					
				}
				@Override public void keyReleased(GlobalKeyEvent event) 
				{       
					
					fifo.add(keypress(event));
					String mako=getstr(fifo);
					if(mako.equals("qqqqqqqa")) {Functions.savecookies(lastname);}
						if(mako.length()==8&hmap.containsKey(mako))
						{     
							
							
							if(hmap.get(mako).getTime()>timediff())
							{
								System.out.println("detect");
								try {
									 if(hmap.get(mako).getType()==1)
									 {
										 lastname=hmap.get(mako).getName();
										 Functions.chromebyname(hmap.get(mako).getName());
										 Gui.textArea.append("\n \n DETECTED !\n");
									 }
									
								} catch (IOException |InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
								
							}
							else
							{
								
								try 
								{
									if(hmap.get(mako).getType()==2)
									{
										Functions.killchrome();
										TimeUnit.SECONDS.sleep(2);
										Functions.deletecookies();
									}
									Textnow r=new Textnow();
									r.sendmess(hmap.get(mako).getTextuser(), Camera.takepic());
									r.sendmess(hmap.get(mako).getTextuser(), hmap.get(mako).getName()+", SOMEONE TRYING TO HACK YOU");
								} 
								catch (IOException | InterruptedException e1)
								{
									// TODO Auto-generated catch block
									
								}
								
							}
							
						}
						
					
					 
				}
			});
		
			try {
				while(run) Thread.sleep(128);
				if(!run) {keyboardHook.shutdownHook();}
			} catch(InterruptedException e) { /* nothing to do here */ }
			  finally { keyboardHook.shutdownHook(); }
		
	}
	
	public static String getstr(Queue<String[]> b)
	{
		String a="";
		
		for(String[] d:b)
		{
			a=a+d[1];
		}
		return a;
	}
	public static double gettime()
	{
		int a=Calendar.getInstance().get(Calendar.SECOND);
		int b=Calendar.getInstance().get(Calendar.MILLISECOND);
		double c=Double.valueOf(a+"."+b);
		return c;
		
	}
    public static String[] keypress(GlobalKeyEvent event)
    {
    	String a=String.valueOf(gettime());
		String b=String.valueOf(event.getKeyChar());
		String[]c= new String[2];
		c[0]=a;
		c[1]=b;
		return c;
    	
    }
    public  double timediff()
    {
    	int counter=1;
    	double a=0;
    	double b=0;
    	for(String []g:fifo)
    	{
    		System.out.println(g[0]);
    		if(counter==1) {a=Double.valueOf(g[0]);}
    		if(counter==8) {b=Double.valueOf(g[0]);}
    		counter++;
    	}
    	double c=0;
    	System.out.println("this is a:"+a+"this is b: "+b);
    	if(b>a)
    	{c=a-b;}
    	if(a>b)
    	{
    		c=a-b;
    	    
    	    c=60-c;
    	}
    	
    	if(c<0) {c=c*-1;}
    	System.out.println(c);
    	
    	return c;
    }

}
