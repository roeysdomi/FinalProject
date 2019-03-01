package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Save {
	
	/**
	 * class that handle the saving and reading of objects
	 */
	public Save() 
	{
		
	}
	public void saveobj(Object obj) throws IOException
	{
		/**
		 * save object by name
		 */
		FileOutputStream f = new FileOutputStream(new File("myObjects"));
		ObjectOutputStream o = new ObjectOutputStream(f);

		// Write objects to file
		o.writeObject(obj);
	    

		o.close();
		f.close();
	}
	public Object readobj () throws IOException, ClassNotFoundException
	{
		/**
		 * read object by name
		 */
		FileInputStream fi = new FileInputStream(new File("myObjects"));
		ObjectInputStream oi = new ObjectInputStream(fi);
		return oi.readObject();
	}
	////-------------------------
	public void saveobj_byname(Object obj,String name) throws IOException
	{
		/**
		 * save object by name
		 */
		FileOutputStream f = new FileOutputStream(new File(name));
		ObjectOutputStream o = new ObjectOutputStream(f);

		// Write objects to file
		o.writeObject(obj);
	    

		o.close();
		f.close();
	}
	public Object readobj_byname (String name) throws IOException, ClassNotFoundException
	{
		/**
		 * read object by name
		 */
		FileInputStream fi = new FileInputStream(new File(name));
		ObjectInputStream oi = new ObjectInputStream(fi);
		return oi.readObject();
	}
    
}
