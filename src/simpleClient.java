import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class simpleClient {

	private static String fileName;
	
	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int port = 8080;
		fileName = "foo.txt";
		
		Socket conn = new Socket(host, port);
		runClient(conn);
	}

	private static void runClient(Socket conn) throws IOException {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))); 	
			
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
			conn.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
