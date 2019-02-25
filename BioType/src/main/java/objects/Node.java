package objects;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Node implements Serializable {
	private double time=0;
	private int type =0;
	private String name="";
	private String textuser="";
	
	/**
	 * this is the node object for the recording keyboard
	 * its contains info in order to confirm sucsses and other factors.
	 */
	
	public Node ()
	{
		
	}
	public Node(double time,int type,String name,String textuser)
	{
		setTime(time);
		setName(name);
		setTextuser(textuser);
		setType(type);
		
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTextuser() {
		return textuser;
	}
	public void setTextuser(String textuser) {
		this.textuser = textuser;
	}
	

}
