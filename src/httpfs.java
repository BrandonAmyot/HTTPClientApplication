import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

public class httpfs {
	public static void main(String[] args) {
		
		String serverDetails = "httpfs -p 80 -d ../directory";
		
		String tempPort = StringUtils.substringAfter(serverDetails, "-p ");
		String stringPort = StringUtils.substringBefore(tempPort, " ");
		int port = Integer.parseInt(stringPort);
		
		String path = serverDetails.substring(serverDetails.lastIndexOf(" ")+1);
		
		try {
		ServerSocket server = new ServerSocket(port);
        System.out.println("Listening for connection on port " + server.getLocalPort() + "...");
        System.out.println("Files in directory " + path);
        
        //Read the files in a directory (https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder)
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println("File " + listOfFiles[i].getName());
        }
        
        
        
        
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
