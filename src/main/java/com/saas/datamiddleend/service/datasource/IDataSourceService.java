package com.saas.datamiddleend.service.datasource;

import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.basic.PageBase;
import com.saas.datamiddleend.entity.dto.datasource.CreateDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.DelDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.FindDataSourceInfoDTO;

/**
 * @ClassName IDataBaseService
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 3:04 PM
 * @Version V1.0
 */
public interface IDataSourceService {

    /**
     * 创建数据源
     *
     * @param createDataSourceDTO
     */
    ApiResult createDataSource(CreateDataSourceDTO createDataSourceDTO);

    /**
     * 获取数据源列表
     *
     * @return
     */
    ApiResult findDataSourceList(PageBase pageBase);

    /**
     * 获取数据源信息
     *
     * @return
     */
    ApiResult findDataSourceInfo(FindDataSourceInfoDTO findDataSourceInfoDTO);

    /**
     * 删除数据源
     *
     * @param delDataSourceDTO
     * @return
     */
    ApiResult delDataSource(DelDataSourceDTO delDataSourceDTO);

    /**
     * 获取目前支持的数据库
     *
     * @return
     */
    ApiResult findDataSourceType();

}
