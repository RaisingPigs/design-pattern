package com.pan.statemachine.enums;

public enum Event {
    ONLINE_WITHOUT_APPLY, // 从 草稿/已上线 变成已上线
    ONLINE_APPLY,  // 从 草稿/已上线 变成审批中
    PUBLISH,  // 从审批中变成已上线
    REJECT,  // 从审批中变成审批不通过
    CANCEL_PUBLISH  // 从已上线变为已下线
}