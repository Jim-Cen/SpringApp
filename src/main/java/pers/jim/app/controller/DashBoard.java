package pers.jim.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import pers.jim.app.annotation.AuthToken;
import pers.jim.app.mapper.ProgramMapper;
import pers.jim.app.model.Program;

@RestController
@Api(tags = {"控制界面"})
public class DashBoard {
	@Autowired
	ProgramMapper programMapper;   
	
	@PostMapping(value = "/apis/program/get",produces = "application/json")
	@ApiOperation(value = "获取程序列表",response = Program.class, responseContainer = "List")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "clientId", value = "用户名", dataType = "string", paramType = "header", example = "Jim", required = true),
		  @ApiImplicitParam(name = "signture", value = "数字签名", dataType = "string", paramType = "header", example = "4cfb486905c3c26e543941c388c12101", required = true),
		  @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "int", paramType = "header", example = "1543766549326", required = true)
		})
	@AuthToken //Token验证
	public Object getUser(@RequestHeader HttpHeaders headers) {
			return programMapper.getProgram();
	}
}
