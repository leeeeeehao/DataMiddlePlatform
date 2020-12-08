package com.saas.datamiddleend.entity.vo.trans;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName FindExecutionLogVO
 * @Description TODO
 * @Author leehao
 * @Date 12/3/20 10:12 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindExecutionLogVO implements Serializable {

    private static final long serialVersionUID = -6595402791524000504L;

    /**
     * 日志ID
     */
    @ApiModelProperty(value = "日志ID", required = true)
    @NotBlank
    private String logChannelId;
}
