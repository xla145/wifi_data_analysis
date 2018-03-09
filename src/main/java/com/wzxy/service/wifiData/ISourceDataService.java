package com.wzxy.service.wifiData;

import com.wzxy.base.utils.JSONReturn;
import com.wzxy.service.model.sourcedata.SourceData;

import java.util.List;


public interface ISourceDataService {
	public abstract JSONReturn findGuestFlow(String time);
	
	public abstract JSONReturn savaWiFiData(List<SourceData> model);
}
