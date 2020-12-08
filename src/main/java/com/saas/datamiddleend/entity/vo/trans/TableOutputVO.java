package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
public class TableOutputVO implements Serializable {

    private static final long serialVersionUID = -8387028321702353434L;

    /**
     * 步骤名称
     */
    @ApiModelProperty(value = "步骤名称", required = true)
    @NotBlank
    private String stepName;

    /**
     * 指定的数据源连接
     */
    @ApiModelProperty(value = "指定的数据源连接", required = true)
    @NotBlank
    private String databaseName;

    /**
     * 目标表
     */
    @ApiModelProperty(value = "目标表", required = true)
    @NotBlank
    private String tableName;

    /**
     * 提交记录数量
     */
    @ApiModelProperty(value = "提交记录数量", required = false)
    private String commitSize;

    /**
     * 映射表字段数组
     */
    @ApiModelProperty(value = "映射表字段数组", required = true)
    @NotNull
    private String[] tableFields;

    /**
     * 映射流字段数组
     */
    @ApiModelProperty(value = "映射流字段数组", required = true)
    @NotNull
    private String[] streamFields;
}
