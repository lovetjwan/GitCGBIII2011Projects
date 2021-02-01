package com.cy.pj.sys.service;

import com.cy.pj.sys.pojo.SysNotice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysNoticeServiceTests {

    @Autowired
    private SysNoticeService sysNoticeService;

    @Test
    void testFindNotices(){
        SysNotice notice = new SysNotice();
        notice.setType("1");
        notice.setModifiedUser("tony");
        List<SysNotice> notices = sysNoticeService.findNotices(notice);
        System.out.println(notices);
    }

}
