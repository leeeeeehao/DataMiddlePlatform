package com.saas.datamiddleend.entity.dto.trans;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName TableInputDTO
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 10:24 AM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TableInputDTO implements Serializable {

    private static final long serialVersionUID = 1103938011173395686L;

    /**
     * 步骤名称
     */
    private String stepName;

    /**
     * 指定的数据源连接
     */
    private String databaseName;

    /**
     * sql语句
     */
    private String sql;

}
