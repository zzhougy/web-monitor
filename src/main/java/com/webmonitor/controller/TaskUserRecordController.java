package com.webmonitor.controller;

import com.webmonitor.config.annotation.GuestAccess;
import com.webmonitor.entity.ResponseVO;
import com.webmonitor.entity.bo.TaskUserRecordPageBO;
import com.webmonitor.entity.vo.PageResult;
import com.webmonitor.entity.vo.TaskUserRecordVO;
import com.webmonitor.service.TaskUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务用户记录控制器
 * 处理用户任务记录的分页查询请求
 */
@RestController
@RequestMapping("/v1/user/task/record")
public class TaskUserRecordController {

  private final TaskUserRecordService taskUserRecordService;

  @Autowired
  public TaskUserRecordController(TaskUserRecordService taskUserRecordService) {
    this.taskUserRecordService = taskUserRecordService;
  }

  /**
   * 分页查询当前用户的任务记录
   */
  @GuestAccess // todo remove
  @PostMapping("/page")
  public ResponseVO<PageResult<TaskUserRecordVO>> getUserTaskRecords(@Validated @RequestBody TaskUserRecordPageBO bo) {
    PageResult<TaskUserRecordVO> result = taskUserRecordService.queryUserTaskRecordsByPage(bo);
    return ResponseVO.success(result);
  }
}