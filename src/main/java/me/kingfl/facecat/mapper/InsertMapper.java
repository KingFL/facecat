package me.kingfl.facecat.mapper;


import me.kingfl.facecat.model.ListItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InsertMapper {


    @Insert("insert into moments" +
            "(momentid, username, nickname, content, locatname, avatar, imagepath, catid, catname)values " +
            "(#{momentid}, #{username}, #{nickname}, #{content}, #{locatname}, " +
            "#{avatar}, #{imagepath}, #{catid}, #{catname})")
    void sendMoment(@Param("momentid") String momentid,
                    @Param("username") String username,
                    @Param("nickname") String nickname,
                    @Param("content") String content,
                    @Param("locatname") String locatname,
                    @Param("avatar") String avatar,
                    @Param("imagepath") String imagepath,
                    @Param("catid") String catid,
                    @Param("catname") String catname);


    @Insert("insert into comments" +
            "(momentid, commentid, username, comment, avatar) values" +
            "(#{momentid}, #{commentid}, #{username}, #{comment}, #{avatar})")
    int sendComment(@Param("momentid") String momentid,
                     @Param("commentid") String commentid,
                     @Param("username") String username,
                     @Param("comment") String comment,
                     @Param("avatar") String avatar);


    @Select("SELECT locatid AS value, locatname AS label " +
            "FROM location " +
            "ORDER BY locatid ")
    List<ListItem> getAllLocations();
}
