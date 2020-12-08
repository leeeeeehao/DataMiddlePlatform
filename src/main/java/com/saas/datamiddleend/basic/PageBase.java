package com.saas.datamiddleend.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @ClassName PageBase
 * @Description TODO
 * @Author leehao
 * @Date 2020-11-13 10:02
 * @Version V1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageBase implements Serializable {

    private static final long serialVersionUID = 2405599527270928450L;

    /**
     * 分页页码
     */
    @ApiModelProperty("分页页码")
    private Integer pageIndex = 1;

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private Integer pageSize = 10;
}
