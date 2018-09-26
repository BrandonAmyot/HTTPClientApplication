import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class httpc {

	public static void main(String[] args) {
		
		String url = "www.concordia.ca";
		
		try {	
			Socket mySocket = new Socket(url, 80);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			out.println("HEAD / HTTP/1.1");
			out.println("Host: " + url);
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
	}

}
