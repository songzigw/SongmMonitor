package cn.songm.monitor.domain;

import java.util.Date;

import cn.songm.common.beans.EntityAdapter;

/**
 * 请求消息
 * 
 * @author zhangsong
 *
 */
public class ReqInfo extends EntityAdapter {

    private static final long serialVersionUID = -8204457053509020093L;

    /** 客户端IP */
    private String clientIp;
    /** 请求URL */
    private String url;
    /** 处理的服务器 */
    private String server;
    /** 处理的开始时间 */
    private Date begTime;
    /** Session ID */
    private String sessionId;
    /** User ID */
    private String userId;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Date getBegTime() {
        return begTime;
    }

    public void setBegTime(Date begTime) {
        this.begTime = begTime;
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
