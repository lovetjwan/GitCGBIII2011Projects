package com.cy.pj.sys.dao;


import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
public class MyBatisTests {

    /**
     * SqlSession是mybatis框架中实现与数据库进行会话的入口对象
     * 假如我们可以通过此对象获取一个与数据库的连接，表示可以通过mybatis
     * 框架实现与数据库会话。
     */
    @Autowired
    private SqlSession sqlSession;//这里的sqlSession指向的对象是SqlSessionTemplate

    @Test
    void testGetConnection(){
        //连接来自哪里？(来自于连接池)
        Connection conn = sqlSession.getConnection();
        System.out.println("conn="+conn);



    }
}
