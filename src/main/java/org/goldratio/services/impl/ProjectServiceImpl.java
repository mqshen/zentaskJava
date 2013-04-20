package org.goldratio.services.impl;


import org.goldratio.models.Project;
import org.goldratio.repositories.ProjectRepository;
import org.goldratio.repositories.ProjectUserRepository;
import org.goldratio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: ProjectServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 5:51:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Autowired 
	private ProjectRepository projectRepository;
	@Autowired 
	private ProjectUserRepository projectUserRepository;

	@Override
	public void create(Project project) {
		
		projectRepository.save(project);
		
	}

}
