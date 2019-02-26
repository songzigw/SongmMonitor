package cn.songm.monitor.api;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import cn.songm.monitor.domain.ApiData;

public class Monitor {

    public static final String MONITOR_API_DATA = "songm-monitor-apidata";
    public static final String QUEUE_API_DATA = "songm.monitor.apidata";
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ObjContainer objContainer;
    private JmsTemplate jmsTemplate;
    
    public Monitor() {
        this.objContainer = new ObjContainer();
        this.jmsTemplate = objContainer.getJmsTemplate();
    }
    
    public void onRequest(HttpServletRequest req, HttpServletResponse res, String sesId, String userId) {
        ApiData ad = new ApiData();
        ad.init();
        ad.setUri(req.getRequestURI());
        ad.setCliIp(req.getRemoteAddr()+":"+req.getRemotePort());
        ad.setSerIp(req.getLocalAddr()+":"+req.getLocalPort());
        if (ad.getBegTime() != null) {
            ad.setBegTime(System.currentTimeMillis());
        }
        ad.setSesId(sesId);
        ad.setUserId(userId);
        req.setAttribute(MONITOR_API_DATA, ad);
        log.info("请求事件, {}", ad.toString());
    }
    
    public void onResponse(HttpServletRequest req, HttpServletResponse res) {
        ApiData ad = (ApiData)req.getAttribute(MONITOR_API_DATA);
        ad.setEndTime(System.currentTimeMillis());
        ad.setErrFlag(false);
        String msg = JsonUtil.toJson(ad, ApiData.class);
        log.info("响应事件, {}", ad.toString());
        
        // 发送API响应相关消息
        jmsTemplate.send(QUEUE_API_DATA, new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
    
    public void onApiError(HttpServletRequest req, Exception e) {
        ApiData ad = (ApiData)req.getAttribute(MONITOR_API_DATA);
        ad.setEndTime(System.currentTimeMillis());
        ad.setErrFlag(true);
        String msg = JsonUtil.toJson(ad, ApiData.class);
        log.info("API异常事件, {}", ad.toString());
        
        // 发送API异常消息
        jmsTemplate.send(QUEUE_API_DATA, new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
    
}
