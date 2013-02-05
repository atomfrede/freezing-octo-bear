package de.atomfrede.mate.application.rest.bottle;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.atomfrede.mate.application.rest.common.LoginFailedException;
import de.atomfrede.mate.application.rest.common.TokenUtil;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.bottle.BottleService;

@Controller
public class BottleController {

	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	BottleService bottleService;
	
	@ResponseBody
	@RequestMapping(value = "/bottle/count", method = RequestMethod.GET)
	public int getAvailableMates(@RequestHeader("Authorization") String basicAuth,
			HttpServletRequest request, HttpServletResponse response){
		
		try {
			HashMap<String, Object> loginResult = tokenUtil
					.generateAccessToken(basicAuth);

			User user = (User) loginResult.get(TokenUtil.USER_KEY);
			if (user != null) {
				return bottleService.getNumberOfNotConsumedBottles();
			}
			throw new LoginFailedException();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
}
