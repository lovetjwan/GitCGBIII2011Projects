package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Mapper注解由mybatis框架定义，用于描述数据持久层
 */
@Mapper
public interface SysNoticeDao {//系统底层会基于JDK中的Proxy API为此接口产生一个实现类

    /**
     * 将内存中的notice对象，持久化到数据库
     */
    int insertNotice(SysNotice notice);

    /**
     * 基于id查询notice
     * @param id 查询条件
     * @return 查询到的notice对象（存储表中的一行记录）
     * 说明：简单的sql映射可以直接将sql语句写到方法上面，以注解进行声明
     */
    @Select("select * from sys_notice where id=#{id}")
    SysNotice selectById(@Param("id") Long id);


    /**
     * 基于id更新notice
     * @param notice 更新对象
     * @return 更新后返回受影响的行数
     */
    int updateNotice(SysNotice notice);

    /**
     * 基于多个id执行记录删除操作
     * @param ids 要删除的记录id，这里的语法为可变参数（可以看成特殊数组）
     * 可变参数主要是用于简化名字相同，参数类型也相同，但个数不同的这样的一系列方法的叫做重载
     * @return
     */
    int deleteById(@Param("ids") Long... ids);//1,2,3

    /**
     * 基于条件查询通告信息
     * @param notice
     * @return 返回查询到的通告信息
     */
    List<SysNotice> selectNotices(SysNotice notice);

}

