package me.kingfl.facecat.mapper;


import me.kingfl.facecat.model.ListItem;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface AnalyseMapper {

    @Select("SELECT SUBSTR(time FROM 1 FOR 10) AS `label`, COUNT(SUBSTR(time FROM 1 FOR 10)) AS `value`\n" +
            "FROM moments\n" +
            "WHERE time BETWEEN #{start} AND #{end} " +
            "GROUP BY SUBSTR(time FROM 1 FOR 10)\n" +
            "ORDER BY time")
    List<ListItem> getMomentsCount(String start, String end);
    @Select("SELECT count(*) " +
            "FROM moments")
    int getMomentsTotal();
    @Select("SELECT count(*) " +
            "FROM comments")
    int getCommentsTotal();
    @Select("SELECT SUBSTR(commenttime FROM 1 FOR 10) AS `label`, COUNT(SUBSTR(commenttime FROM 1 FOR 10)) AS `value`\n" +
            "FROM comments\n" +
            "WHERE commenttime BETWEEN #{start} AND #{end} " +
            "GROUP BY SUBSTR(commenttime FROM 1 FOR 10)\n" +
            "ORDER BY commenttime")
    List<ListItem> getCommentsCount(String start, String end);

    @Select("SELECT catname as `label`, count(catname) as `value` " +
            "FROM moments " +
            "GROUP BY catname " +
            "ORDER BY catid ")
    List<ListItem> getCatMomentCount();

    @Select("SELECT usersex as `label`, COUNT(usersex) as `value` " +
            "FROM personal " +
            "GROUP BY usersex ")
    List<ListItem> getSexCount();

    @Select("SELECT COUNT(*) " +
            "FROM personal " +
            "WHERE usersex = '男'")
    int getManCount();
    @Select("SELECT COUNT(*) " +
            "FROM personal " +
            "WHERE usersex = '女'")
    int getWomanCount();

    @Select("SELECT locatname AS label, count(locatname) AS value " +
            "FROM moments " +
            "GROUP BY locatname")
    List<ListItem> getLocationCount();
}
