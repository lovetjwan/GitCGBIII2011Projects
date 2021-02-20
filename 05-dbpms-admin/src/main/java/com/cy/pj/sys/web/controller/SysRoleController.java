package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.github.pagehelper.ISelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/role")
@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/doTest")
    public void doTest(SysRole entity){//Integer[],Integer...,List<Interger>
        System.out.println("entity=" + entity.getMenuIds());
    }

    @GetMapping
    public JsonResult doFindRoles(SysRole entity){
        return new JsonResult(PageUtil.startPage().doSelectPageInfo(() ->
                sysRoleService.findRoles(entity)
        ));//lambda(jdk8 中特性)
    }

    /**
     *
     * @param entity
     * @return
     */
    @PostMapping
    public JsonResult doSaveRoles(@RequestBody SysRole entity){
        sysRoleService.saveRole(entity);
        return new JsonResult("save ok");
    }

    /**
     * 基于id查找角色以及角色对应的菜单关系数据
     * @param id
     * @return
     * 访问url：http://localhost/role/50
     */
    @GetMapping("/{id}")
    public JsonResult doFindById(@PathVariable Integer id){
        return new JsonResult(sysRoleService.findById(id));
    }

    @PutMapping
    public JsonResult doUpdateRole(@RequestBody SysRole entity){
        sysRoleService.updateRole(entity);
        return new JsonResult("update ok");
    }
}
