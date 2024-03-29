package com.atguigu.scw.ui.controller;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.scw.common.util.AppDateUtils;
import com.atguigu.scw.ui.bean.TMemberAddress;
import com.atguigu.scw.ui.bean.TOrder;
import com.atguigu.scw.ui.bean.TReturn;
import com.atguigu.scw.ui.config.AlipayConfig;
import com.atguigu.scw.ui.response.FormPayForOrder;
import com.atguigu.scw.ui.response.ProjectDetailsVo;
import com.atguigu.scw.ui.service.OrderServiceFeign;
import com.atguigu.scw.ui.service.ProjectServiceFeign;
import com.atguigu.scw.ui.service.UserServiceFeign;
import com.atguigu.scw.vo.response.ResponseVo;
import com.atguigu.scw.vo.response.UserLoginVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {
    
	@Autowired
	ProjectServiceFeign projectServiceFeign;
	@Autowired
	UserServiceFeign userServiceFeign;
	@Autowired
	OrderServiceFeign orderServiceFeign;
	
	
	@GetMapping("/notify_url")
	public String notifyUrl(HttpServletRequest request,Model model) throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			orderServiceFeign.updateOrderStatus(out_trade_no, "1");
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
		}
		return "success";	
	}
	
	
	@GetMapping("/return_url")
	public String returnURI(HttpServletRequest request,Model model) throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			model.addAttribute("out_trade_no", out_trade_no);
			model.addAttribute("trade_no", trade_no);
			model.addAttribute("total_amount", total_amount);
			return "order/success";
		}else {
			return "order/error";
		}
	}

	
	
	@PostMapping("/forPayment")
	@ResponseBody
	public String forPayment(HttpSession session,FormPayForOrder orederVo,HttpServletRequest request) {
		TOrder order = new TOrder();
		BeanUtils.copyProperties(orederVo, order);
		UserLoginVo vo=(UserLoginVo) session.getAttribute("user");
		order.setMemberid(vo.getId());
		
		ProjectDetailsVo proVo=(ProjectDetailsVo) session.getAttribute("projectDetail");
		order.setProjectid(proVo.getId());
		
		TReturn rtn = (TReturn) session.getAttribute("orderReturn");
		order.setReturnid(rtn.getId());
		
		String orderNum=System.currentTimeMillis()+UUID.randomUUID().toString().replace("-", "")+vo.getId();
		order.setOrdernum(orderNum);
		
		order.setCreatedate(AppDateUtils.getFormatTime());
		
		Integer money=orederVo.getRtnCount()*rtn.getSupportmoney()+rtn.getFreight();
		order.setMoney(money);
		
		order.setRtncount(orederVo.getRtnCount());
		
		order.setStatus(""+0);
		
		ResponseVo<String> responseVo = orderServiceFeign.insertOrder(order);
		if ("200".equals(responseVo.getData())) {
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = orderNum;
			//付款金额，必填
			String total_amount = money+"";
			//订单名称，必填
			String subject = proVo.getName();
			//商品描述，可空
			String body = order.getRemark();
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			
			//请求
			String result="";
			try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//输出
			return result;
		}else {
			return null;
		}
		
	}
	
	
	
	@GetMapping(value="/pay-step-2.html",produces="text/html")
	public String payStep2(Model model,@RequestHeader("referer")String refer,Integer count,HttpSession session) {
		UserLoginVo user = (UserLoginVo) session.getAttribute("user");
		session.setAttribute("你好吗", "dddddddddddddd");
		if (user==null) {
			session.setAttribute("refer", refer);
			return "user/login";
		}else {
			String userToken = user.getToken();
			ResponseVo<List<TMemberAddress>> vo = userServiceFeign.getAddress(userToken);
			model.addAttribute("count", count);
			TReturn tReturn=(TReturn) session.getAttribute("orderReturn");
			BigDecimal singPrice = new BigDecimal(tReturn.getSupportmoney());
			BigDecimal freight = new BigDecimal(tReturn.getFreight());
			BigDecimal count2 = new BigDecimal(count);
			BigDecimal add = count2.multiply(singPrice).add(freight);
			double d = add.doubleValue();
			model.addAttribute("totalPrice",d);
			model.addAttribute("addresses", vo.getData());
			return "order/pay-step-2";
		}
	}
	
	@GetMapping("/pay-step-1.html")
	public String payStep1(Integer id,HttpSession session) {
		ResponseVo<TReturn> orderReturn = projectServiceFeign.getReturn(id);
		session.setAttribute("orderReturn", orderReturn.getData());
		log.debug("回报是:{}",orderReturn);
		return "order/pay-step-1";
	}
}
