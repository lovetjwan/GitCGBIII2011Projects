package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {
    /**
     * 新增角色菜单关系数据
     * @param roleId
     * @param menuIds
     * @return
     * BindingException Parameter "menuIds" not found
     * 说明，在jdk8之前是不可以直接获取参数名称，需要使用@Param注解对参数进行描述,然后在sql映射文件
     * 中直接取@Param注解内部声明的名字
     */
    int insertRoleMenus(@Param("roleId") Integer roleId,@Param("menuIds") List<Integer> menuIds);

    /**
     * 基于角色id删除角色和菜单关系数据
     * @param roleId 角色id
     * @return 删除的行数
     */
    @Delete("delete from sys_role_menus where role_id=#{id}")
    int deleteByRoleId(Integer roleId);
}

