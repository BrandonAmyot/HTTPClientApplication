import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class Post {
	private static boolean verbose = false;
	private static boolean headers = false;
	private static String headerContent;
	private static boolean inlineData = false;
	private static String dataContent = "";
	private static boolean isFile = false;
	private static String fileContents = "";
	
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
		return isFile;
	}
	public static void setFileName(boolean fileName) {
		Post.isFile = fileName;
	}
	
	// Make POST request
	public static void doPost(String urlShort) throws Exception {

		URL	url = new URL(urlShort);

		String hostName = url.getHost();
		int port = 80;
	
		Socket mySocket = new Socket(hostName, port);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()))); 
			
		out.println("POST " + url + " HTTP/1.1");
		out.println("Host: " + hostName);
		
		// send content-length of inline data or file
		if(inlineData)
			out.println("Content-Length: " + dataContent.length());
		else if(isFile)
			out.println("Content-Length: " + fileContents.length());
		// if there is a header command, return specific key and value
		if(headers)
			out.println(headerContent);
		out.println();
			
		// if there is inline data command, return data value
		// or if there is a file, return contents of file
		if(inlineData) {
			out.println(dataContent);
		}
		else if(isFile) {
			out.println(fileContents);
		}
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
		headerContent = StringUtils.substringBefore(temp, " ");		
	}
	
	public static void addData(String url) {
		String temp = StringUtils.substringAfter(url, "{");
		dataContent = "{" + StringUtils.substringBefore(temp, "}") + "}";		
	}
	
	public static void addFile(String url) {
		String temp = StringUtils.substringAfter(url, "-f ");
		fileContents = StringUtils.substringBefore(temp, " ");
	}
	
}
