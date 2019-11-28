package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.admin.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
