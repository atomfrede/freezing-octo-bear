package de.atomfrede.mate.application.rest;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestHeader("Authorization") String basicAuth,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println(basicAuth);
		Charset w1252 = Charset.forName("Windows-1252"); // Superset of
															// ISO-8859-1
		Charset utf8 = Charset.forName("UTF-8");

		String coding = new String(basicAuth.split(" ")[1].getBytes(w1252), utf8);
		System.out.println("Coding "+coding);
		String basic = new String(Base64.decodeBase64(basicAuth.split(" ")[1]));
		System.out.println(basic);
		return "üöä";
	}

}
