package com.wzxy.dao;

import com.wzxy.dao.support.BaseDaoAbstract;
import com.wzxy.service.model.guestflow.GuestFlow;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GuestFlowDao extends BaseDaoAbstract<GuestFlow> {

	@Override
	public Class<GuestFlow> getEntityClass() {
		return GuestFlow.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<GuestFlow> findGuestFlowbyTime(String time){
		StringBuffer queryString = new StringBuffer();
		queryString.append("from GuestFlow where 1=1 ");
		queryString.append("and dataTime like '"+time+"%' ");
		return findSession().createQuery(queryString.toString()).list();
	}
	
	
}
