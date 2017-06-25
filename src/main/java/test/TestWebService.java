package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
/**mấy thư viện này trong android jre có rồi, vào thư mục cài đặt Android Studio /lib để copy 3 file http vào*/
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestWebService {
	public static void main(String[] args) {
		TestWebService test = new TestWebService();
		test.PostData();
	}

	public void PostData() {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://localhost:8081/HealthCareService/rest/doctor/register");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("userName", "linhdanTest1"));
			list.add(new BasicNameValuePair("password", "Test123456"));
			list.add(new BasicNameValuePair("name", "Pham Trang Linh Dan"));
			list.add(new BasicNameValuePair("specialty", "Truyền Nhiễm"));
			list.add(new BasicNameValuePair("degree", "12/12"));
			list.add(new BasicNameValuePair("experience", "3"));
			list.add(new BasicNameValuePair("email", "wintersoul1212@gmail.com"));
			list.add(new BasicNameValuePair("doctorAddress", "Tân Đông Hiệp - Dĩ An - Bình Dương"));
			list.add(new BasicNameValuePair("phone", "01284871662"));
			list.add(new BasicNameValuePair("passport", "251036900801"));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			System.out.println("List: "+list.toString());
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			s = readResponse(httpResponse);
			System.out.println(s);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String readResponse(HttpResponse res) {
		InputStream is = null;
		String return_text = "";
		try {
			is = res.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return_text = sb.toString();
		} catch (Exception e) {

		}
		return return_text;

	}
}
