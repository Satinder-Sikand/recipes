package dev.satinder.recipes.user.emails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	private final JavaMailSender javaMailSender;
	private final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
	private final String PASSWORD_RESET_REQUEST = "Reset Password Request";
	@Value("${spring.mail.verify.host}")
	private String host;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	@Async
	public void sendNewAccountEmail(String name, String toEmail, String token) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			message.setTo(toEmail);
			message.setFrom(fromEmail);
			message.setText(getEmailMessage(name, token, toEmail));
			System.out.println(fromEmail);

			javaMailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() + "\n\nCould not create email.\n\n");
		}
	}
	
	@Override
	@Async
	public void sendPasswordResetEmail(String name, String toEmail, String token) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(PASSWORD_RESET_REQUEST);
			message.setTo(toEmail);
			message.setFrom(fromEmail);

			message.setText(getEmailMessage(name, token, toEmail));
			javaMailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() + "\n\nCould not reset password.\n\n");
//			throw new Exception("Could not create email.");
		}
	}
	
	private String getEmailMessage(String name, String token, String email) {
		return "Hello " + name + 
				",\n\n Your new account has been created! To verify your account please click on the link below.\n\n" 
				+ getVerificationUrl(token, email) + "\n\nWarm Regards\nThe Support Team";
	}
	
	private String getResetPasswordMessage(String name, String token, String email) {
		return "Hello " + name + 
				",\n\n Your account has requested a password reset! To change your password please click on the link below.\n\n" 
				+ getResetPasswordUrl(token, email) + "\n\nWarm Regards\nThe Support Team";
	}
	
	private String getVerificationUrl(String token, String email) {
		return this.host + "user/verify/account?token=" + token + "&email=" + email;
	}
	
	private String getResetPasswordUrl(String token, String email) {
		return this.host + "user/verify/password?token=" + token + "&email=" + email;
	}
}
