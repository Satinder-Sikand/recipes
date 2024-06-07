package dev.satinder.recipes.user;

import java.util.List;
import java.util.Map;

import dev.satinder.recipes.user.DTO.User;
import dev.satinder.recipes.user.DTO.UserProfileUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register/user/admin")
	public ResponseEntity<User> createUserAdmin(@RequestBody Map<String, String> payload) {
		System.out.println("Create Admin");
		User user  = userService.createUserAdmin(payload.get("email"), payload.get("password"), payload.get("firstname"), payload.get("lastname"));

		return new ResponseEntity<User>(user, user==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

	@PostMapping("/register/user")
	public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {
		System.out.println("Create User");
		User user  = userService.createUser(payload.get("email"), payload.get("password"), payload.get("firstname"), payload.get("lastname"));
		return new ResponseEntity<User>(user, user==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

	@GetMapping("/verify/account")
	public ResponseEntity<?> verifyUser(@RequestParam String token, @RequestParam String email) {
		return new ResponseEntity<>(userService.verifyUser(token, email));
	}

	@PostMapping("/update/{email}")
	public ResponseEntity<User> createUser(@PathVariable String email, @RequestBody UserProfileUpdateRequest userUpdate) {
		System.out.println("Update User");
		User user = userService.updateUserProfile(email, userUpdate);
		return new ResponseEntity<User>(user, user==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
}
