package com.saas.datamiddleend.dao.database;

import com.saas.datamiddleend.entity.domain.database.RDatabase;

import java.util.List;

public interface RDatabaseMapper {
    int deleteByPrimaryKey(Long idDatabase);

    int insert(RDatabase record);

    int insertSelective(RDatabase record);

    RDatabase selectByPrimaryKey(Long idDatabase);

    List<RDatabase> selectDatabaseList();

    int updateByPrimaryKeySelective(RDatabase record);

    int updateByPrimaryKey(RDatabase record);
}