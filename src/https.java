import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class https {

	public static void main(String[] args) throws IOException{
//https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html
		ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            Socket clientSocket = server.accept();
            InputStreamReader isr =  new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();            
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
        }
// ORACLE WEBSITE
//		int portNumber = 8080;
//		while(true) {
//	        try (
//	                ServerSocket serverSocket = new ServerSocket(portNumber);
//	                Socket clientSocket = serverSocket.accept();     
//	                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
//	                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//	            ) {
//	                String inputLine;
//	                while ((inputLine = in.readLine()) != null) {
//	                    out.println(inputLine);
//	                }
//	            } catch (IOException e) {
//	                System.out.println("Exception caught when trying to listen on port "
//	                    + portNumber + " or listening for a connection");
//	                System.out.println(e.getMessage());
//	            }
//		}
	}

}
