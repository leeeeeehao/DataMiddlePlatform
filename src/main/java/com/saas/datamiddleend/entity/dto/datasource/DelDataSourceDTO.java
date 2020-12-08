package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName DelDataSourceDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/30/20 10:19 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DelDataSourceDTO implements Serializable {

    private static final long serialVersionUID = 7288569674814787883L;

    /**
     * 数据源名称
     */
    private String databaseName;
}
