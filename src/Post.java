import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class Post {
	private static boolean verbose = false;
	private static boolean headers = false;
	public static String header;
	
	private static boolean inlineData = false;
	private static String dataName;
	private static boolean fileName = false;
	private static String file;
	
	// Accessor and Mutator Methods
	public static boolean isVerbose() {
		return verbose;
	}
	public static void setVerbose(boolean verbose) {
		Post.verbose = verbose;
	}
	public static boolean isHeaders() {
		return headers;
	}
	public static void setHeaders(boolean headers) {
		Post.headers = headers;
	}
	public static boolean isInlineData() {
		return inlineData;
	}
	public static void setInlineData(boolean inlineData) {
		Post.inlineData = inlineData;
	}
	public static boolean isFileName() {
		return fileName;
	}
	public static void setFileName(boolean fileName) {
		Post.fileName = fileName;
	}
	
	public static void doPost(String urlShort) throws Exception {

		URL	url = new URL(urlShort);

		String hostName = url.getHost();
		int port = 80;
	
		Socket mySocket = new Socket(hostName, port);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			
		out.println("POST " + url + " HTTP/1.1");
		out.println("Host: " + hostName);
		// if there is a header command, return specific key and value
		if(headers)
			out.println(header);
		out.println();
		// if there is inline data command, return data value
//		if(inlineData)
//			out.println(dataName);
		out.println();
		out.flush();
			
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
			
		in.close();
		mySocket.close();
	}
	
	public static void addHeaders(String url) {
		String temp = StringUtils.substringAfter(url, "-h ");
		header = StringUtils.substringBefore(temp, " ");
		System.out.println("Header to be added: " + header);
		
	}
	
	public static void addData(String url) {
//		dataName = "";
		String temp = StringUtils.substringAfter(url, "{");
		dataName = StringUtils.substringBefore(temp, "}");
		System.out.println("Data to be added: " + dataName);
		
	}
	
}
