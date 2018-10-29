import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.lang3.StringUtils;

public class httpc {
	
	private static boolean getReq = false;
	private static boolean postReq = false;
	private static boolean isTextOut = false;
	private static String outputFileName;
	
	public static void parseURL(String urlLong) {
		
		if(urlLong.matches(".*httpc get?.*")) {
			getReq = true;
			
			if(urlLong.matches(".*-o.*")) {
				isTextOut = true;
				String temp = StringUtils.substringAfter(urlLong, "-o ");
				outputFileName = StringUtils.substringBefore(temp, " ");
			}
			if(urlLong.matches(".*-v.*")) {
				Get.setVerbose(true);
				System.out.println("verbose");
			}
			if(urlLong.matches(".*-h.*")) {
				Get.setHeaders(true);
				Get.addHeaders(urlLong);
			}
			return;
		}
		else if(urlLong.matches(".*httpc post?.*")) {
			postReq = true;
			
			if(urlLong.matches(".*-o.*")) {
				isTextOut = true;
				String temp = StringUtils.substringAfter(urlLong, "-o ");
				outputFileName = StringUtils.substringBefore(temp, " ");
			}
			if(urlLong.matches(".*-v.*")) {
				Post.setVerbose(true);
			}
			if(urlLong.matches(".*-h.*")) {
				Post.setHeaders(true);
				Post.addHeaders(urlLong);
			}
			if(urlLong.matches(".*-d.*")) {
				Post.setInlineData(true);
				Post.addData(urlLong);
			}
			if(urlLong.matches(".*-f.*")) {
				Post.setFileName(true);
				Post.addFile(urlLong);
				System.out.println("fileName"); // remove once working
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		String urlLong = "httpc get http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -v http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc get -v -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1";
//		String urlLong = "httpc post http://httpbin.org/post";
//		String urlLong = "httpc post -v -h Content-Type:application/json http://httpbin.org/post";
//		String urlLong = "httpc post -h Content-Type:application/json http://httpbin.org/post";
//		String urlLong = "httpc post -h Content-Type:application/json --d'{\"Assignment\": 1}' http://httpbin.org/post";
//		String urlLong = "httpc post -v -h Content-Type:application/json --d'{\"Assignment\": 1}' http://httpbin.org/post";
		
		// bonus textfile output
//		String urlLong = "httpc get -v -o hello.txt http://httpbin.org/get?course=networking&assignment=1";

		String urlLong = "httpc get -v -h Content-Type:application/json Content-Type:amma http://localhost:80/";
		
		parseURL(urlLong);
		if(isTextOut) {
			try {
				File outputFile = new File("" + outputFileName);
				if(!outputFile.exists())
					outputFile.createNewFile();
				
				PrintStream outputWriter = new PrintStream(new FileOutputStream(outputFileName, false));
				System.setOut(outputWriter);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
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
		System.out.println("Program terminated normally");
	}
}
