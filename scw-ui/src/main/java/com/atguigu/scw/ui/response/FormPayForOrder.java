package com.atguigu.scw.ui.response;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class FormPayForOrder {
	private String address;

	private String invoice;

	private String invoictitle;

	private String remark;

	private Integer rtnCount;
}
