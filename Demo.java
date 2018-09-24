import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
//		String hostName = "http://httpbin.org/get?course=networking&assignment=1";
//		int portNumber = 80;

		try {
		   Socket echoSocket = new Socket("www.httpbin.org", 80);
		   System.out.println(echoSocket.isConnected());
		   
//		   PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		   BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		   BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));
		   
		   String line;
		   while((line = in.readLine()) != null) {
			   System.out.println(line);
		   }
		   echoSocket.close();
		   in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// GET request
//        URLConnection newConn = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(newConn.getInputStream()));
		
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
