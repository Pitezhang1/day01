package com.jiyun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiyun.entity.Message;
import org.apache.ibatis.annotations.Update;

public interface MessageMapper extends BaseMapper<Message> {
    @Update("update d16_message set read_status='已读'")
    void updateReadStatus();
}
