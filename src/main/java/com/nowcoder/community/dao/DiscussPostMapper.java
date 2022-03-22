package com.nowcoder.community.dao;


import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //分页查询，查询是多条数据。所以返回的是一个集合
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@Param注解用于给参数写别名
    //如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPosterRows(@Param("userId") int userId);
}
