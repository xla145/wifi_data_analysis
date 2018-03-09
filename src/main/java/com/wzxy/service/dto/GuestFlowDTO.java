package com.wzxy.service.dto;

public class GuestFlowDTO {
	
	private long id;
	private String tanZhenId;
	private String phoneMac;
	private String dataTime;
	
	
	
	@Override
	public String toString() {
		return "GuestFlow [id=" + id + ", tanZhenId=" + tanZhenId
				+ ", phoneMac=" + phoneMac + ", dataTime=" + dataTime + "]";
	}
	public GuestFlowDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GuestFlowDTO(long id, String tanZhenId, String phoneMac, String dataTime) {
		super();
		this.id = id;
		this.tanZhenId = tanZhenId;
		this.phoneMac = phoneMac;
		this.dataTime = dataTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTanZhenId() {
		return tanZhenId;
	}
	public void setTanZhenId(String tanZhenId) {
		this.tanZhenId = tanZhenId;
	}
	public String getPhoneMac() {
		return phoneMac;
	}
	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	
	
}
