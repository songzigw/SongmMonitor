package cn.songm.monitor.api;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

public class ObjContainer {

    private ActiveMQConnectionFactory amqConnectionFactory;
    private PooledConnectionFactory pooledConnectionFactory;
    //private SingleConnectionFactory connectionFactory;
    private CachingConnectionFactory connectionFactory;
    private JmsTemplate jmsTemplate;
    
    {
        amqConnectionFactory = new ActiveMQConnectionFactory();
        amqConnectionFactory.setBrokerURL(Configurator.getBrokerUrl());
        amqConnectionFactory.setUserName(Configurator.getBrokerUserName());
        amqConnectionFactory.setPassword(Configurator.getBrokerPassword());
        
        pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(amqConnectionFactory);
        pooledConnectionFactory.setMaxConnections(Configurator.getConnectionMax());
        
        connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(pooledConnectionFactory);
        connectionFactory.setSessionCacheSize(Configurator.getCacheSize());
    
        jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
    }

    public ActiveMQConnectionFactory getAmqConnectionFactory() {
        return amqConnectionFactory;
    }

    public PooledConnectionFactory getPooledConnectionFactory() {
        return pooledConnectionFactory;
    }

    public CachingConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
}
