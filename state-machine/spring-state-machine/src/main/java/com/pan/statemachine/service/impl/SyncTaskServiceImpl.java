package com.pan.statemachine.service.impl;

import com.pan.statemachine.enums.SyncTaskVersionEvents;
import com.pan.statemachine.enums.SyncTaskVersionStates;
import com.pan.statemachine.model.SyncTaskVersion;
import com.pan.statemachine.service.SyncTaskService;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SyncTaskServiceImpl implements SyncTaskService {
    @Resource
    private StateMachine<SyncTaskVersionStates, SyncTaskVersionEvents> syncTaskStateMachine;
    @Resource
    private StateMachinePersister<SyncTaskVersionStates, SyncTaskVersionEvents, SyncTaskVersion> persister;

    @Override
    public void onlineApply(Long id) {
        // 假装从数据中获取SyncTaskVersion
        SyncTaskVersion syncTaskVersion = new SyncTaskVersion();
        syncTaskVersion.setId(id);
        syncTaskVersion.setVersionStates(SyncTaskVersionStates.APPLYING);

        boolean sendRes = sendEvent(SyncTaskVersionEvents.PUBLISH, syncTaskVersion);
        if (!sendRes) {
            throw new RuntimeException("上线审批启动失败");
        }
    }

    // 发送事件的通用方法
    private synchronized boolean sendEvent(SyncTaskVersionEvents events, SyncTaskVersion syncTaskVersion) {
        boolean res = false;
        try {
            syncTaskStateMachine.start();
            persister.restore(syncTaskStateMachine, syncTaskVersion);
            Message<SyncTaskVersionEvents> message = MessageBuilder.withPayload(events).setHeader("syncTaskVersion", syncTaskVersion).build();
            res = syncTaskStateMachine.sendEvent(message);
            persister.persist(syncTaskStateMachine, syncTaskVersion);
        } catch (Exception e) {
            // 打印日志
            System.out.println(e.getMessage());
        } finally {
            syncTaskStateMachine.stop();
        }

        return res;
    }
}