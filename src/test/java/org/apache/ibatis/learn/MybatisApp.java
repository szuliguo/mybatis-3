package org.apache.ibatis.learn;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.learn.person.Person;
import org.apache.ibatis.learn.person.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * src/main/resources/mybatis/mybatis-config.xml
 */
public class MybatisApp {
    public static void main(String[] args) throws IOException {

       // 读取配置文件
        String resource = "org/apache/ibatis/learn/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
      // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      // 得到SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        try {
          // 得到Mapper
            PersonMapper mapper = session.getMapper(PersonMapper.class);
            Person person = mapper.selectOne(1);
            System.out.println(person.getName());
        } finally {
            session.close();
        }
    }
}
