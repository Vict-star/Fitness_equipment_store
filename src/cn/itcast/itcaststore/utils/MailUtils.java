package cn.itcast.itcaststore.utils;
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
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {
	public static void sendMail(String email, String emailMsg)//email：收件人地址，emailMsg：邮件内容
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session，通过邮件服务器发送邮件
		Properties props = new Properties();
		// 设置邮件发送协议为SMTP（只管发送，不用管接收邮件）
		props.setProperty("mail.transport.protocol", "SMTP");  //transport.protocol：邮件传输协议
		// 设置SMTP服务器地址（发送邮箱的服务器）（smtp+qq.com/139.com/126.com）
		props.setProperty("mail.host", "smtp.qq.com");
		// 设置SMTP服务器是否需要用户验证，一定需要验证设置为true
		props.setProperty("mail.smtp.auth", "true");
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1401489294@qq.com", "outpkdqkgskfhigj");//参数1：账号，参数2：密码
			}
		};
		Session session = Session.getInstance(props, auth);//与邮件服务器建立连接
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("1401489294@qq.com")); // 设置发送者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者（第二各参数为数组则发送给多个接收者）
		message.setSubject("用户激活");   //邮件主题
		message.setContent(emailMsg, "text/html;charset=utf-8");  //设置邮件编码
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
}
