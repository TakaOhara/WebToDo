package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;

@Repository
public interface TaskDao{
	
	List<Task> findAll();
	
	Optional<Task> findById(int id);

	void save(Task task);
	
	int update(Task task);
	
	int deleteById(int id);

}