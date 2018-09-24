import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Demo {
	
	public static void main(String[] args) throws Exception {

		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
		
		// GET request
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
//		for (String line; (line = reader.readLine()) != null;) {
//			System.out.println(line);
//		}
   
        // -v command
		Map <String, List<String>> info = url.openConnection().getHeaderFields();
		
		for (Map.Entry<String, List<String>> line : info.entrySet()) {
			if(line.getKey() != null)
				System.out.println(line.getKey() + ": " + line.getValue());
			else
				System.out.println(line.getValue());
		}
		
		// -h command
		Map <String, List<String>> headers = url.openConnection().getHeaderFields();
				
		for (Map.Entry<String, List<String>> line : headers.entrySet()) {
			if(line.getKey() != null)
				System.out.println("Key: " + line.getKey() + "\tValue: " + line.getValue());
		}
		
	}

}
