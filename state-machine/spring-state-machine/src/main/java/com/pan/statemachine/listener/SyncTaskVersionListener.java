package com.pan.statemachine.listener;

import com.pan.statemachine.annotation.StatesOnTransition;
import com.pan.statemachine.enums.SyncTaskVersionStates;
import com.pan.statemachine.model.SyncTaskVersion;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@WithStateMachine(name = "syncTaskStateMachine")
public class SyncTaskVersionListener {
    @StatesOnTransition(source = {SyncTaskVersionStates.TEMP, SyncTaskVersionStates.OFFLINE, SyncTaskVersionStates.APPLYING}, target = SyncTaskVersionStates.ONLINE)
    public void online(@EventHeaders Map<String, Object> headers) {
        SyncTaskVersion syncTaskVersion = (SyncTaskVersion) headers.get("syncTaskVersion");
        System.out.println("执行sql版本上线相关的操作");
        System.out.println("修改sql版本为已上线");
    }

    @StatesOnTransition(source = {SyncTaskVersionStates.TEMP, SyncTaskVersionStates.OFFLINE}, target = SyncTaskVersionStates.APPLYING)
    public void apply(@EventHeaders Map<String, Object> headers) {
        SyncTaskVersion syncTaskVersion = (SyncTaskVersion) headers.get("syncTaskVersion");
        System.out.println("发起上线审批, 执行sql版本审批相关的操作");
        System.out.println("修改状态为审批中");
    }

    @StatesOnTransition(source = SyncTaskVersionStates.APPLYING, target = SyncTaskVersionStates.REJECTED)
    public void reject(@EventHeaders Map<String, Object> headers) {
        SyncTaskVersion syncTaskVersion = (SyncTaskVersion) headers.get("syncTaskVersion");
        System.out.println("执行sql版本审批不通过相关的操作");
        System.out.println("设置sql版本状态为审批不通过");
    }

    @StatesOnTransition(source = SyncTaskVersionStates.ONLINE, target = SyncTaskVersionStates.OFFLINE)
    public void offline(@EventHeaders Map<String, Object> headers) {
        SyncTaskVersion syncTaskVersion = (SyncTaskVersion) headers.get("syncTaskVersion");
        System.out.println("执行sql版本已下线相关的操作");
        System.out.println("修改sql版本状态为已下线");
    }
}