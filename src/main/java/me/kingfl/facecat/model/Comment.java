package me.kingfl.facecat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Comment {
    private String momentid; //帖子id
    private String commentid; //评论id
    private String username; //评论用户名
    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    private String commenttime; //评论时间
    private String comment; //评论文本
    private String avatar; //头像地址

    public String getMomentid() {
        return momentid;
    }

    public void setMomentid(String momentid) {
        this.momentid = momentid;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
