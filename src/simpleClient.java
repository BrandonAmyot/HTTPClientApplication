import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class simpleClient {

	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int port = 8080;
		
		SocketAddress conn = new InetSocketAddress(host, port);
		runClient(conn);
	}

	private static void runClient(SocketAddress conn) throws IOException {
		try (SocketChannel socket = SocketChannel.open()) {
			socket.connect(conn);
			if(socket.isConnected())
				System.out.println("Connected.");
		}
		
	}

}
