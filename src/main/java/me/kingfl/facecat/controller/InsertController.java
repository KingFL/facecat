package me.kingfl.facecat.controller;


import me.kingfl.facecat.service.InsertService;
import me.kingfl.facecat.service.Tools;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class InsertController {
    //文件上传相关配置
    private String imageSavePath = "D:/images/";
    public static final String ROOT = "upload-dir";
    private ResourceLoader resourceLoader;


    private transient InsertService insertService;
    private transient Tools tools;

    @Autowired
    public InsertController(InsertService insertService, Tools tools){
        this.insertService = insertService;
        this.tools = tools;
    }


    /**
     * @return Map<String, Object>
     * @author KingFL
     * @date 2019/5/23 15:35
     * params:
     *
     */
    @ResponseBody
    @RequestMapping(value = "/sendMoment", method = RequestMethod.POST)
    public Map<String, Object> sendMoment(@Param("username") String username,
                                          @Param("content") String content,
                                          @Param("locatname") String locatname,
                                          @Param("catid") String catid,
                                          @Param("catname") String catname,
                                          @Param("file") MultipartFile file){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.out.println("---------- request \"sendMoment\"  received ----------");
        System.err.println(username + ", " + content + ", " + locatname + ", " + catid + ", " + catname);
        if(catname == null) {
            catname = "nocat";
            catid = "-1";
        }
        String time = Tools.getNow(1);
        String filename = username + "_" + catid + "_" + time;
        String fileextion = "." + Tools.getFileExtentName(file.getOriginalFilename()); //获取上传文件的文件类型
        String imagesavepath = imageSavePath + "userupload/" + filename + fileextion; //上传的图片在本地的路径
        String imagepath = "http://127.0.0.1:8080/images/userupload/" + filename + fileextion; //上传的图片外部访问地址
        String momentid = filename;
        String avatar = tools.getAvatar(username); // 用户头像访问地址
        String nickname = tools.getNickname(username); // 用户昵称
        if(!file.isEmpty()){
            try {
                File dest = new File(imagesavepath);
                file.transferTo(dest);
            }
            catch (IOException e){
                String errstr = e.toString();
                System.err.println(errstr);
                e.printStackTrace();
                resultMap.put("fileuploadresult", errstr);
            }
        }

        insertService.sendMoment(momentid, username, nickname, content, locatname, avatar, imagepath, catid, catname);
        System.err.println("----------method \"sendMoment\" execution finished----------");
        return resultMap;
    }



    @ResponseBody
    @RequestMapping(value = "/sendComment", method = RequestMethod.POST)
    public Map<String ,Object> sendComment(@Param("momentid") String momentid,
                                           @Param("comment") String comment,
                                           @Param("username") String username){
        System.out.println("---------- request \"sendComment\"  received ----------");
        System.err.println(momentid + ", " + comment + ", " + username);
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        String commentid = username + "_" + Tools.getNow(1);
        String avatar = tools.getAvatar(username);
        int line = insertService.sendComment(momentid, commentid, username, comment, avatar);
        resultMap.put("line", line);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllLocations", method = RequestMethod.POST)
    public Map<String, Object> getAllLocations(){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("locations", insertService.getAllLocations());
        return resultMap;
    }

}
