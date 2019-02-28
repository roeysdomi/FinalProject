package communication;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Textnow {
	/**
	 * an Api class i created for app called "TEXTNOW"
	 */
     public static String Cookie="unsupported_browsers_notif=true; _ga=GA1.2.1665469293.1550428349; _gid=GA1.2.4927981.1550428349; language=he; _fbp=fb.1.1550428351716.137431393; G_ENABLED_IDPS=google; fbm_134162068005=base_domain=.textnow.com; UserDidVisitApp=true; FirehoseSession-messaging=true; puntCookie=true; __rtgt_sid=js990pincs45xw; d7s_uid=js990pincs45xw; __gads=ID=0c819fee36be8d23:T=1550428376:S=ALNI_Maa_XbQe4jiiw58kmwzIXLrqXJk5A; PermissionPriming=1; FirehoseSession-portal=true; fbsr_134162068005=-xKrSrPZRdbkxdawrOPqK0HktOng00mEMcy8ZNPfHxA.eyJ1c2VyX2lkIjoiMTAwMDAwMTQwNzM1MDYwIiwiY29kZSI6IkFRQVhoSjRYZkpIc3BCUlREdGl0RVlRSzF3RkMtelBxYkp1QTNNLTVfT29JdlBtZ1pOODZTMTg5OGJ6SUwtZlpLWlU1UzhZNDdvU0dnOTRBYTQ3X0lGVjlqWkg0Q2ltQjdzZGR6RmlpVnJKeWVSMmJEdUpCbmRORmlSc050ekItSzYtcnFyaGtoazliODVRUDFTRjhob0NmZkJyZEVqcEtYWTJCMzQtVHFmNEh0RTZuMUwtbWdLVWVvbVdiV3J3VW5EWHlPclZILVc2ck9QakxuNTRyMVlMMThUYno4T19HR3V3VUE0Y3dzU08yTTQ5emY5aUtqODB4VzJhVk9LbWlSUG5KeUJoWUdHVmd4UTBhT0RzT24wMVhPY3dKWWlLTEdmcEVqU3BfT3BheGJmd1VRWVk3eGNnREpVNW5JVXJ2UDdqdDZBYTJBWUU1bXRKVnFKU2JHTzBWIiwiYWxnb3JpdGhtIjoiSE1BQy1TSEEyNTYiLCJpc3N1ZWRfYXQiOjE1NTA0MjkyNDJ9; sm_dapi_session=1; connect.sid=s%3ATQiPYu1MHy7ACWL5l2WKocO0T7pGtwfK.O61Klu0hFB3amke0sJTUtK8Mss6m9%2FzduiVLhaT%2FCGI; stc117823=tsa:1550428357822.1955583937.3724961.1831161290579808.:20190217192531|env:1%7C20190320183237%7C20190217192531%7C17%7C1073241:20200217185531|uid:1550428357821.575358341.9570189.117823.55585102.82:20200217185531|srchist:1073241%3A1%3A20190320183237:20200217185531; d7s_spc=9; XSRF-TOKEN=cMNVVblc-blXaB7KQI41sTK-tqSVdZRX6C_Q" ;
     public static String res="";
	

	
	
	public Textnow()
	{
	    
		/*
		  	System.setProperty("http.proxyHost", "127.0.0.1");
		      System.setProperty("https.proxyHost", "127.0.0.1");
		      System.setProperty("http.proxyPort", "8888");
		      System.setProperty("https.proxyPort", "8888");
		      System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Roey\\Desktop\\FiddlerKeystore");
		      System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
		*/
		
	}
	
	public String [] readlastmess() throws IOException
	{    
		/**
		 * read last messege and user name
		 */
		
		
		String url="https://www.textnow.com/api/users/roeytest/messages?start_message_id=0&direction=future&page_size=0";
		getreq(url);
		int s=res.lastIndexOf("message\":\"");
		int t2=res.indexOf("\"",s+11);
		String f=res.substring(s+10,t2);
		int s2=res.lastIndexOf("contact_value\":\"");
		int t3=res.indexOf("@",s2+11);
		String f2=res.substring(s2+16,t3);
		String []fin= {f2,f};
		return fin;
	}
	public void sendmess(String to,String content) throws IOException
	{
		/**
		 * send messges to user by name and content
		 */
		
		String url="https://www.textnow.com/api/users/roeytest/messages";
		String body ="json={\"contact_value\":\"roey.sdomi\",\"contact_type\":2,\"message\":\"efggflon\",\"read\":1,\"message_direction\":2,\"message_type\":1,\"from_name\":\"\",\"has_video\":false,\"new\":true,\"date\":\"2018-11-09T02:36:32.533Z\"}";
		body=body.replace("roey.sdomi", to).replaceAll("efggflon", content);
		postreq(url, body);
	}
	///-----------http headers commands--------------
	 public   void getreq(String enterurl) throws IOException 
	  {
		 
   	 HttpsURLConnection connection=null;
   
		    String https_url =enterurl;
		    
		    URL url = new URL(https_url);
		    
		   connection = (HttpsURLConnection)url.openConnection();
		    
		    headers(connection, https_url,"GET");
		  
		    connection.getContent();
		    
		    //-----cookies
		    
		    
		    
		    //-----getresponse------
		    
        res= respondmaker(connection);
       
   	 
  
   	
		   
        
		   
		    //------------------
		 }
	 public void headers(HttpsURLConnection connection,String https_url,String type)
	  {
	  	    try {
					connection.setRequestMethod(type);
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					System.out.println("problem in the headers");
				}
			    connection.addRequestProperty(" origin", https_url);	
	  	        System.setProperty("http.KeepAlive", "true");
			  //  connection.addRequestProperty("Upgrade-Insecure-Requests", "1");	
			    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1");	
			    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
			    connection.addRequestProperty("Referer", https_url);	
			    connection.addRequestProperty("Accept-Encoding", "");	
			    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");
			   
			       connection.addRequestProperty("Cookie",Cookie);
			 
			    
			   // connection.addRequestProperty("Connection","keep-alive");
			    connection.setConnectTimeout(1000);

	  }
	 public String respondmaker(HttpsURLConnection connection) throws IOException
	  {
	  	 InputStream output = connection.getInputStream();
			    Scanner s = new Scanner(output).useDelimiter("\\A");
			    String result =".";
			    while (s.hasNext())
			    {
			    	
			    		result=s.next();	    		
			    }
			  return result;
	  	
	  	    	
	  	
	  }
	 public   void postreq(String enterurl,String body) throws IOException
	  {
		 HttpsURLConnection connection=null;
		  try {
		   String https_url =enterurl;
		   
	        URL url = new URL(https_url);
		    
		     connection = (HttpsURLConnection)url.openConnection();
		  //-----headers------
         headers(connection,https_url,"POST");
		  //-----headers------
       
         //-----body------
		    body(connection, body);
         //-----body--------
		    
		    connection.getContent();
		    
		    
		    //-----cookies   
		    
		   //-----cookies
		    res=respondmaker(connection);
		  } 
		    catch (Exception e) {
	    		
			}

		 }
	 public void body(HttpsURLConnection connection,String body) throws IOException
	  {
	  	connection.setDoOutput(true);
		    String str =  body;
		    byte[] outputInBytes = str.getBytes("UTF-8");
		    OutputStream os = connection.getOutputStream();
		    os.write(outputInBytes);    
		    os.close();

	  }

}
