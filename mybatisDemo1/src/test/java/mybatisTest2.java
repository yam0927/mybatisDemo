import com.sample.dao.IAccountDao;
import com.sample.dao.IUserDao;
import com.sample.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatisTest2 {
    private InputStream in ;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象SqlSessionFactory
        factory = builder.build(in);
        //4.使用SqlSessionFactory生产SqlSession对象
        session = factory.openSession();//参数为空则默认禁用session提交
        //将参数设置为true后，调用saveUser(user)则会立刻提交session
        //默认不提交是因为频繁的连接打开和关闭会影响性能，将多个操作放在一个事务中
        //5.使用SqlSession创建dao接口的代理对象
        accountDao = session.getMapper(IAccountDao.class);
    }
    @Test
    public void testFindAll() {
        List<AccountUser> results = accountDao.findAll();
        for(AccountUser au : results){
            System.out.println(au);
        }
        Assert.assertEquals(3, results.size());

    }
    @After
    public void tearDown() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
