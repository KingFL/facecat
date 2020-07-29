package me.kingfl.facecat.mapper;


import me.kingfl.facecat.model.Report;
import me.kingfl.facecat.model.UserInformation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserMapper {

    // 获取用户信息
    @Select("SELECT * " +
            "FROM personal " +
            "WHERE username = #{username}")
    UserInformation getUserInformation(@Param("username") String username);

    // 获取用户密码（用户登录核对
    @Select("SELECT password " +
            "FROM accounts " +
            "WHERE username = #{username}")
    String getPassword(@Param("username") String username);

    @Select("SELECT avatar " +
            "FROM personal " +
            "WHERE username = #{username}")
    String getAvatar(@Param("username") String username);



    // 检查用户名是否存在
    @Select("SELECT count(*) " +
            "FROM accounts " +
            "WHERE username = #{username}")
    int checkUsername(@Param("username") String username);

    // 注册账户（用户名和密码
    @Insert("INSERT into accounts" +
            "(username, password) values" +
            "(#{username}, #{password})")
    int registerAccount(@Param("username") String username,
                            @Param("password") String password);
    // 注册用户（个人信息
    @Insert("INSERT into personal" +
            "(username, nickname, usersex, birthday)values" +
            "(#{username}, #{username}, #{usersex}, #{birthday})")
    int registerUser(@Param("username") String username,
                     @Param("usersex") String usersex,
                     @Param("birthday") String birthday);

//    @Update("update personal " +
//            "set avatar = #{avatar} " +
//            "WHERE username = #{username}")
//    int updateAvatar(@Param("username") String username,
//                     @Param("nickname") String nickname,
//                     @Param("birthday") String birthday,
//                     @Param("avatar") String avatar);

    @Update("update personal " +
            "set nickname = #{nickname}, usersex = #{usersex}, birthday = #{birthday}, avatar = #{avatar}" +
            "WHERE username = #{username}")
    int updateUserInfo(String username, String nickname, String usersex, String birthday, String avatar);
    @Update("update moments " +
            "set Avatar = #{Avatar} " +
            "WHERE username = #{username}")
    int updateMomentsAvatar(String username, String Avatar);
    @Update("update comments " +
            "set Avatar = #{Avatar} " +
            "WHERE username = #{username}")
    int updateCommentsAvatar(String username, String Avatar);
    // 用户建议

    @Insert("insert into reports " +
            "(reportid, username, suggestion, contact) values " +
            "(#{reportid}, #{username}, #{suggestion}, #{contact})")
    int sendReport(@Param("reportid") String reportid,
                   @Param("username") String username,
                   @Param("suggestion") String suggestion,
                   @Param("contact") String contact);



}
