import java.net.*;
import java.util.Scanner;
import java.io.*;

	// A client for our Multithreaded SocketServer. 
	public class Client
	{ 
	  
	    public static void main(String[] args)
	    { 
	        // First parameter has to be Server host name or IP address 
	        if(args.length == 0) 
	        { 
	            System.out.println("Usage : SocketClient <serverName>"); 
	            return; 
	        } 
	       
	        Socket s = null; 
	        
	        // Create the socket connection to the MultiThreadedSocketServer port 11111 
	        try 
	        { 
	            s = new Socket(args[0], 8888); 
	        }        
	        catch(UnknownHostException uhe) 
	        { 
	            // Server Host unreachable 
	            System.out.println("Unknown Host :" + args[0]); 
	            s = null; 
	        } 
	        catch(IOException ioe) 
	        { 
	            // Cannot connect to port on given server host 
	            System.out.println("Cant connect to server at 11111. Make sure it is running."); 
	            s = null; 
	        } 
	        
	        if(s == null) 
	            System.exit(-1); 
	        
	        BufferedReader in = null; 
	        PrintWriter out = null; 
	        Scanner input = null ;
