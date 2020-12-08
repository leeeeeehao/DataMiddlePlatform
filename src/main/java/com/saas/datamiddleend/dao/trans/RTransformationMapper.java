package com.saas.datamiddleend.dao.trans;


import com.saas.datamiddleend.entity.domain.trans.RTransformation;

import java.util.List;

public interface RTransformationMapper {
    Integer deleteByPrimaryKey(Long idTransformation);

    Integer insert(RTransformation record);

    Integer insertSelective(RTransformation record);

    RTransformation selectByPrimaryKey(Long idTransformation);

    Integer updateByPrimaryKeySelective(RTransformation record);

    Integer updateByPrimaryKey(RTransformation record);

    /**
     * 查询转换列表
     *
     * @return
     */
    List<RTransformation> selectTrans();
}