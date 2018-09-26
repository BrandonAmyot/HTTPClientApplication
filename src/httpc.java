import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class httpc {

	public static void main(String[] args) {
		
		String address = "www.concordia.ca";
		
		try {	
			// Create a socket and connection
			Socket mySocket = new Socket(address, 80);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			
			// Sends and HTTP request to the server
			out.println("HEAD / HTTP/1.1");
			out.println("Host: " + address);
			out.println();
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream())); 
			
			// Read the response
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
	}

}
