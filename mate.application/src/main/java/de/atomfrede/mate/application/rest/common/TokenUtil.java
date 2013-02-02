package de.atomfrede.mate.application.rest.common;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

@Component
public class TokenUtil {

	public static final String USER_KEY = "user";
	public static final String TOKEN_KEY = "token";

	@Autowired
	UserService userService;

	protected User checkLogin(String username, String password)
			throws LoginFailedException {
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new LoginFailedException();
		}
		if (user.isPasswordPlain(password)) {
			return user;
		}
		throw new LoginFailedException();
	}

	public HashMap<String, Object> generateAccessToken(String basicAuth)
			throws LoginFailedException, IllegalArgumentException {
		HashMap<String, Object> returnValues = new HashMap<>();

		// First Split into Basic user:password
		String[] splitted = basicAuth.split(" ");
		if (splitted.length != 2) {
			throw new IllegalArgumentException(
					"The given string does not contain a valid basic auth header.");
		}
		String userAndPassword = splitted[1];

		userAndPassword = new String(Base64.decodeBase64(userAndPassword));

		String[] userAndPasswordArray = userAndPassword.split(":");

		if (userAndPasswordArray.length != 2) {
			throw new IllegalArgumentException(
					"The given String does not contain a valid basic auth header.");
		}

		User user = checkLogin(userAndPasswordArray[0], userAndPasswordArray[1]);
		String token = null;
		if (user != null) {
			token = UUID.randomUUID().toString();
			returnValues.put(USER_KEY, user);
			returnValues.put(TOKEN_KEY, token);
			return returnValues;
		}
		
		throw new LoginFailedException();
	}

}
