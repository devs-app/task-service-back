package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.BaseEmail;
import com.task.sie.app.tasksie.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class SendEmailServiceImp implements SendEmailService {

    private final Logger log = LoggerFactory.getLogger(SendEmailServiceImp.class);

    @Autowired
    Configuration fmConfiguration;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sentEmailActivateUser(BaseEmail baseEmail) {
        try {
            MimeMessage mimeMessage =javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(baseEmail.getSubject());
            mimeMessageHelper.setTo(baseEmail.getTo());
            mimeMessageHelper.setText(geContentFromTemplate(baseEmail.getParams(), baseEmail.getTemplate()), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            log.error(e.getCause().toString());
            e.printStackTrace();
        }
    }

    private String geContentFromTemplate(Map< String, Object > model, String template)     {
        StringBuilder content = new StringBuilder();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
