package com.pan.statemachine.transition;

import com.pan.statemachine.action.Action;
import com.pan.statemachine.enums.Event;
import com.pan.statemachine.enums.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transition {
    private State curState;
    private State nextState;
    private Event event;
    private Action action;
}