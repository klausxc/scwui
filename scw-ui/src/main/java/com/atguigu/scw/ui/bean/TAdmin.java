package com.atguigu.scw.ui.bean;


import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description="管理员类",value="TAdmin")
@AllArgsConstructor
@NoArgsConstructor
@Data  // get set
@ToString
@Slf4j
public class TAdmin implements Serializable{
	@ApiModelProperty(hidden=true)
    private Integer id;
	
	@ApiModelProperty(value="登录账号")
    private String loginacct;
	@ApiModelProperty("登录密码")
    private String userpswd;

	
	
}