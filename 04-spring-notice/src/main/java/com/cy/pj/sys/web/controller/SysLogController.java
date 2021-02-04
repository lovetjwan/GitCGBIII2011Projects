package com.cy.pj.sys.web.controller;

import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.web.pojo.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//restful 风格url（一种编码风格）
//@GetMapping 所有-->/log/
//@GetMapping 某个-->/log/{id}
//@DeleteMapping 某些-->/log/{ids}


@RequestMapping("/log")
@RestController
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @DeleteMapping("/doDeleteById/{ids}")
    public JsonResult doDeleteById(@PathVariable Long... ids){
        sysLogService.deleteById(ids);
        return new JsonResult("delete ok");
    }

    @GetMapping("/doFindById/{id}")
    public JsonResult doFindById(@PathVariable Long id){
        PageInfo<Object> objectPageInfo =
                //lambda(jdk8中特性)
                PageUtil.startPage().doSelectPageInfo(() -> sysLogService.findById(id));
        return new JsonResult(objectPageInfo);
    }

    @GetMapping("/doFindLogs")
    public JsonResult doFindLogs(SysLog sysLog){
        return new JsonResult(sysLogService.findLogs(sysLog));
    }


}
