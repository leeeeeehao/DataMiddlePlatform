package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName TableOutputDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 10:25 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TableOutputDTO implements Serializable {

    private static final long serialVersionUID = -8387028321702353434L;

    /**
     * 步骤名称
     */
    private String stepName;

    /**
     * 指定的数据源连接
     */
    private String databaseName;

    /**
     * 目标表
     */
    private String tableName;

    /**
     * 提交记录数量
     */
    private String commitSize;

    /**
     * 映射表字段数组
     */
    private String[] tableFields;

    /**
     * 映射流字段数组
     */
    private String[] streamFields;
}
