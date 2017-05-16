package com.royalrangers.service;

import com.royalrangers.model.User;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfiguration;

    public void sendEmail(User user, String subj, String template, String message) {

        MimeMessagePreparator preparator = getMessagePreparator(user, subj, template, message);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            log.error("MailException", e.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(User user, String subj, String template, String message) {

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setSubject(subj);
            helper.setTo(user.getEmail());

            Map<String, Object> model = new HashMap<>();
            model.put("email", user.getEmail());
            model.put("message", message);

            String text = getFreeMarkerTemplateContent(model, template);
            helper.setText(text, true);
        };
    }

    public String getFreeMarkerTemplateContent(Map<String, Object> model, String template) {

        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfiguration.getTemplate(template), model));
            return content.toString();
        } catch (Exception e) {
            log.error("Exception occurred while processing Email template:", e.getMessage());
        }

        return "";
    }
}
