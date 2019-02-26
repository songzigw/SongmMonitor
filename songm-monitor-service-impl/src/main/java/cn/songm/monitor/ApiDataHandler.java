package cn.songm.monitor;

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

import cn.songm.common.utils.JsonUtils;
import cn.songm.monitor.domain.ApiData;

/**
 * 请求消息的处理
 * @author zhangsong
 *
 */
@Component("apiDataHandler")
public class ApiDataHandler  extends DefaultMessageListenerContainer implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ApiDataHandler(ConnectionFactory connectionFactory,
            @Value("${jms.queue.apidata}") String queueApiData) {
        super.setConnectionFactory(connectionFactory);
        super.setDestination(new ActiveMQQueue(queueApiData));
        super.setMessageListener(this);
    }
    
    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        try {
            String ms = msg.getText();
            ApiData apidata = JsonUtils.getInstance().fromJson(ms, ApiData.class);
            log.info(apidata.toString());
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }

}

