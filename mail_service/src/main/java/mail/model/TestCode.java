package mail.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sam.
 */
@Entity
public class TestCode {

    @Id
    private String tid;

    private String code;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
