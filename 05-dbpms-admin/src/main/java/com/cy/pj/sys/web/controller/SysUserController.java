package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//DispatcherServlet --> Proxy --> Handle
@CrossOrigin//通过此注解描述的Controller，表示它允许跨域访问
@RequestMapping("/user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequiresPermissions("sys:user:view")
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

    @RequiresPermissions("sys:user:update")
    @PatchMapping("{id}/{valid}")//少量数据的更新可使用Patch请求，当然使用put也可以
    public JsonResult doValidById(@PathVariable Integer id,
                                  @PathVariable Integer valid){
        sysUserService.validById(id,valid);
        return new JsonResult("update ok");
    }

    @GetMapping("/login/{username}/{password}")
    public JsonResult doLogin(@PathVariable String username,@PathVariable String password){
        Subject subject = SecurityUtils.getSubject();
        //执行登录（将用户名和密码提交给securityManager）
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        //配置记住我功能
        token.setRememberMe(true);
        subject.login(token);
        return new JsonResult("登录ok");
    }
}
