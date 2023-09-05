package com.pan.statemachine.state.impl;

import com.pan.statemachine.state.State;

// 审批不通过
public class RejectedState extends State {
    @Override
    public void onlineWithoutApply() {
        throw new RuntimeException("in valid: RejectedState -> onlineWithoutApply");
    }

    @Override
    public void onlineApply() {
        throw new RuntimeException("in valid: RejectedState -> onlineApply");
    }

    @Override
    public void reject() {
        throw new RuntimeException("in valid: RejectedState -> reject");
    }

    @Override
    public void publish() {
        throw new RuntimeException("in valid: RejectedState -> publish");
    }

    @Override
    public void cancelPublish() {
        throw new RuntimeException("in valid: RejectedState -> cancelPublish");
    }
}