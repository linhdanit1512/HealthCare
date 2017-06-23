package util;

import java.util.Date;

public class MailUtil {
	public static String getSubject(){
		return "We care for your kid" + new Date();
	}
	
	public static String getTemplateMailConfirmDoctor(){
		return "Đã xác nhận tài khoản của bạn thành công \n Đây chỉ là test gửi mail";
	}
}
