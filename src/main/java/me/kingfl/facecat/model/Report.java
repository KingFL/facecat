package me.kingfl.facecat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Report {

    @DateTimeFormat(pattern="yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String username;
    private String suggestion;
    private String contact;
    private String reportid;

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
