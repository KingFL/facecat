package me.kingfl.facecat.mapper;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.kingfl.facecat.model.*;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Component
public interface ManageMapper {

    @Select("SELECT count(*) " +
            "FROM accounts " +
            "WHERE username = #{username} AND password = #{password}")
    int checkManager(String username, String password);


    // -------------动态管理
    // 动态筛选
    @Select("SELECT * " +
            "FROM moments " +
            "WHERE username LIKE #{username} AND nickname LIKE #{nickname} " +
            "AND `time` BETWEEN #{starttime} AND #{endtime} AND catname LIKE #{catname} AND locatname LIKE #{locatname} " +
            "ORDER BY time DESC " +
            "LIMIT #{curpage}, #{pagesize}")
    List<Moment> getFiltedMoments(String username, String nickname, String starttime, String endtime, String catname, String locatname, int curpage, int pagesize);
    // 动态计数
    @Select("SELECT count(*) " +
            "FROM moments " +
            "WHERE username LIKE #{username} AND nickname LIKE #{nickname} " +
            "AND `time` BETWEEN #{starttime} AND #{endtime} AND catname LIKE #{catname} AND locatname LIKE #{locatname} ")
    int getFiltedMomentsCount(String username, String nickname, String starttime, String endtime, String catname, String locatname);
    // 动态删除
    @Delete("DELETE " +
            "FROM moments " +
            "WHERE momentid = #{momentid} ")
    int deleteMomentByMomentid(@Param("momentid") String momentid);
    // 动态修改
    @Update("update moments " +
            "set time = #{time}, content = #{content} " +
            "where momentid = #{momentid}")
    int updateMoment(String momentid, String content, String time);



    // 评论管理
    // 评论筛选
    @Select("SELECT * " +
            "FROM comments " +
            "WHERE username LIKE #{username} AND comment LIKE #{comment} AND " +
            "commenttime BETWEEN #{starttime} AND #{endtime} " +
            "ORDER BY commenttime DESC " +
            "LIMIT #{curpage}, #{pagesize}")
    List<Comment> getFiltedComment(String username, String starttime, String endtime, String comment, int curpage, int pagesize);
    @Select("SELECT count(*) " +
            "FROM comments " +
            "WHERE username LIKE #{username} AND comment LIKE #{comment} AND " +
            "commenttime BETWEEN #{starttime} AND #{endtime} ")
    int getFiltedCommentCount(String username, String starttime, String endtime, String comment);
    // 更新评论
    @Update("update comments " +
            "SET comment = #{comment}, commenttime = #{time} " +
            "WHERE commentid = #{commentid}")
    int updateComment(String commentid, String comment, String time);
    // 删除评论
    @Delete("delete from comments " +
            "where commentid = #{commentid}")
    int deleteComment(String commentid);
    // 联动动态删除评论
    @Delete("delete from comments " +
            "where momentid = #{momentid} ")
    int deleteCommentByMomentid(String momentid);



    // 猫咪管理
    // 猫咪筛选
    @Select("SELECT * " +
            "FROM cats " +
            "WHERE catid LIKE #{catid} AND catname LIKE #{catname} AND foundtime BETWEEN #{starttime} AND #{endtime} AND remark LIKE #{remark} " +
            "ORDER BY catid " +
            "LIMIT #{curpage}, #{pagesize}")
    List<Cat> getFiltedCats(String catid, String catname, String starttime, String endtime, String remark, int curpage, int pagesize);
    @Select("SELECT count(*) " +
            "FROM cats " +
            "WHERE catid LIKE #{catid} AND catname LIKE #{catname} AND foundtime BETWEEN #{starttime} AND #{endtime} AND remark LIKE #{remark} ")
    int getFiltedCatsCount(String catid, String catname, String starttime, String endtime, String remark);
    // 猫咪修改
    @Update("UPDATE cats " +
            "SET catname = #{catname}, foundtime = #{time}, remark = #{remark} " +
            "WHERE catid = #{catid}")
    int updateCat(String catid, String catname, String time, String remark);
    @Delete("DELETE from cats " +
            "WHERE catid = #{catid}")
    int deleteCat(String catid);



    // 位置管理
    @Select("SELECT locatid AS value, locatname AS label " +
            "FROM location " +
            "WHERE locatname LIKE #{locatname} " +
            "ORDER BY locatid " +
            "LIMIT #{curpage}, #{pagesize}")
    List<ListItem> getLocations(String locatname,int curpage, int pagesize);





    @Select("SELECT count(*) " +
            "FROM location ")
    int locationCount();
    @Update("update location " +
            "set locatname = #{locatname} " +
            "where locatid = #{locatid}")
    int updateLocation(String locatid, String locatname);
    @Insert("INSERT into location " +
            "(locatname) values " +
            "(#{locatname})")
    int insertLocation(String locatname);
    @Delete("delete from location " +
            "where locatid = #{locatid}")
    int deleteLocation(String locatid);

    // 用户管理
    @Select("SELECT * " +
            "FROM personal ")
    List<UserInformation> getAllUsers();
    // 筛选用户
    @Select("SELECT * " +
            "FROM personal " +
            "WHERE username LIKE #{username} AND nickname LIKE #{nickname} AND usersex LIKE #{usersex} " +
            "AND birthday BETWEEN #{starttime} AND #{endtime} " +
            "LIMIT #{curpage}, #{pagesize}")
    List<UserInformation> getFilterUsers(String username, String nickname, String usersex, String starttime, String endtime, int curpage, int pagesize);
    @Select("SELECT count(*) " +
            "FROM personal " +
            "WHERE username LIKE #{username} AND nickname LIKE #{nickname} AND usersex LIKE #{usersex} " +
            "AND birthday BETWEEN #{starttime} AND #{endtime} ")
    int getUserCount(String username, String nickname, String usersex, String starttime, String endtime);

    // 更新用户
    @Update("update personal " +
            "nickname = #{nickname}, usersex = #{usersex}, birthday = #{birthday} " +
            "WHERE username = #{username}")
    int updateUserinfo(String username, String nickname, String usersex, String birthday);
    // 更新用户密码
    @Update("update accounts " +
            "set password = #{password} " +
            "WHERE username = #{username}")
    int updatePassword(String username, String password);
    // 删除用户
    @Delete("delete from personal " +
            "where username = #{username}")
    int deletePerson(String username);
    // 删除账户
    @Delete("delete from accounts " +
            "where username = #{username}")
    int deleteAccount(String username);
    // 用户计数
    @Select("SELECT count(*) " +
            "FROM accounts " +
            "WHERE manager = false")
    int userCount();


    // 反馈管理
    @Select("SELECT * " +
            "FROM reports " +
            "WHERE reportid LIKE #{reportid} AND time BETWEEN #{starttime} AND #{endtime} AND username LIKE #{username} " +
            "AND suggestion LIKE #{suggestion} AND contact LIKE #{contact} " +
            "ORDER BY time " +
            "LIMIT #{curpage}, #{pagesize}")
    List<Report> getReports(String reportid, String starttime, String endtime, String username, String suggestion, String contact, int curpage, int pagesize);
    @Select("SELECT COUNT(*) " +
            "FROM reports " +
            "WHERE reportid LIKE #{reportid} AND time BETWEEN #{starttime} AND #{endtime} AND username LIKE username " +
            "AND suggestion LIKE #{suggestion} AND contact LIKE #{contact} ")
    int getReportsCount(String reportid, String starttime, String endtime, String username, String suggestion, String contact);
    @Delete("delete from reports " +
            "WHERE reportid = #{reportid}")
    int deleteReport(@Param("reportid") String reportid);

}

