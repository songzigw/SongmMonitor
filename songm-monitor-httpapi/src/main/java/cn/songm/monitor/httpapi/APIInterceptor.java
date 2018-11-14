package cn.songm.monitor.httpapi;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class APIInterceptor implements HandlerInterceptor {

    public static final String MONITOR_SESSIONID = "monitor_sessionid";
    public static final String MONITOR_USERID = "monitor_userid";
    
    public static final String QUEUE_REQ = "songm.monitor.req";
    public static final String QUEUE_RES = "songm.monitor.res";
    public static final String QUEUE_ERR = "songm.monitor.err";
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private JmsTemplate jmsTemplate;
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    /**
     * 页面渲染完成，执行这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res,
            Object handler, Exception e) throws Exception {
        
    }

    /**
     * Controller方法运行完成之后，执行这个方法
     */
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 获取SessionID、UserID
        req.getAttribute(MONITOR_SESSIONID);
        req.getAttribute(MONITOR_USERID);
        
        // 发送API相应相关消息
        jmsTemplate.send(QUEUE_RES, new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage("相应信息");
            }
        });
        log.info("相应(Response)相关信息");
    }

    /**
     * 进入Controller方法之前，执行这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
            Object handler) throws Exception {
        // 获取URI、当前服务器的IP
        req.getRequestURI();
        req.getServerName();
        req.getRemoteAddr();
        
        // 发送API请求相关消息
        jmsTemplate.send(QUEUE_REQ, new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage("请求信息");
            }
        });
        log.info("请求(Request)相关信息");
        return true;
    }

}