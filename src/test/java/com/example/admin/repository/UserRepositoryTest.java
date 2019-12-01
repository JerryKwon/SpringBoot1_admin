package com.example.admin.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.entity.User;

public class UserRepositoryTest extends AdminApplicationTests{

	// spring�� ���� �� ��ǥ���� ������ ���� (DI, Dependency Injection) ������ ����. ��ü�� ����ڰ� ���� �������� �ʰ� �������� ���� �����ϴ� ��
	@Autowired 
	private UserRepository userRepository ;

	//	Spring�� �˾Ƽ� autowired�� ��ü�� ã�Ƽ� �������ش�.
	//	private UserRepository userRepository = new UserRepository();

	//Repository�� CRUD �� �׽�Ʈ.
	
	//Hibernate�� ����. ������ ���� �Է����� �ʾƵ� ��ü�� ���� ����
//	@Test
	public void create() {
		
		// ���� ����
		// String query = insert into table (%s, %s, %s) values ("","","");
		// �׷��� JPA�� ������ ������Ʈ�� Ȱ���Ͽ� �̸� ��Ī������
		
		
		//User�� �� DI�� �������� �ʳ���? A. DI�� �̱���. User�� ���� �� ���� ���� �޶����� ������ DI�� �����ΰ� ����ϱ⿡�� ���� X
		User user = new User();
		
//		user.setId(id); auto increment
		user.setAccount("testuser03");
		user.setEmail("testuser01@gmail.com");
		user.setPhoneNumber("000-3333-0000");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("testuser03");
		
		//user��� ������ �����ͺ��̽��� ������ ���ο� user ��ü�� Return ��.
		User newUser = userRepository.save(user);
		System.out.println("new user: "+newUser);
		
	}
	
	//getMethod�� id�� �޾ƿ���,
	@Test
	@Transactional
	public void read() {
		//findById(2L): Optional �̶�� ��ü�� ���� ���� �ְ� ���� ���� �ִٴ� �ǹ�. 2L means Long datatype�� ���� 2�� index
//		Optional<User> user = userRepository.findById(2L);
		
		//select * from user where id = '?';
		//�⺻������ �˻��� �� ���� ID ������ �ϴ� ���� �ƴ� �ٸ� �÷��� ���� Ȱ���Ͽ� ������ => QueryMethod => UserRepository�� ����
		Optional<User> user = userRepository.findByAccount("testuser03");
		
		user.ifPresent(selectUser ->{
		
			//�ϳ��� user�� �������� orderDetail�� ���� �� �ֱ� ������ List�� ó����.
			selectUser.getOrderDetailList().stream().forEach(detail->{
				
				//5 ����ڰ� Detail���� ������ �ִ� ������ id
//				System.out.println(detail.getItem());
				
				Item item = detail.getItem();
				System.out.println(item);
				
			});
		});
		
	}

	//RestAPI ����� read
	//getMethod�� id�� �޾ƿ���,
//	@Test
//	public User read(@RequestParam Long id) {
//		//Optional �̶�� ��ü�� ���� ���� �ְ� ���� ���� �ִٴ� �ǹ�. 2L means Long datatype�� ���� 2�� index
//		Optional<User> user = userRepository.findById(id);
//		
//		user.ifPresent(selectUser ->{
//			System.out.println("User: "+selectUser);
//			System.out.println("User Email: "+selectUser.getEmail());
//		});
//		
//		return user;
//		
//	}
	
//	@Test
	public void update() {
		
		Optional<User> user = userRepository.findById(2L);
		
		user.ifPresent(selectUser -> {
			selectUser.setAccount("pppp");
			selectUser.setUpdatedAt(LocalDateTime.now());
			selectUser.setUpdatedBy("admin");
			
			//Repository.save�� create �ÿ��� ���ο� ��ü�� ��ȯ�Ǵµ� update���� ��� ó���� �ϴ°�??
			//update�ÿ��� findById�� ���� �� Id ���� ���� ������ ���ؼ� Spring�� �ű� ��ü�� �������� ������ ������.
			userRepository.save(selectUser);
		});
		
	}
	
	/*
	 *  Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
	Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
	Hibernate: update user set account=?, created_at=?, created_by=?, email=?, phone_number=?, updated_at=?, updated_by=? where id=?
	 */

	//JPA������ findById�� ���� ��� ���� ã�Ƴ� �Ŀ�, Update�ÿ� �� �� �� id ���� Ȱ���Ͽ� ã�Ƴ���, ã�Ƴ� ���� ���ο� ������ ���ļ� ������ �����Ѵ�.
	
//	@Test
//	@Transactional // �������δ� �Ͼ���� ������ �����ͺ��̽����� ������� ����. ���Ŀ� rollback ������
	public void delete() {
		
		Optional<User> user = userRepository.findById(3L);
		
		Assert.assertTrue(user.isPresent()); // true
		
		user.ifPresent(selectedUser ->{
			userRepository.delete(selectedUser);
		});
		
		Optional<User> deletedUser = userRepository.findById(3L);
		
//		Assert.assertFalse(user.isPresent()); //false
		
		
//		if(deletedUser.isPresent()) {
//			System.out.println("������ ������");
//		}else {
//			System.out.println("������ ������");
//		}
		
	}
}
