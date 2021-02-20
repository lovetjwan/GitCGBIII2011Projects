package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping
    public JsonResult doFindUsers(SysUser entity){
        return new JsonResult(PageUtil.startPage().doSelectPageInfo(
                () -> sysUserService.findUsers(entity)));
    }

    @PostMapping
    public JsonResult doSaveUser(@RequestBody SysUser entity){
        sysUserService.saveUser(entity);
        return new JsonResult("save ok");
    }

    @GetMapping("{id}")
    public JsonResult doFindById(@PathVariable Integer id){
        return new JsonResult(sysUserService.findById(id));
    }

    @PutMapping
    public JsonResult doUpdateUser(@RequestBody SysUser entity){
        sysUserService.updateUser(entity);
        return new JsonResult("update ok");
    }

    @PatchMapping("{id}/{valid}")//少量数据的更新可使用Patch请求，当然使用put也可以
    public JsonResult doValidById(@PathVariable Integer id,
                                  @PathVariable Integer valid){
        sysUserService.validById(id,valid);
        return new JsonResult("update ok");
    }
}
