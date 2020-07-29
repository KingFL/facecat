package me.kingfl.facecat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserInformation {
    private String username;
    private String nickname;
    private String usersex;
    @DateTimeFormat(pattern="yyyy.MM.dd")
    @JsonFormat(pattern="yyyy.MM.dd", timezone = "GMT+8")
    private String birthday;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
