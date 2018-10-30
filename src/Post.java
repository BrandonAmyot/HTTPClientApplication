import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class Post {
	private static boolean verbose = false;
	private static boolean headers = false;
	private static ArrayList<String> headerArr = new ArrayList<String>();
	
	private static boolean inlineData = false;
	private static String dataContent = "";
	
	private static boolean hasFile = false;
	private static String fileName = "";
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
	public static void setInlineData(boolean hasData) {
		Post.inlineData = hasData;
	}
	public static boolean isFileName() {
		return hasFile;
	}
	public static void setIsFile(boolean hasFile) {
		Post.hasFile = hasFile;
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
		else if(hasFile)
			out.println("Content-Length: " + fileContents.length());
		// if there is a header command, return specific key and value
		if(headers) {
			for(int i = 0; i < headerArr.size(); i++) {
				out.println(headerArr.get(i).toString());
			}
		}
		out.println();
			
		// if there is inline data command, return data value
		// or if there is a file, return contents of file
		if(inlineData) {
			out.println(dataContent);
		}
		else if(hasFile) {
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
		for(int i=0; i<url.length(); i++) {
			
			if(url.charAt(i)== '-' && url.charAt(i+1) == 'h') {
				String temp = StringUtils.substring(url, i+3);
				String header = StringUtils.substringBefore(temp, " ");
				headerArr.add(header);
			}
		}
	}
	
	public static void addData(String url) {
		String temp = StringUtils.substringAfter(url, "{");
		dataContent = "{" + StringUtils.substringBefore(temp, "}") + "}";		
	}
	
	public static void readFile(String url) {
		String temp = StringUtils.substringAfter(url, "-f ");
		fileName = StringUtils.substringBefore(temp, " ");
		
		try {
//			File inputFile = new File(fileName);
//			if(!inputFile.exists())
//				System.out.println("The file : " + fileName + " does not exist.");
			
			BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
			
			while((temp = inputReader.readLine()) != null) {
				fileContents += temp;				
			}
			inputReader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
