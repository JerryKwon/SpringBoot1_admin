package com.example.admin.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.model.SearchParam;

@RestController
@RequestMapping("/api")

public class PostController {

	//POST가 일어나는 경우
	//HTML에 FORM TAG
	//ajax로 검색할 떄 (port 방식으로)
	
	//@ReqeustBody => HTML 통신시에 post의 body에 데이터를 집어 넣어서 전달하겠다는 듰
	//json, xml, multipart-form, text-plain 방식으로 데이터를 전달할 수 있음 
	//produce에 지원할 수 있는 형태들을 명시
	
	
	@PostMapping(value = "/postMethod")
	public SearchParam postMethod(@RequestBody SearchParam searchParam) {
	
		return searchParam;
		
	}
	
	@PutMapping("/putMethod")
	public void put() {
		
	}
	
	@PatchMapping("/patchMethod")
	public void patch() {
		
	}
	
}
