package com.example.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.model.SearchParam;

//Spring에게 컨트롤러로 활용함을 알려주는 지시자
@RestController
//이곳으로 들어올 API 주소를 매핑하기 위한 지시자(괄호 안에는 어떤 path에서 받을지 명명)
@RequestMapping("/api") //localhost:8080/api 까지 매핑

//컨트롤러: 사용자로 부터 접속을 받아주기 위한 주소들의 묶음
public class GetController {

	//method는 GET method를 활용하고 ,path는 어떠한 주소로 받아들이다는 것
	@RequestMapping(method = RequestMethod.GET, path="/getMethod") //localhost:8080/api/getMethod로 호출하면 사용자의 명령이 아래의 메소드로 들어옴
	public String getRequest() {
		 
		return "Hi getMethod";
	}
	//사용자가 어떠한 값을 던졌는지 알수가 없음. 파라미터를 받기 위해 어떻게 스프링에서는 설정할 까?
	
	@GetMapping("/getParameter") //RequestMapping과는 달리 path만 명시	localhost:8080/api/getParameter?id=1234@password=abcd 
	public String getParmeter(@RequestParam String id, @RequestParam(name="password") String pwd) {
		
		//만약 반드시 지역변수명으로 password를 사용하여야 한다면?? [Spring에서는 파라미터로 받아서 사용하는 것을 권장] 위의 pwd와 같이 처리
		String password = "???";
		
		System.out.println("id: "+id );
		System.out.println("pwd: "+pwd );
		
		return id+pwd;
	}
	
	//만약 위와 같은 다수의 파라미터가 여러개라면 모두 다 명시하고 있을 것인가??? 하나의 Class로 생성해두어서 그 클래스를 바로 파라미터로 전달 받음
	@GetMapping("/getMultiParameter")
	public SearchParam getMultiParameter(SearchParam searchParam) {
		System.out.println(searchParam.getAccount());
		System.out.println(searchParam.getEmail());
		System.out.println(searchParam.getPage());
		
		//그러나 네트워트 통신시에는 JSON 형태로 통신을 할텐데... JSON은 어떻게 처리하는가?
		//jackson library를 통해 SearchParam에서 명시해줌. => Spring Boot에서는 default로 내장되어 있음.
		//객체를 Return한다는 것은 JSON 형태로 RETURN 하겠다는 뜻.
		
		// {"account":"", "email":"", "page":0}
		
		return searchParam;
	}
	
	
	//최종 개발 목표는 localhost:8080/api/getMethod로 사용자가 호출한 것 처럼,
	//사용자가 호출한 것에 대해서 원하는 정보를 데이터베이스와의 연결을 통해 가공하여 적절한 양식으로 return해 주는 것
	
}

