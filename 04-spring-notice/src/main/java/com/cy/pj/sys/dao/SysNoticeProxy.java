package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysNotice;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Respository注解由spring框架提供，用于描述数据逻辑对象
 * 同样这样的对象会交给spring去管理，是spring容器的一个bean
 */
@Repository
public class SysNoticeProxy {//模拟mybatis为接口产生的实现类

    @Autowired
    private SqlSession sqlSession;

    public int insertNotice(SysNotice notice){
        this.getClass().getInterfaces()[0].getName();
        //数据持久化（底层会基于statement找到对应的sql）
        String statement = "com.cy.pj.sys.dao.SysNoticeDao.insertNotice";
        int rows = sqlSession.insert(statement,notice);
        return rows;
    }
}
