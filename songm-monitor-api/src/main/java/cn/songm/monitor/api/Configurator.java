package cn.songm.monitor.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {

    private static final String CONFIG_FILE = "monitor.properties";
    private static Properties props;
    
    static {
        props = new Properties();
        InputStream input = Configurator.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE);
        try {
            props.load(input);
            input.close();
        } catch (IOException e) {
            
        }
    }
    
    public static String getBrokerUrl() {
        return props.getProperty("monitor.jms.broker.url");
    }
    
    public static String getBrokerUserName() {
        return props.getProperty("monitor.jms.broker.userName");
    }
    
    public static String getBrokerPassword() {
        return props.getProperty("monitor.jms.broker.password");
    }
    
    public static int getCacheSize() {
        return Integer.parseInt(props.getProperty("monitor.jms.cache.size"));
    }
    
    public static int getConnectionMax() {
        return Integer.parseInt(props.getProperty("monitor.jms.connection.max"));
    }
    
    public static String getQueueApidata() {
        return props.getProperty("monitor.jms.queue.apidata");
    }
    
}
