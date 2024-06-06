package dev.satinder.recipes.user.event;

import java.util.Map;

import dev.satinder.recipes.user.DTO.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
	private User user;
	private UserEventType type;
	private Map<?, ?> data;
}
