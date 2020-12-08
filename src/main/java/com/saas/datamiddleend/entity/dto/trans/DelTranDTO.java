package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName DelTranDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/1/20 10:26 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DelTranDTO implements Serializable {

    private static final long serialVersionUID = 965034552977139884L;

    /**
     * 转换名称
     */
    private String tranName;
}
