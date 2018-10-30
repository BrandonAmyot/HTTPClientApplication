import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

public class httpfs {
	public static void main(String[] args) {
		
		String serverDetails = "httpfs -p 8080 -d directory";
		
		String tempPort = StringUtils.substringAfter(serverDetails, "-p ");
		String stringPort = StringUtils.substringBefore(tempPort, " ");
		int port = Integer.parseInt(stringPort);
		
		String path = serverDetails.substring(serverDetails.lastIndexOf(" ")+1);
		
		try {
			ServerSocket server = new ServerSocket(port);
	        System.out.println("Listening for connection on port " + server.getLocalPort() + "...");
	        System.out.println("Files in directory " + path);
	        

        
            while (true) {
                Socket clientSocket = server.accept();
                InputStreamReader input =  new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(input);
                
                // Prepare response
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                
                String response = "HTTP/1.0 200 OK\r\n";
                
    	        //Read the files in a directory (https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder)

//                doDirectory(out, path, response);
//                doGet(out, reader, path, response);
                doPost();
                
                clientSocket.close();
            }
        }
        catch (IOException e) {
        	System.out.println(e);
        }
	}
	
	private static void doDirectory(PrintWriter out, String path, String response) throws IOException{
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
             response += (listOfFiles[i].getName()) + "\n"; 
        }
        
        out.println(response);
	}
	
	private static void doGet(PrintWriter out, BufferedReader reader, String path, String response) throws IOException{
		String line = reader.readLine();
        String tempFileName = StringUtils.substringAfter(line, " /");
		String fileName = StringUtils.substringBefore(tempFileName, " ");
        if(fileName != null) {
        	try {
        		File inputFile = new File(path + "/" + fileName);
    			if(!inputFile.exists()) {            				
    				response = "HTTP/1.0 404: The file " + fileName + " not found.\r\n";
//    				out.println(response);
    			}
    			
    			BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
    			//BufferedReader fileReader = new BufferedReader(new FileReader("../directory/foo.txt"));
    			while((line = fileReader.readLine()) != null) {
    				response += line;				
    			}
    			fileReader.close();
    		}
    		catch(IOException e) {
    			e.printStackTrace();
    		}
        }
        else {
        	response = "HTTP/1.0 404: The file " + fileName + " not found.\r\n";
        	while (!line.isEmpty()) {
        		System.out.println(line);
        		response += line + "\n";
        		line = reader.readLine();
        	}
        }
        
        // send response
        out.println(response);
        
        out.flush();
	}
	
	private static void doPost() {
		
	}
}
