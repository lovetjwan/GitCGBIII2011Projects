package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysLogDao {

    int insertLog(SysLog entity);

    int deleteById(Long... ids);

    SysLog findById(Long id);

    List<SysLog> selectLogs(SysLog sysLog);
}
