package me.kingfl.facecat.controller;

import me.kingfl.facecat.service.Tools;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.kingfl.facecat.service.IndexService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class IndexController {

    private transient IndexService indexService;
    private transient Tools tools;
    private String imageSavePath = "D:/images/";
    public static final String ROOT = "upload-dir";
    private ResourceLoader resourceLoader;

    @Autowired
    public IndexController(IndexService indexService){
        this.indexService = indexService;
    }


    //获取全部动态（首页）
    @ResponseBody
    @RequestMapping(value = "/getAllMoments", method = RequestMethod.POST)
    public Map<String, Object> getAllMoments(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("result", indexService.getAllMoments());
        return resultMap;
    }

    //获取单条动态（动态详情页面）
    @ResponseBody
    @RequestMapping(value = "/getOneMoment", method = RequestMethod.POST)
    public Map<String, Object> getOneComment(@Param("momentid") String momentid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("moment", indexService.getOneMoment(momentid));

        resultMap.put("time", Tools.getNow(1));

        return resultMap;
    }


    //获取所有评论（动态详情页面）
    @ResponseBody
    @RequestMapping(value = "/getComments", method = RequestMethod.POST)
    public Map<String, Object> getComments(@Param("momentid") String momentid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("result" ,indexService.getComments(momentid));
        return resultMap;
    }



}
