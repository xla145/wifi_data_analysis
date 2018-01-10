package com.wzxy.service.sys.log;

import com.wzxy.service.vo.sys.SysOperatorLog;

/**
 * Created by Administrator on 2017/9/21/021.
 */
public interface ISysOperatorLogService {

    /**
     * 添加系统日志信息
     * @param sysOperatorLog
     */
    public void addOperatorLog(SysOperatorLog sysOperatorLog);

}
