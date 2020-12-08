package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName FindDataSourceInfoDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/30/20 2:12 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindDataSourceInfoDTO implements Serializable {

    private static final long serialVersionUID = -1075548174278006070L;

    /**
     * 数据源名称
     */
    private String databaseName;

    /**
     * 节点请求
     */
    private String nodeId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 查询数量
     */
    private Integer limit;

    /**
     * 节点的字面值
     */
    private String text;

    /**
     * 包含的节点类型
     */
    private Integer includeElement;

}
