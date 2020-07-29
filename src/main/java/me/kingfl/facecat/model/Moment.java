package me.kingfl.facecat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Moment {
    private String momentid;
    private String username;
    private String nickname;
    private String title;
    private String locatname;
    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String timest;
    private String imagepath;
    private String avatar;
    private int like;
    private int count;
    private String catid;
    private String catname;

    public String getMomentid() {
        return momentid;
    }

    public void setMomentid(String momentid) {
        this.momentid = momentid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimest() {
        return timest;
    }

    public void setTimest(String timest) {
        this.timest = timest;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getLocatname() {
        return locatname;
    }

    public void setLocatname(String locatname) {
        this.locatname = locatname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void printString(){
        String result = "";
        result += "momentid = " + momentid
                + "\nusername = " + username
                + "\ntitle = " + title
                + "\nlocatname = " + locatname
                + "\ncontent = " + content
                + "\ntime = " + time
                + "\nimagepath = " + imagepath
                + "\navatar = " + avatar
                + "\n like = " + like + " count = " + count;
        System.err.println(result + "\n");
    }
}
