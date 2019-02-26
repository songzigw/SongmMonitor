package cn.songm.monitor;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class MailServiceImpl {

    //@Autowired
    private JavaMailSender sender;
    //@Autowired
    //private ;
    //@Autowired
    private ThreadPoolTaskExecutor pool;
    
    {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.163.com");
        sender.setUsername("");
        sender.setPassword("");
        sender.setDefaultEncoding("UTF-8");
        Properties pros = new Properties();
        pros.setProperty("mail.stmp.auth", "true");
        pros.setProperty("mail.stmp.timeout", "30000");
        sender.setJavaMailProperties(pros);
        
        pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setKeepAliveSeconds(60);
        pool.setMaxPoolSize(32);
        pool.setQueueCapacity(128);
    }
    
    public void sendMail(final MailContent mail) {
        this.pool.execute(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(mail.getFrom());
                message.setTo(mail.getTo());
                message.setSubject(mail.getSubject());
                message.setText(mail.getContent());
                sender.send(message);
            }
        });
    }
}
