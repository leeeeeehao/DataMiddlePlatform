package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName EditTransVO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 4:10 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EditTransVO implements Serializable {

    private static final long serialVersionUID = -8066914330923589961L;

    /**
     * 转换名称
     */
    @ApiModelProperty(value = "转换名称", required = true)
    @NotBlank
    private String transName;

    /**
     * 表输入实体
     */
    @ApiModelProperty(value = "表输入实体", required = true)
    private TableInputVO tableInput;

    /**
     * 表输出实体
     */
    @ApiModelProperty(value = "表输出实体", required = true)
    private TableOutputVO tableOutput;
}
