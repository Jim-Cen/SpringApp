package pers.jim.app.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice
public class DefaultExceptionHandlerAdvice {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public String  defaultException(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(HttpException.class)
	public String  httpException(HttpException ex,HttpServletResponse response) {
		response.setStatus(ex.httpStatus);
		return ex.getMessage();
	}
}