package org.goldratio.services.impl;

import org.goldratio.models.Comment;
import org.goldratio.repositories.CommentRepository;
import org.goldratio.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: CommentServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 7:42:51 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public void create(Comment comment) {
		commentRepository.save(comment);
	}

}
