package com.saas.datamiddleend.enums;

/**
 * @ClassName StepTypeEnums
 * @Description TODO
 * @Author leehao
 * @Date 12/2/20 11:41 AM
 * @Version V1.0
 */
public enum StepTypeEnums {
    TABLE_INPUT("1", "表输入"),
    TABLE_OUTPUT("2", "表输出");

    private String type;

    private String desc;

    StepTypeEnums(String type, String desc) {
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

