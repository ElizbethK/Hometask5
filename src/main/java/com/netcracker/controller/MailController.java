package com.netcracker.controller;

import com.netcracker.model.MailObject;
import com.netcracker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*@RestController
public class MailController {


    @Autowired
    public EmailService emailService;

    @GetMapping(value = "/sendmail/{toemail}")
    public String mailSender(@PathVariable(name="toemail", required = true) String toemail, Model model) {
        model.addAttribute("mailObject", new MailObject());
        return "sendmail";
    }


    @PostMapping("/sendmail")
    public String mailSuccess(@ModelAttribute MailObject mailObject){
        emailService.sendSimpleMessage(mailObject.getTo(), mailObject.getSubject(), mailObject.getText());
    return "mailsuccess";
    }

}*/

@Controller
public class MailController {

    @Autowired
    public EmailService emailService;

    @GetMapping(value = "/sendmail/{toemail}")
    public String mailSender(@PathVariable(name="toemail", required = true) String toemail, Model model) {
        model.addAttribute("mailObject", new MailObject());
        return "sendmail";
    }
   @PostMapping("/sendmail")
    public String mailSenderResult(@ModelAttribute MailObject mailObject) {

        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(),
                mailObject.getText());

        return "mailsuccess";
    }
}






