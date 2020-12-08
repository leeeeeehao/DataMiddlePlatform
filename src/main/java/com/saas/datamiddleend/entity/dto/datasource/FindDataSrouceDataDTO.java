package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName FindDataSrouceDataDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/30/20 4:14 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindDataSrouceDataDTO implements Serializable {

    private static final long serialVersionUID = 4830368746640170880L;

    /**
     * 数据源名称
     */
    private String databaseName;

    /**
     * 数据表名称
     */
    private String tableName;
}
