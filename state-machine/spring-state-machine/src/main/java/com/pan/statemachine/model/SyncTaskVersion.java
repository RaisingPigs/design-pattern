package com.pan.statemachine.model;

import com.pan.statemachine.enums.SyncTaskVersionStates;
import lombok.Data;

@Data
public class SyncTaskVersion {
    private Long id;
    private SyncTaskVersionStates versionStates;
    // ... 省略其他字段
}