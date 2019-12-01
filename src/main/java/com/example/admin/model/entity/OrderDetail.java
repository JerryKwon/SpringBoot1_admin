package com.example.admin.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"user","item"}) // 상호참조로 인한 overflow 해제
public class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime orderedAt;
	
	//JPA 연관관계 설정 주의사항
	//1. 변수를 가지고 있는 클래스에가서 자신을 기준으로 관계를 설정
	//2. 해당 변수의 타입을 상대 클래스로 지정
	//3. 상대 클래스에 가서 추가로 클래스로 정의해줌.(fetchType, mappedBy 지정.)
	
	
	//N(OrderDetail):1(User) 현재 클래스의 입장에서!! [Hibernate를 통한 연관관계 설정] 을 통해 알아서 찾아가게 됨.
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Item item;
	
}
