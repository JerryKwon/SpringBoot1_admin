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
@ToString(exclude= {"user","item"}) // ��ȣ������ ���� overflow ����
public class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime orderedAt;
	
	//JPA �������� ���� ���ǻ���
	//1. ������ ������ �ִ� Ŭ���������� �ڽ��� �������� ���踦 ����
	//2. �ش� ������ Ÿ���� ��� Ŭ������ ����
	//3. ��� Ŭ������ ���� �߰��� Ŭ������ ��������.(fetchType, mappedBy ����.)
	
	
	//N(OrderDetail):1(User) ���� Ŭ������ ���忡��!! [Hibernate�� ���� �������� ����] �� ���� �˾Ƽ� ã�ư��� ��.
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Item item;
	
}
