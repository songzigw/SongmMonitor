package cn.songm.monitor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        JsonUtilsInit.initialization();
    }

}
