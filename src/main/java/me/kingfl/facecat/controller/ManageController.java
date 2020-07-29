package me.kingfl.facecat.controller;

import me.kingfl.facecat.model.Cat;
import me.kingfl.facecat.model.Report;
import me.kingfl.facecat.model.UserInformation;
import me.kingfl.facecat.service.ManageService;
import me.kingfl.facecat.service.Tools;
import me.kingfl.facecat.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ManageController {

    private transient ManageService manageService;
    private transient UserService userService;

    @Autowired
    public ManageController(ManageService manageService, UserService userService){
        this.manageService = manageService;
        this.userService = userService;
    }

    // 管理员登录
    @ResponseBody
    @RequestMapping(value = "/managerLogin", method = RequestMethod.POST)
    public Map<String, Object> managerLogin(@Param("username") String username,
                                            @Param("password") String password){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.err.println(username);
        System.err.println(password);
        boolean check = false;
        if(username.equals("admin"))
            check = manageService.checkManager(username, password);
        resultMap.put("check", check);
        return resultMap;
    }

    // 用户管理
    //getFilterUsers
    @ResponseBody
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public Map<String, Object> getUsers(@Param("username") String username,
                                        @Param("nickname") String nickname,
                                        @Param("usersex") String usersex,
                                        @Param("starttime") String starttime,
                                        @Param("endtime") String endtime,
                                        @Param("curpage") Integer curpage,
                                        @Param("pagesize") int pagesize){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();

        username = Tools.getLikeString(username);
        nickname = Tools.getLikeString(nickname);
        usersex = Tools.getLikeString(usersex);
        if(starttime == null || starttime.isEmpty()) starttime = "1970-01-01 00:00:00";
        if(endtime == null || endtime.isEmpty()) endtime = Tools.getNow(0);
        resultMap.put("count", manageService.getUserCount(username, nickname, usersex, starttime, endtime));
        resultMap.put("curpage", curpage);
        resultMap.put("pagesize", pagesize);
        resultMap.put("users", manageService.getFilterUsers(username, nickname, usersex, starttime, endtime, (curpage-1) * pagesize, pagesize));
        return resultMap;
    }

    // 用户更新
    @ResponseBody
    @RequestMapping(value = "/updateUserinfo2", method = RequestMethod.POST)
    public Map<String, Object> updateUserinfo(@Param("username") String username,
                                              @Param("nickname") String nickname,
                                              @Param("usersex") String usersex,
                                              @Param("birthday") String birthday){

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        UserInformation userinfo = userService.getUserInformation(username);

        if(nickname == null || nickname.isEmpty()) nickname = userinfo.getNickname();
        if(usersex == null || nickname.isEmpty()) usersex = userinfo.getUsersex();
        if(birthday == null || birthday.isEmpty()) birthday = userinfo.getBirthday();

        int line = manageService.updateUserinfo(username, nickname, usersex, birthday);
        resultMap.put("line", line);

        return resultMap;
    }

    // 账户更新
    @ResponseBody
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public Map<String, Object> updateAccount(@Param("username") String username,
                                             @Param("password") String password){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.updateAccount(username, password);
        resultMap.put("line", line);
        return resultMap;
    }
    // 删除用户
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Map<String, Object> deleteUser(@Param("username") String username){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.err.println("!" + username);
        int line = manageService.deleteUser(username);
        resultMap.put("line", line);
        return resultMap;
    }



    // 筛选动态
    @ResponseBody
    @RequestMapping(value = "/getFiltedMoments", method = RequestMethod.POST)
    public Map<String, Object> getFiltedMoments(@Param("username") String username,
                                                @Param("nickname") String nickname,
                                                @Param("starttime") String starttime,
                                                @Param("endtime") String endtime,
                                                @Param("catname") String catname,
                                                @Param("locatname") String locatname,
                                                @Param("curpage") int curpage,
                                                @Param("pagesize") int pagesize){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();

        if(starttime == null || starttime.isEmpty())
            starttime = Tools.getStartTime();
        if(endtime == null || endtime.isEmpty())
            endtime = Tools.getNow(0);

        // 传入值检查
//        System.err.println("username: " + username + ", nickname: " + nickname);
//        System.err.println("time :" + starttime + " -> " + endtime);
//        System.err.println("catname: " + catname + ", locatname = " + locatname);
//        System.err.println("curpage: " + curpage + ", pagesize: " + pagesize);

        // null值处理
        catname = Tools.getLikeString(catname);
        username = Tools.getLikeString(username);
        nickname = Tools.getLikeString(nickname);
        locatname = Tools.getLikeString(locatname);

        int start = (curpage - 1) * pagesize;
        int count = manageService.getFiltedMomentsCount(username, nickname, starttime, endtime, catname, locatname);
        resultMap.put("curpage", curpage);
        resultMap.put("count", count);
        resultMap.put("pagesize", pagesize);
        resultMap.put("moments", manageService.getFiltedMoments(username, nickname, starttime, endtime, catname, locatname, start, pagesize));

        return resultMap;
    }
    // 动态修改
    @ResponseBody
    @RequestMapping(value = "/updateMoment", method = RequestMethod.POST)
    public Map<String, Object> updateMoment(@Param("momentid") String momentid,
                                            @Param("content") String content,
                                            @Param("time") String time){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        if(time == null || time.isEmpty()) time = Tools.getNow(0);

        int line = manageService.updateMoment(momentid, content, time);
        resultMap.put("line", line);
        return resultMap;
    }
    // 删除动态
    @ResponseBody
    @RequestMapping(value = "/deleteMoment", method = RequestMethod.POST)
    public Map<String, Object> deleteMomentByMomentid(@Param("momentid") String momentid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        resultMap.put("line", manageService.deleteMomentByMomentid(momentid));
        resultMap.put("commentsline",manageService.deleteCommentByMomentid(momentid));
        return resultMap;
    }

    // 评论管理
    // 评论筛选
    @ResponseBody
    @RequestMapping(value = "/getFiltedComment", method = RequestMethod.POST)
    public Map<String, Object> getFiltedComment(@Param("username") String username,
                                                @Param("starttime") String starttime,
                                                @Param("endtime") String endtime,
                                                @Param("comment") String comment,
                                                @Param("curpage") int curpage,
                                                @Param("pagesize") int pagesize){

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        username = Tools.getLikeString(username);
        comment = Tools.getLikeString(comment);
        if(starttime == null || starttime.isEmpty())
            starttime = Tools.getStartTime();
        if(endtime == null || endtime.isEmpty())
            endtime = Tools.getNow(0);

        int start = (curpage - 1) * pagesize;

        resultMap.put("count", manageService.getFiltedCommentCount(username, starttime, endtime, comment));
        resultMap.put("curpage", curpage);
        resultMap.put("pagesize", pagesize);
        resultMap.put("comments", manageService.getFiltedComment(username, starttime, endtime, comment, start, pagesize));
        return resultMap;
    }
    // 修改评论
    @ResponseBody
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public Map<String, Object> updateComment(@Param("commentid") String commentid,
                                             @Param("comment") String comment,
                                             @Param("commenttime") String commenttime){

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.err.println(commenttime);
        if(commenttime == null)
            commenttime = Tools.getNow(0);

        int line = manageService.updateComment(commentid, comment, commenttime);
        resultMap.put("line", line);
        return resultMap;

    }
    // 删除评论
    @ResponseBody
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public Map<String, Object> updateComment(@Param("commentid") String commentid){

        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.deleteComment(commentid);
        resultMap.put("line", line);
        return resultMap;

    }

    // 猫咪管理
    @ResponseBody
    @RequestMapping(value = "/getFiltedCats", method = RequestMethod.POST)
    public Map<String, Object> getFiltedCats(@Param("catid") String catid,
                                   @Param("catname") String catname,
                                   @Param("starttime") String starttime,
                                   @Param("endtime") String endtime,
                                   @Param("remark") String remark,
                                   @Param("curpage") int curpage,
                                   @Param("pagesize") int pagesize){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();

        if(starttime == null || starttime.isEmpty())
            starttime = Tools.getStartTime();
        if(endtime == null || endtime.isEmpty())
            endtime = Tools.getNow(0);
        // 处理输入数据
        catid = Tools.getLikeString(catid);
        catname = Tools.getLikeString(catname);
        remark = Tools.getLikeString(remark);

        int start = (curpage - 1) * pagesize;

        resultMap.put("curpage", curpage);
        resultMap.put("pagesize", pagesize);
        resultMap.put("count", manageService.getFiltedCatsCount(catid, catname, starttime, endtime, remark));
        resultMap.put("cats", manageService.getFiltedCats(catid, catname, starttime, endtime, remark, start, pagesize));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "/updateCat", method = RequestMethod.POST)
    public Map<String, Object> updateCat(@Param("catid") String catid,
                                         @Param("catname") String catname,
                                         @Param("foundtime") String foundtime,
                                         @Param("remark") String remark){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        System.err.println(catid + " " + catname + " " + foundtime + " " + remark);
        int line =  manageService.updateCat(catid, catname, foundtime, remark);
        resultMap.put("line", line);
        return resultMap;
    }
    ////////////////////////////////////////////////// editing
    @ResponseBody
    @RequestMapping(value = "/insertCat", method = RequestMethod.POST)
    public Map<String, Object> uploadCatimage(@Param("catname") String catname,
                                              @Param("catid") String catid,
                                              @Param("file") MultipartFile file,
                                              @Param("remark") String remark){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();


        return resultMap;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteCat", method = RequestMethod.POST)
    public Map<String, Object> deleteCat(@Param("catid") String catid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line =  manageService.deleteCat(catid);
        resultMap.put("line", line);
        return resultMap;
    }
    // 获取位置列表 不分页



    // 获取位置列表
    @ResponseBody
    @RequestMapping(value = "/getLocations", method = RequestMethod.POST)
    public Map<String, Object> getLocations(@Param("locatname") String locatname,
                                            @Param("curpage") int curpage,
                                            @Param("pagesize") int pagesize){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();

        int count = manageService.locationCount();
        resultMap.put("count", count);
        int start = (curpage - 1) * pagesize;
        locatname = Tools.getLikeString(locatname);
        resultMap.put("locations", manageService.getLocations(locatname, start, pagesize));
        return resultMap;
    }
    // 更新位置
    @ResponseBody
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public Map<String, Object> updateLocation(@Param("locatid") String locatid,
                                              @Param("locatname") String locatname){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.updateLocation(locatid, locatname);
        resultMap.put("line", line);
        return resultMap;
    }
    // 新增位置
    @ResponseBody
    @RequestMapping(value = "/insertLocation", method = RequestMethod.POST)
    public Map<String, Object> insertLocation(@Param("locatname") String locatname){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.insertLocation(locatname);
        resultMap.put("line", line);
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.POST)
    public Map<String, Object> deleteLocation(@Param("locatid") String locatid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.deleteLocation(locatid);
        resultMap.put("line", line);
        return resultMap;
    }


    // 反馈管理
    @ResponseBody
    @RequestMapping(value = "/getReports", method = RequestMethod.POST)
    public Map<String, Object> getReports(@Param("reportid") String reportid,
                                          @Param("starttime") String starttime,
                                          @Param("endtime") String endtime,
                                          @Param("username") String username,
                                          @Param("suggestion") String suggestion,
                                          @Param("contact") String contact,
                                          @Param("curpage") int curpage,
                                          @Param("pagesize") int pagesize){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        if(starttime == null || starttime.isEmpty())
            starttime = Tools.getStartTime();
        if(endtime == null || endtime.isEmpty())
            endtime = Tools.getNow(0);
        reportid = Tools.getLikeString(reportid);
        username = Tools.getLikeString(username);
        suggestion = Tools.getLikeString(suggestion);
        contact = Tools.getLikeString(contact);

        int start = (curpage - 1) * pagesize;
        int count = manageService.getReportsCount(reportid, starttime, endtime, username, suggestion, contact);
        resultMap.put("count", count);
        resultMap.put("curpage", curpage);
        resultMap.put("pagesize", pagesize);
        resultMap.put("reports", manageService.getReports(reportid, starttime, endtime, username, suggestion, contact, start, pagesize));
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteReport", method = RequestMethod.POST)
    public Map<String, Object> deleteReport(@Param("reportid") String reportid){
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        int line = manageService.deleteReport(reportid);
        resultMap.put("line", line);
        return resultMap;
    }


}
