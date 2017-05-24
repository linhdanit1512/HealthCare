package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {
	public static void main(String[] args) {
		try {
			URL url = new URL("192.068.0.127:8080/HealthCare/rest/doctor/1");
			try {
				HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//				httpConn.setAllowUserInteraction(false);
//				httpConn.setInstanceFollowRedirects(true);
//				httpConn.setRequestMethod("GET");
				httpConn.connect();
				int resCode = httpConn.getResponseCode();
				InputStream in;
				BufferedReader br = null;
				if (resCode == HttpURLConnection.HTTP_OK) {
					in = httpConn.getInputStream();
					br = new BufferedReader(new InputStreamReader(in));

					StringBuilder sb = new StringBuilder();
					String s = null;
					while ((s = br.readLine()) != null) {
						sb.append(s);
						sb.append("\n");
					}
					System.out.println(sb.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
