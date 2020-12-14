package com.saas.datamiddleend.entity.vo.trans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FindTransListResVO
 * @Description TODO
 * @Author leehao
 * @Date 12/1/20 4:29 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindTransListResVO implements Serializable {

    private static final long serialVersionUID = 6272050985531896884L;

    /**
     * 转换ID
     */
    private Long idTransformation;

    /**
     * 转换名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建用户
     */
    private String createdUser;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;
}
