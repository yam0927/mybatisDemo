import com.sample.dao.IUserDao;
import com.sample.domain.QueryVo;
import com.sample.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

public class mybatisTest {
    private InputStream in ;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void setUp() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象SqlSessionFactory
        factory = builder.build(in);
        //4.使用SqlSessionFactory生产SqlSession对象
        session = factory.openSession(true);//参数为空则默认禁用session提交
        //将参数设置为true后，调用saveUser(user)则会立刻提交session
        //默认不提交是因为频繁的连接打开和关闭会影响性能，将多个操作放在一个事务中
        //5.使用SqlSession创建dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void testFindOne() {
// 6.执行操作
        User user = userDao.findById(41);
        System.out.println(user);
        assert user.getUsername().equals("张三");
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("华泰boy");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());

        int id = userDao.saveUser(user);

        User saveUser = userDao.findById(user.getId());
        Assert.assertEquals("华泰boy",saveUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        int id = 46;
//1.根据id查询
        User user = userDao.findById(id);
//2.更新操作
        user.setAddress("北京市顺义区");
        int res = userDao.updateUser(user);
// 3. 验证保存结果
        User savedUser = userDao.findById(id);
        assert user.getAddress().equals("北京市顺义区");
    }

    @Test
    public void testCount(){
        int res = userDao.count();
        assert res == 11;
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");//%匹配0或者多个字符
        for(User user : users){
            System.out.println(user);
        }
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void testFindByName2() {
        List<User> users = userDao.findByName2("王");
        for(User user : users){
            System.out.println(user);
        }
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        vo.setName("%王%");
        vo.setAddress("%南京%");
        List<User> users = userDao.findByVo(vo);
        assert users.size() == 1;
    }

    @Test
    public void testFindByVo2() {
        QueryVo vo = new QueryVo();
        vo.setName("%王%");
        vo.setAddress(null);
        List<User> users = userDao.findByVo2(vo);
        assert users.size() == 2;
    }

    @After
    public void tearDown() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
