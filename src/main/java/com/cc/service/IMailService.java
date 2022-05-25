package com.cc.service;

import javax.servlet.http.HttpSession;

public interface IMailService {
    boolean send(String email, HttpSession session);

    boolean verify(String code, HttpSession session);
}
