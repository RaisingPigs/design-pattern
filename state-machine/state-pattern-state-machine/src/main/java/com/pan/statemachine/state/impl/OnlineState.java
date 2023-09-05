package com.pan.statemachine.state.impl;

import com.pan.statemachine.state.State;

// 已上线
public class OnlineState extends State {
    @Override
    public void onlineWithoutApply() {
        throw new RuntimeException("in valid: OnlineState -> onlineWithoutApply");
    }

    @Override
    public void onlineApply() {
        throw new RuntimeException("in valid: OnlineState -> onlineApply");
    }

    @Override
    public void reject() {
        throw new RuntimeException("in valid: OnlineState -> reject");
    }

    @Override
    public void publish() {
        throw new RuntimeException("in valid: OnlineState -> publish");
    }

    @Override
    public void cancelPublish() {
        getStateMachine().offline();
    }
}