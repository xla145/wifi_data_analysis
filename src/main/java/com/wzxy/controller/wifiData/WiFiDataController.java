package com.wzxy.controller.wifiData;

import com.wzxy.base.utils.BaseUtils;
import com.wzxy.base.utils.JSONReturn;
import com.wzxy.controller.ControllerAbstract;
import com.wzxy.service.wifiData.ISourceDataService;
import com.wzxy.service.model.sourcedata.SourceData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Scope
@Controller
public class WiFiDataController extends ControllerAbstract {

	@Autowired
	private ISourceDataService sourceDataService;
	/**
	 * 获取wifi探针数据-通过HttpServletRequest中携带的data参数获取
	 * @param req 浏览器请求
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "dataUpload.do")
	public JSONReturn data(HttpServletRequest req) {
		String data = req.getParameter("data");
    	if(BaseUtils.isEmpty(data))
    		return JSONReturn.buildFailureWithEmptyBody();
		List<SourceData> sourceDataList = parseToJson(data);
		sourceDataService.savaWiFiData(sourceDataList);
		return JSONReturn.buildSuccessWithEmptyBody();
	}

	//解析探针的json数据
	public static List<SourceData> parseToJson(String jsonStriing) {
		JSONObject jb = new JSONObject();
		JSONArray ja = new JSONArray();
		List<SourceData> dataList = new ArrayList<SourceData>();
		jb = JSONObject.fromObject(jsonStriing);
		ja = jb.getJSONArray("data");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			     
		String dateString = formatter.format(date);
		for (int j = 0; j < ja.size(); j++) {
			SourceData wifiData = new SourceData();
			wifiData.setWid(jb.getString("id"));
			wifiData.setMmac(jb.getString("mmac"));
			wifiData.setRate(jb.getString("rate"));			
			wifiData.setTime(dateString);
			wifiData.setLat(jb.getString("lat"));
			wifiData.setLon(jb.getString("lon"));
			wifiData.setMac(ja.getJSONObject(j).getString("mac"));
			wifiData.setRssi(ja.getJSONObject(j).getString("rssi"));
			wifiData.setRanges(ja.getJSONObject(j).getString("range"));
															 
			if(ja.getJSONObject(j).containsKey("ts")&&ja.getJSONObject(j).containsKey("tmc")&&ja.getJSONObject(j).containsKey("tc")){
				tmacfind(jb,ja,wifiData,j);
			}else{			
			 wifiData.setTs("no");
			 wifiData.setTmc("no");
			 wifiData.setTc("no");
			}
			
			if(jb.containsKey("wssid") ){				
				wifiData.setWssid(ja.getJSONObject(j).getString("wssid"));
			}else{
			 wifiData.setWssid("no");			
			}			
			if(jb.containsKey("wmac") ){				
				wifiData.setWmac(ja.getJSONObject(j).getString("wmac"));
			}else{
				 wifiData.setWmac("no");			
			}			
			if(jb.containsKey("addr") ){				
				wifiData.setAddr(ja.getJSONObject(j).getString("addr"));
			}else{
				wifiData.setAddr("no");			
			}			 
			if(ja.getJSONObject(j).containsKey("ds")){
				wifiData.setDs(ja.getJSONObject(j).getString("ds"));
			}else{
				wifiData.setDs("no");
			}
			if(ja.getJSONObject(j).containsKey("essid0")){
				wifiData.setEssid0(ja.getJSONObject(j).getString("essid0"));
			}else{
				wifiData.setEssid0("no");
			}
			if(ja.getJSONObject(j).containsKey("essid1")){
				wifiData.setEssid1(ja.getJSONObject(j).getString("essid1"));
			}else{
				wifiData.setEssid1("no");
			}
			if(ja.getJSONObject(j).containsKey("essid2")){
				wifiData.setEssid2(ja.getJSONObject(j).getString("essid2"));
			}else{
				wifiData.setEssid2("no");
			}
			if(ja.getJSONObject(j).containsKey("essid3")){
				wifiData.setEssid3(ja.getJSONObject(j).getString("essid3"));
			}else{
				wifiData.setEssid3("no");
			}
			if(ja.getJSONObject(j).containsKey("essid4")){
				wifiData.setEssid4(ja.getJSONObject(j).getString("essid4"));
			}else{
				wifiData.setEssid4("no");
			}
			if(ja.getJSONObject(j).containsKey("essid5")){
				wifiData.setEssid5(ja.getJSONObject(j).getString("essid5"));
			}else{
				wifiData.setEssid5("no");
			}
			if(ja.getJSONObject(j).containsKey("essid6")){
				wifiData.setEssid6(ja.getJSONObject(j).getString("essid6"));
			}else{
				wifiData.setEssid6("no");
			}
			dataList.add(wifiData);
		}
		return dataList;
	}
	
	public static void tmacfind(JSONObject jb,JSONArray ja,SourceData wifiData,int j){
		wifiData.setTs(ja.getJSONObject(j).getString("ts"));
		wifiData.setTmc(ja.getJSONObject(j).getString("tmc"));
		wifiData.setTc(ja.getJSONObject(j).getString("tc"));

	}
}
