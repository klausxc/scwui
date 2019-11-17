package com.atguigu.scw.ui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.scw.ui.bean.TReturn;
import com.atguigu.scw.ui.response.ProjectDetailsVo;
import com.atguigu.scw.vo.response.ProjectResponseVo;
import com.atguigu.scw.vo.response.ResponseVo;

@FeignClient(value="SCW-PROJECT")
public interface ProjectServiceFeign {
	
	@GetMapping("/getReturn")
	@ResponseBody
	public ResponseVo<TReturn> getReturn(@RequestParam("id")Integer id);
	
	@GetMapping("/getAllProjects")
	public ResponseVo<List<ProjectResponseVo>> getAllProject();
	
	@GetMapping("/getProjectDetailById")
	public ResponseVo<ProjectDetailsVo> getProjectById(@RequestParam("projectid")Integer id);
	
}
