package com.saas.datamiddleend.entity.vo.datasource;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName CreateDataSourceVO
 * @Description TODO
 * @Author leehao
 * @Date 11/27/20 1:55 PM
 * @Version V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateDataSourceVO implements Serializable {

    private static final long serialVersionUID = 8936682366455070505L;

    /**
     * 数据库名称
     */
    @ApiModelProperty(value = "数据库名称", required = true)
    @NotBlank(message = "数据库名称不能为空")
    private String name;

    /**
     * 数据库类型
     */
    @ApiModelProperty(value = "数据库类型", required = true)
    @NotBlank(message = "数据库类型不能为空")
    private String type;

    /**
     * 数据库访问类型
     */
    @ApiModelProperty(value = "数据库访问类型", required = true)
    @NotBlank(message = "数据库访问类型不能为空")
    private String access;

    /**
     * 数据库主机地址
     */
    @ApiModelProperty(value = "数据库主机地址", required = true)
    @NotBlank(message = "数据库访问类型不能为空")
    private String host;

    /**
     * 数据库名称
     */
    @ApiModelProperty(value = "数据库名称", required = true)
    @NotBlank(message = "数据库名称不能为空")
    private String db;

    /**
     * 数据库端口号
     */
    @ApiModelProperty(value = "数据库端口号", required = true)
    @NotBlank(message = "数据库端口号不能为空")
    private String port;

    /**
     * 数据库用户名
     */
    @ApiModelProperty(value = "数据库用户名", required = true)
    @NotBlank(message = "数据库用户名不能为空")
    private String user;

    /**
     * 数据库密码
     */
    @ApiModelProperty(value = "数据库密码", required = true)
    @NotBlank(message = "数据库密码不能为空")
    private String password;
}
