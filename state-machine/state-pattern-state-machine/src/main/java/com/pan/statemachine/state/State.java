package com.pan.statemachine.state;

import com.pan.statemachine.machine.StateMachine;

public abstract class State {
    // 状态机
    private StateMachine stateMachine;

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    // 上线不经过审批
    public abstract void onlineWithoutApply();

    // 上线审批
    public abstract void onlineApply();

    // 审批不通过
    public abstract void reject();

    // 发布 (审批中->已上线)
    public abstract void publish();

    // 取消发布 (已上线->已下线)
    public abstract void cancelPublish();
}