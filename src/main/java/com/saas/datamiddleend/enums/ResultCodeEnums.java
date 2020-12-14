package com.saas.datamiddleend.enums;

/**
 * @ClassName ResultCodeEnums
 * @Description TODO
 * @Author leehao
 * @Date 12/3/20 9:58 AM
 * @Version V1.0
 */
public enum ResultCodeEnums {
    SUCCESS("200", "请求成功"),
    FAIL("500", "请求失败"),
    EXCEPTIONS("400","请求异常");

    private String type;

    private String desc;

    ResultCodeEnums(String type, String desc) {
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
