import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class https {

	public static void main(String[] args){
		try {
		ServerSocket server = new ServerSocket(80);
        System.out.println("Listening for connection on port " + server.getLocalPort() + "...");
            while (true) {
                Socket clientSocket = server.accept();
                InputStreamReader input =  new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(input);
                String line = reader.readLine();
                
                // Prepare response
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                
                String response = "HTTP/1.0 200 OK\r\n";
                 
                while (!line.isEmpty()) {
                    System.out.println(line);
                    response += line + "\n";
                    line = reader.readLine();
                }
                // send response
                out.println(response);
                
                out.flush();
                clientSocket.close();
            }
        }
        catch (IOException e) {
        	System.out.println(e);
        }
	}
}
