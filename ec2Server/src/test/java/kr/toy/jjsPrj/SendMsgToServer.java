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
		post();
	}
	
	public static void main(String[] args) {
		new SendMsgToServer();
	}
	
	// GET method로 selectAll 호출
	private void get() {
		try {
			conn = (HttpsURLConnection)(new URL(ipAddr + "selectAll").openConnection());
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
//			OutputStream os = conn.getOutputStream();
//			VO vo = new VO("a","name","description","makeCompany","distributeCompany",10);
//			os.write(vo.toString().getBytes("UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String inputLine = null;
			StringBuffer outResult = new StringBuffer();
			while((inputLine = in.readLine()) != null)
				outResult.append(inputLine);
			System.out.println(outResult.toString());
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// POST method로 다른 것들 호출
	private void post() {
		try {
			conn = (HttpsURLConnection)(new URL(rAddr).openConnection());

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			OutputStream os = conn.getOutputStream();
			ProductVO vo = new ProductVO();
			vo.setbName("asdaf");
			vo.setDescription("dasdfa");
			vo.setDistributeCompany("zxcvds");
			vo.setMakeCompany("eqwds");
			vo.setName("dddd");
			vo.setPrice(1231245123);
			vo.setValidAppKey("AAAAAAAAAA");
			vo.setValidName("nameA");
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
	private void test(){
		ProductVO vo = new ProductVO();
		vo.setbName("ccc");
		vo.setDescription("d");
		vo.setDistributeCompany("dd");
		vo.setMakeCompany("ddd");
		vo.setName("dddd");
		vo.setPrice(500);
		ObjectMapper om = new ObjectMapper();
		try {
			String str = om.writeValueAsString(vo);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
