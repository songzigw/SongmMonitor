package cn.songm.monitor.service;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.songm.monitor.domain.ReqInfo;

/**
 * 请求消息的处理
 * @author zhangsong
 *
 */
@Component("reqInfoHandler")
public class ReqInfoHandler  extends DefaultMessageListenerContainer implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReqInfoHandler(ConnectionFactory connectionFactory,
            @Value("${jms.queue.req}") String queueReq) {
        super.setConnectionFactory(connectionFactory);
        super.setDestination(new ActiveMQQueue(queueReq));
        super.setMessageListener(this);
        System.out.println("======="+queueReq);
    }
    
    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        try {
            String ms = msg.getText();
            log.info("Receive ReqInfo: {}", ms);
            JsonObject jObj = new JsonParser().parse(ms).getAsJsonObject();
            ReqInfo req = new ReqInfo();
            req.setClientIp(jObj.get("clientIp").getAsString());
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }

}

