package cn.songm.monitor.service;

import cn.songm.common.service.AppBoot;

/**
 * 监控消息接收器
 * 
 * @author zhangsong
 *
 */
public class AppAcceptor {

    public static void main(String[] args) {
        AppBoot.start("app-monitor.xml", args);
    }
}
