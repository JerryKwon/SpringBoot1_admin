package com.example.admin.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends AdminApplicationTests{

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
//	@Test
	public void create() {
		
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setOrderedAt(LocalDateTime.now());

		//어떤 사람이 주문했는지
//		orderDetail.setUser_id(1L);

		//어떤 상품을 주문했는지
//		orderDetail.setItemId(1L);

		
		orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(orderDetail);
		
		
	}

//	@Test
	public void read() {
			
		Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
		
		orderDetail.ifPresent(od ->{
			System.out.println(od);
		});
		
	}
	
	@Test
	public void update() {
		
		Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
		
		orderDetail.ifPresent(od->{
//			od.setUser_id(5L);
			orderDetailRepository.save(od);
		});
		
	}
	public void delete() {
		
	}
}
