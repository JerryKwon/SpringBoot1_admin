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
		
		item.setName("�Ｚ���� ��Ʈ��");
		item.setPrice(500000);
		item.setContent("�̺��⵵�� ����� �Ｚ ��Ʈ���Դϴ�");
		
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
