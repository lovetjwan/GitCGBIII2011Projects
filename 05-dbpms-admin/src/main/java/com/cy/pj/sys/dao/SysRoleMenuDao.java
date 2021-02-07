package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuDao {
    /**
     * 新增角色菜单关系数据
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertRoleMenu(Integer roleId,Integer[] menuIds);
}

