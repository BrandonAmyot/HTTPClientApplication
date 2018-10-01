import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Get {
	private static boolean verbose = false;
	private static boolean headers = false;
	private static String headerKey;
	private static String headerValue;
	
	public static boolean isVerbose() {
		return verbose;
	}
	public static void setVerbose(boolean verbose) {
		Get.verbose = verbose;
	}
	public static boolean isHeaders() {
		return headers;
	}
	public static void setHeaders(boolean headers) {
		Get.headers = headers;
	}
	
	public static void doGet(String urlShort) throws Exception {
		
		URL url = new URL(urlShort);

		String hostName = url.getHost();
		int port = 80;
		
		Socket mySocket = new Socket(hostName, port);
			
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			
		out.println("GET " + url + " HTTP/1.1");
		out.println("Host: " + hostName);
		out.println();
		out.flush();

			
		// Get request
		BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream())); 
			
		String userInput;
		while((userInput = in.readLine()) != null) {
			System.out.println(userInput);
		}
			
		// if there is a verbose command, return header fields
		if(verbose) {
				
		}
			
		// if there is a header command, return specific key and value
		if(headers) {
				
		}
			
		in.close();
		mySocket.close();

	}
	
	
	
}
