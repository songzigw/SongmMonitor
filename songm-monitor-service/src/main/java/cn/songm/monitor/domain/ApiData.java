package cn.songm.monitor.domain;

import cn.songm.common.beans.EntityAdapter;
import cn.songm.common.utils.StringUtils;

public class ApiData extends EntityAdapter {

    private static final long serialVersionUID = 6824626088551248736L;

    /** 请求URI */
    private String uri;
    /** 客户端IP */
    private String cliIp;
    /** 客户端信息(类型、版本号等信息) */
    private String cliInfo;
    /** 处理服务器的IP */
    private String serIp;
    /** 处理的开始时间 */
    private Long begTime;
    /** 处理结束时间 */
    private Long endTime;
    /** 请求是否有异常错误 */
    private Boolean errFlag;
    /** SessionID 描述客户端的唯一标识 */
    private String sesId;
    /** User ID */
    private String userId;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getBegTime() {
        return begTime;
    }

    public void setBegTime(Long begTime) {
        this.begTime = begTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getSesId() {
        return sesId;
    }

    public void setSesId(String sesId) {
        this.sesId = sesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCliIp() {
        return cliIp;
    }

    public void setCliIp(String cliIp) {
        this.cliIp = cliIp;
    }

    public String getCliInfo() {
        return cliInfo;
    }

    public void setCliInfo(String cliInfo) {
        this.cliInfo = cliInfo;
    }

    public String getSerIp() {
        return serIp;
    }

    public void setSerIp(String serIp) {
        this.serIp = serIp;
    }

    public Boolean getErrFlag() {
        return errFlag;
    }

    public void setErrFlag(Boolean errFlag) {
        this.errFlag = errFlag;
    }

    @Override
    public String toString() {
        return StringUtils.toString(this);
    }
}
