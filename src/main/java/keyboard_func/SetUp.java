package keyboard_func;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Queue;
import java.util.Map.Entry;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import functions.Functions;
import functions.Save;
import gui.Gui;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.example.KeyboardFunc;
import lock.Main;
import objects.Node;

public class SetUp implements Runnable {

	public HashMap<String, Node> hmap = Main.hmap;
	 
    public String name ="";
    public int type=1;
    public String user="";
	//----------------------------
	public   Queue<String[]> fifo = Main.fifo;
	private static boolean run = true;
	
	/**
	 * class that handle the recording when u setup
	 */

	
	public SetUp()
	{}
	public void run()
	{
		reckeyboard();
		Functions.savecookies(name);
		Save s=new Save();
		try {
			s.saveobj(hmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reckeyboard()
	{
		/**
		 * recored up to 8 characters
		 */
		run=false;
		run=true;
		fifo=new CircularFifoQueue<String[]>(8);
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input

		  
		   
		   
		   
		   
		   
		   
		   
		   
		   
		  
		for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet())
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override public void keyPressed(GlobalKeyEvent event) {
				//System.out.println(event);
				if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
					run = false;
			}
			@Override public void keyReleased(GlobalKeyEvent event)
			{       
				
				fifo.add(keypress(event));
				String mako=getstr(fifo);
					if(mako.length()==8)
					{     
						
						Node n=new Node(timediff(), type, name, user);
						
						hmap.put(mako, n);
						Gui.textArea.append("\n user have been added");
						Gui.textArea.append(System.lineSeparator());
						keyboardHook.shutdownHook();
						Gui.preventrec.setText("Start recording!");
						Gui.quickrec.setText("Start recording!");
						System.out.println("done recoreding");
						Gui.textArea.append("\n pass:"+mako);
						Gui.textArea.append(System.lineSeparator());
						Gui.textArea.append("\n speed: "+n.getTime());
						Gui.textArea.append(System.lineSeparator());
						System.out.println(n.getTime()+"  :"+mako);
						
						run=false;
						return;
						
					}
					
				
				 
			}
		});
	
		try {
			while(run) Thread.sleep(128);
		} catch(InterruptedException e) { /* nothing to do here */ }
		  finally { keyboardHook.shutdownHook(); }
	}
	public static String getstr(Queue<String[]> b)
	{
		/**
		 * get the passw string
		 */
		String a="";
		
		for(String[] d:b)
		{
			a=a+d[1];
		}
		return a;
	}
	public static double gettime()
	{
		/**
		 * get the typing time
		 */
		int a=Calendar.getInstance().get(Calendar.SECOND);
		int b=Calendar.getInstance().get(Calendar.MILLISECOND);
		double c=Double.valueOf(a+"."+b);
		return c;
		
	}
    public static String[] keypress(GlobalKeyEvent event)
    {
    	/**
    	 * get the time and the letter u pressed
    	 */
    	String a=String.valueOf(gettime());
		String b=String.valueOf(event.getKeyChar());
		String[]c= new String[2];
		c[0]=a;
		c[1]=b;
		return c;
    	
    }
    public  double timediff()
    {
    	/**
    	 * calc the time of the whole passw press
    	 */
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
