package pers.jim.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="返回结果")
public class LoginResult {

	@ApiModelProperty("Token")
	private String token;
	
	public LoginResult(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
