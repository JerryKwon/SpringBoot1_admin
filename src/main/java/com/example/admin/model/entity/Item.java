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
	
	//Fetch Type���� LAZY �� EAGER
	//LAZY= ���� �ε� => Select * from item where id =?
	//EAGER= ��� �ε� [�������谡 ������ ��� ���̺� ���ؼ� ������ �߻�] => �������迡 �־ �� �Ǹ� ���� ���� ��õ
	//from item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id where item0_.id=?
	//��� ���̺��� ����
	@OneToMany(fetch=FetchType.EAGER,mappedBy="item")
	private List<OrderDetail> orderDetailList;
	
}
