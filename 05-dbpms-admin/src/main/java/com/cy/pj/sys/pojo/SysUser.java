package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 8093994528737653794L;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String email;
    private Integer valid=1;//默认为有效状态,0 代表无效
    private Integer deptId;
    private String deptName;
    /**用户拥有的角色*/
    private List<Integer> roleIds;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
