package com.pan.statemachine.enums;

public enum State {
    TEMP(0, "草稿"),
    ONLINE(1, "已上线"),
    PROCESSING(2, "审批中"),
    REJECTED(3, "审批不通过"),
    OFFLINE(4, "已下线");

    private final int code;
    private final String desc;

    State(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "State{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}