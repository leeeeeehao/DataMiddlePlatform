package com.saas.datamiddleend.service.trans.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.config.App;
import com.saas.datamiddleend.dao.trans.RTransformationMapper;
import com.saas.datamiddleend.entity.domain.trans.RTransformation;
import com.saas.datamiddleend.entity.dto.trans.CreateTransDTO;
import com.saas.datamiddleend.entity.dto.trans.DelTranDTO;
import com.saas.datamiddleend.entity.dto.trans.EditTransDTO;
import com.saas.datamiddleend.entity.dto.trans.ExecuteTransDTO;
import com.saas.datamiddleend.entity.vo.trans.FindTransListResVO;
import com.saas.datamiddleend.enums.ResultCodeEnums;
import com.saas.datamiddleend.service.trans.ITransService;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.KettleLogLayout;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.KettleLoggingEvent;
import org.pentaho.di.core.logging.LoggingRegistry;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ITransServiceImpl
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 5:46 PM
 * @Version V1.0
 */
@Slf4j
@Service
public class ITransServiceImpl implements ITransService {

    @Autowired
    private RTransformationMapper rTransformationMapper;

    @Value("${trans.execute.config}")
    private String transExecuteConfig;

    @Override
    public ApiResult createTrans(CreateTransDTO createTransDTO) {
        try {
            List<RTransformation> rTransformations = rTransformationMapper.selectTrans();
            if (!CollectionUtils.isEmpty(rTransformations)) {
                for (RTransformation rTransformation : rTransformations) {
                    //判断是否有重名转换
                    if (createTransDTO.getTranName().equals(rTransformation.getName())) {
                        return ApiResult.fail("该转换已存在,请使用其他名称", null);
                    }
                }
            }
            KettleDatabaseRepository repository = App.getInstance().getRepository();
            TransMeta transMeta = new TransMeta();
            //设置转换名称
            transMeta.setName(createTransDTO.getTranName());

            if (!ObjectUtils.isEmpty(createTransDTO.getTranDesc())) {
                //设置转换描述
                transMeta.setDescription(createTransDTO.getTranDesc());
            }

            //转换的元资源库对象
            transMeta.setRepository(repository);
            //创建转换保存的位置,暂默认/目录
            transMeta.setRepositoryDirectory(repository.findDirectory("/"));
            repository.save(transMeta, null);
            return ApiResult.ok();
        } catch (KettleException e) {
            return ApiResult.fail();
        }
    }

    @Override
    public ApiResult editTrans(EditTransDTO editTransDTO) {
        try {
            KettleDatabaseRepository repository = App.getInstance().getRepository();

            //获取指定转换
            RepositoryDirectoryInterface dir = repository.findDirectory("/");
            ObjectId id = repository.getTransformationID(editTransDTO.getTransName(), dir);
            TransMeta transMeta = repository.loadTransformation(id, "");

            //构造表输入步骤
            TableInputMeta tableInputMeta = new TableInputMeta();
            //设置表输入的数据库连接
            tableInputMeta.setDatabaseMeta(transMeta.findDatabase(editTransDTO.getTableInput().getDatabaseName()));
            //设置步骤sql
            tableInputMeta.setSQL(editTransDTO.getTableInput().getSql());
            StepMeta inputStepMeta = new StepMeta("表输入", tableInputMeta);

            //构造表输出步骤
            TableOutputMeta tableOutputMeta = new TableOutputMeta();
            //设置插入步骤的数据连接
            tableOutputMeta.setDatabaseMeta(transMeta.findDatabase(editTransDTO.getTableOutput().getDatabaseName()));
            //设置目标表
            tableOutputMeta.setTableName(editTransDTO.getTableOutput().getTableName());
            //设置提交数量
            tableOutputMeta.setCommitSize(editTransDTO.getTableOutput().getCommitSize());
            //指定数据库字段
            tableOutputMeta.setSpecifyFields(true);
            //设置表字段
            tableOutputMeta.setFieldDatabase(editTransDTO.getTableOutput().getTableFields());
            //设置流字段
            tableOutputMeta.setFieldStream(editTransDTO.getTableOutput().getStreamFields());
            StepMeta outputStepMeta = new StepMeta("表输出", tableOutputMeta);

            //设置步骤连接
            TransHopMeta transHopMeta = new TransHopMeta(inputStepMeta, outputStepMeta);

            //转换添加步骤
            transMeta.addStep(inputStepMeta);
            transMeta.addStep(outputStepMeta);
            //转换添加节点连接
            transMeta.addTransHop(transHopMeta);
            transMeta.setRepository(repository);
            transMeta.setRepositoryDirectory(repository.findDirectory("/"));
            //将转换保存至资源库(覆盖原有转换)
            repository.save(transMeta, null);
            log.info("编辑转换成功");
            return ApiResult.ok();
        } catch (Exception e) {
            log.error("编辑转换失败,原因{}", e.toString());
            return ApiResult.fail();
        }
    }

