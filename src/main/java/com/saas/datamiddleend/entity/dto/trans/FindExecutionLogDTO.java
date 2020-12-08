package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName FindExecutionLog
 * @Description TODO
 * @Author leehao
 * @Date 12/3/20 10:04 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindExecutionLogDTO implements Serializable {

    private static final long serialVersionUID = 7623000534284930881L;

    /**
     * 日志ID
     */
    private String logChannelId;
}
