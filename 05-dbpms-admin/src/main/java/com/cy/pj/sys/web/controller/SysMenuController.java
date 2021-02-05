package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/menu")
@RestController
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping
    public JsonResult doFindMenus(){
        return new JsonResult(sysMenuService.findMenus());
    }

    @GetMapping("/treeNodes")
    public JsonResult doFindMenuTreeNodes(){
        return new JsonResult(sysMenuService.findMenuTreeNodes());
    }

    @PostMapping
    public JsonResult doSaveMenu(@RequestBody SysMenu menu){
        sysMenuService.saveMenu(menu);
        return new JsonResult("Save ok");
    }

    @PutMapping
    public JsonResult doUpdateMenu(@RequestBody SysMenu menu){
        sysMenuService.updateMenu(menu);
        return new JsonResult("Update ok");
    }
}
