package com.atguigu.scw.ui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.scw.ui.response.ProjectDetailsVo;
import com.atguigu.scw.ui.service.ProjectServiceFeign;
import com.atguigu.scw.vo.response.ResponseVo;

@RequestMapping("/project")
@Controller
public class ProjectController {

	@Autowired
	ProjectServiceFeign projectServiceFeign;
	
	@GetMapping("/project.html")
	public String toProjectDetail(Integer projectid,HttpSession session) {
		ResponseVo<ProjectDetailsVo> vo = projectServiceFeign.getProjectById(projectid);
		session.setAttribute("projectDetail", vo.getData());
		return "project/project";
	}
	
	
}
