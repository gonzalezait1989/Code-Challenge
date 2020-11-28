package com.aitorgonzalez.challenge.controller.experimental;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to show the differences between a Controller and a Rest
 * Controller. A @Controller triggers logic and needs to redirect to a resource
 * that will represent the view. This Controller will not work because there are
 * no static resources to return.
 * 
 * @author aitor.gonzalez
 */
@Controller
public class CountController {

	private int countOperations = 0;

	/**
	 * Browser would not need a CORS header, but this GET method is configured to
	 * report the pre-flight request only to calls from localhost:8080. If it's
	 * called, for example, by client running on 9090 port, Spring Boot would not
	 * return the CORS header and the client will dismiss any result from the call.
	 * 
	 * @return name as parameter.
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/count")
	public String get() {
		countOperations++;
		return String.valueOf(countOperations);
	}

	/**
	 * Method OPTIONS to demostrate that actually the browser calls OPTIONS
	 * operation than anything else to know which operations are available on the
	 * Endpoint or a preflight request, is a mechanism in CORS by the browser to
	 * check if the resource destination is willing to accept the real request or
	 * not
	 * https://dev.to/p0oker/why-is-my-browser-sending-an-options-http-request-instead-of-post-5621
	 * 
	 * @return the number of called operations since the application was started.
	 */
	@RequestMapping(value = "/count", method = RequestMethod.OPTIONS)
	public int options(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,PATCH");
		response.setHeader("Allow", "HEAD,GET,POST,PUT,PATCH,OPTIONS");
		response.setHeader("Access-Control-Allow-Origin", "*");
		countOperations++;
		return countOperations;
	}

	@PatchMapping("/count")
	public String patch() {
		countOperations++;
		return String.valueOf(countOperations);
	}

	@PostMapping("/count")
	public String post() {
		countOperations++;
		return String.valueOf(countOperations);
	}

	@PutMapping("/count")
	public String put() {
		countOperations++;
		return String.valueOf(countOperations);
	}
}
