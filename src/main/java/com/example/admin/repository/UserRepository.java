package com.example.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.admin.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//QueryMethod ���� 
	//findBy���Ŀ� JPA���� �ڵ����� � �������� ã�� ������ ������ ����.
	//select * from user where account ='?';
	Optional<User> findByAccount(String account);
	
	Optional<User> findByEmail(String email);
	
	//���������� ���ÿ� �Ϸ���??
	//select * from user where account ='?' and email='?';
	Optional<User> findByAccountAndEmail(String account, String email);
	
}
