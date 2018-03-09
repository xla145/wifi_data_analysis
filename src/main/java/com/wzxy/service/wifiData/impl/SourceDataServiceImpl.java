package com.wzxy.service.wifiData.impl;

import com.wzxy.base.utils.BaseUtils;
import com.wzxy.base.utils.JSONReturn;
import com.wzxy.dao.SourceDataDao;
import com.wzxy.service.dto.GuestFlowDTO;
import com.wzxy.service.model.sourcedata.SourceData;
import com.wzxy.service.wifiData.ISourceDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Scope
@Service
@Transactional(readOnly = true)
public class SourceDataServiceImpl implements ISourceDataService {
	@Autowired
	private SourceDataDao sourceDataDao;
	private Log log=LogFactory.getLog(getClass());
	
	public JSONReturn findGuestFlow(String time) {
		List<GuestFlowDTO> guestFlowDTOList=sourceDataDao.findGuestFlow(time);
		log.info(guestFlowDTOList.size());
		return JSONReturn.buildSuccess(guestFlowDTOList);
	}
	
	@Transactional
	public JSONReturn savaWiFiData(List<SourceData> model) {
		if(BaseUtils.isEmpty(model))
			return JSONReturn.buildFailureWithEmptyBody();
		for(SourceData data:model){
			sourceDataDao.save(data);
		}
		return JSONReturn.buildSuccessWithEmptyBody();
	}

}
