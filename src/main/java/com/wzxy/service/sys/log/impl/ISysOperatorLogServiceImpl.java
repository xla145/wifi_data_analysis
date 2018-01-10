package com.wzxy.service.sys.log.impl;

import cn.assist.easydao.dao.BaseDao;
import com.wzxy.service.sys.log.ISysOperatorLogService;
import com.wzxy.service.vo.sys.SysOperatorLog;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/21/021.
 */
@Service("ISysOperatorLogService")
public class ISysOperatorLogServiceImpl implements ISysOperatorLogService {

    @Override
    public void addOperatorLog(SysOperatorLog sysOperatorLog) {
        BaseDao.dao.insert(sysOperatorLog);
    }
}
