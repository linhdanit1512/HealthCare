package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import DAO.DoctorDAO;
import entity.Schedules;

public class TestWebService {
	public static void main(String[] args) {
		TestWebService test = new TestWebService();
		System.out.println("nguyentrungtinh3009995@gmai.com".length());
		test.registerDoctor();
	}

	/**
	 * mấy thư viện này trong android jre có rồi, vào thư mục cài đặt Android
	 * Studio /lib để copy 3 file http vào, viết trong Android Studio thì không
	 * cần copy đâu =))
	 */
	public void registerDoctor() {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://healthcare21617.azurewebsites.net/rest/doctor/register");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("userName", "abcd151515"));
			list.add(new BasicNameValuePair("password", "Test123456"));
			list.add(new BasicNameValuePair("name", "Nguyen Trung Tinh"));
			list.add(new BasicNameValuePair("specialty", "Tim"));
			list.add(new BasicNameValuePair("birthDate", "156494646"));
			list.add(new BasicNameValuePair("degree", "Đại học"));
			list.add(new BasicNameValuePair("experience", "5"));
			list.add(new BasicNameValuePair("email", "14130344@st.hcmuaf.edu.vn"));
			list.add(new BasicNameValuePair("doctorAddress", "Tân Đông Hiệp - Dĩ An - Bình Dương"));
			list.add(new BasicNameValuePair("phone", "03556822644"));
			list.add(new BasicNameValuePair("passport", "203106803"));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));

			System.out.println("List: " + list.toString());
			HttpResponse httpResponse = httpClient.execute(httpPost);
			s = readResponse(httpResponse);
			System.out.println(s);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void forgetPassword() {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPost = new HttpPut("http://localhost:8080/HealthCareService/rest/doctor/forgetpassword");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("email", "14130185@st.hcmuaf.edu.vn"));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			System.out.println("List: " + list.toString());
			HttpResponse httpResponse = httpClient.execute(httpPost);
			s = readResponse(httpResponse);
			System.out.println(s);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Complete");
	}

	public void regisSchedule() {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://healthcare21617.azurewebsites.net/rest/schedules/registry/list");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("idDoctor", "5"));
			list.add(new BasicNameValuePair("scheduleList",
					Schedules.toJsonList(DoctorDAO.getDoctor(2).getScheduleses())));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			System.out.println("List: " + list.toString());
			HttpResponse httpResponse = httpClient.execute(httpPost);
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
