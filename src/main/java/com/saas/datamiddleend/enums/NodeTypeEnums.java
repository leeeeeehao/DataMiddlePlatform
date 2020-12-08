package com.saas.datamiddleend.enums;

/**
 * @ClassName NodeTypeEnums
 * @Description TODO
 * @Author leehao
 * @Date 11/30/20 2:24 PM
 * @Version V1.0
 */
public enum NodeTypeEnums {
    NODE_ROOT("root", "根节点"),
    NODE_SCHEMA("schema", "模式"),
    NODE_TABLE("table", "表"),
    NODE_DATA("data", "数据"),
    NODE_VIEW("view", "视图");

    private String type;

    private String desc;

    NodeTypeEnums(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
