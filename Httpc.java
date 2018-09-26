import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Httpc {
	
	public static void main(String[] args) throws Exception {
		
		URL url;
		try {
			url = new URL("http://httpbin.org/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String hostName = url.getHost();
		int port = 80;
		
		try {	
			Socket mySocket = new Socket(hostName, port);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			
			out.println("HEAD " + url.getPath() + " HTTP/1.1");
			out.println("Host: " + hostName);
			out.println();
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream())); 
			
			String userInput;
			while((userInput = in.readLine()) != null) {
				System.out.println(userInput);
			}
			
			in.close();
			mySocket.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		String hostName = "http://httpbin.org/get?course=networking&assignment=1";
//		int portNumber = 80;
//		Socket mySocket;
//		
//		try {
//		   mySocket = new Socket("www.posttestserver.com", 80);
//		   System.out.println(mySocket.isConnected());
//		   
//		   PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
//		   
//		   System.out.println(out.toString());
//		   BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
//		   System.out.println("AF"+out.toString());
//		   System.out.println(input.readLine());
//		   BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//		   
//		   String line;
//		   while((line = input.readLine()) != null) {
//			   System.out.println("Hello");
//			   System.out.println(line);
//		   }
//		   System.out.println("Hello");
//		   mySocket.close();
//		   System.out.println(mySocket.isConnected());
//		   input.close();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		
		// GET request
//        URLConnection newConn = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(newConn.getInputStream()));
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
//		for (String line; (line = reader.readLine()) != null;) {
//			System.out.println(line);
//		}
   
        // -v command
//		Map <String, List<String>> info = url.openConnection().getHeaderFields();
//		
//		for (Map.Entry<String, List<String>> line : info.entrySet()) {
//			if(line.getKey() != null)
//				System.out.println(line.getKey() + ": " + line.getValue());
//			else
//				System.out.println(line.getValue());
//		}
		
		// -h command
//		Map <String, List<String>> headers = url.openConnection().getHeaderFields();
//				
//		for (Map.Entry<String, List<String>> line : headers.entrySet()) {
//			if(line.getKey() != null)
//				System.out.println("Key: " + line.getKey() + "\tValue: " + line.getValue());
//		}
		
	}

}
