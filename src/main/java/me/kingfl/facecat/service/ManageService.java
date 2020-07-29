package me.kingfl.facecat.service;

import me.kingfl.facecat.mapper.ManageMapper;
import me.kingfl.facecat.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageService<getCommentCount> {

    private transient ManageMapper manageMapper;

    @Autowired
    public ManageService(ManageMapper manageMapper){
        this.manageMapper = manageMapper;
    }

    // 管理员登录
    public boolean checkManager(String username, String password){
        int i = manageMapper.checkManager(username, password);
        if(i == 1)
            return true;
        return false;
    }

    // 更新用户信息
    public int updateUserinfo(String username, String nickname, String usersex, String birthday){
        return manageMapper.updateUserinfo(username, nickname, usersex, birthday);
    }
    // 更新用户密码
    public int updateAccount(String username, String password){
        return manageMapper.updatePassword(username, password);
    }
    // 删除用户
    public int deleteUser(String username){
        System.err.println(username);
        int i1 = manageMapper.deleteAccount(username);
        int i2 = manageMapper.deletePerson(username);
        System.err.println(i1 + "....." + i2);
        return (i1 == i2) ? i1 : 0;
    }
    // 用户计数
    public int userCount(){
        return manageMapper.userCount();
    }
    // 用户筛选计数
    public int getUserCount(String username, String nickname, String usersex, String starttime, String endtime){
        return manageMapper.getUserCount(username, nickname, usersex, starttime, endtime);
    }

    // 获取用户列表
    public List<UserInformation> getFilterUsers(String username, String nickname, String usersex, String starttime, String endtime, int curpage, int pagesize){
        List<UserInformation> list = new ArrayList<>();
        list = manageMapper.getFilterUsers(username, nickname, usersex, starttime, endtime, curpage, pagesize);

        return list;
    };


    // 动态操作

    public List<Moment> getFiltedMoments(String username, String nickname, String starttime, String endtime, String catname, String locatname, int curpage, int pagesize){
        return manageMapper.getFiltedMoments(username, nickname, starttime, endtime, catname, locatname, curpage, pagesize);
    }
    // 获取动态计数
    public int getFiltedMomentsCount(String username, String nickname, String starttime, String endtime, String catname, String locatname){
        return manageMapper.getFiltedMomentsCount(username, nickname, starttime, endtime, catname, locatname);
    }
    // 动态修改
    public int updateMoment(String momentid, String moment, String time) {
        return manageMapper.updateMoment(momentid, moment, time);
    }
    // 动态删除
    public int deleteMomentByMomentid(String momentid){
        return manageMapper.deleteMomentByMomentid(momentid);
    }
    public int deleteCommentByMomentid(String momentid){
        return manageMapper.deleteCommentByMomentid(momentid);
    }
    // ----------------------------------------
    // 评论管理
    public List<Comment> getFiltedComment(String username, String starttime, String endtime, String comment, int curpage, int pagesize){
        return manageMapper.getFiltedComment(username, starttime, endtime, comment, curpage, pagesize);
    }
    public int getFiltedCommentCount(String username, String starttime, String endtime, String comment) {
        return manageMapper.getFiltedCommentCount(username, starttime, endtime, comment);
    }
    // 评论修改
    public int updateComment(String commentid, String comment, String time){
        return manageMapper.updateComment(commentid, comment, time);
    }
    public int deleteComment(String commentid){
        return manageMapper.deleteComment(commentid);
    }

    // 猫咪管理
    public List<Cat> getFiltedCats(String catid, String catname, String starttime, String endtime, String remark, int curpage, int pagesize){
        return manageMapper.getFiltedCats(catid, catname, starttime, endtime, remark, curpage, pagesize);
    }
    public int getFiltedCatsCount(String catid, String catname, String starttime, String endtime, String remark){
        return manageMapper.getFiltedCatsCount(catid, catname, starttime, endtime, remark);
    }
    public int updateCat(String catid, String catname, String time, String remark){
        return manageMapper.updateCat(catid, catname, time, remark);
    }
    public int deleteCat(String catid){
        return manageMapper.deleteCat(catid);
    }




    // 位置管理
    public List<ListItem> getLocations(String locatname, int curpage, int pagesize){
        return manageMapper.getLocations(locatname, curpage, pagesize);
    }


    public int locationCount(){
        return manageMapper.locationCount();
    }
    public int updateLocation(String locatid, String locatname){
        return manageMapper.updateLocation(locatid, locatname);
    }
    public int insertLocation(String locatname){
        return manageMapper.insertLocation(locatname);
    }
    public int deleteLocation(String locatid){
        return manageMapper.deleteLocation(locatid);
    }



    // 反馈管理
    public List<Report> getReports(String reportid, String starttime, String endtime, String username, String suggestion, String contact, int curpage, int pagesize){
        return manageMapper.getReports(reportid, starttime, endtime, username, suggestion, contact, curpage, pagesize);
    }
    public int getReportsCount(String reportid, String starttime, String endtime, String username, String suggestion, String contact){
        return manageMapper.getReportsCount(reportid, starttime, endtime, username, suggestion, contact);
    }
    public int deleteReport(String reportid){
        return manageMapper.deleteReport(reportid);
    }

}
