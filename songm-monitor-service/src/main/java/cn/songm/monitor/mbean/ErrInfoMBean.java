package cn.songm.monitor.mbean;

import java.util.Date;

public interface ErrInfoMBean {

    public Date getEndTime();

    public void setEndTime(Date endTime);

    public String getSessionId();

    public void setSessionId(String sessionId);
    
    public String getUserId();

    public void setUserId(String userId);
    
}
