package cpr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@PreAuthorize("hasAnyRole('USER')")
@Controller
public class MainController {
		
	@GetMapping("/")
	public String home(){
		return "index";
	}
}
