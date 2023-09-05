package com.pan.statemachine.action.impl;

import com.pan.statemachine.action.Action;

public class OnlineWithoutApplyAction implements Action {
    @Override
    public void execute() {
        System.out.println("执行sql版本上线相关的操作");
        System.out.println("修改sql版本为已上线");
    }
}