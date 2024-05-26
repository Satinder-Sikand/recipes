package dev.satinder.recipes.user.emails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService{
	private final JavaMailSender javaMailSender;
	private final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
	private final String PASSWORD_RESET_REQUEST = "Reset Password Request";
	@Value("{$spring.mail.verify.host}")
	private String host;
	@Value("{$spring.mail.username}")
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
			message.setText(getEmailMessage(name, token));
			javaMailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage() + "\n\nCould not create email.\n\n");
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
			message.setText(getEmailMessage(name, token));
			javaMailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage() + "\n\nCould not reset password.\n\n");
//			throw new Exception("Could not create email.");
		}
	}
	
	private String getEmailMessage(String name, String token) {
		return "Hello " + name + 
				",\n\n Your new account has been created! To verify your account please click on the link below.\n\n" 
				+ getVerificationUrl(token) + "\n\nWarm Regards\nThe Support Team";
	}
	
	private String getResetPasswordMessage(String name, String token) {
		return "Hello " + name + 
				",\n\n Your account has requested a password reset! To change your password please click on the link below.\n\n" 
				+ getResetPasswordUrl(token) + "\n\nWarm Regards\nThe Support Team";
	}
	
	private String getVerificationUrl(String token) {
		return this.host + "/verify/account?token=" + token;
	}
	
	private String getResetPasswordUrl(String token) {
		return this.host + "/verify/password?token=" + token;
	}
}
