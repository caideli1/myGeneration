package com.caideli.springBoot.base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/16 18:42
 * 描述：
 */
public interface CommonService<P> {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    P selectByPrimaryKey(Serializable id);

    /**
     * 根据属性条件查询单个对象
     * @param p
     * @return
     */
    P selectOne(P p);

    /**
     * 获取所有
     * @return
     */
    List<P> selectAll();
    /**
     * 获取列表
     * @param p
     * @return
     */
    List<P> selectList(P p);

    /**
     * 获取分页
     * @param p
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<P> selectPage(P p, int pageNum, int pageSize);

    /**
     * 列表插入，返回插入条数
     * @param list
     * @return
     */
    int insertList(List<P> list);

    /**
     * 根据主键修改入参对象里面非空的字段,实体主键里面要加上注解@Id
     * @param p
     * @return
     */
    int updateByPrimaryKeySelective(P p);

    /**
     * 获取单表的数量
     * @param p
     * @return
     */
    int selectCount(P p);

    /**
     * 根据主键删除一条记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Serializable id);

    /**
     * 插入入参对象里面的非空字段
     * @param p
     * @return
     */
    int insertSelective(P p);

    /**
     * 插入之后，入参的对象里面会给主键id赋值
     * @param p
     * @return
     */
    int insertUseGeneratedKeys(P p);

    /**
     * 根据主键判断这条记录是否存在
     * @param id
     * @return
     */
    Boolean existsWithPrimaryKey(Serializable id);
}
