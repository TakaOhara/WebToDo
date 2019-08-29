package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskDao;

@Service
public class TaskServiceImpl implements TaskService {

		
	private final TaskDao dao;

	@Autowired
	public TaskServiceImpl(TaskDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Task> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Task task) {
		dao.insert(task);
	}

	@Override
	public void update(Task task) {
		if(dao.update(task) == 0) {
			throw new TaskNotFoundException("指定されたタスクが存在しません。");
		}
	}

	@Override
	public void deleteById(int id) {
		if(dao.deleteById(id) == 0) {
			throw new TaskNotFoundException("指定されたタスクが存在しません。");
		}
	}
	
	@Override
	public Optional<TaskForm> getTaskForm(int id) {//サービスにformを持ち込まないほうがよい
		Optional<Task> task = dao.findById(id);

		if(!task.isPresent()) {
			return Optional.empty(); 
		}

		return task.map(tsk ->
                new TaskForm(tsk.getTypeId(), tsk.getTitle(), tsk.getDetail(), tsk.getDeadline(), false));
	}

	@Override
	public Optional<Task> getTask(int id) {
		return dao.findById(id);
	}


}
