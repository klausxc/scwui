package com.atguigu.scw.ui.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.scw.ui.bean.TAdmin;

@Controller
@RequestMapping("/ui")
public class HelloController {

	@GetMapping("/hello")
	public String hello(Model model,HttpSession session) {
		model.addAttribute("requestkey", "requestval\\");
		ArrayList<TAdmin> list = new ArrayList<>();
		list.add(new TAdmin(1, "aaaaaa", "1111"));
		list.add(new TAdmin(2, "bbbbbb", "2222"));
		list.add(new TAdmin(3, "cccccc", null));
		list.add(new TAdmin(4, "dddddd", ""));
		
		session.setAttribute("sessionKey", list);
		session.getServletContext().setAttribute("applicationKey", "applicationVal");
		session.setAttribute("aaa", "sadasdsadkalsmd");
		return "test/index";
	}
	
}
