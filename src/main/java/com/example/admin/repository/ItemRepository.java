package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admin.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
