import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
        
            while (true) {
                Socket clientSocket = server.accept();
                InputStreamReader input =  new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(input);
                String line = reader.readLine();
                
                // Prepare response
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                
                String response = "HTTP/1.0 200 OK\r\n";   
                String method = StringUtils.substringBefore(line, " /");
                
                if(method.equalsIgnoreCase("get")) {
                	doGet(line, out, reader, path, response);
                }
                else if(method.equalsIgnoreCase("post")) {
                	doPost(line, out, reader, path, response);
                }
                
                clientSocket.close();
            }
        }
        catch (IOException e) {
        	System.out.println(e);
        }
	}
	
	private static void doGet(String line, PrintWriter out, BufferedReader reader, String path, String response) throws IOException{
		
        String tempFileName = StringUtils.substringAfter(line, " /");
		String fileName = StringUtils.substringBefore(tempFileName, " ");
		System.out.println(fileName);
        if(fileName.equals("")) {
        	System.out.println("hello");
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                 response += (listOfFiles[i].getName()) + "\n"; 
            }
        }
        else {
        	try {
        		File inputFile = new File(path + "/" + fileName);
    			if(!inputFile.exists()) {      
    				response = "HTTP/1.0 404: The file " + fileName + " not found.\r\n";
    			}
    			
    			BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
    			while((line = fileReader.readLine()) != null) {
    				response += line;				
    			}
    			fileReader.close();
    		}
    		catch(IOException e) {
    			System.out.println("File was not found");
    		}
        }
        
        // send response
        out.println(response);
        
        out.flush();
	}
	
	private static void doPost(String line, PrintWriter out, BufferedReader reader, String path, String response) throws IOException{
        String tempFileName = StringUtils.substringAfter(line, " /");
        String fileName = StringUtils.substringBefore(tempFileName, " ");
		
		// prepare file for output
		File outputFile = new File(path + "/" + fileName);
		outputFile.getParentFile().mkdirs();
		outputFile.createNewFile();

		PrintStream outputWriter = new PrintStream(new FileOutputStream(outputFile, false));
		System.setOut(outputWriter);
		
		boolean isBody = false;
		while((line = reader.readLine()) != null){
			if(line.equals("")) {
				isBody = true;
			}
			else if(isBody) {
				System.out.println(line);
			}	
		}
		out.println(response);
		
		out.flush();
	}
}
