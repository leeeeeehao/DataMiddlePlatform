package com.saas.datamiddleend.dao.database;


import com.saas.datamiddleend.entity.domain.database.RDatabaseType;

public interface RDatabaseTypeMapper {
    int deleteByPrimaryKey(Long idDatabaseType);

    int insert(RDatabaseType record);

    int insertSelective(RDatabaseType record);

    RDatabaseType selectById(Long idDatabaseType);

    int updateByPrimaryKeySelective(RDatabaseType record);

    int updateByPrimaryKey(RDatabaseType record);
}