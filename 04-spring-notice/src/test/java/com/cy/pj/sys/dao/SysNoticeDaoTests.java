package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysNotice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysNoticeDaoTests {

    @Autowired
    private SysNoticeDao sysNoticeDao;

    @Test
    void testInsertNotice(){
        //创建SysNotice对象,通过此对象封装要写入到数据库的数据
        SysNotice notice = new SysNotice();
        notice.setTitle("CGB2011结课时间");
        notice.setContent("2021/3/20正式结课");
        notice.setStatus("0");
        notice.setType("1");
        notice.setRemark(null);
        notice.setCreatedUser("tony");
        notice.setModifiedUser("tony");
        sysNoticeDao.insertNotice(notice);
    }

    @Test
    void testSelectById(){
        SysNotice notice = sysNoticeDao.selectById(1L);
        System.out.println(notice);
    }

    @Test
    void testUpdateNotice(){
        //基于id查询
        SysNotice notice = sysNoticeDao.selectById(1L);
        notice.setType("2");
        notice.setContent("2021/07/09 春节假期");
        notice.setModifiedUser("json");
        //将更新以后的内容持久化到数据库
        sysNoticeDao.updateNotice(notice);
    }

    @Test
    void testDeleteById(){
        int rows = sysNoticeDao.deleteById();
        System.out.println("rows="+rows);
    }

    @Test
    void testSelectNotices(){
        SysNotice notice = new SysNotice();
        notice.setType("1");
        notice.setModifiedUser("tony");
        notice.setTitle("开学");
        List<SysNotice> list = sysNoticeDao.selectNotices(notice);
        for(SysNotice n : list){
            System.out.println(n);
        }
    }
}
