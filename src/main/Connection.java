package main;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import org.jsoup.nodes.Document;

public class Connection implements Response {
	
	private static boolean persistense = true;
	private static org.jsoup.Connection conn;
	private static int timeout = 10;
	private static Response response;
	
	void setTimeout(int sec) {
		timeout = sec;
	}
	
	void setPersistense(boolean b) {
		persistense = b;
	}
	
	public static Response connect(String link) throws IOException, ConnectionTimeout {
		long start, end;
		conn = Jsoup.connect(link);
		start = new Date().getTime();
		while(persistense && conn.execute().statusCode() != 200) {
			conn = Jsoup.connect(link);
			end = new Date().getTime();
			if((end - start) / 1000 >= timeout) {
				throw new ConnectionTimeout();
			}
			System.out.println(conn.response().statusMessage());
		}
		response = conn.response();
		return response;
	}
	
	@Override
	public String cookie(String arg0) {
		// TODO Auto-generated method stub
		return response.cookie(arg0);
	}

	@Override
	public Response cookie(String arg0, String arg1) {
		return response.cookie(arg0, arg1);
	}

	@Override
	public Map<String, String> cookies() {
		// TODO Auto-generated method stub
		return response.cookies();
	}

	@Override
	public boolean hasCookie(String arg0) {
		// TODO Auto-generated method stub
		return response.hasCookie(arg0);
	}

	@Override
	public boolean hasHeader(String arg0) {
		// TODO Auto-generated method stub
		return response.hasHeader(arg0);
	}

	@Override
	public boolean hasHeaderWithValue(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return response.hasHeaderWithValue(arg0, arg1);
	}

	@Override
	public String header(String arg0) {
		// TODO Auto-generated method stub
		return response.header(arg0);
	}

	@Override
	public Response header(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return response.header(arg0, arg1);
	}

	@Override
	public Map<String, String> headers() {
		// TODO Auto-generated method stub
		return response.headers();
	}

	@Override
	public Method method() {
		// TODO Auto-generated method stub
		return response.method();
	}

	@Override
	public Response method(Method arg0) {
		// TODO Auto-generated method stub
		return response.method(arg0);
	}

	@Override
	public Response removeCookie(String arg0) {
		// TODO Auto-generated method stub
		return response.removeCookie(arg0);
	}

	@Override
	public Response removeHeader(String arg0) {
		// TODO Auto-generated method stub
		return response.removeHeader(arg0);
	}

	@Override
	public URL url() {
		// TODO Auto-generated method stub
		return response.url();
	}

	@Override
	public Response url(URL arg0) {
		// TODO Auto-generated method stub
		return response.url(arg0);
	}

	@Override
	public String body() {
		// TODO Auto-generated method stub
		return response.body();
	}

	@Override
	public byte[] bodyAsBytes() {
		// TODO Auto-generated method stub
		return response.bodyAsBytes();
	}

	@Override
	public String charset() {
		// TODO Auto-generated method stub
		return response.charset();
	}

	@Override
	public String contentType() {
		// TODO Auto-generated method stub
		return response.contentType();
	}

	@Override
	public Document parse() throws IOException {
		// TODO Auto-generated method stub
		return response.parse();
	}

	@Override
	public int statusCode() {
		// TODO Auto-generated method stub
		return response.statusCode();
	}

	@Override
	public String statusMessage() {
		// TODO Auto-generated method stub
		return response.statusMessage();
	}


}
