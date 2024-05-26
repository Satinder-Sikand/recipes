package dev.satinder.recipes.user.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.satinder.recipes.user.emails.EmailService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEventListener {
	private final EmailService emailService;
	
	@EventListener
	public void onUserEvent(UserEvent event) {
		switch (event.getType()) {
		case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String)event.getData().get("key"));
		case RESETPASSWORD -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String)event.getData().get("key"));
		default -> {}
		}
	}
}
