package me.kingfl.facecat.mapper;

import me.kingfl.facecat.model.Cat;
import me.kingfl.facecat.model.ListItem;
import me.kingfl.facecat.model.Moment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatsMapper {

    @Select("SELECT * " +
            "FROM cats " +
            "ORDER BY catid")
    List<Cat> getAllCats();

    @Select("SELECT count(catid) " +
            "FROM moments " +
            "WHERE catid = #{catid}")
    int getCatMomentCount(String catid);

    @Select("SELECT * " +
            "FROM cats " +
            "WHERE catid = #{catid} " +
            "LIMIT 1")
    List<Cat> getOneCat(@Param("catid") String catid);

    @Select("SELECT * " +
            "FROM moments " +
            "WHERE catid = #{catid} " +
            "ORDER BY time")
    List<Moment> getMomentsByCatid(@Param("catid") String catid);

    @Select("SELECT catid AS value, catname AS label " +
            "FROM cats " +
            "ORDER BY catid")
    List<ListItem> getCatsList();
}
