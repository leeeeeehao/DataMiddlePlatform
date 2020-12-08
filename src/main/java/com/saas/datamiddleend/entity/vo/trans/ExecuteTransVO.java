package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName ExecuteTransVO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 5:28 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExecuteTransVO implements Serializable {

    private static final long serialVersionUID = 7166971367012953177L;

    /**
     * 转换名称
     */
    @ApiModelProperty(value = "转换名称", required = true)
    @NotBlank
    private String transName;
}
