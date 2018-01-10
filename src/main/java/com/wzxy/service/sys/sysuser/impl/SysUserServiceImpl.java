package com.wzxy.service.sys.sysuser.impl;

import cn.assist.easydao.common.*;
import cn.assist.easydao.dao.BaseDao;
import cn.assist.easydao.pojo.PagePojo;
import com.wzxy.base.cache.MCache;
import com.wzxy.base.utils.CommonUtil;
import com.wzxy.base.utils.MD5;
import com.wzxy.base.utils.RecordBean;
import com.wzxy.service.sys.sysuser.ISysUserService;
import com.wzxy.service.vo.sys.SysAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统用户service
 * 
 * @author caibin
 *
 */
@Service("ISysUserService")
public class SysUserServiceImpl implements ISysUserService {
	/**
	 * 登录
	 * 
	 * @param username
	 * @param pswd
	 * @return
	 */
	public RecordBean<SysAction.SysUser> login(String username, String pswd){
		String cipher = MD5.encode(pswd);
		Conditions conn = new Conditions("name", SqlExpr.EQUAL, username);
		conn.add(new Conditions("pswd", SqlExpr.EQUAL, cipher), SqlJoin.AND);
		SysAction.SysUser sysUser = BaseDao.dao.queryForEntity(SysAction.SysUser.class,conn);
		if (sysUser == null) {
			return RecordBean.error("用户名或密码错误！");
		}
		if(sysUser.getIsValid() != SysUserConstant.USET_DALID_YES){
			return RecordBean.error("登录失败，用户无效！");
		}
		return RecordBean.success("成功登录",sysUser);
	}
	

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public SysAction.SysUser getSysUser(String username) {
		Conditions conn = new Conditions("name", SqlExpr.EQUAL, username);
		SysAction.SysUser sysUser = BaseDao.dao.queryForEntity(SysAction.SysUser.class, conn);
		return sysUser;
	}
	
	/**
	 * 根据uid查询系统用户信息
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@MCache(expire = 30)
	public SysAction.SysUser getSysUser(int uid){
		SysAction.SysUser sysUser = BaseDao.dao.queryForEntity(SysAction.SysUser.class, uid);
		return sysUser;
	}

	/**
	 * 分页查询系统用户
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@MCache(expire = 30)
	public PagePojo<SysAction.SysUser> getSysUsers(Map<String,Object> map, int pageNo, int pageSize) {
		//查询条件
		StringBuffer sql = new StringBuffer();
		List<Object> params= new ArrayList<Object>();
		sql.append("SELECT * FROM sys_user ");
		String name = (String) map.get("name");
		String realName = (String) map.get("realName");
		String mobile = (String) map.get("mobile");
		String createTime = (String) map.get("createTime");
		String lastLoginTime = (String) map.get("lastLoginTime");
		Integer isValid = (Integer) map.get("isValid");

		sql.append("WHERE 1=1 ");
		if (StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name)) {
			sql.append("AND name like ? ");
			params.add(CommonUtil.queryLike(name.trim()));
		}
		if (StringUtils.isNotBlank(realName) && StringUtils.isNotEmpty(realName)) {
			sql.append("AND real_name like ? ");
			params.add(CommonUtil.queryLike(realName.trim()));
		}
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotEmpty(mobile)) {
			sql.append("AND mobile = ?");
			params.add(CommonUtil.queryLike(mobile.trim()));
		}
		if (StringUtils.isNotBlank(createTime) && StringUtils.isNotEmpty(createTime)) {
			sql.append("AND create_time BETWEEN ? AND ? ");
			String time[] = createTime.split(" ~ ");
			params.add(time[0]);
			params.add(time[1]);
		}
		if (StringUtils.isNotBlank(lastLoginTime) && StringUtils.isNotEmpty(lastLoginTime)) {
			sql.append("AND last_login_time BETWEEN ? AND ? ");
			String time[] = lastLoginTime.split(" ~ ");
			params.add(time[0]);
			params.add(time[1]);
		}
		if (isValid != null) {
			sql.append("AND is_valid = ? ");
			params.add(isValid);
		}

		Sort sort = new Sort("uid", SqlSort.ASC);
		return BaseDao.dao.queryForListPage(SysAction.SysUser.class, sql.toString(),params, sort, pageNo, pageSize);
	}

	/**
	 * 添加 系统用户
	 * 
	 * @param sysUser
	 */
	public boolean addSysUsers(SysAction.SysUser sysUser) {
		String cipher = MD5.encode(sysUser.getPswd());
		sysUser.setPswd(cipher);
		int i = BaseDao.dao.insert(sysUser);
		return i == 1;
	}

	/**
	 * 编辑 系统用户
	 * 
	 * @param sysUser
	 */
	public boolean editSysUsers(SysAction.SysUser sysUser) {
		if (sysUser.getIsValid() == null) {
			sysUser.setIsValid(SysUserConstant.USET_DALID_NO);
		}
		int i = BaseDao.dao.update(sysUser);
		return i == 1;
	}

	@Override
	public int delSysUsers(String[] ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE sys_user set is_valid = 0 AND update_time = now() WHERE uid IN(");
		sql.append(CommonUtil.arrayToSqlIn(ids));
		sql.append(")");
		return BaseDao.dao.update(sql.toString());
	}

	/**
	 * 修改密码
	 * @param sysUser
	 * @return
	 */
	@Override
	public boolean updatePwd(SysAction.SysUser sysUser) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE sys_user SET pswd = ? ,update_time = now() WHERE uid = ?");
		int result = BaseDao.dao.update(sql.toString(),MD5.encode(sysUser.getPswd()),sysUser.getUid());
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据用户手机号获取用户信息
	 * @param mobile
	 * @return
	 */
	@Override
	public SysAction.SysUser getSysUserByMobile(String mobile) {
		return BaseDao.dao.queryForEntity(SysAction.SysUser.class,new Conditions("mobile",SqlExpr.EQUAL,mobile));
	}
}
