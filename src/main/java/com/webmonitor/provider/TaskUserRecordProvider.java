package com.webmonitor.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.webmonitor.entity.po.TaskUserConfig;
import com.webmonitor.entity.po.TaskUserRecord;
import com.webmonitor.mapper.TaskUserRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TaskUserRecordProvider extends ServiceImpl<TaskUserRecordMapper, TaskUserRecord> {

  @Resource
  private TaskUserRecordMapper taskUserRecordMapper;

  public Page<TaskUserRecord> queryUserTaskRecordsByPage(Integer userId, int pageNum, int pageSize) {
    MPJLambdaWrapper<TaskUserRecord> wrapper = new MPJLambdaWrapper<TaskUserRecord>()
            .selectAll(TaskUserRecord.class)
            .selectAssociation(TaskUserConfig.class, TaskUserRecord::getTaskUserConfig)
            .innerJoin(TaskUserConfig.class, TaskUserConfig::getId, TaskUserRecord::getTaskConfigId)
            .eq(TaskUserRecord::getUserId, userId)
            .orderByDesc(TaskUserRecord::getCreateTime);

    Page<TaskUserRecord> listPage = taskUserRecordMapper.selectJoinPage(new Page<>(pageNum, pageSize), TaskUserRecord.class, wrapper);

    return listPage;
  }
}
