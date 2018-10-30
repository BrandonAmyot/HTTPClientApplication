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
		
		String userPath = "httpfs get -p 80 -d ../directory/foo.txt";
		
		String tempPort = StringUtils.substringAfter(userPath, "-p ");
		String stringPort = StringUtils.substringBefore(tempPort, " ");
		int port = Integer.parseInt(stringPort);
		
		
		//FIX THIS, make it so that only ../directory is used
		String tempPath = StringUtils.substringAfter(userPath, "-d ");
		int last = tempPath.lastIndexOf("/");
		String path = StringUtils.substringBefore(tempPath, Character.toString(tempPath.charAt(last)));
		
		System.out.println(path);
		
		try {
		ServerSocket server = new ServerSocket(port);
        System.out.println("Listening for connection on port " + server.getLocalPort() + "...");
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
            System.out.println("File " + listOfFiles[i].getName());
          } else if (listOfFiles[i].isDirectory()) {
            System.out.println("Directory " + listOfFiles[i].getName());
          }
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
