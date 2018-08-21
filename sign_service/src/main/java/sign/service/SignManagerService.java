package sign.service;

import sign.model.Admin;
import sign.model.Student;

import java.util.Map;

/**
 * Created by sam.
 */
public interface SignManagerService {

    public boolean IfExist(String account,String type);

    public boolean CheckPassword(String account,String password,String type);

    public String getSid(String account);

    public String getName(String account);

    public Student GetUser(String account);

    public Admin GetAdmin(String account);

    //注册
    public int StudentReg(String sid, String password, String email, String name, String grade);

    //public boolean AdminReg(String password,String email,String name);

    public Boolean Active(String code);
}
