package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName findDataSourceListResDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/27/20 4:02 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FindDataSourceListResDTO implements Serializable {

    private static final long serialVersionUID = -5687815327764773514L;

    /**
     * 序号
     */
    private Long id;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 主机端口
     */
    private Integer port;



}
