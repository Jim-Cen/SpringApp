package pers.jim.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pers.jim.util.ApiStatusCode;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppSwaggerConfiguration  implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
    	List<ResponseMessage> responses = new ArrayList<ResponseMessage>();
    	Stream.of(ApiStatusCode.values()).forEach( e -> {
    		responses.add(new ResponseMessageBuilder()
    						  .code(e.getStatusCode())
    						  .message(e.getStatusDesc())
    					      .build()
    					  );
    	});
        return new Docket(DocumentationType.SWAGGER_2)
        		.globalResponseMessage(RequestMethod.POST,responses)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.jim.app.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Jim's App")
                .contact(new Contact("Jim Cen", null, "c_weking@126.com"))
                .version("1.0")
                .description("API 描述")
                .build();
    }
}
