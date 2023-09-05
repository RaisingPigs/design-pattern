package com.pan.statemachine.enums;

import com.pan.statemachine.state.State;
import com.pan.statemachine.state.impl.*;

public enum StateEnum {
    TEMP(0, new TempState()),
    ONLINE(1, new OnlineState()),
    PROCESSING(2, new ProcessingState()),
    REJECTED(3, new RejectedState()),
    OFFLINE(4, new OfflineState()),
    ;
    private final int code;
    private final State state;

    StateEnum(int code, State state) {
        this.code = code;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "StateEnum{" +
                "state=" + state +
                '}';
    }
}