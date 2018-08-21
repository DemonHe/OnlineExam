package mail.service;
import mail.dao.CourseDao;
import mail.dao.StudentMailDao;
import mail.dao.TestCodeDao;
import mail.dao.TestDao;
import mail.model.TestCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

@Service
public class MailManagerServiceImpl implements MailManagerService{
    @Autowired
    private TestCodeDao testCodeDao;

    @Autowired
    private StudentMailDao studentMailDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TestDao testDao;


    //发件人的邮箱密码
    private static String EmailAddress = "18362923886@163.com";
    private static String password = "r4anwe5we5123456";

    //发件人邮箱地址(163)
    private static String EmailHost = "smtp.163.com";


    @Override
    public int sendMail(String target, String titile,String content) {

        // 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", EmailHost);         // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

//        //smtp的端口(SSL)
//        final String smtpPort = "465";
//        props.setProperty("mail.smtp.port", smtpPort);
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);

        try{
            // 创建一封邮件
            MimeMessage message = createMessage(session, titile, content , target);
            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            transport.connect(EmailAddress,password);
            transport.sendMessage(message, message.getAllRecipients());

            // 关闭连接
            transport.close();
        }catch(Exception e){
            return 0;
        }

        return 1;
    }

    private MimeMessage createMessage(Session session, String title, String content, String receiveEmailAddress) throws Exception{
        // 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // From: 发件人
        message.setFrom(new InternetAddress(EmailAddress, "Online-Examination", "UTF-8"));

        // To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveEmailAddress, "", "UTF-8"));

        // Subject: 邮件主题
        message.setSubject(title, "UTF-8");

        // Content: 邮件正文（可以使用html标签)
        message.setContent(content, "text/html;charset=UTF-8");

        // 保存设置
        message.saveChanges();

        return message;
    }


    @Override
    public int sendCode(String sid, String tid) {
        String email = studentMailDao.getEmailBySid(sid);

        String code = testCodeDao.findCodeByTid(tid);
        if(code == null){
            //无该tid
            //生成用户code
            code= UUID.randomUUID().toString().replace("-", "");
            TestCode testCode = new TestCode();
            testCode.setTid(tid);
            testCode.setCode(code);
            testCodeDao.save(testCode);
        }

        int couseId = testDao.getCouseId(Integer.parseInt(tid));
        String name = courseDao.getName(couseId);
        int res = this.sendMail(email,"考试验证码",sid+"同学，您好，您参加的考试:"+name+"(id:"+tid+"),验证码为："+code);

        return res;
    }

    @Override
    public int verifyCode(String tid, String code) {
        String number = testCodeDao.findCodeByTid(tid);
        if(code.equals(number)){
            return 1;
        }else{
            return 0;
        }

    }

    @Override
    public int sendScore(String sid,String tid,String score){
        String email = studentMailDao.getEmailBySid(sid);

        int couseId = testDao.getCouseId(Integer.parseInt(tid));

        String name = courseDao.getName(couseId);

        int res = this.sendMail(email,"考试成绩",sid+"同学，您好,您参加的考试:"+name+"(考试号为:"+tid+")最后成绩为:"+score);
        return res;
    }
}
