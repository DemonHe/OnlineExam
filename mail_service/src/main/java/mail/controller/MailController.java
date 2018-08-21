package mail.controller;

import mail.service.MailManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mail")
@RestController
public class MailController {
    @Autowired
    private MailManagerService mailManagerService;

    @RequestMapping("/sendEmail")
    public int sendEmail(@RequestParam("tid") String tid ,@RequestParam("sid") String sid){     //返回 1:发送成功, 0:发送失败
        int result = mailManagerService.sendCode(sid,tid);
//        if(result == (-1)){
//            return "发送邮件出错";
//        }else{
//            return "邮件发送成功";
//        }
        return result;
    }

    @RequestMapping("/verify")
    public int verify(@RequestParam("tid") String tid,@RequestParam("code") String code){   //返回 1:验证通过, 0:验证失败
    	System.out.println(tid);
    	int res = mailManagerService.verifyCode(tid,code);
        
        return res;
    }

    @RequestMapping("/score")
    public int sendScore(@RequestParam("sid") String sid,@RequestParam("tid") String tid,@RequestParam("score") String score){
        System.out.println("@!@"+sid+tid);
        int res = mailManagerService.sendScore(sid, tid, score);
        return res;
    }

}
