package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName CreateTransDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 5:37 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateTransDTO implements Serializable {

    private static final long serialVersionUID = -3800607709712758118L;

    /**
     * 转换名称
     */
    private String tranName;

    /**
     * 转换描述
     */
    private String tranDesc;

}
