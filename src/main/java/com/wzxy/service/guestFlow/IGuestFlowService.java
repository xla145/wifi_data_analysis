package com.wzxy.service.guestFlow;


import com.wzxy.base.utils.JSONReturn;

public interface IGuestFlowService {
	
	public JSONReturn getGuestFlowDayByNowDate();
	
	public abstract JSONReturn getGuestFlowDayByDate(String dataTime);
	
	public JSONReturn getGuestFlowMonthByDate(String dateTime);
	
	public JSONReturn getGuestFlowYearByDate(String dateTime);
	
	
}
