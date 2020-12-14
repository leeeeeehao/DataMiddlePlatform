package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName FindFieldsDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/10/20 1:52 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindFieldsDTO implements Serializable {

    private static final long serialVersionUID = 7759052287112221473L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private String filedType;

    /**
     * 字段长度
     */
    private Integer filedLength;

    /**
     * 注释
     */
    private String filedComment;
}
