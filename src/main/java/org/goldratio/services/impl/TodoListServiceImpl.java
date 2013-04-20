package org.goldratio.services.impl;

import org.goldratio.models.TodoList;
import org.goldratio.repositories.TodoListRepository;
import org.goldratio.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: TodoListServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 7:51:28 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Service("todoListService")
public class TodoListServiceImpl implements TodoListService {
	@Autowired
	private TodoListRepository todoListRepository;

	@Override
	public void create(TodoList todoList) {
		todoListRepository.save(todoList);
	}

}
