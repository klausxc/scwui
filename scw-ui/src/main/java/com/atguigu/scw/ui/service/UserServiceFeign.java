package com.atguigu.scw.ui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.scw.ui.bean.TMemberAddress;
import com.atguigu.scw.vo.response.ResponseVo;
import com.atguigu.scw.vo.response.UserLoginVo;

import io.swagger.annotations.ApiOperation;

@FeignClient(value="SCW-USER")
public interface UserServiceFeign {

	@GetMapping("/user/getAddress")
	public ResponseVo<List<TMemberAddress>> getAddress (@RequestParam("userToken")String userToken);
	
	@PostMapping("/user/login")
	@ApiOperation(value="登录模块")
	public ResponseVo<UserLoginVo> login(@RequestParam("loginacct")String loginacct,@RequestParam("userpswd")String userpswd);
}
