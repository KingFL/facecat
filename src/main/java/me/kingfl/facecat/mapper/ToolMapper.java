package me.kingfl.facecat.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface ToolMapper {



    @Select("SELECT avatar " +
            "FROM personal " +
            "WHERE username = #{username} " +
            "LIMIT 1")
    String getUserAvatar(@Param("username") String username);

    // 查询用户昵称
    @Select("SELECT nickname " +
            "FROM personal " +
            "WHERE username = #{username} " +
            "LIMIT 1")
    String getUserNickname(@Param("username") String username);
}
