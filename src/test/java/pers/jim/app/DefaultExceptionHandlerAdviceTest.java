package pers.jim.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pers.jim.app.exception.HttpException;
 
//@RestControllerAdvice
public class DefaultExceptionHandlerAdviceTest {
	
	   /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * https://www.jb51.net/article/117887.htm
     * https://blog.csdn.net/Mynewclass/article/details/79086372
     * https://blog.csdn.net/gaojp008/article/details/80583301
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        DateFormat dateFormat1 = new SimpleDateFormat("d-MM-yyyy");  
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat1, true);  
        DateFormat dateFormat2 = new SimpleDateFormat("MMM d, YYYY");  
        CustomDateEditor shipDateEditor = new CustomDateEditor(dateFormat2, true);  
        binder.registerCustomEditor(Date.class, "orderDate", orderDateEditor);  
        binder.registerCustomEditor(Date.class, "shipDate", shipDateEditor);  
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * https://blog.csdn.net/lovesomnus/article/details/78873089
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

	

	@ExceptionHandler(HttpException.class)
	public String  httpException(HttpException ex, HttpServletResponse response) {
		response.setStatus(ex.httpStatus);
		return ex.getMessage();
	}
	
    @ExceptionHandler(Exception.class)
    public Map<String, Object> errorHandler(Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

}