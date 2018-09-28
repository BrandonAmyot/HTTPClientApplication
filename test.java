import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class test {

	public static void main(String[] args) {
		try {	
			URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
			
			String hostName = url.getHost();
			int port = 80;
			
			Socket mySocket = new Socket(hostName, port);
		   
			PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
		   
			System.out.println(out.toString());
			BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			System.out.println(input.readLine());
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		   
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
	}

}
