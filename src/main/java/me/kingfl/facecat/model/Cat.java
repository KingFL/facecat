package me.kingfl.facecat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Cat {
    private String catid; //猫咪的id
    private String catname; //猫咪的名字
    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    private String foundtime; //发现时间
    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    private String nametime; //起名时间
    private String stat; //状态（备用
    private String remark; //猫咪的描述文字
    private String imagepath; //猫咪的示例图片路径
    private int count; //总动态数量

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
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

    public String getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(String foundtime) {
        this.foundtime = foundtime;
    }

    public String getNametime() {
        return nametime;
    }

    public void setNametime(String nametime) {
        this.nametime = nametime;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
