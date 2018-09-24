import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		String hostName = "http://httpbin.org/get?course=networking&assignment=1";
//		int portNumber = 80;
		Socket mySocket;
		
		try {
		   mySocket = new Socket("www.mock-server.com", 80);
		   System.out.println(mySocket.isConnected());
		   
//		   PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		   BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
//		   BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		   
		   String line;
		   while((line = input.readLine()) != null) {
			   System.out.println("Hello");
			   System.out.println(line);
		   }
		   System.out.println("Hello");
		   mySocket.close();
		   System.out.println(mySocket.isConnected());
		   input.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// GET request
//        URLConnection newConn = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(newConn.getInputStream()));
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
//		for (String line; (line = reader.readLine()) != null;) {
//			System.out.println(line);
//		}
   
        // -v command
//		Map <String, List<String>> info = url.openConnection().getHeaderFields();
//		
//		for (Map.Entry<String, List<String>> line : info.entrySet()) {
//			if(line.getKey() != null)
//				System.out.println(line.getKey() + ": " + line.getValue());
//			else
//				System.out.println(line.getValue());
//		}
		
		// -h command
//		Map <String, List<String>> headers = url.openConnection().getHeaderFields();
//				
//		for (Map.Entry<String, List<String>> line : headers.entrySet()) {
//			if(line.getKey() != null)
//				System.out.println("Key: " + line.getKey() + "\tValue: " + line.getValue());
//		}
		
	}

}
