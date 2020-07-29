package me.kingfl.facecat.controller;


import com.sun.corba.se.spi.ior.ObjectKey;
import me.kingfl.facecat.service.AnalyseService;
import me.kingfl.facecat.service.Tools;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class AnalyseController {

    private transient AnalyseService analyseService;

    public AnalyseController(AnalyseService analyseService){
        this.analyseService = analyseService;
    }


    @ResponseBody
    @RequestMapping(value = "/totalAnalyse", method = RequestMethod.POST)
    public Map<String, Object> totalAnalyse(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        String lastWeek = Tools.getLastWeek();
        String lastMonth = Tools.getLastMonth();
        String now = Tools.getNow(0);
        resultMap.put("moments7", analyseService.getMomentCount(7));
        resultMap.put("moments30", analyseService.getMomentCount(30));
        resultMap.put("comments7", analyseService.getCommentCount(7));
        resultMap.put("comments30", analyseService.getCommentCount(30));
        resultMap.put("momenttotal", analyseService.getMomentsTotal());
        resultMap.put("commenttotal", analyseService.getCommentsTotal());
        resultMap.put("weekmomentchange", analyseService.getMomentsCount(lastWeek, now));
        resultMap.put("monthmomentchange", analyseService.getMomentsCount(lastMonth, now));
        resultMap.put("weekcommentchange", analyseService.getCommentsCount(lastWeek, now));
        resultMap.put("monthcommentchange", analyseService.getCommentsCount(lastMonth, now));
        return resultMap;
    }

    // 用户数据分析
    @ResponseBody
    @RequestMapping(value = "/dataAnalyse", method = RequestMethod.POST)
    public Map<String, Object> dataAnalyse(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("sexcount", analyseService.getSexCount());
        resultMap.put("catcount", analyseService.getCatMomentCount());
        resultMap.put("locationcount", analyseService.getLocationCount());
        return resultMap;
    }

    // 用户数据分析
    @ResponseBody
    @RequestMapping(value = "/userAnalyse", method = RequestMethod.POST)
    public Map<String, Object> userAnalyse(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("users", analyseService.getSexCount());
        return resultMap;
    }

    // 猫咪动态分布
    @ResponseBody
    @RequestMapping(value = "/catAnalyse", method = RequestMethod.POST)
    public Map<String, Object> catAnalyse(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("count", analyseService.getCatMomentCount());
        return resultMap;
    }



}
