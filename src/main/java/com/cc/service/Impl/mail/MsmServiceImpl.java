package com.cc.service.Impl.mail;

import com.cc.service.IMsmService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MsmServiceImpl implements IMsmService {
    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean send(String email, String title, String code) {

        if (StringUtils.isEmpty(email)) return false;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSubject(title);
        message.setText(code);
        mailSender.send(message);
        logger.info("邮件发送成功");
        return true;
    }
}
