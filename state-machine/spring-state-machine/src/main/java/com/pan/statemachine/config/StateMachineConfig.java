package com.pan.statemachine.config;

import com.pan.statemachine.enums.SyncTaskVersionEvents;
import com.pan.statemachine.enums.SyncTaskVersionStates;
import com.pan.statemachine.model.SyncTaskVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "syncTaskStateMachine")
public class StateMachineConfig extends StateMachineConfigurerAdapter<SyncTaskVersionStates, SyncTaskVersionEvents> {

    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<SyncTaskVersionStates, SyncTaskVersionEvents> states) throws Exception {
        states.withStates()
                .initial(SyncTaskVersionStates.TEMP)
                .states(EnumSet.allOf(SyncTaskVersionStates.class));
    }

    /**
     * 配置状态转换与事件的关系
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<SyncTaskVersionStates, SyncTaskVersionEvents> transitions) throws Exception {
        transitions
                // 草稿状态到上线
                .withExternal()
                .source(SyncTaskVersionStates.TEMP)
                .target(SyncTaskVersionStates.ONLINE)
                .event(SyncTaskVersionEvents.ONLINE_WITHOUT_APPLY)
                .and()
                // 已下线状态到上线
                .withExternal()
                .source(SyncTaskVersionStates.OFFLINE)
                .target(SyncTaskVersionStates.ONLINE)
                .event(SyncTaskVersionEvents.ONLINE_WITHOUT_APPLY)
                .and()
                // 草稿状态到审批中
                .withExternal()
                .source(SyncTaskVersionStates.TEMP)
                .target(SyncTaskVersionStates.APPLYING)
                .event(SyncTaskVersionEvents.ONLINE_APPLY)
                .and()
                // 已下线状态到审批中
                .withExternal()
                .source(SyncTaskVersionStates.OFFLINE)
                .target(SyncTaskVersionStates.APPLYING)
                .event(SyncTaskVersionEvents.ONLINE_APPLY)
                .and()
                // 审批中到已上线
                .withExternal()
                .source(SyncTaskVersionStates.APPLYING)
                .target(SyncTaskVersionStates.ONLINE)
                .event(SyncTaskVersionEvents.PUBLISH)
                .and()
                // 从审批中变成审批不通过
                .withExternal()
                .source(SyncTaskVersionStates.APPLYING)
                .target(SyncTaskVersionStates.REJECTED)
                .event(SyncTaskVersionEvents.REJECT)
                .and()
                // 从已上线变为已下线
                .withExternal()
                .source(SyncTaskVersionStates.ONLINE)
                .target(SyncTaskVersionStates.OFFLINE)
                .event(SyncTaskVersionEvents.CANCEL_PUBLISH);
    }

    @Bean
    public StateMachinePersister<SyncTaskVersionStates, SyncTaskVersionEvents, SyncTaskVersion> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<SyncTaskVersionStates, SyncTaskVersionEvents, SyncTaskVersion>() {
            @Override
            public void write(StateMachineContext<SyncTaskVersionStates, SyncTaskVersionEvents> context, SyncTaskVersion syncTaskVersion) {
                //此处并没有进行持久化操作
                // 这里持久化一般是将StateMachineContext状态上下文保存到内存或者redis中, 下次读取在用, 但是实际上状态一般随着实体类保存在数据库表中, 并且状态可能会因为其他原因被修改, 所以实际开发中一般不进行持久化操作. 都是实时获取状态
            }

            @Override
            public StateMachineContext<SyncTaskVersionStates, SyncTaskVersionEvents> read(SyncTaskVersion syncTaskVersion) {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext<>(syncTaskVersion.getVersionStates(), null, null, null);
            }
        });
    }
}