package com.example.admin.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;

public class ItemRepositoryTest extends AdminApplicationTests{

	@Autowired
	ItemRepository itemRepository;
	
//	@Test
	public void create() {
		
		Item item = new Item();
		
		item.setName("삼성전자 노트북");
		item.setPrice(500000);
		item.setContent("이변년도에 출시한 삼성 노트북입니다");
		
		itemRepository.save(item);
		Assert.assertNotNull(item);
	}
	
	@Test
	public void read() {
	
		Optional<Item> item = itemRepository.findById(1L);
//		
//		item.ifPresent(selectedItem ->{
//			System.out.println("Gotten Item: "+selectedItem);
//		});
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
}
