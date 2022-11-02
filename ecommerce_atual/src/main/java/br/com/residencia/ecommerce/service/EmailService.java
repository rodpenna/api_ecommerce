package br.com.residencia.ecommerce.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Value("${mail.from}")
	private String mailFrom;
	@Value("${spring.mail.host}")
	private String emailServerHost;
	@Value("${spring.mail.port}")
	private Integer emailServerPort;
	@Value("${spring.mail.username}")
	private String emailServerUserName;
	@Value("${spring.mail.password}")
	private String emailServerPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties props = new Properties();

		mailSender.setHost(emailServerHost);
		mailSender.setPort(emailServerPort);
		mailSender.setUsername(emailServerUserName);
		mailSender.setPassword(emailServerPassword);

		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);

		mailSender.setJavaMailProperties(props);

		return mailSender;
	}

	public EmailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
	}

	public void sendEmail(String destinatario, String assunto, String mensagem) {
		var mailMessage = new SimpleMailMessage();

		mailMessage.setTo(destinatario);
		mailMessage.setSubject(assunto);
		mailMessage.setText(mensagem);
		mailMessage.setFrom(mailFrom);
		try {
			emailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println("ocorreu um erro" + e);
		}
	}

}