package com.pan.statemachine.config;

import com.pan.statemachine.action.impl.CancelPublishAction;
import com.pan.statemachine.action.impl.OnlineApplyAction;
import com.pan.statemachine.action.impl.OnlineWithoutApplyAction;
import com.pan.statemachine.action.impl.RejectAction;
import com.pan.statemachine.enums.Event;
import com.pan.statemachine.enums.State;
import com.pan.statemachine.transition.Transition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StateMachine {
    private final List<Transition> transitionList = Stream.of(
            // 草稿状态到上线
            Transition.builder()
                    .curState(State.TEMP)
                    .nextState(State.ONLINE)
                    .event(Event.ONLINE_WITHOUT_APPLY)
                    .action(new OnlineWithoutApplyAction())
                    .build(),
            // 已下线状态到上线
            Transition.builder()
                    .curState(State.OFFLINE)
                    .nextState(State.ONLINE)
                    .event(Event.ONLINE_WITHOUT_APPLY)
                    .action(new OnlineWithoutApplyAction())
                    .build(),
            // 草稿状态到审批中
            Transition.builder()
                    .curState(State.TEMP)
                    .nextState(State.PROCESSING)
                    .event(Event.ONLINE_APPLY)
                    .action(new OnlineApplyAction())
                    .build(),
            // 已下线状态到审批中
            Transition.builder()
                    .curState(State.OFFLINE)
                    .nextState(State.PROCESSING)
                    .event(Event.ONLINE_APPLY)
                    .action(new OnlineApplyAction())
                    .build(),
            // 审批中到已上线
            Transition.builder()
                    .curState(State.PROCESSING)
                    .nextState(State.ONLINE)
                    .event(Event.PUBLISH)
                    // 这里可以复用OnlineWithoutApplyAction()的逻辑
                    .action(new OnlineWithoutApplyAction())
                    .build(),
            // 从审批中变成审批不通过
            Transition.builder()
                    .curState(State.PROCESSING)
                    .nextState(State.REJECTED)
                    .event(Event.REJECT)
                    .action(new RejectAction())
                    .build(),
            // 从已上线变为已下线
            Transition.builder()
                    .curState(State.ONLINE)
                    .nextState(State.OFFLINE)
                    .event(Event.CANCEL_PUBLISH)
                    .action(new CancelPublishAction())
                    .build()
    ).collect(Collectors.toList());

    private State state;

    public StateMachine(State state) {
        this.state = state;
    }

    public void execute(Event event) {
        // 如果觉得这里次次遍历不太好, 可以用HashMap来存储, 以Event为key, 以Transition为value
        Transition transition = transitionList.stream()
                .filter(trans -> trans.getEvent().equals(event))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("in valid event" + event));
        
        this.state = transition.getNextState();
        transition.getAction().execute();
    }
}