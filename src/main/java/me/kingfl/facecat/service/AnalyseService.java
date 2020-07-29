package me.kingfl.facecat.service;

import me.kingfl.facecat.mapper.AnalyseMapper;
import me.kingfl.facecat.model.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnalyseService {

    private transient AnalyseMapper analyseMapper;

    @Autowired
    public AnalyseService(AnalyseMapper analyseMapper){
        this.analyseMapper = analyseMapper;
    }

    public Map<String, Object> getMomentsCount(String start, String end){
        List<ListItem> resultList = analyseMapper.getMomentsCount(start, end);
        Map<String, Object> countMap = new ConcurrentHashMap<>();
        List<String> countlist = new ArrayList<>();
        List<String> taglist = new ArrayList<>();
        for(ListItem item : resultList){
            countlist.add(item.getValue());
            taglist.add(item.getLabel());
        }
        countMap.put("values", countlist);
        countMap.put("labels", taglist);
        countMap.put("title", "动态量");
        return countMap;
    }
    public Map<String, Object> getCommentsCount(String start, String end){
        List<ListItem> resultList = analyseMapper.getCommentsCount(start, end);
        Map<String, Object> countMap = new ConcurrentHashMap<>();
        List<String> countlist = new ArrayList<>();
        List<String> taglist = new ArrayList<>();
        for(ListItem item : resultList){
            countlist.add(item.getValue());
            taglist.add(item.getLabel());
        }
        countMap.put("values", countlist);
        countMap.put("labels", taglist);
        countMap.put("title", "评论量");
        return countMap;
    }
    public Map<String, Object> getCatMomentCount(){
        List<ListItem> resultlist= analyseMapper.getCatMomentCount();
        Map<String, Object> countMap = new ConcurrentHashMap<>();
        List<String> countlist = new ArrayList<>();
        List<String> taglist = new ArrayList<>();
        for(ListItem item : resultlist){
            countlist.add(item.getValue());
            taglist.add(item.getLabel());
        }
        countMap.put("values", countlist);
        countMap.put("labels", taglist);
        return countMap;
    }
    // 用户性别统计
    public Map<String, Object> getSexCount(){
        Map<String, Object> countMap = new ConcurrentHashMap<>();
        List<ListItem> resultlist = analyseMapper.getSexCount();
        List<Map<String, String>> list = new ArrayList<>();
        List<String> countlist = new ArrayList<>();
        List<String> taglist = new ArrayList<>();
        for(ListItem item : resultlist){
            Map<String, String> tempMap = new ConcurrentHashMap<>();
            tempMap.put("value", item.getValue());
            tempMap.put("name", item.getLabel());
            list.add(tempMap);
            countlist.add(item.getValue());
            taglist.add(item.getLabel());
        }
        countMap.put("data", list);
        return countMap;
    }
    public int getMomentCount(int token){
        List<ListItem> list = new ArrayList<>();
        String starttime = "";
        String endtime = Tools.getNow(0);
        if(token == 7)
            starttime = Tools.getLastWeek();
        if(token == 30)
            starttime = Tools.getLastMonth();
        list = analyseMapper.getMomentsCount(starttime, endtime);
        int count = 0;

        for(ListItem i : list){
            count += Integer.parseInt(i.getValue());
        }

        return count;
    }
    public int getCommentCount(int token){
        List<ListItem> list = new ArrayList<>();
        String starttime = "";
        String endtime = Tools.getNow(0);
        if(token == 7)
            starttime = Tools.getLastWeek();
        if(token == 30)
            starttime = Tools.getLastMonth();

        list = analyseMapper.getCommentsCount(starttime, endtime);
        int count = 0;
        for(ListItem i : list){
            count += Integer.parseInt(i.getValue());
        }
        return count;
    }

    public Map<String, Object> getLocationCount(){
        Map<String, Object> countMap = new ConcurrentHashMap<>();
        List<ListItem> resultlist = analyseMapper.getLocationCount();
        List<String> countlist = new ArrayList<>();
        List<String> taglist = new ArrayList<>();
        for(ListItem item : resultlist){
            countlist.add(item.getValue());
            taglist.add(item.getLabel());
        }
        countMap.put("labels", taglist);
        countMap.put("values", countlist);
        return countMap;
    }

    public int getMomentsTotal(){
        return analyseMapper.getMomentsTotal();
    }

    public int getCommentsTotal(){
        return analyseMapper.getCommentsTotal();
    }






}
