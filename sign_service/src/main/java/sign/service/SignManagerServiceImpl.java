package sign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sign.dao.AdminDao;
import sign.dao.StudentDao;
import sign.model.Admin;
import sign.model.Student;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by sam.
 */

@Service
public class SignManagerServiceImpl implements SignManagerService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AdminDao adminDao;

    private static boolean isEmail(String str){
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //登录
    @Override
    public boolean IfExist(String account, String type) {
        if(type.equals("Student")){
            String id = "";
            if(isEmail(account)){
                id = studentDao.findbyEmail(account);
            }else {
                id = studentDao.findbySid(account);
            }
            //String id = studentDao.findbyEmail(account);
            //String id2 = studentDao.findbySid(account);
            if(id == null){
                return false;
            }else{
                return true;
            }
        }else{
            String id = adminDao.findbyEmail(account);
            if(id == null){
                return false;
            }else{
                return true;
            }
        }

    }

    @Override
    public boolean CheckPassword(String account, String password, String type) {
        boolean Ifexist = this.IfExist(account,type);
        if(!Ifexist){
            return false;
        }

        String pass = "";
        String state = "";
        if(type.equals("Student")){
            if(isEmail(account)){
                pass = studentDao.findbyEmail(account);
                state = studentDao.checkStateUseEmail(account);
            }else{
                pass = studentDao.findbySid(account);
                state = studentDao.checkStateUseSid(account);
            }
            //String pass1 = studentDao.findbyEmail(account);
            //String pass2 = studentDao.findbySid(account);

        }else{
            pass = adminDao.findbyEmail(account);
            state = adminDao.checkState(account);
        }

        if( (pass.equals(password) && (state.equals("on")))){  //登录成功
            return true;
        }else{
            return false;
        }

    }

    @Override
    public String getSid(String account){
        if(isEmail(account)){
            String sid = studentDao.findSidByEmail(account);
            return sid;
        }else{
            return account;
        }
    }

    @Override
    public String getName(String account){
        if(isEmail(account)){
            String name = studentDao.findNameByEmail(account);
            return name;
        }else{
            String name = studentDao.findNameBySid(account);
            return name;
        }
    }

    @Override
    public Student GetUser(String account) {
        return studentDao.getOne(account);
    }

    @Override
    public Admin GetAdmin(String account) {
        return adminDao.getOne(account);
    }


    //注册
    public static boolean sendMail(String to, String code) {
        System.out.println("Start send the email!!");

        try {
            Properties props = new Properties();
            props.setProperty("username", "18362923886@163.com");
            props.setProperty("password", "r4anwe5we5123456");
            props.setProperty("mail.transport.protocol", "smtp" );
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.auth", "true");
            //props.put("mail.smtp.port", "465" );

            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(true);

            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("18362923886@163.com", "online-Examination", "UTF-8"));
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("激活邮件");
            msg.setContent("<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1><h3><a href='http://localhost:10009/register_active?code="+code+"'>http://localhost:10001/account/activeService</a></h3>","text/html;charset=UTF-8");
            msg.setSentDate(new Date());
            msg.saveChanges();

            Transport transport = mailSession.getTransport("smtp");
            System.out.println("connecting!!!!");
            transport.connect(props.getProperty("mail.smtp.host"), props
                    .getProperty("username"), props.getProperty("password"));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
        return true;
    }


    @Override
    public int StudentReg(String sid, String password, String email, String name, String grade) {
        String name1 = studentDao.checkSid(sid);
        String name2 = studentDao.checkEmail(email);

        //Map<String,String> maps = new HashMap<String,String>();

        if(name1 != null){
//            maps.put("result","error");
//            maps.put("message","学号已经被注册");
//            return maps;
            return 1;   //学号已经被注册
        }

        if(name2 != null){
//            maps.put("result","error");
//            maps.put("message","邮箱已经被注册");
//            return maps;
            return 2;   //邮箱已经被注册
        }

        //生成用户code
        String code= UUID.randomUUID().toString().replace("-", "");

        Student student = new Student(sid,name,email,grade,password);
        student.setCode(code);
        student.setState("off");
        studentDao.save(student);

        //向用户发送激活邮件
        sendMail(email,code);

//        maps.put("result","success");

        return 0;   //success
    }

//    @Override
//    public boolean AdminReg(String password, String email,String name) {
//        boolean result = false;
//        //生成用户code
//        String code= UUID.randomUUID().toString().replace("-", "");
//
//        Admin admin = new Admin(email,password,name);
//
//        admin.setCode(code);
//        admin.setState("off");
//        adminDao.save(admin);
//
//        //向用户发送激活邮件
//        sendMail(email,code);
//        return false;
//    }

    @Override
    public Boolean Active(String code){
        String id = studentDao.findbyCode(code);
        if(id != null) {
            studentDao.updateState(id, "on");
            return true;
        }else{
            return false;
        }
//        }else{
//            id = adminDao.findbyCode(code);
//            adminDao.updateState(id,"on");
//        }

//        return true;
    }



}
