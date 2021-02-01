package com.cy.pj.sys.service;

import com.cy.pj.sys.pojo.SysNotice;

import java.util.List;

/**此对象为通告业务逻辑对象负责对通告业务做具体落地实现
 * 1、调用数据逻辑对象执行数据操作
 * 2、
 * 3、事务处理
 * 4、缓存
 * 5、权限
 * 6、....
 *
 */
public interface SysNoticeService {

    /**
     * 传入对象进行添加通告信息
     * @param notice
     * @return
     */
    int saveNotice(SysNotice notice);

    /**
     * 基于条件查询通告信息
     * @param notice
     * @return
     */
    List<SysNotice> findNotices(SysNotice notice);

    /**
     * 基于单个或多个id进行删除通告信息
     * @param ids
     * @return
     */
    int deleteById(Long[] ids);

    /**
     * 基于id查询通告信息
     * @param id
     * @return
     */
    SysNotice findById(Long id);

    /**
     * 基于id查询通告信息，然后以传入的对象为主修改通告信息
     * @param notice
     * @return
     */
    int updateNotice(SysNotice notice);
}
