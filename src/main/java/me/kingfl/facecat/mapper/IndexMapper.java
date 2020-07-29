package me.kingfl.facecat.mapper;

import me.kingfl.facecat.model.Comment;
import me.kingfl.facecat.model.Moment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexMapper {


    @Select("SELECT * " +
            "FROM moments " +
            "ORDER BY time DESC")
    List<Moment> getAllMoments();

    @Select("SELECT * " +
            "FROM moments " +
            "WHERE momentid = #{momentid} " +
            "LIMIT 1")
    List<Moment> getOneMoment(@Param("momentid") String momentid);

    @Select("SELECT COUNT(*) " +
            "FROM comments " +
            "WHERE momentid = #{momentid} " +
            "GROUP BY momentid")
    Integer getCommentsCount(@Param("momentid") String momentid);

    @Select("SELECT * " +
            "FROM comments " +
            "WHERE momentid = #{momentid} " +
            "ORDER BY commenttime DESC")
    List<Comment> getComments(@Param("momentid") String momentid);

    @Insert("insert into moments" +
            "(momentid, username, content, locatname, avatar, imagepath, catid, catname)values " +
            "(#{momentid}, #{username}, #{content}, #{locatname}, " +
            "#{avatar}, #{imagepath}, #{catid}, #{catname})")
    void sendMoment(@Param("momentid") String momentid,
                    @Param("username") String username,
                    @Param("content") String content,
                    @Param("locatname") String locatname,
                    @Param("avatar") String avatar,
                    @Param("imagepath") String imagepath,
                    @Param("catid") String catid,
                    @Param("catname") String catname);


}
