package com.pan.statemachine.state.impl;

import com.pan.statemachine.state.State;

// 审批中
public class ProcessingState extends State {
    @Override
    public void onlineWithoutApply() {
        throw new RuntimeException("in valid: ProcessingState -> onlineWithoutApply");
    }

    @Override
    public void onlineApply() {
        throw new RuntimeException("in valid: ProcessingState -> onlineApply");
    }

    @Override
    public void reject() {
        getStateMachine().reject();
    }

    @Override
    public void publish() {
        getStateMachine().online();
    }

    @Override
    public void cancelPublish() {
        throw new RuntimeException("in valid: ProcessingState -> cancelPublish");
    }
}