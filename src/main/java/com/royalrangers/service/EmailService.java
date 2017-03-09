package com.royalrangers.service;

import com.royalrangers.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Configuration freeMarkerConfiguration;

    public void sendEmail(UserBean userBean) {

        MimeMessagePreparator preparator = getMessagePreparator(userBean);
        try {
            mailSender.send(preparator);
        }
        catch (MailException e) {
            System.err.println(e.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(UserBean userBean){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setSubject("Please, comfirm your registration:");
                helper.setFrom(" no-reply@royalrangers.com");
                helper.setTo(userBean.getEmail());

                Map<String, Object> model = new HashMap<String, Object>();

                String text = getFreeMarkerTemplateContent(model);

                helper.setText(text, true);
            }
        };
        return preparator;
    }

    public String getFreeMarkerTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfiguration.getTemplate("EmailTemplate.txt"),model));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing Emailtemplate:"+e.getMessage());
        }
        return "";
    }

}
