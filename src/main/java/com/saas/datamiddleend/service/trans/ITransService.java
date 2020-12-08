package com.saas.datamiddleend.service.trans;

import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.entity.dto.trans.CreateTransDTO;
import com.saas.datamiddleend.entity.dto.trans.DelTranDTO;
import com.saas.datamiddleend.entity.dto.trans.EditTransDTO;
import com.saas.datamiddleend.entity.dto.trans.ExecuteTransDTO;

/**
 * @ClassName ITransService
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 5:35 PM
 * @Version V1.0
 */
public interface ITransService {

    /**
     * 创建转换
     *
     * @param createTransDTO
     */
    ApiResult createTrans(CreateTransDTO createTransDTO);

    /**
     * 编辑转换
     *
     * @param editTransDTO
     * @return
     */
    ApiResult editTrans(EditTransDTO editTransDTO);

    /**
     * 执行转换
     *
     * @param executeTransDTO
     * @return
     */
    ApiResult executeTrans(ExecuteTransDTO executeTransDTO);

    /**
     * 获取转换日志
     *
     * @param logChannelId
     * @return
     */
    ApiResult previewLogs(String logChannelId);

    /**
     * 分页查询转换列表(可不分页)
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ApiResult findTransList(Integer pageIndex, Integer pageSize);

    /**
     * 查询转换信息
     *
     * @param transName
     * @return
     */
    ApiResult findTransInfo(String transName);

    /**
     * 删除转换
     *
     * @param delTranDTO
     * @return
     */
    ApiResult delTran(DelTranDTO delTranDTO);

}
