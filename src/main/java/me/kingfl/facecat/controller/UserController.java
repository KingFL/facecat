package me.kingfl.facecat.controller;

import com.sun.corba.se.spi.ior.ObjectKey;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import me.kingfl.facecat.model.UserInformation;
import me.kingfl.facecat.service.Tools;
import me.kingfl.facecat.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserController {

    private transient UserService userService;
    private String imageSavePath = "D:/images/";

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    // 用户登录 返回stat为1密码正确 2为错误
    @ResponseBody
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Map<String, Object> userLogin(@Param("username") String username,
                                     @Param("password") String password){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("stat", userService.userLogin(username, password));
        return resultMap;
    }
    // 获取用户信息
    @ResponseBody
    @RequestMapping(value = "/getUserInformation", method = RequestMethod.POST)
    public Map<String, Object> getUserInformation(@Param("username") String username){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("userinformation", userService.getUserInformation(username));
        return resultMap;
    }

    // 检查用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    public Map<String, Object> checkUsername(@Param("username") String username){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.err.println("username = " + username);
        resultMap.put("count", userService.checkUsername(username));
        return resultMap;
    }

    // 用户注册
    @ResponseBody
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public Map<String, Object> registerUser(@Param("username") String username,
                                            @Param("password") String password,
                                            @Param("usersex") String usersex,
                                            @Param("birthday") String birthday){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.out.println("新用户注册：");
        if(usersex == "男")
            usersex = "male";
        if(usersex == "女")
            usersex = "female";
        System.out.println("username = " + username + ",password = " + password
                + ", usersex = " + usersex + ", birthday = " + birthday);
        // 需要编写一个保存默认头像的方法

        //String avatar = "http://192.168.1.188:8080/images/avatar/" + username + ".jpg";
        int line1 = userService.registerUser(username, usersex, birthday);
        int line2 = userService.registerAccount(username, password);
        resultMap.put("line", (line1 == line2) ? line1 : 0);
        return resultMap;
    }

    // 更新用户信息
    @ResponseBody
    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    public Map<String, Object> updateAvatar(@Param("file") MultipartFile file,
                                            @Param("username") String username){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        UserInformation userinfo = userService.getUserInformation(username);

        try {
            String fileName = username + "." + Tools.getFileExtentName(file.getOriginalFilename());
            String avatar = "http://127.0.0.1:8080/images/avatar/" + fileName;
            String filePath = imageSavePath + "avatar/" + fileName;

            File dest = new File(filePath);
            file.transferTo(dest);

            userService.updateUserInfo(userinfo.getUsername(), userinfo.getNickname(), userinfo.getUsersex(), userinfo.getBirthday(), avatar);

        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("err", e.toString());
        }
        return resultMap;
    }

    // 更新用户信息
    @ResponseBody
    @RequestMapping(value = "/updateUserinfo", method = RequestMethod.POST)
    public Map<String, Object> updateAvatar(@Param("username") String username,
                                            @Param("nickname") String nickname,
                                            @Param("usersex") String usersex,
                                            @Param("birthday") String birthday){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        UserInformation userinfo =  userService.getUserInformation(username);

        if(nickname == null || nickname == "")
            nickname = userinfo.getNickname();
        if (birthday == null || birthday == "")
            birthday = userinfo.getBirthday();

        int line = userService.updateUserInfo(username, nickname, usersex, birthday, userinfo.getAvatar());
        resultMap.put("old", userinfo);
        resultMap.put("line", line);


        return resultMap;
    }

    // 用户反馈
    @ResponseBody
    @RequestMapping(value = "/sendReport", method = RequestMethod.POST)
    public Map<String, Object> sendReport(@Param("username") String username,
                                          @Param("suggestion") String suggestion,
                                          @Param("contact") String contact) {

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        if(username == "") username = "匿名";
        resultMap.put("count", userService.sendReport(username, suggestion, contact));
        return resultMap;
    }





}
