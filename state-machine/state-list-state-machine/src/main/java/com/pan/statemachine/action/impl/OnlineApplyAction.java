package com.pan.statemachine.action.impl;

import com.pan.statemachine.action.Action;

public class OnlineApplyAction implements Action {
    @Override
    public void execute() {
        System.out.println("发起上线审批, 执行sql版本审批相关的操作");
        System.out.println("修改状态为审批中");
    }
}
