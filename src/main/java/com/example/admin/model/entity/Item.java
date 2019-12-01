package com.example.admin.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int price;
	
	private String content;
	
	//Fetch Type에는 LAZY 와 EAGER
	//LAZY= 지연 로딩 => Select * from item where id =?
	//EAGER= 즉시 로딩 [연관관계가 설정된 모든 테이블에 대해서 조인이 발생] => 연관관계에 있어서 한 건만 있을 때만 추천
	//from item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id where item0_.id=?
	//모든 테이블을 조인
	@OneToMany(fetch=FetchType.EAGER,mappedBy="item")
	private List<OrderDetail> orderDetailList;
	
}
