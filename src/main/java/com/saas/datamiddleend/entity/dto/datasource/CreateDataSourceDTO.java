package com.saas.datamiddleend.entity.dto.datasource;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName CreateDataSourceDTO
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 3:09 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateDataSourceDTO implements Serializable {

    private static final long serialVersionUID = -1381650546492934052L;

    /**
     * 数据库名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private String type;

    /**
     * 数据库访问类型
     */
    private String access;

    /**
     * 数据库主机地址
     */
    private String host;

    /**
     * 数据库名称
     */
    private String db;

    /**
     * 数据库端口号
     */
    private String port;

    /**
     * 数据库用户名
     */
    private String user;

    /**
     * 数据库密码
     */
    private String password;

}
