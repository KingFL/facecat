package me.kingfl.facecat.service;

import me.kingfl.facecat.mapper.IndexMapper;
import me.kingfl.facecat.model.Comment;
import me.kingfl.facecat.model.Moment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IndexService {

    private transient IndexMapper indexMapper;

    @Autowired
    public IndexService(IndexMapper indexMapper){
        this.indexMapper = indexMapper;
    }

    public List<Moment> getAllMoments(){
        List<Moment> list = indexMapper.getAllMoments();
        for(Moment moment : list) {
            Integer count = indexMapper.getCommentsCount(moment.getMomentid());
            moment.setCount((count == null) ? 0 : count); // 三元运算符 判断当count为null时将回复数记为0
            //System.err.println(moment.getMomentid() + "  count  ==  " + count); // 打印每条moment对应回复数
        }
        return list;
    }

    public List<Moment> getOneMoment(String momentid){
        List<Moment> list = indexMapper.getOneMoment(momentid);
        return list;
    }

    public List<Comment> getComments(String momentid){
        List<Comment> list = new ArrayList<>();
        list = indexMapper.getComments(momentid);
        return list;
    }

    public Map<String, Object> sendMoment(String momentid, String username, String content,
                              String locatname, String avatar, String imagepath,
                              String catid, String catname){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        String err = "";
        try {
            indexMapper.sendMoment(momentid, username, content, locatname,
                    avatar, imagepath, catid, catname);
        }
        catch (Exception e){
            err = e.toString();
            //e.printStackTrace();
        }
        if(err == "")
            resultMap.put("success", "success");
        else
            resultMap.put("fail", err);

        return resultMap;
    }


}
