package com.lee.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HTTPControllerTest {
	
	// 인터넷 브라우저 요청은 무조건 get 요청 밖에 할 수 없다. 
	// http://localhost:8080/http/get (select)
	
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청 " + id + ", " + username;
//	}

	@GetMapping("/http/get")
	public String getTest(Member m) { // id=1&username=amy 이 부분이 Member object에 들어간다. 
		return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getEmail() + ", " + m.getPassword();
	}
	
	// http://localhost:8080/http/post (insert)
//	@PostMapping("/http/post")
//	public String postTest(Member m) { // Body -> x-www-form-urlencoded
//		return "post 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getEmail();
//	}

	@PostMapping("/http/post")
	public String postTest(@RequestBody String text) { // Body -> raw -> text (text/plain)
		return "post 요청" + text;
	}
	
//	@PostMapping("/http/post")
//	public String postTest(@RequestBody Member m) { // Body -> raw -> json (application/json)
//		return "post 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getEmail();
//	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	private static final String TAG = "HTTPControllerTest";
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "amy", "1234", "email"); // AllArgsConstructor
		Member m = Member.builder().username("amy").password("1234").email("email").build(); // @Builder
		System.out.println(TAG + "getter: " + m.getPassword());
		m.setPassword("0000");
		System.out.println(TAG + "setter: " + m.getPassword());
		return "lombok test 완료";
	}
}
