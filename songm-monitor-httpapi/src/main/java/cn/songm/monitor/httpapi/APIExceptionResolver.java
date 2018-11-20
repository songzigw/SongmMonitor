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
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * API异常分解器
 * @author zhangsong
 *
 */
public abstract class APIExceptionResolver implements HandlerExceptionResolver {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private JmsTemplate jmsTemplate;
    
    public APIExceptionResolver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    @Override
    public ModelAndView resolveException(HttpServletRequest req,
            HttpServletResponse res, Object obj, Exception e) {
        // 获取SessionID、UserID
        req.getAttribute(APIInterceptor.MONITOR_SESSIONID);
        req.getAttribute(APIInterceptor.MONITOR_USERID);
        
        // 发送API异常消息
        jmsTemplate.send(APIInterceptor.QUEUE_ERR, new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage("异常信息");
            }
        });
        
        log.error("请求相应异常", e);
        return new ModelAndView();
    }

}
