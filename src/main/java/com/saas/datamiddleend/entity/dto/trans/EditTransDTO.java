package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName EditTransDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 10:22 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EditTransDTO implements Serializable {

    private static final long serialVersionUID = -5946162657049525628L;

    /**
     * 转换名称
     */
    private String transName;

    /**
     * 表输入实体
     */
    private TableInputDTO tableInput;

    /**
     * 表输出实体
     */
    private TableOutputDTO tableOutput;
}
