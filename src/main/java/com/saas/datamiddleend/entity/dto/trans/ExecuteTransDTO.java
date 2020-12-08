package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName ExecuteTransDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 4:57 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExecuteTransDTO implements Serializable {

    private static final long serialVersionUID = 4876593576709866730L;

    /**
     * 转换名称
     */
    private String transName;
}
