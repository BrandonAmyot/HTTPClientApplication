import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class httpc {

	public static void main(String[] args) {
		
		try {	
			Socket mySocket = new Socket("www.httpbin.org", 80);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			out.println("GET / HTTP/1.1");
			out.println("Host: www.httpbin.org");
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
