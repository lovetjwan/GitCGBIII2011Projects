package com.cy;

import com.cy.pj.sys.pojo.SysNotice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JSONTests {

    @Test
    void testPojoToJason() throws JsonProcessingException {
        SysNotice notice = new SysNotice();
        notice.setId(100L);
        notice.setTitle("SpringBoot 技术分享");
        ObjectMapper objectMapper = new ObjectMapper();
        //将pojo对象转换为json格式字符串
        String jsonStr = objectMapper.writeValueAsString(notice);
        System.out.println(jsonStr);
//        String[] ids = {"1","2","3"};
//        String idsStr = objectMapper.writeValueAsString(ids);
//        System.out.println(idsStr);
        //将json结构的字符串转换为pojo对象
        notice = objectMapper.readValue(jsonStr,SysNotice.class);
        System.out.println(notice);
    }
}
