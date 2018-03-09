package com.wzxy.service.dto;

import java.util.List;

public class EchartsLine {
	private String titleName;	   //标题
	private String titleSubtext;   //副标题
	private String[] legend;	   //可选显示数据名
	private String[] xAxis;        //X轴线下的变量（通常为时间）
	private String[] yAxis;        //Y轴线傍边的变量（通常为指标）
	private List<SeriesData> data; //series数据，各项数据指标的数据
	private Integer[] seriesString;
	
	
	public EchartsLine() {
		super();
	}
	

	public EchartsLine(String titleName, String titleSubtext, String[] legend,
			String[] xAxis, String[] yAxis, List<SeriesData> data,
			Integer[] seriesString) {
		super();
		this.titleName = titleName;
		this.titleSubtext = titleSubtext;
		this.legend = legend;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.data = data;
		this.seriesString = seriesString;
	}


	public String getTitleName() {
		return titleName;
	}


	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}


	public String getTitleSubtext() {
		return titleSubtext;
	}


	public void setTitleSubtext(String titleSubtext) {
		this.titleSubtext = titleSubtext;
	}


	public String[] getLegend() {
		return legend;
	}


	public void setLegend(String[] legend) {
		this.legend = legend;
	}


	public String[] getxAxis() {
		return xAxis;
	}


	public void setxAxis(String[] xAxis) {
		this.xAxis = xAxis;
	}


	public String[] getyAxis() {
		return yAxis;
	}


	public void setyAxis(String[] yAxis) {
		this.yAxis = yAxis;
	}


	public List<SeriesData> getData() {
		return data;
	}


	public void setData(List<SeriesData> data) {
		this.data = data;
	}


	public Integer[] getSeriesString() {
		return seriesString;
	}


	public void setSeriesString(Integer[] seriesString) {
		this.seriesString = seriesString;
	}
	

	
	
}
