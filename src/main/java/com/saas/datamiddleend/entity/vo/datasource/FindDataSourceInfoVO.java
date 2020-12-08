package com.saas.datamiddleend.entity.vo.datasource;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName findDataSourceInfoVO
 * @Description TODO
 * @Author leehao
 * @Date 11/30/20 2:39 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindDataSourceInfoVO implements Serializable {

    private static final long serialVersionUID = -8652163451204505593L;

    /**
     * 数据源名称
     */
    @ApiModelProperty(value = "数据源名称", required = true)
    @NotBlank(message = "数据源名称不能为空")
    private String databaseName;

    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    private String tableName;

    /**
     * 查询数量
     */
    @ApiModelProperty(value = "查询数量")
    private Integer limit;

    /**
     * 节点请求
     */
    @ApiModelProperty(value = "节点请求", required = true)
    @NotBlank(message = "节点请求不能为空")
    private String nodeId;

    /**
     * 节点的字面值
     */
    @ApiModelProperty(value = "节点的字面值")
    private String text;

    /**
     * 包含的节点类型
     */
    @ApiModelProperty(value = "包含的节点类型")
    private Integer includeElement;

}
