package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName CreateTranVO
 * @Description TODO
 * @Author leehao
 * @Date 12/1/20 4:49 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateTranVO implements Serializable {

    private static final long serialVersionUID = 3559277136561358186L;

    /**
     * 转换名称
     */
    @ApiModelProperty(value = "转换名称", required = true)
    @NotBlank(message = "转换名称不能为空")
    private String tranName;

    /**
     * 转换描述
     */
    @ApiModelProperty(value = "转换描述")
    private String tranDesc;
}
