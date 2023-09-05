package com.pan.statemachine.controller;

import com.pan.statemachine.service.SyncTaskService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-09-05 22:58
 **/
@RestController
@RequestMapping("/api/sync_task")
public class SyncTaskController {
    @Resource
    private SyncTaskService syncTaskService;

    @PostMapping("/apply/{id}")
    public void apply(@PathVariable Long id) {
        syncTaskService.onlineApply(id);
    }
}
