package com.example.demo.entity;

public class Log {
    private String old_id;
    private String new_id;
    private String delete_flag;


    public String getNew_id() {
        return new_id;
    }

    public void setNew_id(String new_id) {
        this.new_id = new_id;
    }

    public String getOld_id() {
        return old_id;
    }

    public void setOld_id(String old_id) {
        this.old_id = old_id;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Log(String old_id, String new_id) {
        this.old_id = old_id;
        this.new_id = new_id;
    }
}
