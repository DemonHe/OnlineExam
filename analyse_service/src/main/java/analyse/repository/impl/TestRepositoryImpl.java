package analyse.repository.impl;

import analyse.domain.entity.Test;
import analyse.repository.TestDao;
import analyse.repository.TestRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Kimone.
 */
@Service
public class TestRepositoryImpl implements TestDao {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public List<Test> getTestList(Integer courseID, String startTime, String endTime, int start, int length) {
        StringBuffer buffer = new StringBuffer("select * from Test where 1=1");
        System.out.println("getTestListParam: courseID="+courseID+",startTime="+startTime+",endTime="+endTime+",start="+start+",lenth="+length);
        if (courseID!=null) {
            buffer.append(" and courseID=" + courseID);
        }
        if(!startTime.equals("")) {
            buffer.append(" and start>='"+startTime+"'");
        }
        if(!endTime.equals("")) {
            buffer.append(" and end<='"+endTime+"'");
        }
        buffer.append(" limit "+start+","+length);
        String sql = buffer.toString();
        System.out.println("sql:"+sql);

        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery(sql, Test.class);
        List<Test> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public int getTestListCount(Integer courseID, String startTime, String endTime) {
        StringBuffer buffer = new StringBuffer("select count(*) from Test where 1=1");
        if (courseID!=null) {
            buffer.append(" and courseID=" + courseID);
        }
        if(!startTime.equals("")) {
            buffer.append(" and start>='"+startTime+"'");
        }
        if(!endTime.equals("")) {
            buffer.append(" and end<='"+endTime+"'");
        }
        String sql = buffer.toString();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery(sql);
        BigInteger num = (BigInteger) query.getResultList().get(0);
        return num.intValue();
    }
}
