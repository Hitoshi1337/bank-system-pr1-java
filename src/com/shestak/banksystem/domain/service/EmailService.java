package com.shestak.banksystem.domain.service;

public class EmailService {
    // Заглушка: вместо реальной почты выводим в консоль
    public void send(String to, String subject, String text) {
        System.out.println("---- EMAIL ----");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println(text);
        System.out.println("--------------");
    }
}
