package com.example.asus.wechat.homeActivity.model.bean;


/**
 * Created by ASUS on 2016/11/10.
 */

public class SendBean {
    private String content;
    private String time;
    private String name;
    private String id;
    private int face;
    private int type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SendBean(String content, String time, String name, String id, int face, int type) {

        this.content = content;
        this.time = time;
        this.name = name;
        this.id = id;
        this.face = face;
        this.type = type;
    }

    @Override
    public String toString() {
        return "SendBean{" +
                "content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", face=" + face +
                ", type=" + type +
                '}';
    }
}
