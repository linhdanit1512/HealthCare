package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static String mailServer = "nguyentued@gmail.com";
	public static String passServer = "nguyentuesdd";

	public static void sendMail(String mailFrom) {
		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.port", "465");
		pro.put("mail.smtp.socketFactory.class", javax.net.ssl.SSLSocketFactory.class.getName());

		Session session = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailServer, passServer);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailFrom));
			message.setSubject(MailUtil.getSubject());
			String content  = MailUtil.getTemplateMailConfirmDoctor();
			message.setContent(content, "text/html");
			
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String mail = "14130185@st.hcmuaf.edu.vn";
		try {
			sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
