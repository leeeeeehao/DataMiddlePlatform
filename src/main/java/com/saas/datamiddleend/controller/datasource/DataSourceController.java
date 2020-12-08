package com.saas.datamiddleend.controller.datasource;

import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.basic.PageBase;
import com.saas.datamiddleend.entity.dto.datasource.CreateDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.DelDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.FindDataSourceInfoDTO;
import com.saas.datamiddleend.entity.vo.datasource.CreateDataSourceVO;
import com.saas.datamiddleend.entity.vo.datasource.FindDataSourceInfoVO;
import com.saas.datamiddleend.service.datasource.IDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName DataSourceController
 * @Description TODO
 * @Author leehao
 * @Date 11/27/20 1:52 PM
 * @Version V1.0
 */
@Api(value = "数据源控制器", tags = "数据源控制器")
@RestController
@RequestMapping("dataSource")
public class DataSourceController {

    @Autowired
    private IDataSourceService iDataSourceService;

    @ApiOperation("添加数据源")
    @PostMapping("/add")
    public ApiResult add(@RequestBody @Valid CreateDataSourceVO createDataSourceVO) {
        return iDataSourceService.createDataSource(new DozerBeanMapper().map(createDataSourceVO, CreateDataSourceDTO.class));
    }

    @ApiOperation("查询数据源列表")
    @PostMapping("/findList")
    public ApiResult findList(@RequestBody PageBase pageBase) {
        return iDataSourceService.findDataSourceList(pageBase);
    }

    @ApiOperation("查询数据源信息")
    @PostMapping("/findInfo")
    public ApiResult findInfo(@RequestBody @Valid FindDataSourceInfoVO findDataSourceInfoVO) {
        return iDataSourceService.findDataSourceInfo(new DozerBeanMapper().map(findDataSourceInfoVO, FindDataSourceInfoDTO.class));
    }

    @ApiOperation("删除数据源")
    @DeleteMapping("/del/{databaseName}")
    public ApiResult del(@PathVariable("databaseName") @Valid String databaseName) {
        return iDataSourceService.delDataSource(DelDataSourceDTO.builder().databaseName(databaseName).build());
    }

    @ApiOperation("获取支持数据库类型")
    @GetMapping("/types")
    public ApiResult types() {
        return iDataSourceService.findDataSourceType();
    }

}
