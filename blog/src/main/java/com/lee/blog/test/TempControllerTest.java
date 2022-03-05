package com.lee.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //해당 경로 이하의 파일을 리턴 
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로: src/main/resources/static
		// 리턴명: /home.html
		// 풀경로: src/main/resources/static/home.html
		return "/home.html";	
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views/
		// suffix: .jsp
		// 풀네임: /WEB-INF/views/test.jsp
		return "test"; // 정적파일(컴파일)이라서 static 안에서는 실행안됨. 
	}

}


