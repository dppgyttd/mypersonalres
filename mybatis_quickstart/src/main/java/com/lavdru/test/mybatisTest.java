package com.lavdru.test;

import com.lavdru.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;



public class mybatisTest {
    /**
     *快速入门测试方法
     * */
    @Test
    public void mybatisQuickTest() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlsession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql  参数：statementid :namespace.id,就是我们在usermapper.xml中定义的namespace和id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        //5.遍历打印结果
        for(User user : users){
            System.out.println(user);
        }

        //6.关闭资源
        sqlSession.close();

    }
    //新增用户
    @Test
    public void testSave() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlsession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        User user = new User();
        user.setUsername("自动提交事务1");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");


        sqlSession.insert("userMapper.saveUser",user);
        //DML 语句 要手动提交事务
        //sqlSession.commit();
        sqlSession.close();


    }

    //测试更新用户
    @Test
    public void testUpdate() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlsession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);
        user.setUsername("太极lucy1");
        user.setSex("女");


        sqlSession.update("userMapper.updateUser",user);
        //DML 语句 要手动提交事务
        sqlSession.commit();
        sqlSession.close();


    }

    //测试删除用户
    @Test
    public void testDelete() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlsession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();




        sqlSession.delete("userMapper.deleteUser",3);
        //DML 语句 要手动提交事务
        sqlSession.commit();
        sqlSession.close();


    }
}

