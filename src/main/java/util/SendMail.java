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
	public static String mailServer = "itnonglam84@gmail.com";
	public static String passServer = "itnonglam";

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
			String content = MailUtil.getTemplateMailConfirmDoctor();
			message.setContent(content, "text/html; charset=UTF-8");

			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static boolean sendMail(String to, String content, String subject) {

		Properties properties = new Properties();
		// Setup mail server
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.secure", "ssl");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		// properties.put("mail.debug", "true");
		// properties.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(mailServer));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setContent(content, "text/html; charset=UTF-8");

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", mailServer, passServer);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		String mail = "nhanvuongngocbao@gmail.com";
		try {
			System.out.println(sendMail(mail, MailUtil.forgetPasswordTemplete("123456", "azureyla"), "abdc"));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
