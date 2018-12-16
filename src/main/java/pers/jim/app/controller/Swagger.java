package pers.jim.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore()
public class Swagger {
	@GetMapping("/swagger")
	public void index(HttpServletResponse resp) throws Exception{
		resp.sendRedirect("static/swagger/index.html");	
	}
}
