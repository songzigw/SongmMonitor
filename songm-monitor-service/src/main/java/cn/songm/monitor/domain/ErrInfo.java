package cn.songm.monitor.domain;

import java.util.Date;

import cn.songm.common.beans.EntityAdapter;

/**
 * 错误消息
 * @author zhangsong
 *
 */
public class ErrInfo extends EntityAdapter {

    private static final long serialVersionUID = -3903085495197748293L;

    /** 处理结束时间 */
    private Date endTime;
    /** Session ID */
    private String sessionId;
    /** User ID */
    private String userId;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
