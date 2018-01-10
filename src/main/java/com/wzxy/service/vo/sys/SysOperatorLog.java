
package com.wzxy.service.vo.sys;

import cn.assist.easydao.annotation.Id;

import cn.assist.easydao.annotation.Temporary;

import cn.assist.easydao.pojo.BasePojo;
import java.util.Date;

/**
 * 系统人员操作日志
 */
public class SysOperatorLog extends BasePojo {
	@Temporary
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer sysUid;
	private String sysName;
	private String name;
	private String ip;
	private String content;
	private Date createTime;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysUid() {
		return sysUid;
	}

	public void setSysUid(Integer sysUid) {
		this.sysUid = sysUid;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

