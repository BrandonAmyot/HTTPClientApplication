import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
		final int portNumber = 80;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's you name?");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();

			pw.println("Hello, " + str);
			pw.close();
			socket.close();

			System.out.println("Just said hello to:" + str);
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
