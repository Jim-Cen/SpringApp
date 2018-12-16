package pers.jim.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.jim.app.annotation.AuthToken;
import pers.jim.app.business.UserService;
import pers.jim.app.exception.HttpException;

//GET/POST -- @RequestParam String name
//POST --- @RequestBody Map<String, String> params 
//header--@RequestHeader(value="User-Agent", defaultValue = "0")  String userAgent 
//		  @RequestHeader HttpHeaders headers headers.getFirst("User-Agent")
//path--@PathVariable

//@ModelAttribute
//@RequestMapping(value="/owners/{ownerId}/pets/{petId}/edit")  
//public String processSubmit(@ModelAttribute Pet pet)

//@SessionAttributes
//@SessionAttributes("pet")  
//public class EditPetForm {   
//}  

//@CookieValue
//public void displayHeaderInfo(@CookieValue("JSESSIONID") String cookie) 

//@RequestHeader
//public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding)

//@RequestMapping(value = {"/modifyGet.do","/modifyGet1.do"}, 
//				  method={RequestMethod.POST, RequestMethod.GET},
//				  consumes={"application/json"}, produces={"application/json"}, 
//				  params={"name=mike","pwd=123456"},headers={"a=1"}) 

@RestController
public class MockController {
	
	@RequestMapping("/test")
	//@AuthToken(type="TokenTest")
    public Object getUser(String name,
    						String password,
    						@RequestHeader HttpHeaders headers) throws HttpException {
		throw new HttpException(1001,"Try");
//		try {
//		return true;
//	        //return new ReturnObj(true,refMapper.getRef("program",null,new String[] {"program_id"}));
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
    }
}
