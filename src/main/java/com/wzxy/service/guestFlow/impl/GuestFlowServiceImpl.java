package com.wzxy.service.guestFlow.impl;

import com.wzxy.base.utils.BaseUtils;
import com.wzxy.base.utils.JSONReturn;
import com.wzxy.dao.GuestFlowDao;
import com.wzxy.dao.SourceDataDao;
import com.wzxy.service.dto.EchartsLine;
import com.wzxy.service.guestFlow.IGuestFlowService;
import com.wzxy.service.model.guestflow.GuestFlow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class GuestFlowServiceImpl implements IGuestFlowService {
	@Autowired
	private SourceDataDao sourceDataDao;
	@Autowired
	private GuestFlowDao guestFlowDao;
	
	private Log log= LogFactory.getLog(getClass());
	
	@Transactional
	public JSONReturn getGuestFlowDayByDate(String dataTime) {
		dataTime="2017-12-26";
		String nowHourDate= BaseUtils.getDateString(null, "yyyy-MM-dd HH");
		List<Object> dataTimeList = sourceDataDao.findHourTimebyTime(dataTime.substring(0,10));
		//log.info("getGuestFlowDayByDate-->dataTimeList:"+dataTimeList+" dataTime.substring(0,10):"+dataTime.substring(0,10));
		for(Object dt:dataTimeList){
			if(!nowHourDate.equals(dt)){
				GuestFlow guestFlow = guestFlowDao.findUniqueByProperty("dataTime",dt);
				if(BaseUtils.isEmpty(guestFlow))
					sourceDataDao.insertGuestFlowByTime(dt);
			}
		}
		List<GuestFlow> guestFlowList = guestFlowDao.findGuestFlowbyTime(dataTime.substring(0,10));
		List<String> dateList=new ArrayList<String>();
		List<Integer> dataList=new ArrayList<Integer>();
		for(GuestFlow gf:guestFlowList){
			dateList.add(gf.getDataTime().substring(11,13)+"时");
			dataList.add(gf.getCountMac());
		}
		EchartsLine echartsLine=new EchartsLine();
		echartsLine.setTitleName(dataTime+"日客流量");
		echartsLine.setTitleSubtext(dataTime);
		echartsLine.setLegend(new String[]{"当天客流量","昨天客流量"});
		echartsLine.setxAxis(dateList.toArray(new String[0]));
		echartsLine.setSeriesString(dataList.toArray(new Integer[0]));
		return JSONReturn.buildSuccess(echartsLine);
	}

	public JSONReturn getGuestFlowMonthByDate(String dateTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONReturn getGuestFlowYearByDate(String dateTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONReturn getGuestFlowDayByNowDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
