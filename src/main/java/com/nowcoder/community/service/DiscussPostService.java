package com.nowcoder.community.service;


import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//加个注解就可以让容器扫描到创建其实例化对象，直接调用其方法。
@Service
public class DiscussPostService {
    //肯定要调用mapper中（dao）中的方法查询，所以讲其注入为属性也就是对象实例，可以调用其方法。
    @Autowired
    private DiscussPostMapper discussPostMapper;

    //封装一个业务方法，查询某页数据（分页查询？）
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPosterRows(userId);
    }

}