    @Override
    public ApiResult executeTrans(ExecuteTransDTO executeTransDTO) {
        try {
            KettleDatabaseRepository repository = App.getInstance().getRepository();

            //获取指定转换
            RepositoryDirectoryInterface dir = repository.findDirectory("/");
            ObjectId id = repository.getTransformationID(executeTransDTO.getTransName(), dir);
            TransMeta transMeta = repository.loadTransformation(id, "");

            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();

            log.info("执行转换成功");
            return ApiResult.ok(trans.getLogChannelId());
        } catch (Exception e) {
            log.error("执行转换失败,原因{}", e.toString());
            return ApiResult.fail();
        }
    }

    @Override
    public ApiResult previewLogs(String logChannelId) {
        try {

            List<String> logStrs = new ArrayList<>();
            KettleLogLayout logLayout = new KettleLogLayout(true);
            List<String> childIds = LoggingRegistry.getInstance().getLogChannelChildren(logChannelId);
            List<KettleLoggingEvent> logLines = KettleLogStore.getLogBufferFromTo(childIds, true, -1, KettleLogStore.getLastBufferLineNr());
            for (int i = 0; i < logLines.size(); i++) {
                KettleLoggingEvent event = logLines.get(i);
                String line = logLayout.format(event).trim();
                logStrs.add(line);
            }
            log.info("获取转换日志成功");
            return ApiResult.ok(logStrs);
        } catch (Exception e) {
            log.error("获取转换日志失败,原因{}", e.toString());
            return ApiResult.fail();
        }
    }

    @Override
    public ApiResult findTransList(Integer pageIndex, Integer pageSize) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<FindTransListResVO> findTransListResVOS = new ArrayList<>();

            if (pageIndex != null && pageSize != null) {
                PageHelper.startPage(pageIndex, pageSize);
            }
            //分页获取所有转换列表
            List<RTransformation> rTransformations = rTransformationMapper.selectTrans();
            PageInfo<RTransformation> pageInfo = new PageInfo<>(rTransformations);
            for (RTransformation transformation : rTransformations) {
                transformation.setCreatedDate(format.parse(format.format(transformation.getCreatedDate())));
                transformation.setModifiedDate(format.parse(format.format(transformation.getModifiedDate())));
                FindTransListResVO findTransListResVO = new FindTransListResVO();
                BeanUtils.copyProperties(transformation, findTransListResVO);
                findTransListResVOS.add(findTransListResVO);
            }
            PageInfo<FindTransListResVO> findTransListResVOPageInfo = new PageInfo<>(findTransListResVOS);
            findTransListResVOPageInfo.setTotal(pageInfo.getTotal());
            findTransListResVOPageInfo.setPageNum(pageInfo.getPageNum());
            findTransListResVOPageInfo.setPageSize(pageInfo.getPageSize());
            log.info("分页查询转换列表");
            return ApiResult.ok(findTransListResVOPageInfo);
        } catch (Exception e) {
            log.error("分页查询转换列表失败,原因{}", e.toString());
            return ApiResult.fail();
        }

    }

    @Override
    public ApiResult findTransInfo(String transName) {
        try {
            KettleDatabaseRepository repository = App.getInstance().getRepository();
            RepositoryDirectoryInterface dir = repository.findDirectory("/");
            ObjectId id = repository.getTransformationID(transName, dir);
            TransMeta transMeta = repository.loadTransformation(id, "");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResult delTran(DelTranDTO delTranDTO) {
        try {
            if (ObjectUtils.isEmpty(rTransformationMapper.selectTransByName(delTranDTO.getTranName()))) {
                return ApiResult.fail(ResultCodeEnums.EXCEPTIONS.getType(), "删除失败，没有这个转换");
            }
            KettleDatabaseRepository repository = App.getInstance().getRepository();
            RepositoryDirectoryInterface dir = repository.findDirectory("/");
            ObjectId id = repository.getTransformationID(delTranDTO.getTranName(), dir);
            repository.deleteTransformation(id);
            log.info("删除转换成功");
            return ApiResult.ok();
        } catch (Exception e) {
            log.info("删除转换失败,原因{}", e.toString());
            return ApiResult.fail();
        }
    }

}
