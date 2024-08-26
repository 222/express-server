package com.express.server.common;

/**
 * @author youping.tan
 * @date 2024/8/6 10:18
 */
public abstract class BaseModel {
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新时间
     */
    private String updatedTime;
    /**
     * 创建者
     */
    private String createdBy;
    /**
     * 更新者
     */
    private String updatedBy;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
