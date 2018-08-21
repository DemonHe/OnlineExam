package mail.service;


public interface MailManagerService {
    public int sendMail(String target, String titile,String content);

    public int sendCode(String sid,String tid);

    public int sendScore(String sid,String tid,String score);

    public int verifyCode(String tid,String code);
}