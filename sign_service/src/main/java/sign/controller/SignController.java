package sign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sign.service.SignManagerService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/account")
@RestController
public class SignController  {

    @Autowired
    private SignManagerService signManagerService;

    @RequestMapping(value = "/sign")
    public Map<String,String> sign(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("type") String type){
//        System.out.println("username:"+username);
        boolean result = signManagerService.CheckPassword(username,password,type);
//        System.out.println("The Result is :"+result+"!@!@");
        String sid = "";
        Map<String,String> map = new HashMap<String,String>();
        if(result){
            map.put("result","pass");
            if(type.equals("Student")){
                sid = signManagerService.getSid(username);
                map.put("account",sid);
                String name = signManagerService.getName(username);
                map.put("name",name);
            }else{
                map.put("account",username);
            }

            return map;
        }else{
            map.put("result","fail");
            map.put("account",username);
            return map;
        }

    }

    @RequestMapping(value = "/exist")
    public boolean exist(@RequestParam("username") String username,@RequestParam("type") String type){
        return signManagerService.IfExist(username,type);
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public int register(@RequestBody Map<String,Object> requestInfo){
        String email = (String) requestInfo.get("email");
        String password = (String)requestInfo.get("password");
        String type = (String)requestInfo.get("type");
        System.out.println("the email is:" + email);
        String sid = (String)requestInfo.get("sid");
        String name = (String)requestInfo.get("name");
        String grade = (String)requestInfo.get("grade");
        int res = signManagerService.StudentReg(sid,password,email,name,grade);

        return res;
    }

    @RequestMapping(value = "/activeService")
    public boolean activeAccount(@RequestParam("code") String code){
        return signManagerService.Active(code);
    }
}
