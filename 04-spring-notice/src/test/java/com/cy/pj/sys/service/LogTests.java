package com.cy.pj.sys.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


// 如果不想每次都写private final Logger logger = LoggerFactory.getLogger(当前类名.class);
// 可以用注解@Slf4j;
// log级别：ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF。
// All:最低等级的，用于打开所有日志记录.
// Trace:是追踪，就是程序推进一下.
// Debug:指出细粒度信息事件对调试应用程序是非常有帮助的.
// Info:消息在粗粒度级别上突出强调应用程序的运行过程.
// Warn:输出警告及warn以下级别的日志.
// Error:输出错误信息日志.
// Fatal:输出每个严重的错误事件将会导致应用程序的退出的日志.
// OFF:最高等级的，用于关闭所有日志记录

@SpringBootTest
public class LogTests {

    private static final Logger log = LoggerFactory.getLogger(LogTests.class);

    @Test
    void testLevel(){
        //level: trace<debug<info<warn<error
        //当我们在springboot配置文件中指定的日志
        log.trace("log.level.trace");
        log.warn("log.level.warn");
        log.info("log.level.info");
        log.debug("log.level.debug");
        log.error("log.level.error");
    }
}
