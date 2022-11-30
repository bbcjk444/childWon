package com.example.project;

public class VO {
    String id;
    String title;
    String content;
    String time;

    public VO(String id1, String title1, String content1, String time1){
        id = id1;
        title = title1;
        content = content1;
        time = time1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
