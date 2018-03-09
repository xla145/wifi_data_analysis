package com.wzxy.controller.guestFlow;

import com.wzxy.base.utils.BaseUtils;
import com.wzxy.base.utils.JSONReturn;
import com.wzxy.controller.ControllerAbstract;
import com.wzxy.service.dto.EchartsLine;
import com.wzxy.service.dto.SeriesData;
import com.wzxy.service.guestFlow.IGuestFlowService;
import com.wzxy.service.wifiData.ISourceDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Scope
@RequestMapping(value="guest")
@Controller
public class GuestFlowController extends ControllerAbstract {

	@Autowired
	private ISourceDataService sourceDataService;
	@Autowired
	private IGuestFlowService guestFlowService;
	private Log log=LogFactory.getLog(getClass());
	
	@ResponseBody
	@RequestMapping(value="getGFByNowDay")
	public JSONReturn getGFByNowDay(HttpServletRequest req){
		String dateTime= BaseUtils.getDateString(null, "yyyy-MM-dd");
		return guestFlowService.getGuestFlowDayByDate(dateTime);
	}
	//这个的搜索框还没写
	@ResponseBody
	@RequestMapping(value="getGFByBeforeDay")
	public JSONReturn getGFByBeforeDay(HttpServletRequest req){
		return guestFlowService.getGuestFlowDayByNowDate();
	}
	//这个用不到
	@ResponseBody
	@RequestMapping(value="getGuestFlowDayData")
	public JSONReturn getGuestFlowDayData(){
		EchartsLine echartsLine=new EchartsLine();
		List<SeriesData> data=new ArrayList<SeriesData>();
		echartsLine.setTitleName("未来一周气温变化");
		echartsLine.setTitleSubtext("纯属虚构");
		echartsLine.setLegend(new String[]{"最高气温","最低气温"});
		echartsLine.setxAxis(new String[]{"1","2","3","4","5"});
		//echartsLine.setyAxis(new String[]{""});
		SeriesData seriesData;
		for(int i=0;i<2;i++){
			if(i==0){
				seriesData=new SeriesData();
				seriesData.setName("最高气温");
				seriesData.setType("line");
				seriesData.setData(new Integer[]{7,5,4,10,6});
				data.add(seriesData);
			}else{
				seriesData=new SeriesData();
				seriesData.setName("最低气温");
				seriesData.setType("line");
				seriesData.setData(new Integer[]{1,2,-1,0,3});
				data.add(seriesData);
			}
		}
		echartsLine.setData(data);
		//JSONObject json = JSONObject.fromObject(echartsLine);
		//System.out.println(json.toString());
		return JSONReturn.buildSuccess(echartsLine);
	}
}
