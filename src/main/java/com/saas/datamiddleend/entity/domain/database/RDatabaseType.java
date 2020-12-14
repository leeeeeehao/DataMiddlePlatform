package com.saas.datamiddleend.entity.domain.database;

import lombok.*;

import java.io.Serializable;

/**
 * R_DATABASE_TYPE
 *
 * @author
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RDatabaseType implements Serializable {
    /**
     * 类型ID
     */
    private Long idDatabaseType;

    /**
     * 数据库类型
     */
    private String code;

    /**
     * 数据库描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}