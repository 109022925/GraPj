package com.cc.service.Impl.mail;

import com.cc.service.IMailService;
import com.cc.utils.SecurityCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private MsmServiceImpl msmService;

    @Override
    public boolean send(String email, HttpSession session) {
        String code = SecurityCodeUtil.getSecurityCode(6, SecurityCodeUtil.SecurityCodeLevel.Hard, true);

        if (msmService.send(email, "ccblog验证码", code)) {
            session.setAttribute("code", code);
            return true;
        }

        return false;
    }

    @Override
    public boolean verify(String code, HttpSession session) {
        if (code.equals(session.getAttribute("code"))) {
            return true;
        }
        return false;
    }
}
