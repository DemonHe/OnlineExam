package sign.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sign.model.Admin;

/**
 * Created by sam.
 */
@Repository
public interface AdminDao extends JpaRepository<Admin,String> {

//    //注册
//    public Admin save(Admin admin);
//
//    @Query("select a.email from Admin a where a.code = ?1")
//    public String findbyCode(String code);
//
//    @Transactional
//    @Modifying
//    @Query("update Admin a set a.state = ?2 where a.email = ?1")
//    public void updateState(String email,String state);

    //登录
    @Query("select a.password from Admin a where a.email = ?1")
    public String findbyEmail(String email);

    @Query("select a.state from Admin a where a.email = ?1")
    public String checkState(String email);

    public Admin getOne(String id);
}
