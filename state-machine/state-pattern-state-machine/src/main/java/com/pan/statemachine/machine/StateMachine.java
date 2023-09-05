package com.pan.statemachine.machine;

import com.pan.statemachine.enums.Event;
import com.pan.statemachine.enums.StateEnum;
import com.pan.statemachine.state.State;

public class StateMachine {
    // 当前状态
    private State curState;

    public StateMachine(State curState) {
        setCurState(curState);
    }

    // 传入不同的事件, 执行不同状态的动作
    public void execute(Event event) {
        switch (event) {
            case ONLINE_WITHOUT_APPLY:
                getCurState().onlineWithoutApply();
                break;
            case ONLINE_APPLY:
                getCurState().onlineApply();
                break;
            case PUBLISH:
                getCurState().publish();
                break;
            case REJECT:
                getCurState().reject();
                break;
            case CANCEL_PUBLISH:
                getCurState().cancelPublish();
                break;
            default:
                throw new RuntimeException("in valid event: " + event);
        }
    }

    // 以下的4个属于action
    public void online() {
        System.out.println("执行sql版本上线相关的操作");
        System.out.println("修改sql版本为已上线");
        setCurState(StateEnum.ONLINE.getState());
    }

    public void apply() {
        System.out.println("发起上线审批, 执行sql版本审批相关的操作");
        System.out.println("修改状态为审批中");
        setCurState(StateEnum.PROCESSING.getState());
    }

    public void reject() {
        System.out.println("执行sql版本审批不通过相关的操作");
        System.out.println("设置sql版本状态为审批不通过");
        setCurState(StateEnum.REJECTED.getState());
    }

    public void offline() {
        System.out.println("执行sql版本已下线相关的操作");
        System.out.println("修改sql版本状态为已下线");
        setCurState(StateEnum.OFFLINE.getState());
    }

    public State getCurState() {
        return curState;
    }

    public void setCurState(State curState) {
        this.curState = curState;
        curState.setStateMachine(this);
    }
}