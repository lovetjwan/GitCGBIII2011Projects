package com.cy.pj.sys.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过此单元测试
 * @SpringBootTest 注解描述的类
 * 为springboot中的单元测试类
 * 说明：
 * 1、springboot中的单元测试类必须放在启动类所在包或子包中
 * 2、springboot中的单元测试类必须使用@SpringBootTest注解描述
 */
@SpringBootTest
public class DataSourceTests {
    /**
     * 在项目中添加了数据库相关依赖以后，springboot底层会自动帮我们配置一个
     * 数据源(DataSource)对象，此对象是连接池的规范
     * @Autowired注解描述属性时，是告诉spring框架，要基于反射机制为属性赋值（依赖注入）
     * 赋值时，首先会基于属性类型从spring容器查找相匹配的对象，假如只有一个则直接注入
     * 有多个相同类型的对象时，还会比较属性名（检测属性名是否与bean名字相同），有相同的则直接注入
     * 没有相同的直接抛出异常
     */
    @Autowired
    private DataSource dataSource;//HikariDataSource(类)

    @Test//org.junit.jupiter.api.Test
    void testGetConnection() throws SQLException {
        //获取链接时，会基于dataSource获取连接池对象，进而从池中获取连接
        Connection conn = dataSource.getConnection();
        System.out.println("conn="+conn);
    }

    @Test
    void testSaveNotice01() throws SQLException{//homework(通过此方法基于jdbc向数据库写入一条数据)
        //JDBC(是java中推出的连接数据库的一组API，是规范)
        //数据库厂商提供JDBC驱动(jdbc规范的实现)负责实现数据库的操作
        //1、建立连接(负责与数据库进行通讯)
        Connection conn = dataSource.getConnection();
        //2、创建statement(sql传送器->负责与将sql发送到数据库端)
        String sql = "insert into sys_notice " +
                "(title,content,type,status,createdTime,createdUser,modifiedTime,modifiedUser)"+
                "values ('加速通知','本周六加课','1','0',now(),'tony',now(),'tony')";
        Statement stmt = conn.createStatement();
        //3、发送sql
        boolean flag = stmt.execute(sql);
        //4、处理结果
        if (flag){
            System.out.println("insert ok");
        }
        //5、释放资源
        stmt.close();
        conn.close();
    }

    @Test
    void testSaveNotice02() throws SQLException{//homework(通过此方法基于jdbc向数据库写入一条数据)
        //JDBC(是java中推出的连接数据库的一组API，是规范)
        //数据库厂商提供JDBC驱动(jdbc规范的实现)负责实现数据库的操作
        //1、建立连接(负责与数据库进行通讯)
        Connection conn = dataSource.getConnection();
        //2、创建statement(sql传送器->负责与将sql发送到数据库端)
        String sql = "insert into sys_notice " +
                "(title,content,type,status,createdTime,createdUser,modifiedTime,modifiedUser)"+
                "values (?,?,?,?,?,?,?,?)";//?表示占位符
        PreparedStatement stmt = conn.prepareStatement(sql);//预编译方式创建Statement
        //3、发送sql
        //3.1给sql中的?赋值
        stmt.setString(1,"开学通知");
        stmt.setString(2,"2021年3月18号 开学");
        stmt.setString(3,"1");
        stmt.setString(4,"0");
        stmt.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
        stmt.setString(6,"jason");
        stmt.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
        stmt.setString(8,"tony");
        stmt.execute();
        //3.2发送sql
        //4、处理结果
        //5、释放资源(后续释放资源要写到finally代码块中)
        stmt.close();
        conn.close();
    }

    @Test
    void testSelectNotice() throws SQLException{
        Connection conn = dataSource.getConnection();
        String sql = "select id,title,content,status,type,createdTime from sys_notice where id>=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //3.发送sql
        pstmt.setInt(1,2);
        //4.处理结果
        boolean flag = pstmt.execute();
        ResultSet rs = null;
        if (flag){//true表示查询，有结果集
            //获取结果集(二维表)
            rs = pstmt.getResultSet();
            List<Map<String,Object>> list = new ArrayList<>();
            //获取结果集中的元数据+
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){//一行记录应为一个map对象（行映射）
                Map<String,Object> map = new HashMap();//将来也可以使用pojo
                //将取出类的数据存储到map（key为字段名，值为字段value）
//                map.put("id",rs.getInt("id"));
//                map.put("title",rs.getString("title"));
//                map.put("content",rs.getString("content"));
//                map.put("status",rs.getString("status"));
//                map.put("type",rs.getString("type"));
//                map.put("createdTime",rs.getTimestamp("createdTime"));
                for (int i=1;i<=rsmd.getColumnCount();i++){
                    map.put(rsmd.getColumnLabel(i),rs.getObject(rsmd.getColumnLabel(i)));
                }
                //...
                //将每行记录对应的map对象存储到list集合
                System.out.println(map);
                list.add(map);
            }
        }
        //5.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

}
