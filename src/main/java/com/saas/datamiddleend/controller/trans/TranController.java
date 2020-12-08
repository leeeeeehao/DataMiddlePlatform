package com.saas.datamiddleend.controller.trans;

import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.entity.dto.trans.CreateTransDTO;
import com.saas.datamiddleend.entity.dto.trans.DelTranDTO;
import com.saas.datamiddleend.entity.dto.trans.EditTransDTO;
import com.saas.datamiddleend.entity.dto.trans.ExecuteTransDTO;
import com.saas.datamiddleend.entity.vo.trans.CreateTranVO;
import com.saas.datamiddleend.entity.vo.trans.DelTranVO;
import com.saas.datamiddleend.entity.vo.trans.EditTransVO;
import com.saas.datamiddleend.entity.vo.trans.ExecuteTransVO;
import com.saas.datamiddleend.service.trans.ITransService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName Trancontroller
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 8:52 PM
 * @Version V1.0
 */
@Api(value = "转换控制器", tags = "转换控制器")
@RestController
@RequestMapping("/trans")
public class TranController {

    @Autowired
    private ITransService iTransService;

    @ApiOperation("创建转换")
    @PostMapping("/save")
    public ApiResult save(@RequestBody @Valid CreateTranVO createTranVO) {
        return iTransService.createTrans(new DozerBeanMapper().map(createTranVO, CreateTransDTO.class));
    }

    @ApiOperation("编辑转换")
    @PostMapping("/edit")
    public ApiResult edit(@RequestBody @Valid EditTransVO editTransVO) {
        return iTransService.editTrans(new DozerBeanMapper().map(editTransVO, EditTransDTO.class));
    }

    @ApiOperation("执行转换")
    @PostMapping("/execute")
    public ApiResult execute(@RequestBody @Valid ExecuteTransVO executeTransVO) {
        return iTransService.executeTrans(new DozerBeanMapper().map(executeTransVO, ExecuteTransDTO.class));
    }

    @ApiOperation("获取转换日志")
    @GetMapping("/PreviewLogs/{logChannelId}")
    public ApiResult PreviewLogs(@PathVariable(value = "logChannelId") String logChannelId) {
        return iTransService.previewLogs(logChannelId);
    }

    @ApiOperation("分页查询转换列表(可不分页)")
    @GetMapping(value = {"/findList/{pageIndex}/{pageSize}", "/findList"})
    public ApiResult findList(@PathVariable(value = "pageIndex", required = false) Integer pageIndex,
                              @PathVariable(value = "pageSize", required = false) Integer pageSize) throws Exception {
        return iTransService.findTransList(pageIndex, pageSize);
    }

    @ApiOperation("查询转换信息")
    @GetMapping("/findInfo/{transName}")
    public ApiResult findInfo(@PathVariable("transName") String transName) {
        return iTransService.findTransInfo(transName);
    }

    @ApiOperation("删除转换")
    @DeleteMapping("/del")
    public ApiResult del(@RequestBody DelTranVO delTranVO) {
        return iTransService.delTran(new DozerBeanMapper().map(delTranVO, DelTranDTO.class));
    }
}
