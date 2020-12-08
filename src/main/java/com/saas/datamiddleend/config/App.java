package com.saas.datamiddleend.config;

import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.springframework.stereotype.Component;

/**
 * @ClassName App
 * @Description TODO
 * @Author leehao
 * @Date 11/26/20 5:20 PM
 * @Version V1.0
 */
@Slf4j
@Component
public class App {

    private static App app;

    private KettleDatabaseRepository repository;

    //单例方法
    public static synchronized App getInstance() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    public KettleDatabaseRepository getRepository() {
        initRepository();
        return repository;
    }

    //初始化资源库
    public void initRepository() {
        try {
            if (repository != null) {
                return;
            }
            DatabaseMeta databaseMeta = new DatabaseMeta("newkettle", "mysql", "Native(JDBC)", "192.168.200.67", "newkettle",
                    "3306", "root", "saasbackend!");

            //数据库形式的资源库元对象
            KettleDatabaseRepositoryMeta repository = new KettleDatabaseRepositoryMeta();
            //保存数据库连接至元对象种
            repository.setConnection(databaseMeta);
            //数据库形式的资源库对象
            KettleDatabaseRepository databaseRepository = new KettleDatabaseRepository();
            //用资源库元对象初始化资源库对象
            databaseRepository.init(repository);
            //连接到资源库 默认为admin admin
            databaseRepository.connect("admin", "admin");
            //判断状态为false | true
            if (databaseRepository.isConnected()) {
                log.info("连接成功，资源库名称为：" + databaseRepository.getDatabaseMeta());
                this.repository = databaseRepository;
            } else {
                log.info("连接失败，资源库名称为：" + databaseRepository.getDatabaseMeta());
            }

        } catch (Exception e) {
            log.error("连接资源库失败", e.toString());
        }

    }

}
