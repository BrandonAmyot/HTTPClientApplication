import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class simpleClient {

	private static String fileName;
	
	public static void main(String[] args) throws IOException {
		
//		String userLong = "httpfs get /";
//		String userLong = "httpfs get /foo";
		String userLong = "httpfs post /bar";
		
		String host = "localhost";
		int port = 8080;
		String tempFileName = StringUtils.substringAfter(userLong, "/");
		if(tempFileName.equals("")) {
			fileName = tempFileName;
		}
		else {
			fileName = tempFileName + ".txt";
		}
		
		Socket conn = new Socket(host, port);
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))); 	
		
		String tempLong = StringUtils.substringAfter(userLong, " ");
		String httpMethod = StringUtils.substringBefore(tempLong, " ");
		
		if(fileName.equals("")) {
			runDirectory(out, conn);
		}
		else if(httpMethod.equals("get")) {
			runGet(fileName, out, conn);
		}
		else if(httpMethod.equals("post")) {
			runPost(fileName, out, conn);
		}
		
		conn.close();
	}

	private static void runDirectory(PrintWriter out, Socket conn) throws IOException{
		out.println("GET HTTP/1.0");
		out.println();
		out.flush();
		
		// Print the response
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		
		String response;
		while((response = in.readLine()) != null) {
			System.out.println(response);
		}
		
		in.close();
		
	}
	
	private static void runGet(String fileName, PrintWriter out, Socket conn) throws IOException{
		out.println("GET /" + fileName + " HTTP/1.0");
		out.println("Host: localhost");
		out.println();
		out.flush();
			
		// Print the response
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		
		String response;
		while((response = in.readLine()) != null) {
			System.out.println(response);
		}
		
		in.close();
	}
	
	private static void runPost(String fileName, PrintWriter out, Socket conn) throws IOException{
		Date dateNow = new Date();
		out.println("POST /" + fileName + " HTTP/1.0");
		out.println("Host: localhost");
		out.println();
		out.println("Replace content with this body created: " + dateNow.toString());
		out.flush();
			
		// Print the response
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		
		String response;
		while((response = in.readLine()) != null) {
			System.out.println(response);
		}
		
		in.close();
	}

}
