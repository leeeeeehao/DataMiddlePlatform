package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName TableInputDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 10:24 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TableInputVO implements Serializable {

    private static final long serialVersionUID = 1103938011173395686L;

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
     * sql语句
     */
    @ApiModelProperty(value = "sql语句", required = true)
    @NotBlank
    private String sql;

}
