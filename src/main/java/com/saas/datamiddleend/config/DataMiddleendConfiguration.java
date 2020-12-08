package com.saas.datamiddleend.config;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 数仓配置
 *
 * @author leehao
 * @since 1.0
 */
@Configuration
public class DataMiddleendConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(DataMiddleendConfiguration.class);

    /**
     * 初始化数仓运行环境
     */
    @PostConstruct
    public void setup() {
        try {
            LOG.info("数据仓库环境初始化");
            KettleEnvironment.init();
            App.getInstance().getRepository();
        } catch (KettleException e) {
            LOG.error("init datamiddleend env error", e);
            System.exit(-1);
        }
    }

    /**
     * 销毁数仓运行环境
     */
    @PreDestroy
    public void cleanup() {
        KettleEnvironment.shutdown();
    }

}
