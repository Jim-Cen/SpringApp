package pers.jim.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pers.jim.app.model.Program;

@RestController

public class TestController {
	
	@PostMapping(value = "/test",  produces = "application/json")
	@ApiOperation(value = "检验密码是否正确", response = ProgramList.class, responseContainer = "List")
	@ApiResponses({
	        @ApiResponse(code = 200, message = "密码正确"),
	})
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "id", value = "编号",paramType = "body", 
			  allowMultiple=true, dataType = "Program", required = true),
	})
	public ProgramList test() {
		return new ProgramList();
	}
}
