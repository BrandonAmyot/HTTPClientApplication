import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;


public class httpc {

	public static void main(String[] args) {
		
		URL url;
		try {
			url = new URL("http://www.google.com/");
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
	}

}
