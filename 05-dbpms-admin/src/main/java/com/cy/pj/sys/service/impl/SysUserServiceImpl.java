package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @RequiredLog(operation = "查询用户信息")
    @Override
    public List<SysUser> findUsers(SysUser entity) {
        return sysUserDao.selectUsers(entity);
    }

    @RequiredLog(operation = "查询用户个人信息")
    @Override
    public SysUser findById(Integer id) {
        //查询用户以及用户对应的部门信息
        SysUser user = sysUserDao.selectById(id);
        if(user == null)
            throw new ServiceException("没有找到对应的用户");
        //查询用户对应的角色信息
        List<Integer> roleIds = sysUserRoleDao.selectRoleIdsByUserId(id);
        //将查询到的角色id封装到user对象
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public int saveUser(SysUser entity) {
        //1.保存用户自身信息
        //1.1对参数进行校验
        //1.2对密码进行加密(MD5盐值加密-MD5特点(不可逆，相同内容加密结果也相同))
        String password = entity.getPassword();
        String salt = UUID.randomUUID().toString();//随机字符串
        //借用shiro框架中的api对密码进行md5加密
        SimpleHash simpleHash = new SimpleHash("MD5",password,salt,1);
        entity.setSalt(salt);
        //将加密结果转换为16进制字符串（建议）
        entity.setPassword(simpleHash.toHex());
        //1.3将用户信息持久化到数据库
        int rows = sysUserDao.insertUser(entity);
        //保存用户和角色关系数据
        sysUserRoleDao.insertUserRoles(entity.getId(),entity.getRoleIds());
        return rows;
    }

    @Override
    public int updateUser(SysUser entity) {
        //更新用户自身信息
        int rows = sysUserDao.updateUser(entity);
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
        //2.更新用户和角色关系数据
        //2.1删除原有用户和角色关系数据
        sysUserRoleDao.deleteByRoleId(entity.getId());
        //2.2添加新的用户和角色关系数据
        sysUserRoleDao.insertUserRoles(entity.getId(),entity.getRoleIds());
        return rows;
    }

    /**
     * 更新用户状态
     * @param id 用户id
     * @param valid 用户状态
     * @return
     * @RequiresPermissions 注解由shiro框架定义，基于此注解定义授权切入点方法
     * 也就是说用户访问此方法时，需要授权才可访问。shiro框架底层会基于登录用户
     * 获取登录用户权限（菜单的权限标识），然后判定用户权限中是否包含@RequiresPermissions
     * 注解中定义的权限标识。
     */
    @Override
    public int validById(Integer id, Integer valid) {
        //获取登录用户
        //获取登录用户权限
        //检测用户权限中是否包含访问此方法的权限
        //这里的admin为假数据，以后需要在加上
        int rows = sysUserDao.validById(id, valid, "admin");
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }
}
