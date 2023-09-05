package com.pan.statemachine.action.impl;

import com.pan.statemachine.action.Action;

public class RejectAction implements Action {
    @Override
    public void execute() {
        System.out.println("执行sql版本审批不通过相关的操作");
        System.out.println("设置sql版本状态为审批不通过");
    }
}