package pers.jim.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import pers.jim.app.business.AuthTokenService;
import pers.jim.app.exception.HttpException;


@Aspect
@Component
public class AuthTokenAOP {
    
	@Autowired
    AuthTokenService authTokenService;
    
    @Pointcut("@annotation(pers.jim.app.annotation.AuthToken)")  
    public void authTokenCheck(){}
    
    @Before("authTokenCheck()")
    public void before(JoinPoint joinPoint) throws Throwable{
    	try {
    	      Object[] args= joinPoint.getArgs();
    	      for ( Object arg : args ) {
    	    	  if ( arg.getClass() == HttpHeaders.class ) {
    	    		  HttpHeaders header = (HttpHeaders)arg;
    	    		  String signture = header.getFirst("signture");
    	    		  String userName = header.getFirst("clientId");
    	    		   String timestamp = header.getFirst("timestamp");
    	    		  authTokenService.authTokenCheck(userName, timestamp,signture);
    	    		  return ;
    	    	  }
    	      }	
    	      throw new HttpException(412,"Fail to Auth");
    	}catch (Throwable ex) {
    		throw new HttpException(412,ex.getMessage());
    	}

    }
}
