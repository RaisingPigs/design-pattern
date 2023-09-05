package com.pan.statemachine.state.impl;

import com.pan.statemachine.state.State;

// 已下线
public class OfflineState extends State {
    @Override
    public void onlineWithoutApply() {
        getStateMachine().online();
    }

    @Override
    public void onlineApply() {
        getStateMachine().apply();
    }

    @Override
    public void reject() {
        throw new RuntimeException("in valid: OfflineState -> reject");
    }

    @Override
    public void publish() {
        throw new RuntimeException("in valid: OfflineState -> publish");
    }

    @Override
    public void cancelPublish() {
        throw new RuntimeException("in valid: OfflineState -> cancelPublish");
    }
}
