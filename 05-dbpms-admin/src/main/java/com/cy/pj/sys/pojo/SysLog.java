package com.cy.pj.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysLog {
    private Integer id;
    private String ip;
    private String username;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private Integer status=1;
    private String error;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
}
