package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;

import java.util.List;

public interface SysMenuService {

    /**
     * 查询所有菜单信息以及菜单对应的上级菜单名称
     * @return
     */
    List<SysMenu> findMenus();

    /**
     * 新增菜单信息
     * @param entity
     * @return
     */
    int saveMenu(SysMenu entity);

    /**
     * 更新菜单信息
     * @param entity
     * @return
     */
    int updateMenu(SysMenu entity);

    /**
     * 查找菜单树节点信息
     * @return
     */
    List<Node> findMenuTreeNodes();
}
