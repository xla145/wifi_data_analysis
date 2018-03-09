package com.wzxy.service.model.guestflow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GuestFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "guest_flow")
public class GuestFlow implements java.io.Serializable {

	// Fields

	@Id
	private Long id;
	private String dataTime;
	private Integer countMac;

	// Constructors

	/** default constructor */
	public GuestFlow() {
	}

	/** full constructor */
	public GuestFlow(String dataTime, Integer countMac) {
		this.dataTime = dataTime;
		this.countMac = countMac;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataTime() {
		return this.dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public Integer getCountMac() {
		return this.countMac;
	}

	public void setCountMac(Integer countMac) {
		this.countMac = countMac;
	}

}
