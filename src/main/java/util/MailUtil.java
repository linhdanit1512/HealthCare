package util;

import java.util.Date;

public class MailUtil {

	public static String forgetPasswordTemplete(String pass, String username) {
		StringBuilder s = new StringBuilder();
		Date date = new Date(System.currentTimeMillis());
		String time = date.toString();
		s.append("<h3><strong>LẤY LẠI MẬT KHẨU THÀNH CÔNG</strong></h3><br><br>");
		s.append("<div>Bạn đã yêu cầu lấy lại mật khẩu vào lúc: " + time + "(" + date.toGMTString() + ")<br>");
		s.append("Mật khẩu tài khoản " + username + " của bạn được đổi thành <strong>" + pass + "</strong><br>");
		s.append(
				"Mật khẩu chỉ có hiệu lực trong vòng 20 phút, nếu bạn không đăng nhập trong thời gian 20 phút, hệ thống sẽ khôi phục lại mật khẩu như trước<br>");
		s.append("Cảm ơn bạn đã đồng hành cùng chúng tôi trong thời gian qua!<br></div>");
		s.append("<hr><br>");
		s.append("We care for your kid");
		return s.toString();
	}

	public static String getSubject() {
		return "[We care for your kid " + new Date()+"]";
	}

	public static String getTemplateMailConfirmDoctor() {
		return "Đã xác nhận tài khoản của bạn thành công \n Đây chỉ là test gửi mail";
	}
}
