package com.atguigu.scw.ui.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.scw.ui.service.ProjectServiceFeign;
import com.atguigu.scw.ui.service.UserServiceFeign;
import com.atguigu.scw.vo.response.ProjectResponseVo;
import com.atguigu.scw.vo.response.ResponseVo;
import com.atguigu.scw.vo.response.UserLoginVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	ProjectServiceFeign serviceFeign;
	@Autowired
	UserServiceFeign userServiceFeign;
	
	
	@PostMapping("/user/login")
	public String toLogin(String loginacct,String userpswd,HttpSession session,Model model){
		ResponseVo<UserLoginVo> responseVo = userServiceFeign.login(loginacct, userpswd);
		if ("200".equals(responseVo.getCode())) {
			session.setAttribute("user", responseVo.getData());
			String refer = (String) session.getAttribute("refer");
			System.out.println(refer);
			if (StringUtils.isEmpty(refer)) {
				return "redirect:/index.html";
			}else {
				session.removeAttribute("refer");
				return "redirect:"+refer ;
			}
		}else {
			model.addAttribute("errorMsg", "账号名或密码错误");
			return "user/login";
		}
		
	}
	
	
	@GetMapping(value= {"/","index","index.html"})
	public String toIndex(Model model) {
		ResponseVo<List<ProjectResponseVo>> list = serviceFeign.getAllProject();
		log.debug("项目是:{}",list);
		model.addAttribute("projects", list.getData());
		return "index";
	}
}
