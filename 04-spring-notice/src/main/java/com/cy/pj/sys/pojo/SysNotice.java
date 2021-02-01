package com.cy.pj.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * SysNotice对象用于存储通知数据（例如从数据库查询到的通知信息）
 * Java中的对象可以简单分为两大类型
 * 1、一类是用于执行逻辑（打天下 -- 控制逻辑，业务逻辑，数据逻辑）的对象
 * 2、一类是用来存储数据（守天下 -- pojo）的对象
 * 2.1、一个对象靠什么存储数据？
 */
//@Setter
//@Getter
//@ToString
@Data //添加此注解后，类中会自动添加setter，getter，toString，hashCode，equals方法
@NoArgsConstructor //为类添加无参构造函数
@AllArgsConstructor //为类添加全参构造函数
public class SysNotice {
    /** 公告 ID */
    private Long id;
    /** 公告标题 */
    private String title;
    /** 公告类型（1 通知 2 公告） */
    private String type;
    /** 公告内容 */
    private String content;
    /** 公告状态（0 正常 1 关闭） */
    private String status;
    /** 创建时间 */
    //当使用DateTimeFormat注解描述此属性时，表示客户端需要按此格式为属性传入值
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")//默认
    //当使用JsonFormat注解描述方法参数时，表示调用此属性的get方法取值并转换为字符串时
    //按照pattern指定的格式进行转换
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /** 修改时间*/
    private Date modifiedTime;
    /** 创建用户 */
    private String createdUser;
    /** 修改用户*/
    private String modifiedUser;
    private String remark;
}
