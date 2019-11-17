package com.atguigu.scw.ui.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TProjectInitiator implements Serializable{
	@ApiModelProperty(hidden=true)
    private Integer id;
	
	@ApiModelProperty(hidden=true)
    private Integer projectid;

    private String selfintroduction;

    private String detailselfintroduction;

    private String telphone;

    private String hotline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getSelfintroduction() {
        return selfintroduction;
    }

    public void setSelfintroduction(String selfintroduction) {
        this.selfintroduction = selfintroduction == null ? null : selfintroduction.trim();
    }

    public String getDetailselfintroduction() {
        return detailselfintroduction;
    }

    public void setDetailselfintroduction(String detailselfintroduction) {
        this.detailselfintroduction = detailselfintroduction == null ? null : detailselfintroduction.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline == null ? null : hotline.trim();
    }
}