package com.saas.datamiddleend.entity.vo.datasource;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName DelDataSourceVO
 * @Description TODO
 * @Author leehao
 * @Date 12/8/20 7:38 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DelDataSourceVO implements Serializable {

    private static final long serialVersionUID = -4276271478505591230L;

    /**
     * 数据源名称
     */
    @ApiModelProperty("数据源名称")
    @NotBlank
    private String databaseName;

}
