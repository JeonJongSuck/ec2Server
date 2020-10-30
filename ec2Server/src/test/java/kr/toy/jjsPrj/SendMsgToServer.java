package kr.toy.jjsPrj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.toy.jjsPrj.product.vo.ProductVO;

// 따로 테스트 프레임워크 사용하지 않고 static main구문으로 돌림.
public class SendMsgToServer {
	static {
		// CA 설정 부분
	    // https://doomphantom.wordpress.com/2014/05/03/java-security-cert-certificateexception-no-name-matching-localhost-found/
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){
	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("ec2-3-129-69-97.us-east-2.compute.amazonaws.com")) {
	                return true;
	            }
	            return false;
	        }
	    });
		System.setProperty("javax.net.ssl.trustStore", "C:/Temp/keystore/jssecacerts");
	}
	
//	private static String ipAddr = "https://ec2-3-129-69-97.us-east-2.compute.amazonaws.com/js/js/rest/";
	private static String ipAddr = "https://localhost/js/rest/";
	private static String methodNm = "selectAll";
	private static String rAddr = ipAddr  + methodNm;;
	private HttpsURLConnection conn;
	private ObjectMapper om = new ObjectMapper();

	public SendMsgToServer() {
		ProductVO vo = new ProductVO();
//		vo.setbName("asdaf");
//		vo.setDescription("dasdfa");
//		vo.setDistributeCompany("zxcvds");
//		vo.setMakeCompany("eqwds");
//		vo.setName("dddd");
//		vo.setPrice(1231245123);
		vo.setValidAppKey("AAAAAAAAAA");
		vo.setValidName("nameA");
		
		post(vo);
	}
	
	public static void main(String[] args) {
		new SendMsgToServer();
	}
	
	// POST method로 다른 것들 호출
	private void post(ProductVO vo) {
		try {
			conn = (HttpsURLConnection)(new URL(rAddr).openConnection());

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			OutputStream os = conn.getOutputStream();
			os.write(om.writeValueAsBytes(vo));
			os.flush();

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			char[] cbuf = new char[2000];
			isr.read(cbuf);
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
			System.out.println(cbuf);
		} catch (IOException e) {
			e.printStackTrace();
			InputStreamReader isr = new InputStreamReader(conn.getErrorStream());
			char[] cbuf = new char[2000];
			try {
				isr.read(cbuf);
				System.out.println(cbuf);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("abcde");
		} finally {
			conn.disconnect();
		}
	}

	// 테스트용
	private void test(ProductVO vo){
		ObjectMapper om = new ObjectMapper();
		try {
			String str = om.writeValueAsString(vo);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
