package com.caideli.springBoot.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/16 18:43
 * 描述：
 */
public class CommonServiceImpl<P> implements CommonService<P> {

    protected CommonMapper<P> commonMapper;

    private Class<P> pClass;

    public CommonServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        pClass = (Class<P>) pt.getActualTypeArguments()[0];
    }

    @Override
    public P selectByPrimaryKey(Serializable id) {
        validate(id);
        return commonMapper.selectByPrimaryKey(id);
    }

    @Override
    public P selectOne(P p) {
        return commonMapper.selectOne(p);
    }

    @Override
    public List<P> selectAll() {
        return commonMapper.selectAll();
    }

    @Override
    public List<P> selectList(P p) {
        return commonMapper.select(p);
    }

    @Override
    public PageInfo<P> selectPage(P p,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<P> list = commonMapper.select(p);
        PageInfo<P> pageInfo = new PageInfo<P>(list);
        return pageInfo;
    }

   /* public PageInfo<P> selectPageOrderBy(P p, int pageNum, int pageSize, Map<String,Object> orderKeysMap) {
        Example example = new Example(pClass);
        example.setOrderByClause();
        Example.Criteria criteria = example.createCriteria();
        criteria.
        criteria.andEqualTo("name", collectionGroupVo.getName());
        CollectionGroupVo collectionGroupVo_ = this.findOne(example);
        PageHelper.startPage(pageNum, pageSize);
        List<P> list = commonMapper.select(p);
        PageInfo<P> pageInfo = new PageInfo<P>(list);
        return pageInfo;
    }*/

    @Override
    public int insertList(List<P> list) {
        validate(list);
        return commonMapper.insertList(list);
    }

    @Override
    public int updateByPrimaryKeySelective(P p) {
        validateP(p);
        return commonMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public int selectCount(P p) {
        return commonMapper.selectCount(p);
    }

    @Override
    public int deleteByPrimaryKey(Serializable id) {
        validate(id);
        return commonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(P p) {
        return commonMapper.insertSelective(p);
    }

    @Override
    public int insertUseGeneratedKeys(P p) {
        return commonMapper.insertUseGeneratedKeys(p);
    }

    @Override
    public Boolean existsWithPrimaryKey(Serializable id) {
        validate(id);
        return commonMapper.existsWithPrimaryKey(id);
    }

    public void validate(Object o){
        if (o==null){
            throw new RuntimeException("CommonService统一入参不能为空！");
        }
    }

    public void validateP(P p){
        validate(p);
        try {
            Method getIdMethod = pClass.getMethod("getId", null);
            if (getIdMethod.invoke(p)==null){
                throw new RuntimeException("CommonService统一入参Model里面的主键不能为空!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
