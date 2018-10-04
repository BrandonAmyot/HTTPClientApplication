
public class httpc {
	
	private static boolean getReq = false;
	private static boolean postReq = false;
	
	public static void parseURL(String urlLong) {
		
		if(urlLong.matches(".*httpc get?.*")) {
			getReq = true;
			
			if(urlLong.matches(".*-v.*")) {
				Get.setVerbose(true);
				System.out.println("verbose"); // remove once working
			}
			if(urlLong.matches(".*-h.*")) {
				Get.setHeaders(true);
				Get.addHeaders(urlLong);
				System.out.println("headers"); // remove once working

				// need to extract key and value
			}
			return;
		}
		else if(urlLong.matches(".*httpc post?.*")) {
			postReq = true;
			
			if(urlLong.matches(".*-v.*")) {
				Post.setVerbose(true);
				System.out.println("verbose"); // remove once working
			}
			if(urlLong.matches(".*-h.*")) {
				Post.setHeaders(true);
				Post.addHeaders(urlLong);
				System.out.println("headers"); // remove once working
				
				// need to extract key and value
			}
			if(urlLong.matches(".*-d.*")) {
				Post.setInlineData(true);
				System.out.println("inlineData"); // remove once working
				
				// need to extract data string value
			}
			if(urlLong.matches(".*-f.*")) {
				Post.setFileName(true);
				System.out.println("fileName"); // remove once working
				
				// need to extract file
			}			
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		String urlLong = "httpc get http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -v http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -v -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1";
		String urlLong = "httpc post -v -h Content-Type:application/json http://httpbin.org/post";

		
		
		parseURL(urlLong);
		
		String urlShort = urlLong.substring(urlLong.lastIndexOf(" ")+1);
		System.out.println(urlShort);
		
		if(getReq) {
			System.out.println("get request executing...\n");
			Get.doGet(urlShort);
		}
		else if(postReq) {
			System.out.println("post request executing...\n");
			Post.doPost(urlShort);
		}
		else {
			System.out.println("Oops something went wrong!");
		}
		
//		URL url = new URL("http://httpbin.org/get?course=networking&assignment=1");
//		String hostName = "http://httpbin.org/get?course=networking&assignment=1";
//		int portNumber = 80;
//		Socket mySocket;
//		
//		try {
//		   mySocket = new Socket("www.posttestserver.com", 80);
//		   System.out.println(mySocket.isConnected());
//		   
//		   PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
//		   
//		   System.out.println(out.toString());
//		   BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
//		   System.out.println("AF"+out.toString());
//		   System.out.println(input.readLine());
//		   BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//		   
//		   String line;
//		   while((line = input.readLine()) != null) {
//			   System.out.println("Hello");
//			   System.out.println(line);
//		   }
//		   System.out.println("Hello");
//		   mySocket.close();
//		   System.out.println(mySocket.isConnected());
//		   input.close();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		
		// GET request
//        URLConnection newConn = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(newConn.getInputStream()));
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
//		for (String line; (line = reader.readLine()) != null;) {
//			System.out.println(line);
//		}
   
        // -v command
//		Map <String, List<String>> info = url.openConnection().getHeaderFields();
//		
//		for (Map.Entry<String, List<String>> line : info.entrySet()) {
//			if(line.getKey() != null)
//				System.out.println(line.getKey() + ": " + line.getValue());
//			else
//				System.out.println(line.getValue());
//		}
		
	}


}
