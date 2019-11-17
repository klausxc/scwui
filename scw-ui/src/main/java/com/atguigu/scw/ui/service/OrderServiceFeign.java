package com.atguigu.scw.ui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.scw.ui.bean.TOrder;
import com.atguigu.scw.vo.response.ResponseVo;

@FeignClient("SCW-ORDER")
public interface OrderServiceFeign {

	@PostMapping("/order/updateOrderStatus")
	public ResponseVo<String> updateOrderStatus(@RequestParam("orderNum")String orderNum,@RequestParam("status")String status);
	
	
	@PostMapping("/order/insertOrder")
	public ResponseVo<String> insertOrder(@RequestBody TOrder order);
	
}
