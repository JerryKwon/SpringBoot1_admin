package com.example.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.admin.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//QueryMethod 설정 
	//findBy이후에 JPA에서 자동으로 어떤 변수에서 찾을 것인지 쿼리를 만듦.
	//select * from user where account ='?';
	Optional<User> findByAccount(String account);
	
	Optional<User> findByEmail(String email);
	
	//여러가지를 동시에 하려면??
	//select * from user where account ='?' and email='?';
	Optional<User> findByAccountAndEmail(String account, String email);
	
}
