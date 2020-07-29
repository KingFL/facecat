package me.kingfl.facecat.service;

import me.kingfl.facecat.mapper.ToolMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class Tools {

    private transient ToolMapper toolMapper;

    public Tools(ToolMapper toolMapper){
        this.toolMapper = toolMapper;
    }

    // 获取用户头像
    public String getAvatar(String username){
        return toolMapper.getUserAvatar(username);
    }
    // 获取用户昵称
    public String getNickname(String username){
        return toolMapper.getUserNickname(username);
    }

    // 获取当前时间
    public static String getNow(int format){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式;;
        if(format == 1){
            df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式;
        }
        if(format == 2)
            df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = df.format(new Date());
        //System.out.println("获取到当前时间为：" + time);// new Date()为获取当前系统时间
        return time;
    }

    public static String getLikeString(String str){
        if(str == null)
            return "%%";
        else
            return "%" + str + "%";
    }
    public static String getStartTime(){
        return "1970-01-02 00:00:00";
    }

    // 获取当前日期
    public static String getToday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        //System.out.println("获取到时间为：" + date);// new Date()为获取当前系统时间
        return date;
    }

    // 截取文件扩展名
    public static String getFileExtentName(String filename){
        String[] fileNameSplit = filename.split("\\.");
        String extend = fileNameSplit[fileNameSplit.length - 1];
        System.err.println(extend);
        return extend;
    }


    // 获取六天前的零点字符串
    public static String getLastWeek(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, - 6);
        Date d = c.getTime();
        String day = format.format(d);
        day += " 00:00:00";
        //System.out.println("过去七天："+day);
        return day;
    }

    // 获取29天前零点字符串
    public static String getLastMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, - 29);
        Date d = c.getTime();
        String day = format.format(d);
        day += " 00:00:00";
        //System.out.println("过去三十天："+day);
        return day;
    }
}
