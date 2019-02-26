package cn.songm.monitor.api;

import org.junit.Assert;
import org.junit.Test;

public class ConfiguratorTest {

    @Test
    public void testGet() {
        Assert.assertEquals(Configurator.getBrokerUrl(), "tcp://192.168.3.151:61616");
        Assert.assertEquals(Configurator.getBrokerUserName(), "admin");
        Assert.assertEquals(Configurator.getBrokerPassword(), "admin");
        Assert.assertEquals(Configurator.getConnectionMax(), 10);
        Assert.assertEquals(Configurator.getQueueApidata(), "songm.monitor.apidata");
        Assert.assertEquals(Configurator.getCacheSize(), 3);
    }
    
}
