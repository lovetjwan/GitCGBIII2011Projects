package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.annotation.RequiredTime;
import com.cy.pj.sys.dao.SysNoticeDao;
import com.cy.pj.sys.pojo.SysNotice;
import com.cy.pj.sys.service.SysNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Service注解由spring提供
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    /**
     * 创建日志门面API对象
     * 通过API提供的日志工厂创建日志对象，而日志对象需要指定包字节码对象或全限定类名来表示记录指定类的日志
     */
    private static final Logger log = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

    @Autowired
    private SysNoticeDao sysNoticeDao;

    //重写方法的生成（选中类，然后alt+enter）
    @RequiredLog(operation = "新增公告记录")
    @Override
    public int saveNotice(SysNotice notice) {
        int rows = sysNoticeDao.insertNotice(notice);
        return rows;
    }

    //RequiredLog注解描述的方法为日志切入点方法
    @RequiredLog(operation = "查询公告列表")
    @Override
    public List<SysNotice> findNotices(SysNotice notice) {
        String tName = Thread.currentThread().getName();
        System.out.println("SysNoticeService.findNotices.threadName="+tName);
//        log.debug("start: {}",System.currentTimeMillis());//这里的{}表示占位符
        List<SysNotice> list = sysNoticeDao.selectNotices(notice);
//        log.debug("end: {}",System.currentTimeMillis());
        return list;
    }

    @RequiredLog(operation = "删除公告记录")
    @Override
    public int deleteById(Long[] ids) {
        //检查用户权限
        //开启事务
        //log.debug("start: {}",System.currentTimeMillis())
        int rows = sysNoticeDao.deleteById(ids);
        if(rows==0)throw new RuntimeException("记录可能已经不存在");
        return rows;
    }

    //@RequiredCaches
    @RequiredLog(operation = "基于id查询通知")
    @RequiredTime
    @Override
    public SysNotice findById(Long id) {
        SysNotice notice = sysNoticeDao.selectById(id);
        return notice;
    }

    @RequiredLog(operation = "修改公告记录")
    @Override
    public int updateNotice(SysNotice notice) {
        int rows = sysNoticeDao.updateNotice(notice);
        return rows;
    }
}
