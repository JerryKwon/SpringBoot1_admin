package com.example.admin.model.entity;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB에 있는 엔터티 명과 동일하게 클래스 이름을 명명
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String account;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
	//1:N
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<OrderDetail> orderDetailList;
	
}
