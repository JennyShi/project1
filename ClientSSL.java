import java.io.*;
import java.net.*;


import javax.net.ssl.SSLSocketFactory;


public class ClientSSL {

	private static int calculate(String str)
	{
		String m[] = str.split(" ");
		int a = Integer.parseInt(m[2]);
		int b = Integer.parseInt(m[4]);
		int c = 0;
		
		if (m[3].equals("+") == true)
			c = a + b;
		else if(m[3].equals("/") == true)
			c = Math.round(a / b);
		else if(m[3].equals("-") == true)
			c = a - b;
		else if(m[3].equals("*") == true)
			c = a * b;
		return c;
	}
	public static void main(String[] args) 
	{ 
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String NUID = args[2];
		
		Socket clientSocket = null;
		// TODO Auto-generated method stub
		
		try {
			System.setProperty("javax.net.ssl.trustStore", "clientKey.jks");
			System.setProperty("javax.net.ssl.trustStorePassword","sslkey1");
			SSLSocketFactory sslsf = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
		//	System.out.println("Connecting...");
			clientSocket = sslsf.createSocket(host,port);
		//	System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream

()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			
			out.println("cs5700spring2014 HELLO " + NUID);
			
			String responseLine = in.readLine();
			
			//System.out.println(responseLine);
			
			
			//System.out.println(calculate(responseLine));
			while(responseLine.contains("BYE") != true)
			{
				System.out.println(responseLine);
				//responseLine = in.readLine();
				int solution = calculate(responseLine);
				//System.out.println("cs5700spring2014 " + solution +"\n");
				out.println("cs5700spring2014 " + solution +"\n");
				responseLine = in.readLine();
				//out.flush();
				//System.out.println("Goodbye!");
				
				//System.exit(0);
			}
			System.out.println(responseLine);	
			out.close(); 
			in.close(); 
			clientSocket.close(); 
		}
		catch(UnknownHostException e){
			System.out.println("Unknown host!");
		}
		
		catch (IOException e) 
		{
		//	System.out.println(e);
			System.out.println("Error!");
		} 
	}
	
}
