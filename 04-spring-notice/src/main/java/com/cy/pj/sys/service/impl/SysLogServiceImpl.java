package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * @Async //此注解描述的方法为一个异步切入点方法，
     * 这个方法会在切面通知方法中通过一个新的线程调用执行,
     * 这里的线程由springboot内置的线程池提供
     * @param sysLog
     */
    @Async
    @Override
    public void saveLog(SysLog sysLog) {
        String tName = Thread.currentThread().getName();
        System.out.println("SysLogService.saveLog.threadName="+tName);
        sysLogDao.insertLog(sysLog);
        //假如方法有返回值，此时我们要返回具体值时候，可以
        //return new AsyncResult<>(返回结果).get()
    }

    //异步方法如果需要返回内容，则需要封装在AsyncResult中,否则会报错
//    @Async
//    public Integer saveLog(SysLog sysLog) throws Exception {
////        String tName = Thread.currentThread().getName();
////        System.out.println("SysLogService.saveLog.threadName="+tName);
//        int rows = sysLogDao.insertLog(sysLog);
//        return new AsyncResult<Integer>(rows).get();
//    }

    @RequiredLog(operation = "删除日志")
    @Override
    public int deleteById(Long... ids) {
        return sysLogDao.deleteById(ids);
    }

    @Override
    public SysLog findById(Long id) {
        return sysLogDao.findById(id);
    }

    @Override
    public List<SysLog> findLogs(SysLog sysLog) {
        return sysLogDao.selectLogs(sysLog);
    }
}
