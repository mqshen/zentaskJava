package org.goldratio.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.goldratio.models.Comment;
import org.goldratio.models.Message;
import org.goldratio.models.Operation;
import org.goldratio.models.TodoItem;
import org.goldratio.models.TodoList;
import org.goldratio.models.Operation.OperationType;
import org.goldratio.models.Operation.TargetType;
import org.goldratio.models.Project;
import org.goldratio.repositories.MessageRepository;
import org.goldratio.repositories.OperationRepository;
import org.goldratio.repositories.TodoListRepository;
import org.goldratio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/** 
 * ClassName: OperationAspect <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 4:02:51 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Aspect
@Component
public class OperationAspect {
	@Autowired 
	private OperationRepository operationRepository;

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired UserService userService;
	
	@After("execution(* org.goldratio.services.ProjectService.create(..))")
	public void createProjectOperation(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0 && args[0] instanceof Project) {
			
			Project project = (Project) args[0];
		
			Operation operation = new Operation();
			operation.setTargetType(TargetType.project);
			operation.setOpType(OperationType.create);
			operation.setTargetId(project.getId());
			operation.setAuthorId(project.getAuthorId());
			operation.setCreateTime(project.getCreateTime());
			operation.setTitle(project.getTitle());
			operation.setTeamId(project.getTeamId());
			operation.setProjectId(project.getId());
			operationRepository.save(operation);
			
			userService.sendMessage(project);
		}
	}
	
	
	@After("execution(* org.goldratio.services.MessageService.create(..))")
	public void createMessageOperation(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0 && args[0] instanceof Message) {
			
			Message project = (Message) args[0];
		
			Operation operation = new Operation();
			operation.setTargetType(TargetType.message);
			operation.setOpType(OperationType.create);
			operation.setTargetId(project.getId());
			operation.setAuthorId(project.getAuthorId());
			operation.setCreateTime(project.getCreateTime());
			operation.setTitle(project.getTitle());
			operation.setTeamId(project.getTeamId());
			operation.setProjectId(project.getProjectId());
			operationRepository.save(operation);
			
			userService.sendMessage(project);
		}
	}
	
	
	@After("execution(* org.goldratio.services.CommentService.create(..))")
	public void createCommentOperation(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0 && args[0] instanceof Comment) {
			
			Comment project = (Comment) args[0];
		
			Operation operation = new Operation();
			operation.setTargetType(TargetType.comment);
			operation.setOpType(OperationType.create);
			operation.setTargetId(project.getId());
			operation.setAuthorId(project.getAuthorId());
			operation.setCreateTime(project.getCreateTime());
			Message message = messageRepository.findById(project.getMessageId());
			operation.setTitle(message.getTitle());
			operation.setDigest(project.getContent());
			operation.setTeamId(project.getTeamId());
			operation.setProjectId(project.getProjectId());
			operationRepository.save(operation);
			
			userService.sendMessage(project);
		}
	}
	
	
	@After("execution(* org.goldratio.services.TodoListService.create(..))")
	public void createTodoListOperation(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0 && args[0] instanceof TodoList) {
			
			TodoList project = (TodoList) args[0];
		
			Operation operation = new Operation();
			operation.setTargetType(TargetType.todoList);
			operation.setOpType(OperationType.create);
			operation.setTargetId(project.getId());
			operation.setAuthorId(project.getAuthorId());
			operation.setCreateTime(project.getCreateTime());
			operation.setTitle(project.getTitle());
			operation.setTeamId(project.getTeamId());
			operation.setProjectId(project.getProjectId());
			operationRepository.save(operation);
			
			userService.sendMessage(project);
		}
	}
	
	@After("execution(* org.goldratio.services.TodoItemService.create(..))")
	public void createTodoItemOperation(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0 && args[0] instanceof TodoItem) {
			
			TodoItem project = (TodoItem) args[0];
		
			Operation operation = new Operation();
			operation.setTargetType(TargetType.todoItem);
			operation.setOpType(OperationType.create);
			operation.setTargetId(project.getId());
			operation.setAuthorId(project.getAuthorId());
			operation.setCreateTime(project.getCreateTime());
			TodoList todoList = todoListRepository.findById(project.getTodoListId());
			operation.setTitle(todoList.getTitle());
			operation.setDigest(project.getTitle());
			operation.setTeamId(project.getTeamId());
			operation.setProjectId(project.getProjectId());
			operationRepository.save(operation);
			
			userService.sendMessage(project);
		}
	}
	
}
