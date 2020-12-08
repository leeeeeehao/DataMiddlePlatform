package com.saas.datamiddleend.test;

import com.saas.datamiddleend.config.App;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;

/**
 * @ClassName Test
 * @Description TODO
 * @Author leehao
 * @Date 11/25/20 7:10 PM
 * @Version V1.0
 */
public class Test {

    private Repository repository;

    //创建转换
    private static TransMeta createTrans() {
        try {
            TransMeta transMeta = new TransMeta();
            //设置转化名称
            transMeta.setName("测试转换");

            //设置抽取库的连接
            DatabaseMeta databaseMeta = new DatabaseMeta("fromdb", "MYSQL", "Native(JDBC)", "192.168.200.67", "fromdb", "3306", "root", "saasbackend!");
            transMeta.addDatabase(databaseMeta);
            //设置目标库的连接
            transMeta.addDatabase(new DatabaseMeta("todb", "MYSQL", "Native(JDBC)", "192.168.200.67", "todb", "3306", "root", "saasbackend!"));
            return transMeta;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }

    //设置步骤一
    private static StepMeta createstep1(TransMeta transMeta) {
        //新建表输入步骤(tableInputMeta)
        TableInputMeta tableInputMeta = new TableInputMeta();
        //设置表输入的数据库连接
        tableInputMeta.setDatabaseMeta(transMeta.findDatabase("fromdb"));
        //设置步骤sql
        tableInputMeta.setSQL("select * from from_user");
        //设置步骤名称
        StepMeta stepMeta = new StepMeta("表输入", tableInputMeta);
        return stepMeta;
    }

    //设置步骤二
    private static StepMeta createstep2(TransMeta transMeta) {

        //新建插入/更新步骤
        InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
        //设置插入步骤的数据连接
        insertUpdateMeta.setDatabaseMeta(transMeta.findDatabase("todb"));
        //设置目标表
        insertUpdateMeta.setTableName("to_user");
        //设置用来查询的关键字
        insertUpdateMeta.setKeyLookup(new String[]{"id"});
        //设置比较符
        insertUpdateMeta.setKeyCondition(new String[]{"="});
        //设置流里的字段
        insertUpdateMeta.setKeyStream(new String[]{"id"});
        insertUpdateMeta.setKeyStream2(new String[]{""});//一定加上
        //设置更新表字段
        insertUpdateMeta.setUpdateLookup(new String[]{"id", "name"});
        //设置更新流字段
        insertUpdateMeta.setUpdateStream(new String[]{"id", "name"});
        insertUpdateMeta.setUpdate(new Boolean[]{false, true});
        StepMeta stepMeta = new StepMeta("插入/更新", insertUpdateMeta);
        return stepMeta;
    }

    //创建节点连接
    private static TransHopMeta createHop(StepMeta stepMeta1, StepMeta stepMeta2) {

        //设置起始步骤和目标步骤，关联步骤
        TransHopMeta transHopMeta = new TransHopMeta(stepMeta1, stepMeta2);
        return transHopMeta;
    }

    //执行转换
    private static void runTrans(TransMeta transMeta) throws KettleException {
        //执行转换
        Trans trans = new Trans(transMeta);
        trans.execute(null);//执行转换
        trans.waitUntilFinished();//等待转换结束

    }

    /**
     * 定义一个转换，但是还没有保存到资源库
     *
     * @return
     * @throws KettleException
     */
    private static TransMeta generateTrans() throws KettleException {
        TransMeta transMeta = createTrans();
        // 创建步骤1并添加到转换中
        StepMeta step1 = createstep1(transMeta);
        transMeta.addStep(step1);
        // 创建步骤2并添加到转换中
        StepMeta step2 = createstep2(transMeta);
        transMeta.addStep(step2);
        // 创建hop连接并添加hop
        TransHopMeta TransHopMeta = createHop(step1, step2);
        transMeta.addTransHop(TransHopMeta);
        return transMeta;
    }

    public static void main(String[] args) throws KettleException {
        KettleEnvironment.init();
        App.getInstance().initRepository();
//        TransMeta transMeta = generateTrans();
//        runTrans(transMeta);
    }

}
