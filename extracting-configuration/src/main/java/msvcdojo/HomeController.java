package msvcdojo;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ConfigurationProperties(prefix="prop")
@RestController
public class HomeController {

	@Value("${name}") //ha on magaban futatjuk
	private String name;
	
	@RequestMapping("/")
	String home(){
		return "Hello, "+ name +"!";
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
	
	
}
