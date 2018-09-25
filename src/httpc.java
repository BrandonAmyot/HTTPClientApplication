import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class httpc {

	public static void main(String[] args) {
		
		try {
		Socket mySocket = new Socket("www.mock-server.com", 80);
	    InputStream input = mySocket.getInputStream();
	    PrintWriter pw = new PrintWriter(mySocket.getOutputStream());
	    pw.println("GET / HTTP/1.0");
	    pw.println();
	    pw.flush();
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = input.read(buffer)) != -1) {
	        String output = new String(buffer, 0, read);
	        System.out.print(output);
	        System.out.flush();
	    };
	    mySocket.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
