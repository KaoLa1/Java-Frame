package com.example.demo.entity;

public class Source {
    private String s_id;
    private String dir_id;
    private String new_oid;
    private String old_oid;
    private String create_time;
    private String update_time;
    private String old_update_time;
    private String check_source;

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getDir_id() {
        return dir_id;
    }

    public void setDir_id(String dir_id) {
        this.dir_id = dir_id;
    }

    public String getNew_oid() {
        return new_oid;
    }

    public void setNew_oid(String new_oid) {
        this.new_oid = new_oid;
    }

    public String getOld_oid() {
        return old_oid;
    }

    public void setOld_oid(String old_oid) {
        this.old_oid = old_oid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOld_update_time() {
        return old_update_time;
    }

    public void setOld_update_time(String old_update_time) {
        this.old_update_time = old_update_time;
    }

    public String getCheck_source() {
        return check_source;
    }

    public void setCheck_source(String check_source) {
        this.check_source = check_source;
    }

    @Override
    public String toString() {
        return "Source{" +
                "old_oid='" + old_oid + '\'' +
                ", old_update_time='" + old_update_time + '\'' +
                '}';
    }
}
