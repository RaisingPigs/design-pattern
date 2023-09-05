package com.pan.statemachine.machine;

import com.pan.statemachine.enums.Event;
import com.pan.statemachine.enums.StateEnum;
import com.pan.statemachine.state.State;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-09-05 23:28
 **/
public class MachineTest {
    @Test
    void test() {
        StateMachine stateMachine = new StateMachine(StateEnum.TEMP.getState());
        stateMachine.execute(Event.ONLINE_APPLY);
        stateMachine.execute(Event.PUBLISH);
        stateMachine.execute(Event.CANCEL_PUBLISH);
        stateMachine.execute(Event.ONLINE_WITHOUT_APPLY);
    }
}
