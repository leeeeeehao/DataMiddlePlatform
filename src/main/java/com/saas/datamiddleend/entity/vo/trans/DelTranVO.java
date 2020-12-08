package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName DelTranVO
 * @Description TODO
 * @Author leehao
 * @Date 12/1/20 10:59 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DelTranVO implements Serializable {

    private static final long serialVersionUID = -8570382208568614925L;

    /**
     * 转换名称
     */
    @ApiModelProperty(value = "转换名称", required = true)
    @NotBlank
    private String tranName;
}
