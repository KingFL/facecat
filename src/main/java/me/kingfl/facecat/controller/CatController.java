package me.kingfl.facecat.controller;


import com.sun.corba.se.spi.ior.ObjectKey;
import me.kingfl.facecat.model.Cat;
import me.kingfl.facecat.service.CatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.kingfl.facecat.service.IndexService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CatController {

    private transient CatService catService;

    @Autowired
    public CatController(CatService catService){
        this.catService = catService;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllCats", method = RequestMethod.POST)
    public Map<String, Object> getAllCats(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        List<Cat> catList = catService.getAllCats();
        for(Cat cat : catList){
            cat.setCount(catService.getCatMomentCount(cat.getCatid()));
        }
        resultMap.put("result", catList);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getOneCat", method = RequestMethod.POST)
    public Map<String, Object> getOneCat(@Param("catid") String catid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("cat", catService.getOneCats(catid));
        resultMap.put("moments", catService.getMomentsByCatid(catid));
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getCatsList", method = RequestMethod.POST)
    public Map<String, Object> getCatsList(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("result", catService.getCatsList());
        return resultMap;
    }
}
