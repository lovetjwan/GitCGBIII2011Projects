package com.cy.pj.sys.web.controller;

import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysNotice;
import com.cy.pj.sys.service.SysNoticeService;
import com.cy.pj.sys.web.pojo.JsonResult;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 这个类我们称为控制层对象的处理器（Handler），通过此对象处理
 * DispatcherServlet（核心处理器-Contorller）分发的请求，具体的
 * 处理包括：
 * 1、定义请求url映射
 * 2、通过参数对象封装请求参数数据
 * 3、调用业务方法处理业务逻辑
 * 4、封装处理结果交给DispatcherServlet进行处理
 * restful风格url，对于notice模块而言
 * 查询操作：@GetMapping /notice/
 * 添加操作：@PostMapping /notice/
 * 修改操作：@PutMapping /notice/
 * 删除操作：@DeleteMapping /notice/{ids}
 * 基于id查找 @GetMapping /notice/{id}
 */
@RequestMapping("/notice")
@RestController
//@Controller
//@ResponseBody
public class SysNoticeController {//这里写的controller又称为handler

    @Autowired
    private SysNoticeService sysNoticeService;

    //http://localhost:8080/notice/doFindNotices
    //http://localhost:8080/notice/doFindNotices?type=1&modifiedUser=tony
    //此方法会由dispatcher（controller）对象通过反射进行调用
    //dispatcherServlet拿到请求中参数时会将参数注入给反射调用的方法参数
//    @RequestMapping(value = "/doFindNotices",method = RequestMethod.GET)
//    @ResponseBody
//    @GetMapping("/doFindNotices")
    @GetMapping
    public JsonResult doFindNotices(SysNotice notice){//参数：HttpServletRequest request
//        String pageSize = request.getParameter("pageSize");
//        System.out.println("pageSize="+10);
//        PageInfo<Object> objectPageInfo = PageUtil.startPage().doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                sysNoticeService.findNotices(notice);
//            }
//        });
        PageInfo<Object> objectPageInfo =
                //lambda(jdk8中特性)
                PageUtil.startPage().doSelectPageInfo(() -> sysNoticeService.findNotices(notice));
        return new JsonResult(objectPageInfo);
        //此方法的返回值会交给DispatcherServlet
        //假如方法有@ResponBody注解修饰，DispatcherServlet会调用jackson api将方法的返回值转换为Json格式字符串
        //底层转换时会调用返回值对象的get方法,以get方法的方法名（去掉get单词）为key，get方法的返回值为value
        //来构建json格式字符串
    }

//    @RequestMapping(value = "/doSaveNotice", method = RequestMethod.POST)

    /**
     * 假如方法参数使用了@RequestBody描述，客户端数据的提交要求：
     * 1、请交方式：post
     * 2、请求头Content-Type设计：application/json
     * 3、请求数据格式{"key1":"v1","key2":2,.....}
     *
     * @param notice
     * @return
     */
//    @PostMapping("/doSaveNotice")
    @PostMapping
    public JsonResult doSaveNotice(SysNotice notice){
//        try {
            sysNoticeService.saveNotice(notice);
//        }catch (Throwable e){
//            return new JsonResult(e);
//        }

//        System.out.println("save ok");
        return new JsonResult("save ok");
    }

    /**
     * 基于id查询notice对象
     */
    //普通方式url
    //http://ip:port/notice/doFindById?id=1
    //rest风格url（软件架构编码风格），允许在url中定义变量，基于{}方式定义变量
    //rest风格应用目的：1、跨服务端，2、通用url设计。（让url的设计更加通用和简单）
    //http://ip:port/notice/doFinById/1
    //方法参数的值假如来自url中{var}变量的值，则需要使用@PathVariable注解对参数进行声明
//    @GetMapping("/doFindById/{id}")
    @GetMapping("{id}")
    public JsonResult doFindById(@PathVariable("id") Long id){
//  表示参数必须传入否则报400错误，可以指定名称，传入参数时会进行转换
//  public SysNotice doFindById(@RequestParam("aid") Long id){
        return new JsonResult(sysNoticeService.findById(id));
    }

//    @PutMapping("/doUpdateNotice")
    @PutMapping
    public JsonResult doUpdateNotice(SysNotice notice){
        sysNoticeService.updateNotice(notice);
        return new JsonResult("update ok");
    }

//    @DeleteMapping("/doDeleteById{ids}")
    @DeleteMapping("{ids}")
    public JsonResult doDeleteById(@PathVariable Long... ids){
        sysNoticeService.deleteById(ids);
        return new JsonResult("delete ok");
    }

}

/**
 * 请求响应处理
 * 1、请求url的定义（普通风格url，rest风格url）
 * 2、请求方式的设计（@GetMapping,@PostMapping,@DeleteMapping,@PutMapping,@RequestMapping）
 * 3、请求参数设计（直接量,pojo对象）
 * 4、请求参数修饰（@PathVariable - 从url接收数据，@RequstBody - 告诉服务端接收json数据，@RequestParam - 对传统请求参数进行约束）
 * 响应数据
 * 1、响应标准设计
 * 2、响应数据转换（json）
 * 3、统一异常的处理（？）
 */
