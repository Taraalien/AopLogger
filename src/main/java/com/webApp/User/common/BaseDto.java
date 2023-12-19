package com.webApp.User.common;

import java.util.Date;

public class BaseDto {


    private Integer version;

    private String createBye;

    private String lastModify;

    private Date lastModifyDate;

    private Date createdDate;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreateBye() {
        return createBye;
    }

    public void setCreateBye(String createBye) {
        this.createBye = createBye;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
