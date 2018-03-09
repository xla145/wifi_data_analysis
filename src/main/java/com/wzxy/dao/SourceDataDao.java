package com.wzxy.dao;

import com.wzxy.base.utils.BaseUtils;
import com.wzxy.dao.support.BaseDaoAbstract;
import com.wzxy.service.dto.GuestFlowDTO;
import com.wzxy.service.model.sourcedata.SourceData;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SourceDataDao extends BaseDaoAbstract<SourceData> {

	@Override
	public Class<SourceData> getEntityClass() {
		return SourceData.class;
	}
	//简单的数据筛选分析
	@SuppressWarnings("unchecked")
	public List<GuestFlowDTO> findGuestFlow(String time){
		StringBuffer queryString =new StringBuffer();
		queryString .append("select new com.wifi.business.dto.GuestFlowDTO ");
		queryString.append("(ds.id,ds.wid,ds.mac,ds.time) ");
		queryString.append("from SourceData ds where ds.time like '"+time+"%' ");
		queryString.append(" group by ds.mac having count(ds.mac)>1 order by ds.time ");
		//queryString.append("from SourceData where 1=1");
		return findSession().createQuery(queryString.toString()).list();
	}
	
	public int insertGuestFlowByTime(Object time){
		StringBuffer queryString =new StringBuffer();
		queryString .append("insert into guest_flow(dataTime,countMac) ");
		queryString.append(" select left(time,13),count(distinct mac) from source_data ");
		queryString.append(" where time like '"+time+"%' ");
		return findSession().createNativeQuery(queryString.toString()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findHourTimebyTime(Object time){
		StringBuffer queryString =new StringBuffer();
		queryString.append("select left(sd.time,13) as hourTime from source_data sd where 1=1 ");
		if(!BaseUtils.isEmpty(time))
			queryString.append(" and sd.time like '"+time+"%' ");
		queryString.append(" group by hourTime");
		return findSession().createNativeQuery(queryString.toString()).list();
	}
	
}
