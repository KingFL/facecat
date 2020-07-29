package me.kingfl.facecat.service;


import me.kingfl.facecat.mapper.InsertMapper;
import me.kingfl.facecat.model.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertService {

    private transient InsertMapper insertMapper;
    private transient Tools tools;
    @Autowired
    public InsertService(InsertMapper insertMapper, Tools tools){
        this.insertMapper = insertMapper;
        this.tools = tools;
    }

    //发送动态
    public void sendMoment(String momentid, String username, String nickname, String content,
                           String locatname, String avatar, String imagepath,
                           String catid, String catname){
        insertMapper.sendMoment(momentid, username, nickname, content, locatname, avatar, imagepath, catid, catname);

    }


    public int sendComment(String momentid, String commentid, String username, String comment, String avatar){

        int line = insertMapper.sendComment(momentid, commentid, username, comment, avatar);
        return line;
    }

    public List<ListItem> getAllLocations(){
        return insertMapper.getAllLocations();
    }

}
