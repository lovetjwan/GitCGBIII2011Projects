package com.cy.pj.sys.service.impl;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务管理
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuDao sysMenuDao;

    /**
     * 查询所有菜单，请思考，菜单数据会经常变化吗，
     * 假如每次访问菜单数据都查数据库是否会对数据库带来一定的访问压力。
     * 即便没有压力，那访问数据库的性能是不是会低一些？我们能否将查询到的
     * 数据在缓存存放一份，下次再取，从数据库取
     * @return
     */
    @Cacheable("sysMenu")
    @Override
    public List<SysMenu> findMenus() {
        //从缓存取，缓存有则直接返回
        //假如缓存没有，则去查数据库，然后将查询到结果存储到缓存
        return sysMenuDao.selectMenus();
    }


    @CacheEvict(value = "sysMenu",allEntries = true)
    @Override
    public int saveMenu(SysMenu entity) {
        return sysMenuDao.insertMenu(entity);
    }


    /**
     * 清缓存的切入点方法
     * @param entity
     * @return
     */
    @CacheEvict(value = "sysMenu",allEntries = true,beforeInvocation = false)
    @Override
    public int updateMenu(SysMenu entity) {
        return sysMenuDao.updateMenu(entity);
    }

    @Override
    public List<Node> findMenuTreeNodes() {
        return sysMenuDao.selectMentTreeNodes();
    }
}
