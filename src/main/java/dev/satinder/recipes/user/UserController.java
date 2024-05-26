package dev.satinder.recipes.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> temp = new ArrayList<User>();
		return new ResponseEntity<List<User>>(temp, HttpStatus.OK);
	}
	
	@PostMapping("/register/user/admin")
	public ResponseEntity<User> createUserAdmin(@RequestBody Map<String, String> payload) {
		System.out.println("Create Admin");
		User user  = userService.createUser(payload.get("email"), payload.get("password"), payload.get("firstname"), payload.get("lastname"));

		return new ResponseEntity<User>(user, user==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

	@PostMapping("/register/user")
	public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {
		System.out.println("Create User");
		User user  = userService.createUser(payload.get("email"), payload.get("password"), payload.get("firstname"), payload.get("lastname"));
		return new ResponseEntity<User>(user, user==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
}
