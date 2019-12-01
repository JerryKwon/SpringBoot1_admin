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

	// spring의 장정 및 대표적인 디자인 패턴 (DI, Dependency Injection) 의존성 주입. 객체를 사용자가 직접 선언하지 않고 스프링이 직접 관리하는 것
	@Autowired 
	private UserRepository userRepository ;

	//	Spring이 알아서 autowired된 객체를 찾아서 주입해준다.
	//	private UserRepository userRepository = new UserRepository();

	//Repository의 CRUD 를 테스트.
	
	//Hibernate의 장점. 쿼리를 따로 입력하지 않아도 객체를 통해 가능
//	@Test
	public void create() {
		
		// 예시 쿼리
		// String query = insert into table (%s, %s, %s) values ("","","");
		// 그러나 JPA의 장점은 오브젝트를 활용하여 이를 매칭시켜줌
		
		
		//User는 왜 DI로 관리하지 않나요? A. DI는 싱글톤. User는 만들 때 마다 값이 달라지기 때문에 DI로 만들어두고 사용하기에는 적합 X
		User user = new User();
		
//		user.setId(id); auto increment
		user.setAccount("testuser03");
		user.setEmail("testuser01@gmail.com");
		user.setPhoneNumber("000-3333-0000");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("testuser03");
		
		//user라는 정보를 데이터베이스에 저장한 새로운 user 객체가 Return 됨.
		User newUser = userRepository.save(user);
		System.out.println("new user: "+newUser);
		
	}
	
	//getMethod로 id를 받아오면,
	@Test
	@Transactional
	public void read() {
		//findById(2L): Optional 이라는 객체는 있을 수도 있고 없을 수도 있다는 의미. 2L means Long datatype을 가진 2번 index
//		Optional<User> user = userRepository.findById(2L);
		
		//select * from user where id = '?';
		//기본적으로 검색을 할 때는 ID 만으로 하는 것이 아닌 다른 컬럼의 값을 활용하여 수행함 => QueryMethod => UserRepository에 설정
		Optional<User> user = userRepository.findByAccount("testuser03");
		
		user.ifPresent(selectUser ->{
		
			//하나의 user는 여러개의 orderDetail을 가질 수 있기 때문에 List로 처리함.
			selectUser.getOrderDetailList().stream().forEach(detail->{
				
				//5 사용자가 Detail에서 가지고 있는 아이템 id
//				System.out.println(detail.getItem());
				
				Item item = detail.getItem();
				System.out.println(item);
				
			});
		});
		
	}

	//RestAPI 연결시 read
	//getMethod로 id를 받아오면,
//	@Test
//	public User read(@RequestParam Long id) {
//		//Optional 이라는 객체는 있을 수도 있고 없을 수도 있다는 의미. 2L means Long datatype을 가진 2번 index
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
			
			//Repository.save를 create 시에는 새로운 객체가 반환되는데 update에는 어떻게 처리를 하는가??
			//update시에는 findById를 통해 얻어낸 Id 값의 존재 유무를 통해서 Spring이 신규 객체를 생성할지 말지를 결정함.
			userRepository.save(selectUser);
		});
		
	}
	
	/*
	 *  Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
	Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
	Hibernate: update user set account=?, created_at=?, created_by=?, email=?, phone_number=?, updated_at=?, updated_by=? where id=?
	 */

	//JPA에서는 findById를 통해 결과 값을 찾아낸 후에, Update시에 한 번 더 id 값을 활용하여 찾아내고, 찾아낸 값과 새로운 값들을 합쳐서 쿼리를 수행한다.
	
//	@Test
//	@Transactional // 동작으로는 일어나지만 실제로 데이터베이스에는 적용되지 않음. 추후에 rollback 시켜줌
	public void delete() {
		
		Optional<User> user = userRepository.findById(3L);
		
		Assert.assertTrue(user.isPresent()); // true
		
		user.ifPresent(selectedUser ->{
			userRepository.delete(selectedUser);
		});
		
		Optional<User> deletedUser = userRepository.findById(3L);
		
//		Assert.assertFalse(user.isPresent()); //false
		
		
//		if(deletedUser.isPresent()) {
//			System.out.println("데이터 존재함");
//		}else {
//			System.out.println("데이터 삭제됨");
//		}
		
	}
}
