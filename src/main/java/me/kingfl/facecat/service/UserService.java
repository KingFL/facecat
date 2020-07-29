package me.kingfl.facecat.service;


import me.kingfl.facecat.mapper.UserMapper;
import me.kingfl.facecat.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private transient UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    // 获取用户信息
    public UserInformation getUserInformation(String username){
        UserInformation user = userMapper.getUserInformation(username);
        System.err.println(username);
        System.err.println(user);
        return user;
    }

    // 注册账户 分两个方法
     public int registerAccount(String username, String password){
            return userMapper.registerAccount(username, password);
        }
    public int registerUser(String username, String usersex, String birthday){
        return userMapper.registerUser(username, usersex, birthday);
    }


    public int userLogin(String username, String password){
        String truthpwd = userMapper.getPassword(username);
        System.err.println("输入的密码为：" + password);
        System.err.println("库中的密码为：" + truthpwd);
        if(password.equals(truthpwd))
            return 1;
        else
            return 2;

    }

    public int checkUsername(String username){
        return userMapper.checkUsername(username);
    }

    public int updateUserInfo(String username, String nickname, String usersex, String birthday, String avatar){
        System.err.println(username + " " + nickname + " " + avatar);
        userMapper.updateCommentsAvatar(nickname, avatar);
        userMapper.updateMomentsAvatar(nickname, avatar);
        return userMapper.updateUserInfo(username, nickname, usersex, birthday, avatar);
    }

    // 发送反馈
    public int sendReport(String username, String suggestion, String contact){
        return userMapper.sendReport(Tools.getNow(1), username, suggestion, contact); // reportid使用当前时间填充
    }




}
