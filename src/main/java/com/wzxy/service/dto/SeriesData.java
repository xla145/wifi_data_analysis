package com.wzxy.service.dto;

public class SeriesData {
	private String name;	//属性名
	private String type;    //类型
	private Integer[] data;  //数据
	
	
	
	public SeriesData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SeriesData(String name, String type, Integer[] data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer[] getData() {
		return data;
	}
	public void setData(Integer[] data) {
		this.data = data;
	}
	
	
	
}
