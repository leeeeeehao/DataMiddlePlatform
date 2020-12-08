package com.saas.datamiddleend.service.datasource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saas.datamiddleend.basic.ApiResult;
import com.saas.datamiddleend.basic.DatabaseNode;
import com.saas.datamiddleend.basic.DatabaseType;
import com.saas.datamiddleend.basic.PageBase;
import com.saas.datamiddleend.config.App;
import com.saas.datamiddleend.dao.database.RDatabaseMapper;
import com.saas.datamiddleend.entity.domain.database.RDatabase;
import com.saas.datamiddleend.entity.dto.datasource.CreateDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.DelDataSourceDTO;
import com.saas.datamiddleend.entity.dto.datasource.FindDataSourceInfoDTO;
import com.saas.datamiddleend.entity.dto.datasource.FindDataSourceListResDTO;
import com.saas.datamiddleend.enums.NodeTypeEnums;
import com.saas.datamiddleend.service.datasource.IDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.LoggingObjectInterface;
import org.pentaho.di.core.logging.LoggingObjectType;
import org.pentaho.di.core.logging.SimpleLoggingObject;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @ClassName IDataBaseServiceImpl
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 3:05 PM
 * @Version V1.0
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements IDataSourceService {

    @Autowired
    private RDatabaseMapper rDatabaseMapper;

    public static final LoggingObjectInterface loggingObject = new SimpleLoggingObject("DataSourceServiceImpl", LoggingObjectType.DATABASE, null);

    @Override
    public ApiResult createDataSource(CreateDataSourceDTO dataSource) {
        try {
            DatabaseMeta databaseMeta = new DatabaseMeta(dataSource.getName()
                    , dataSource.getType()
                    , dataSource.getAccess()
                    , dataSource.getHost()
                    , dataSource.getDb()
                    , dataSource.getPort()
                    , dataSource.getUser()
                    , dataSource.getPassword());
            Repository repository = App.getInstance().getRepository();
            log.info("创建数据源成功");
            repository.save(databaseMeta, null, null);
            return ApiResult.ok();
        } catch (Exception e) {
            log.error("创建数据源失败,原因{}", e.toString());
            return ApiResult.fail("创建数据源失败", null);
        }
    }

    @Override
    public ApiResult findDataSourceList(PageBase pageBase) {
        try {
            List<FindDataSourceListResDTO> resList = new ArrayList<>();
            PageHelper.startPage(pageBase.getPageIndex(), pageBase.getPageSize());
            List<RDatabase> databases = rDatabaseMapper.selectDatabaseList();
            databases.stream().forEach(item -> {
                FindDataSourceListResDTO res = FindDataSourceListResDTO
                        .builder()
                        .id(item.getIdDatabase())
                        .databaseName(item.getDatabaseName())
                        .databaseType(item.getIdDatabaseType().toString())
                        .hostName(item.getHostName())
                        .port(item.getPort())
                        .build();

                resList.add(res);
            });
            PageInfo<FindDataSourceListResDTO> pageInfo = new PageInfo(resList);
            log.info("查询数据源列表成功");
            return ApiResult.ok(pageInfo);
        } catch (Exception e) {
            log.error("查询数据源失败,原因{}", e.toString());
            return ApiResult.fail("查询数据源失败", null);
        }
    }

    @Override
    public ApiResult findDataSourceInfo(FindDataSourceInfoDTO findDataSourceInfoDTO) {
        try {
            //是否找到对应数据源标识
            boolean flag = false;
            KettleDatabaseRepository repository = App.getInstance().getRepository();
            JSONArray jsonArray = new JSONArray();
            DatabaseMeta databaseMeta = new DatabaseMeta();
            List<DatabaseMeta> databaseMetas = repository.getDatabases();
            for (DatabaseMeta item : databaseMetas) {
                if (findDataSourceInfoDTO.getDatabaseName().equals(item.getDatabaseName())) {
                    flag = true;
                    databaseMeta = item;
                }
            }

            //如果数据组件不为空
            if (flag) {

                Database db = new Database(loggingObject, databaseMeta);
                if (NodeTypeEnums.NODE_TABLE.getType().equals(findDataSourceInfoDTO.getNodeId())) {//如果是请求表

                    db.connect();
                    //获取指定库下的数据表列表
                    Map<String, Collection<String>> tableMap = db.getTableMap();
                    List<String> tableKeys = new ArrayList<String>(tableMap.keySet());
                    Collections.sort(tableKeys);
                    for (String schema : tableKeys) {
                        List<String> tables = new ArrayList<String>(tableMap.get(schema));
                        Collections.sort(tables);
                        for (String tableName : tables)
                            jsonArray.add(DatabaseNode.initNode(tableName, schema, "datatable", true));
                    }
                } else if (NodeTypeEnums.NODE_DATA.getType().equals(findDataSourceInfoDTO.getNodeId()) && !ObjectUtils.isEmpty(findDataSourceInfoDTO.getLimit())) {//如果是请求字段名

                    db.connect();
                    //获取表字段输出为数组列表
                    RowMetaInterface fields = db.getTableFields(findDataSourceInfoDTO.getTableName());

                    if (fields != null) {
                        List<String> fieldList = new ArrayList<>();
                        for (int i = 0; i < fields.size(); i++) {
                            ValueMetaInterface field = fields.getValueMeta(i);
                            fieldList.add(field.getName());
                        }
                        String[] fieldArray = new String[fields.size()];
                        fieldList.toArray(fieldArray);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("field", fieldArray);
                        jsonArray.add(jsonObject);
                    }

                    //获取字段值输出为数组列表
                    List<Object[]> objects = db.getFirstRows(findDataSourceInfoDTO.getTableName(), findDataSourceInfoDTO.getLimit());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("value", objects);
                    jsonArray.add(jsonObject);
                }

                db.disconnect();
                log.info("查询数据源信息成功");
                return ApiResult.ok(jsonArray);
            } else {
                log.error("没有查询到对应的数据源信息");
                return ApiResult.fail("没有查询到对应的数据源信息", null);
            }

        } catch (
                Exception e) {
            log.error("查询数据源信息失败,原因{}", e.toString());
            return ApiResult.fail();
        }

    }

    @Override
    public ApiResult delDataSource(DelDataSourceDTO delDataSourceDTO) {
        try {
            KettleDatabaseRepository repository = App.getInstance().getRepository();
            repository.deleteDatabaseMeta(delDataSourceDTO.getDatabaseName());
            log.info("删除数据源成功");
            return ApiResult.ok();
        } catch (Exception e) {
            log.error("删除数据源失败,原因{}", e.toString());
            return ApiResult.fail();
        }
    }

    @Override
    public ApiResult findDataSourceType() {
        try {
            JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(DatabaseType.instance().loadSupportedDatabaseTypes()));
            log.info("获取支持数据库类型成功");
            return ApiResult.ok(jsonArray);
        } catch (Exception e) {
            log.error("获取支持数据库类型失败");
            return ApiResult.fail();
        }
    }
}
