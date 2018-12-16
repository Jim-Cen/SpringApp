package pers.jim.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import pers.jim.app.business.UserService;
import pers.jim.app.exception.HttpException;
import pers.jim.app.model.LoginResult;

@RestController
@Api(tags = {"登录"})
@RequestMapping(value = "/apis/login",produces = "application/json")
public class Login {
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/user")
	@ApiOperation(value = "检验密码是否正确",response = LoginResult.class)
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query", example = "Jim", required = true),
	  @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query", example = "1", required = true)
	})
	public Object getUser(@RequestParam String name,
						  @RequestParam String password) throws HttpException {
		 String token = userService.checkPassword(name, password) ;
	     if(token != null) {
	    	 return new LoginResult(token);
	     }else {
	    	 throw new HttpException(412,"密码/账号不正确");
	     }
    }
}
