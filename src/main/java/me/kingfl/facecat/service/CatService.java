package me.kingfl.facecat.service;

import me.kingfl.facecat.mapper.CatsMapper;
import me.kingfl.facecat.model.Cat;
import me.kingfl.facecat.model.ListItem;
import me.kingfl.facecat.model.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private transient CatsMapper catsMapper;

    @Autowired
    public CatService(CatsMapper catsMapper){
        this.catsMapper = catsMapper;
    }

    public List<Cat> getAllCats(){
        List<Cat> list = catsMapper.getAllCats();
        return list;
    }
    public int getCatMomentCount(String catid){
        int count = catsMapper.getCatMomentCount(catid);
        return count;
    }

    public List<Cat> getOneCats(String catid){
        List<Cat> list = catsMapper.getOneCat(catid);
        return list;
    }

    public List<Moment> getMomentsByCatid(String catid){
        List<Moment> list = catsMapper.getMomentsByCatid(catid);
        return list;
    }

    public List<ListItem> getCatsList(){
        List<ListItem> list = catsMapper.getCatsList();
        return list;
    }
}
