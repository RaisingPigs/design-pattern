package com.pan.statemachine.mytest;

import com.pan.statemachine.config.StateMachine;
import com.pan.statemachine.enums.Event;
import com.pan.statemachine.enums.State;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-09-05 23:19
 **/
public class MyTest {
    @Test
    void test() {
        StateMachine stateMachine = new StateMachine(State.TEMP);
        stateMachine.execute(Event.ONLINE_APPLY);
        stateMachine.execute(Event.PUBLISH);
        stateMachine.execute(Event.CANCEL_PUBLISH);
        stateMachine.execute(Event.ONLINE_WITHOUT_APPLY);
    }
}
