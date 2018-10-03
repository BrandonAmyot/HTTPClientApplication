import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class Get {
	private static boolean verbose = false;
	private static boolean headers = false;
	public static String[] headerKey;
	public static String[] headerValue;
	
	// Accessor and Mutator methods
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
			
		// send request
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 	

		out.println("GET " + url + " HTTP/1.1");			
		out.println("Host: " + hostName);
		out.println();
		out.flush();

			
		// Print the get request
		BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream())); 
			
		String response;
		boolean messageBody = false;
		while((response = in.readLine()) != null) {
			if(verbose)
				System.out.println(response);
			else {
				if(messageBody)
					System.out.println(response);
				if(response.equals(""))
					messageBody = true;
			}
		}
			
		// if there is a header command, return specific key and value
		if(headers) {
				
		}
			
		in.close();
		mySocket.close();

	}
	
	public static void addHeaders(String url) {
		
		headerKey = url.split(" |:");
		
		for (String x : headerKey)
			System.out.println(x);
		
	}
	
	
	
}
